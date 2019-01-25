/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package aenu.eide;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;

import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.os.ParcelFileDescriptor;
import android.util.Log;

import jackpal.androidterm.emulatorview.ColorScheme;
import jackpal.androidterm.emulatorview.TermSession;
import jackpal.androidterm.emulatorview.UpdateCallback;
import jackpal.androidterm.TermExec;

//import jackpal.androidterm.compat.FileCompat;
//import jackpal.androidterm.util.TermSettings;

/**
 * A terminal session, consisting of a TerminalEmulator, a TranscriptScreen,
 * and the I/O streams used to talk to the process.
 */
class E_TermSession extends TermSession {
    //** Set to true to force into 80 x 24 for testing with vttest. */
    private static final boolean VTTEST_MODE = false;

    //private static Field descriptorField;

    private final long createdAt;

    // A cookie which uniquely identifies this session.
    private String mHandle;

    final ParcelFileDescriptor mTermFd;

    //TermSettings mSettings;

    public static final int PROCESS_EXIT_FINISHES_SESSION = 0;
    public static final int PROCESS_EXIT_DISPLAYS_MESSAGE = 1;

    private String mProcessExitMessage;

    //private Process mProc;
    private int mProcId;
    
    private static final int PROCESS_EXITED = 1;
    
    private Handler mMsgHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (!isRunning()) {
                return;
            }
            if (msg.what == PROCESS_EXITED) {
                onProcessExit();
            }
        }
    };
    
    private UpdateCallback mUTF8ModeNotify = new UpdateCallback() {
        public void onUpdate() {
            setPtyUTF8Mode(getUTF8Mode());
        }
    };

    private Thread mWatcherThread;
    
    private String mInitialCommand;

    public E_TermSession(String initCommand,boolean exitOnEOF) throws IOException {
        super(exitOnEOF);
        
        this.mInitialCommand=initCommand;

        this.mTermFd = ParcelFileDescriptor.open(new File("/dev/ptmx"), ParcelFileDescriptor.MODE_READ_WRITE);

        this.createdAt = System.currentTimeMillis();

        //updatePrefs(settings);
        setDefaultUTF8Mode(true);
        
        String argc="/system/bin/sh";
        String args[]={"-"};
        String env[]={"TERM=term"};
        
        mProcId=TermExec.createSubprocess(mTermFd,argc,args,env);
        
        setTermOut(new ParcelFileDescriptor.AutoCloseOutputStream(mTermFd));
        setTermIn(new ParcelFileDescriptor.AutoCloseInputStream(mTermFd));
        
        mWatcherThread = new Thread() {
            @Override
            public void run() {
                //Log.i(TermDebug.LOG_TAG, "waiting for: " + mProcId);
                //try{
                //    int result = mProc.waitFor();               
                //    mMsgHandler.sendMessage(mMsgHandler.obtainMessage(PROCESS_EXITED, result));            
                //}catch (InterruptedException e) {    
                //    //mWatcherThread.start();//FIXME
                //}
                int result = TermExec.waitFor(mProcId);
                //Log.i(TermDebug.LOG_TAG, "Subprocess exited: " + result);
                mMsgHandler.sendMessage(mMsgHandler.obtainMessage(PROCESS_EXITED, result));
                
                //Log.i(TermDebug.LOG_TAG, "Subprocess exited: " + result);
            }
        };
        mWatcherThread.setName("Process watcher");
        
    }
    
    /*
    public void updatePrefs(TermSettings settings) {
        mSettings = settings;
        setColorScheme(new ColorScheme(settings.getColorScheme()));
        setDefaultUTF8Mode(settings.defaultToUTF8Mode());
    }*/

    @Override
    public void initializeEmulator(int columns, int rows) {
        if (VTTEST_MODE) {
            columns = 80;
            rows = 24;
        }
        super.initializeEmulator(columns, rows);

        setPtyUTF8Mode(getUTF8Mode());
        setUTF8ModeUpdateCallback(mUTF8ModeNotify);
        mWatcherThread.start();
        
        if(mInitialCommand!=null&&!mInitialCommand.isEmpty())
            write(mInitialCommand+'\n');
    }

    @Override
    public void updateSize(int columns, int rows) {
        if (VTTEST_MODE) {
            columns = 80;
            rows = 24;
        }
        // Inform the attached pty of our new size:
        setPtyWindowSize(rows, columns, 0, 0);
        super.updateSize(columns, rows);
    }

    /* XXX We should really get this ourselves from the resource bundle, but
     we cannot hold a context */
    public void setProcessExitMessage(String message) {
        mProcessExitMessage = message;
    }

    @Override
    protected void onProcessExit() {
        if (true/*mSettings.closeWindowOnProcessExit()*/) {
            finish();
        } else if (mProcessExitMessage != null) {
            try {
                byte[] msg = ("\r\n[" + mProcessExitMessage + "]").getBytes("UTF-8");
                appendToEmulator(msg, 0, msg.length);
                notifyUpdate();
            } catch (UnsupportedEncodingException e) {
                // Never happens
            }
        }
    }

    @Override
    public void finish() {
        
        //mProc.destroy();
        TermExec.sendSignal(-mProcId, 1);
        try {
            mTermFd.close();
        } catch (IOException e) {
            // ok
        }

        super.finish();
    }

    /**
     * Gets the terminal session's title.  Unlike the superclass's getTitle(),
     * if the title is null or an empty string, the provided default title will
     * be returned instead.
     *
     * @param defaultTitle The default title to use if this session's title is
     *     unset or an empty string.
     */
    public String getTitle(String defaultTitle) {
        String title = getTitle();
        if (title != null && title.length() > 0) {
            return title;
        } else {
            return defaultTitle;
        }
    }

    public void setHandle(String handle) {
        if (mHandle != null) {
            throw new IllegalStateException("Cannot change handle once set");
        }
        mHandle = handle;
    }

    public String getHandle() {
        return mHandle;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + '(' + createdAt + ',' + mHandle + ')';
    }

    /**
     * Set the widow size for a given pty. Allows programs
     * connected to the pty learn how large their screen is.
     */
    void setPtyWindowSize(int row, int col, int xpixel, int ypixel) {
        // If the tty goes away too quickly, this may get called after it's descriptor is closed
        if (!mTermFd.getFileDescriptor().valid())
            return;

        try {
            //Exec.setPtyWindowSizeInternal(getIntFd(mTermFd), row, col, xpixel, ypixel);
            setPtyWindowSizeInternal(mTermFd.getFd(), row, col, xpixel, ypixel);
        } catch (IOException e) {
            Log.e("exec", "Failed to set window size: " + e.getMessage());

            if (isFailFast())
                throw new IllegalStateException(e);
        }
    }

    /**
     * Set or clear UTF-8 mode for a given pty.  Used by the terminal driver
     * to implement correct erase behavior in cooked mode (Linux >= 2.6.4).
     */
    void setPtyUTF8Mode(boolean utf8Mode) {
        // If the tty goes away too quickly, this may get called after it's descriptor is closed
        if (!mTermFd.getFileDescriptor().valid())
            return;

        try {
            //Exec.setPtyUTF8ModeInternal(getIntFd(mTermFd), utf8Mode);
            setPtyUTF8ModeInternal(mTermFd.getFd(), utf8Mode);        
        } catch (IOException e) {
            Log.e("exec", "Failed to set UTF mode: " + e.getMessage());

            if (isFailFast())
                throw new IllegalStateException(e);
        }
    }

    /**
     * @return true, if failing to operate on file descriptor deserves an exception (never the case for ATE own shell)
     */
    boolean isFailFast() {
        return false;
    }
    
    static {
        System.loadLibrary("jackpal-androidterm5");
    }

    static native void setPtyWindowSizeInternal(int fd, int row, int col, int xpixel, int ypixel) throws IOException;

    static native void setPtyUTF8ModeInternal(int fd, boolean utf8Mode) throws IOException;
    
    /*
    private static void cacheDescField() throws NoSuchFieldException {
        if (descriptorField != null)
            return;

        descriptorField = FileDescriptor.class.getDeclaredField("descriptor");
        descriptorField.setAccessible(true);
    }

    private static int getIntFd(ParcelFileDescriptor parcelFd) throws IOException {
        if (Build.VERSION.SDK_INT >= 12)
            return FdHelperHoneycomb.getFd(parcelFd);
        else {
            try {
                cacheDescField();

                return descriptorField.getInt(parcelFd.getFileDescriptor());
            } catch (Exception e) {
                throw new IOException("Unable to obtain file descriptor on this OS version: " + e.getMessage());
            }
        }
    }*/
}
