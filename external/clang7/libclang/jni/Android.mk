
LOCAL_PATH       := $(call my-dir)

LLVM_ROOT_PATH   :=../../../llvm-7.0.1.src
CLANG_ROOT_PATH  :=../../../cfe-7.0.1.src

include $(LOCAL_PATH)/0.mk

clang_lib_toolinginclusions_DIR=$(CLANG_ROOT_PATH)/lib/Tooling/Inclusions

clang_lib_toolinginclusions_SRC_FILES:= \
  $(clang_lib_toolinginclusions_DIR)/HeaderIncludes.cpp \
  $(clang_lib_toolinginclusions_DIR)/IncludeStyle.cpp

include $(CLEAR_VARS)
LOCAL_MODULE := LLVM-7
LOCAL_SRC_FILES := libLLVM-7.so
include $(PREBUILT_SHARED_LIBRARY)

include $(CLEAR_VARS)

LOCAL_SRC_FILES  := \
$(clang_lib_tooling_SRC_FILES) \
$(clang_lib_toolingcore_SRC_FILES) \
$(clang_lib_parse_SRC_FILES) \
$(clang_lib_astmatchers_SRC_FILES) \
$(clang_lib_sema_SRC_FILES) \
$(clang_lib_ast_SRC_FILES) \
$(clang_lib_frontend_SRC_FILES) \
$(clang_lib_serialization_SRC_FILES) \
$(clang_lib_lex_SRC_FILES) \
$(clang_lib_driver_SRC_FILES) \
$(clang_lib_basic_SRC_FILES) \
$(clang_lib_edit_SRC_FILES) \
$(clang_lib_rewrite_SRC_FILES) \
$(clang_lib_format_SRC_FILES) \
$(clang_lib_index_SRC_FILES) \
$(clang_lib_analysis_SRC_FILES) \
$(clang_lib_toolinginclusions_SRC_FILES) \
  $(CLANG_ROOT_PATH)/tools/libclang/CIndexCXX.cpp \
  $(CLANG_ROOT_PATH)/tools/libclang/CIndexer.cpp \
  $(CLANG_ROOT_PATH)/tools/libclang/CIndexInclusionStack.cpp \
  $(CLANG_ROOT_PATH)/tools/libclang/CIndexUSRs.cpp \
  $(CLANG_ROOT_PATH)/tools/libclang/CXSourceLocation.cpp \
  $(CLANG_ROOT_PATH)/tools/libclang/ARCMigrate.cpp \
  $(CLANG_ROOT_PATH)/tools/libclang/CIndex.cpp \
  $(CLANG_ROOT_PATH)/tools/libclang/CIndexDiagnostic.cpp \
  $(CLANG_ROOT_PATH)/tools/libclang/CXIndexDataConsumer.cpp \
  $(CLANG_ROOT_PATH)/tools/libclang/CIndexCodeCompletion.cpp \
  $(CLANG_ROOT_PATH)/tools/libclang/CXCompilationDatabase.cpp \
  $(CLANG_ROOT_PATH)/tools/libclang/BuildSystem.cpp \
  $(CLANG_ROOT_PATH)/tools/libclang/CXString.cpp \
  $(CLANG_ROOT_PATH)/tools/libclang/CXComment.cpp \
  $(CLANG_ROOT_PATH)/tools/libclang/Indexing.cpp \
  $(CLANG_ROOT_PATH)/tools/libclang/CXCursor.cpp \
  $(CLANG_ROOT_PATH)/tools/libclang/CIndexHigh.cpp \
  $(CLANG_ROOT_PATH)/tools/libclang/CXType.cpp \
  $(CLANG_ROOT_PATH)/tools/libclang/CXLoadedDiagnostic.cpp \
  $(CLANG_ROOT_PATH)/tools/libclang/CXStoredDiagnostic.cpp 
  
LOCAL_MODULE:= clang-7

LOCAL_SHARED_LIBRARIES := libLLVM-7

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
  -fPIC \
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

LOCAL_LDLIBS +=-lz

LOCAL_CPPFLAGS +=-frtti -fexceptions -std=c++11 -fPIC
include $(BUILD_SHARED_LIBRARY)

# \
include $(BUILD_EXECUTABLE)

