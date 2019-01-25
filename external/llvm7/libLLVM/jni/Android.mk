LOCAL_PATH       := $(call my-dir)

LLVM_ROOT_PATH   :=../../../llvm-7.0.1.src
CLANG_ROOT_PATH  :=../../../cfe-7.0.1.src

include $(LOCAL_PATH)/abcd.mk

llvm_lib_aggressiveinstcombine_DIR=$(LLVM_ROOT_PATH)/lib/Transforms/AggressiveInstCombine

llvm_lib_aggressiveinstcombine_SRC_FILES:= \
  $(llvm_lib_aggressiveinstcombine_DIR)/AggressiveInstCombine.cpp \
  $(llvm_lib_aggressiveinstcombine_DIR)/TruncInstCombine.cpp

include $(CLEAR_VARS)

X_LOCAL_SRC_FILES := \
$(llvm_lib_armasmparser_SRC_FILES) \
$(llvm_lib_armcodegen_SRC_FILES) \
$(llvm_lib_armasmprinter_SRC_FILES) \
$(llvm_lib_armdisassembler_SRC_FILES) \
$(llvm_lib_armdesc_SRC_FILES) \
$(llvm_lib_arminfo_SRC_FILES) \
$(llvm_lib_armutils_SRC_FILES) \
$(llvm_lib_irreader_SRC_FILES) \
$(llvm_lib_asmparser_SRC_FILES) \
$(llvm_lib_asmprinter_SRC_FILES) \
$(llvm_lib_bitreader_SRC_FILES) \
$(llvm_lib_bitwriter_SRC_FILES) \
$(llvm_lib_selectiondag_SRC_FILES) \
$(llvm_lib_ipo_SRC_FILES) \
$(llvm_lib_instcombine_SRC_FILES) \
$(llvm_lib_instrumentation_SRC_FILES) \
$(llvm_lib_codegen_SRC_FILES) \
$(llvm_lib_object_SRC_FILES) \
$(llvm_lib_linker_SRC_FILES) \
$(llvm_lib_mc_SRC_FILES) \
$(llvm_lib_mcparser_SRC_FILES) \
$(llvm_lib_scalaropts_SRC_FILES) \
$(llvm_lib_transformutils_SRC_FILES) \
$(llvm_lib_vectorize_SRC_FILES) \
$(llvm_lib_analysis_SRC_FILES) \
$(llvm_lib_core_SRC_FILES) \
$(llvm_lib_option_SRC_FILES) \
$(llvm_lib_support_SRC_FILES) \
$(llvm_lib_target_SRC_FILES) \
$(llvm_lib_debuginfocodeview_SRC_FILES) \
$(llvm_lib_debuginfodwarf_SRC_FILES) \
$(llvm_lib_profiledata_SRC_FILES) \
$(llvm_lib_binaryformat_SRC_FILES) \
$(llvm_lib_globalisel_SRC_FILES) \
$(llvm_lib_mcdisassembler_SRC_FILES) \
$(llvm_lib_passes_SRC_FILES) \
$(llvm_lib_coverage_SRC_FILES) \
$(llvm_lib_objcarcopts_SRC_FILES) \
$(llvm_lib_lto_SRC_FILES) \
$(llvm_lib_coroutines_SRC_FILES) \
$(llvm_lib_aarch64asmparser_SRC_FILES) \
$(llvm_lib_aarch64disassembler_SRC_FILES) \
$(llvm_lib_aarch64asmprinter_SRC_FILES) \
$(llvm_lib_aarch64desc_SRC_FILES) \
$(llvm_lib_aarch64info_SRC_FILES) \
$(llvm_lib_aarch64utils_SRC_FILES) \
$(llvm_lib_aarch64codegen_SRC_FILES) \
$(llvm_lib_x86asmparser_SRC_FILES) \
$(llvm_lib_x86disassembler_SRC_FILES) \
$(llvm_lib_x86asmprinter_SRC_FILES) \
$(llvm_lib_x86desc_SRC_FILES) \
$(llvm_lib_x86info_SRC_FILES) \
$(llvm_lib_x86utils_SRC_FILES) \
$(llvm_lib_x86codegen_SRC_FILES) \
$(llvm_lib_webassemblyasmparser_SRC_FILES) \
$(llvm_lib_webassemblydisassembler_SRC_FILES) \
$(llvm_lib_webassemblyasmprinter_SRC_FILES) \
$(llvm_lib_webassemblydesc_SRC_FILES) \
$(llvm_lib_webassemblyinfo_SRC_FILES) \
$(llvm_lib_webassemblycodegen_SRC_FILES) \
$(llvm_lib_dlltooldriver_SRC_FILES) \
$(llvm_lib_libdriver_SRC_FILES) \
$(llvm_lib_debuginfomsf_SRC_FILES) \
$(llvm_lib_debuginfopdb_SRC_FILES) \
$(llvm_lib_windowsmanifest_SRC_FILES) \
$(llvm_lib_demangle_SRC_FILES) \
$(llvm_lib_aggressiveinstcombine_SRC_FILES) \
to_string.cpp

LOCAL_SRC_FILES := \
$(llvm_lib_armasmparser_SRC_FILES) \
$(llvm_lib_armcodegen_SRC_FILES) \
$(llvm_lib_armasmprinter_SRC_FILES) \
$(llvm_lib_armdisassembler_SRC_FILES) \
$(llvm_lib_armdesc_SRC_FILES) \
$(llvm_lib_arminfo_SRC_FILES) \
$(llvm_lib_armutils_SRC_FILES) \
$(llvm_lib_irreader_SRC_FILES) \
$(llvm_lib_asmparser_SRC_FILES) \
$(llvm_lib_asmprinter_SRC_FILES) \
$(llvm_lib_bitreader_SRC_FILES) \
$(llvm_lib_bitwriter_SRC_FILES) \
$(llvm_lib_selectiondag_SRC_FILES) \
$(llvm_lib_ipo_SRC_FILES) \
$(llvm_lib_instcombine_SRC_FILES) \
$(llvm_lib_instrumentation_SRC_FILES) \
$(llvm_lib_codegen_SRC_FILES) \
$(llvm_lib_object_SRC_FILES) \
$(llvm_lib_linker_SRC_FILES) \
$(llvm_lib_mc_SRC_FILES) \
$(llvm_lib_mcparser_SRC_FILES) \
$(llvm_lib_scalaropts_SRC_FILES) \
$(llvm_lib_transformutils_SRC_FILES) \
$(llvm_lib_vectorize_SRC_FILES) \
$(llvm_lib_analysis_SRC_FILES) \
$(llvm_lib_core_SRC_FILES) \
$(llvm_lib_option_SRC_FILES) \
$(llvm_lib_support_SRC_FILES) \
$(llvm_lib_target_SRC_FILES) \
$(llvm_lib_debuginfocodeview_SRC_FILES) \
$(llvm_lib_debuginfodwarf_SRC_FILES) \
$(llvm_lib_profiledata_SRC_FILES) \
$(llvm_lib_binaryformat_SRC_FILES) \
$(llvm_lib_globalisel_SRC_FILES) \
$(llvm_lib_mcdisassembler_SRC_FILES) \
$(llvm_lib_passes_SRC_FILES) \
$(llvm_lib_coverage_SRC_FILES) \
$(llvm_lib_objcarcopts_SRC_FILES) \
$(llvm_lib_lto_SRC_FILES) \
$(llvm_lib_coroutines_SRC_FILES) \
$(llvm_lib_aarch64asmparser_SRC_FILES) \
$(llvm_lib_aarch64disassembler_SRC_FILES) \
$(llvm_lib_aarch64asmprinter_SRC_FILES) \
$(llvm_lib_aarch64desc_SRC_FILES) \
$(llvm_lib_aarch64info_SRC_FILES) \
$(llvm_lib_aarch64utils_SRC_FILES) \
$(llvm_lib_aarch64codegen_SRC_FILES) \
to_string.cpp

LOCAL_MODULE:= LLVM-7

LOCAL_C_INCLUDES  := \
  $(LOCAL_PATH)/$(LLVM_ROOT_PATH)/include   \
  $(LOCAL_PATH)/../../common-llvm/include \
  $(LOCAL_C_INCLUDES) 

LOCAL_C_INCLUDES += $(LOCAL_PATH)/../../common-llvm/include/llvm/Target/AArch64
LOCAL_C_INCLUDES += $(LOCAL_PATH)/$(LLVM_ROOT_PATH)/lib/Target/AArch64
LOCAL_C_INCLUDES += $(LOCAL_PATH)/../../common-llvm/include/llvm/Target/ARM
LOCAL_C_INCLUDES += $(LOCAL_PATH)/$(LLVM_ROOT_PATH)/lib/Target/ARM
LOCAL_C_INCLUDES += $(LOCAL_PATH)/../../common-llvm/include/llvm/Target/WebAssembly
LOCAL_C_INCLUDES += $(LOCAL_PATH)/$(LLVM_ROOT_PATH)/lib/Target/WebAssembly

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

LOCAL_CPPFLAGS +=-frtti -fexceptions -std=c++11

LOCAL_LDLIBS +=-lz

include $(BUILD_STATIC_LIBRARY)
#TMD.. \
include $(BUILD_EXECUTABLE)
