
//license wtfpl 2.0

//by aenu 2018/10/22
//   email:202983447@qq.com

package aenu.eide.util;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.zip.ZipOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.Enumeration;
import java.util.Arrays;

public final class IOUtils
{
    public final static void file_copy(File out_file,File in_file) throws IOException{
        FileInputStream in=new FileInputStream(in_file);
        FileOutputStream out=new FileOutputStream(out_file);
        byte[] buf=new byte[4096];
        int n;
        while((n=in.read(buf))!=-1)
            out.write(buf,0,n);
        in.close();
        out.close();
    }
    
    public final static byte[] file_read(String path) throws IOException{
        FileInputStream in=new FileInputStream(path);
        ByteArrayOutputStream out=new ByteArrayOutputStream();
        byte[] buf=new byte[4096];
        int n;
        while((n=in.read(buf))!=-1)
            out.write(buf,0,n);
        in.close();
        return out.toByteArray();
    }
    
    public final static String file_read2(File path) throws IOException{
        FileInputStream in=new FileInputStream(path);
        ByteArrayOutputStream out=new ByteArrayOutputStream();
        byte[] buf=new byte[4096];
        int n;
        while((n=in.read(buf))!=-1)
            out.write(buf,0,n);
        in.close();
        return out.toString();
    }
    
    public final static void file_write(String path,byte[] data) throws IOException{
        FileOutputStream out=new FileOutputStream(path);
        out.write(data,0,data.length);
        out.close();
    }
    
    public final static void file_write2(String path,InputStream strm) throws IOException{
        FileOutputStream out=new FileOutputStream(path);
        final byte[] buf=new byte[4096];
        int n;
        while((n=strm.read(buf))!=-1)
            out.write(buf,0,n);
        out.close();
    }
    
    public static String stream_read(InputStream strm) throws IOException{
        ByteArrayOutputStream out=new ByteArrayOutputStream();
        final byte[] buf=new byte[4096];
        int n;
        while((n=strm.read(buf))!=-1)
            out.write(buf,0,n);
        return out.toString();
    }
    
    public static void zip_compressF(File srcF,ZipOutputStream zip,String entry) throws IOException{
        
        final byte[] zip_compressBuf=new byte[4096*2];
        
        ZipEntry zipE=new ZipEntry(entry);
        zip.putNextEntry(zipE);

        int n;

        FileInputStream in=new FileInputStream(srcF);
        while((n=in.read(zip_compressBuf))!=-1)
            zip.write(zip_compressBuf,0,n);
        in.close();
    }

    public static void zip_compressD(File dir,ZipOutputStream zip,String prefix) throws IOException{
        final File[] list=dir.listFiles();

        for(File f:list){       
            String new_entry=prefix==null?f.getName():prefix+"/"+f.getName();
            if(f.isDirectory())
                zip_compressD(f,zip,new_entry);
            else
                zip_compressF(f,zip,new_entry);         
        }
    }

    public static void zip_compressZ2(ZipFile zF,ZipOutputStream zip) throws IOException{

        final byte[] zip_compressBuf=new byte[4096*2];
        
        FileInputStream fin=new FileInputStream(zF.getName());
        ZipInputStream zin=new ZipInputStream(fin);
        ZipEntry entry;
        int n;

        while((entry=zin.getNextEntry())!=null){
            zip.putNextEntry(entry);
            InputStream in=zF.getInputStream(entry);
            while((n=in.read(zip_compressBuf))!=-1)
                zip.write(zip_compressBuf,0,n);
            in.close();
        }

        zin.close();
        fin.close();
    }

    public static void zip_compressZ(ZipFile zF,ZipOutputStream zip) throws IOException{
        Enumeration<? extends java.util.zip.ZipEntry> entries=zF.entries();//FIXME bug....
        final byte[] zip_compressBuf=new byte[4096*2];
        
        int n;

        for(ZipEntry e=entries.nextElement();entries.hasMoreElements();e=entries.nextElement()){

            //if(e.getName().endsWith("/")) continue;

            zip.putNextEntry(e);
            InputStream is=zF.getInputStream(e);

            while((n=is.read(zip_compressBuf))!=-1)
                zip.write(zip_compressBuf,0,n);

            is.close();
        }
    }
    
    public static boolean stream_compare(InputStream in1,InputStream in2) throws IOException{
        byte[] buf1=new byte[4096];
        byte[] buf2=new byte[4096];

        final byte zero=0;

        Arrays.fill(buf1,zero);
        Arrays.fill(buf2,zero);

        if(in1.read(buf1)!=in2.read(buf2))
            return false;

        return Arrays.equals(buf1,buf2);
    }
}
