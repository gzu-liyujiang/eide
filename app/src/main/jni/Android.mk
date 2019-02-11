
LOCAL_PATH := $(call my-dir)

include $(LOCAL_PATH)/build-libos-utils.mk
include $(LOCAL_PATH)/build-libjackpal-androidterm5.mk

LLVM_ROOT_PATH   :=../../../../external/llvm-7.0.1.src
CLANG_ROOT_PATH  :=../../../../external/cfe-7.0.1.src

include $(CLEAR_VARS)
LOCAL_MODULE     := libLLVM-7
LOCAL_SRC_FILES  := prebuilt/libLLVM-7.so
include $(PREBUILT_SHARED_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE     := libclang-7
LOCAL_SHARED_LIBRARIES := libLLVM-7
LOCAL_SRC_FILES  := prebuilt/libclang-7.so
include $(PREBUILT_SHARED_LIBRARY)

include $(LOCAL_PATH)/build-libcxxcomplete.mk
include $(LOCAL_PATH)/build-libcxxlexer.mk
include $(LOCAL_PATH)/build-libcx-diag.mk
