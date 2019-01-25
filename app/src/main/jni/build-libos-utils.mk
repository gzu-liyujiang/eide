LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := libos-utils
LOCAL_SRC_FILES := os_utils.c

LOCAL_ARM_MODE :=arm

include $(BUILD_SHARED_LIBRARY)
