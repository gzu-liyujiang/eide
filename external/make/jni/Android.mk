LOCAL_PATH:= $(call my-dir)

include $(CLEAR_VARS)

make_DIR  := ../../make-4.2.1

make_SRC_FILES := \
  $(make_DIR)/glob/fnmatch.c \
  $(make_DIR)/glob/glob.c \
  $(make_DIR)/ar.c \
  $(make_DIR)/arscan.c \
  $(make_DIR)/remote-stub.c \
  $(make_DIR)/commands.c \
  $(make_DIR)/default.c \
  $(make_DIR)/dir.c \
  $(make_DIR)/expand.c \
  $(make_DIR)/file.c \
  $(make_DIR)/function.c \
  $(make_DIR)/getopt.c \
  $(make_DIR)/getopt1.c \
  $(make_DIR)/guile.c \
  $(make_DIR)/implicit.c \
  $(make_DIR)/job.c \
  $(make_DIR)/load.c \
  $(make_DIR)/loadapi.c \
  main.c \
  $(make_DIR)/misc.c \
  $(make_DIR)/posixos.c \
  $(make_DIR)/output.c \
  $(make_DIR)/read.c \
  $(make_DIR)/remake.c \
  $(make_DIR)/rule.c \
  $(make_DIR)/signame.c \
  $(make_DIR)/strcache.c \
  $(make_DIR)/variable.c \
  $(make_DIR)/version.c \
  $(make_DIR)/vpath.c \
  $(make_DIR)/hash.c \
  $(make_DIR)/getloadavg.c \
  
LOCAL_SRC_FILES = $(make_SRC_FILES)

LOCAL_MODULE := make

LOCAL_C_INCLUDES := \
    $(LOCAL_PATH) \
    $(LOCAL_PATH)/../../make-4.2.1 \
    $(LOCAL_PATH)/../../make-4.2.1/glob \
    $(LOCAL_C_INCLUDES) 
    
LOCAL_CFLAGS := \
  -std=c99 \
  -Os \
  -fomit-frame-pointer    \
  -Wall   \
  -W  \
  -Wno-unused-parameter   \
  -Wwrite-strings \
  -marm \
  -DHAVE_CONFIG_H=1 \
  -DLOCALEDIR=\"/sdcard/eide/locale\" \
  -DINCLUDEDIR=\"/sdcard/eide/include\" \
  -DLIBDIR=\"/sdcard/eide/lib\" \
  $(LOCAL_CFLAGS)

include $(BUILD_EXECUTABLE)
