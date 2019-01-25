
####
####
##by aenu
####
##license wtfpl 2.0
####
####

LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE     := cxxcomplete
LOCAL_C_INCLUDES := \
  $(LOCAL_PATH)/$(CLANG_ROOT_PATH)/include   \
  $(LOCAL_PATH)/$(LLVM_ROOT_PATH)/include   \
  $(LOCAL_PATH)/../../../../external/clang7/common-clang/include     \
  $(LOCAL_PATH)/../../../../external/clang7/common-llvm/include     \
  $(LOCAL_C_INCLUDES) 
  
LOCAL_CFLAGS     +=  -Os -marm -std=c99 -D_GNU_SOURCE -D__STDC_LIMIT_MACROS -D__STDC_CONSTANT_MACROS 
LOCAL_CPPFLAGS   +=  -Os -marm -frtti -fexceptions -std=c++11

LOCAL_LDLIBS     +=  -llog

LOCAL_SHARED_LIBRARIES := libclang

LOCAL_SRC_FILES :=cxxcomplete.c

include $(BUILD_SHARED_LIBRARY)

