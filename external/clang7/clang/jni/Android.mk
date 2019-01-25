
LOCAL_PATH       := $(call my-dir)

LLVM_ROOT_PATH   :=../../../llvm-7.0.1.src
CLANG_ROOT_PATH  :=../../../cfe-7.0.1.src

include $(CLEAR_VARS)
LOCAL_MODULE := LLVM-7
LOCAL_SRC_FILES := libLLVM-7.a
include $(PREBUILT_STATIC_LIBRARY)

include $(LOCAL_PATH)/list.mk

llvm_lib_aggressiveinstcombine_DIR=$(LLVM_ROOT_PATH)/lib/Transforms/AggressiveInstCombine

llvm_lib_aggressiveinstcombine_SRC_FILES:= \
  $(llvm_lib_aggressiveinstcombine_DIR)/AggressiveInstCombine.cpp \
  $(llvm_lib_aggressiveinstcombine_DIR)/TruncInstCombine.cpp

clang_tools_driver_DIR :=$(CLANG_ROOT_PATH)/tools/driver

include $(CLEAR_VARS)

LOCAL_SRC_FILES  := \
$(clang_lib_frontendtool_SRC_FILES) \
$(clang_lib_frontend_SRC_FILES) \
$(clang_lib_analysis_SRC_FILES) \
$(clang_lib_arcmigrate_SRC_FILES) \
$(clang_lib_driver_SRC_FILES) \
$(clang_lib_serialization_SRC_FILES) \
$(clang_lib_codegen_SRC_FILES) \
$(clang_lib_rewritefrontend_SRC_FILES) \
$(clang_lib_rewritecore_SRC_FILES) \
$(clang_lib_parse_SRC_FILES) \
$(clang_lib_sema_SRC_FILES) \
$(clang_lib_edit_SRC_FILES) \
$(clang_lib_ast_SRC_FILES) \
$(clang_lib_lex_SRC_FILES) \
$(clang_lib_basic_SRC_FILES) \
$(clang_lib_rewrite_SRC_FILES) \
$(clang_lib_astmatchers_SRC_FILES) \
$(clang_tools_driver_DIR)/driver.cpp \
$(clang_tools_driver_DIR)/cc1_main.cpp \
$(clang_tools_driver_DIR)/cc1as_main.cpp \
$(clang_tools_driver_DIR)/cc1gen_reproducer_main.cpp \
to_string.cpp \
$(llvm_lib_aggressiveinstcombine_SRC_FILES)

LOCAL_MODULE:= clang

LOCAL_C_INCLUDES := \
  $(LOCAL_PATH)/$(LLVM_ROOT_PATH)/include   \
  $(LOCAL_PATH)/$(CLANG_ROOT_PATH)/include   \
  $(LOCAL_PATH)/../../common-llvm/include \
  $(LOCAL_PATH)/../../common-clang/include \
  $(LOCAL_C_INCLUDES) 


LOCAL_C_INCLUDES += $(LOCAL_PATH)/../../common-llvm/include/llvm/Target/AArch64
LOCAL_C_INCLUDES += $(LOCAL_PATH)/$(LLVM_ROOT_PATH)/lib/Target/AArch64
LOCAL_C_INCLUDES += $(LOCAL_PATH)/../../common-llvm/include/llvm/Target/ARM
LOCAL_C_INCLUDES += $(LOCAL_PATH)/$(LLVM_ROOT_PATH)/lib/Target/ARM
LOCAL_C_INCLUDES += $(LOCAL_PATH)/../../common-llvm/include/llvm/Target/WebAssembly
LOCAL_C_INCLUDES += $(LOCAL_PATH)/$(LLVM_ROOT_PATH)/lib/Target/WebAssembly
LOCAL_C_INCLUDES += $(LOCAL_PATH)/../../common-llvm/include/llvm/Target/X86
LOCAL_C_INCLUDES += $(LOCAL_PATH)/$(LLVM_ROOT_PATH)/lib/Target/X86
LOCAL_C_INCLUDES += $(LOCAL_PATH)/$(CLANG_ROOT_PATH)/lib/Basic
LOCAL_C_INCLUDES += $(LOCAL_PATH)/$(CLANG_ROOT_PATH)/lib/Driver
       
LOCAL_CFLAGS := \
  -D_GNU_SOURCE   \
  -D__STDC_LIMIT_MACROS   \
  -D__STDC_CONSTANT_MACROS    \
  -Os \
  -marm \
  -DLLVM_BUILD_GLOBAL_ISEL \
  $(LOCAL_CFLAGS)

LOCAL_CFLAGS += \
  -DPRId8=\"d\" \
  -DPRIx8=\"x\" \
  -DPRIX8=\"X\" \
  -DPRIu8=\"u\" \
  -DPRId16=\"d\" \
  -DPRIx16=\"x\" \
  -DPRIX16=\"X\" \
  -DPRIu16=\"u\" \
  -DPRId32=\"d\" \
  -DPRIx32=\"x\" \
  -DPRIX32=\"X\" \
  -DPRIu32=\"u\" \
  -DPRId64=\"lld\" \
  -DPRIx64=\"llx\" \
  -DPRIX64=\"llX\" \
  -DPRIu64=\"llu\" 

ifeq ($(TARGET_ARCH_ABI),arm64-v8a)
  LOCAL_CFLAGS += -DPRIxPTR=\"llx\"
endif

ifeq ($(TARGET_ARCH_ABI),armeabi-v7a)
  LOCAL_CFLAGS += -DPRIxPTR=\"x\"
endif

ifeq ($(TARGET_ARCH_ABI),armeabi)
  LOCAL_CFLAGS += -DPRIxPTR=\"x\"
endif

LOCAL_STATIC_LIBRARIES := LLVM-7
LOCAL_LDLIBS +=-lz

LOCAL_CPPFLAGS +=-frtti -fexceptions -std=c++11

include $(BUILD_EXECUTABLE)

