LOCAL_PATH       := $(call my-dir)

LLVM_ROOT_PATH   :=../../../../eide/external/llvm-7.0.1.src

include $(CLEAR_VARS)

llvm_lib_support_DIR=$(LLVM_ROOT_PATH)/lib/Support

llvm_lib_support_SRC_FILES:= \
  $(llvm_lib_support_DIR)/AMDGPUMetadata.cpp \
  $(llvm_lib_support_DIR)/APFloat.cpp \
  $(llvm_lib_support_DIR)/APInt.cpp \
  $(llvm_lib_support_DIR)/APSInt.cpp \
  $(llvm_lib_support_DIR)/ARMAttributeParser.cpp \
  $(llvm_lib_support_DIR)/ARMBuildAttrs.cpp \
  $(llvm_lib_support_DIR)/ARMWinEH.cpp \
  $(llvm_lib_support_DIR)/Allocator.cpp \
  $(llvm_lib_support_DIR)/Atomic.cpp \
  $(llvm_lib_support_DIR)/BinaryStreamError.cpp \
  $(llvm_lib_support_DIR)/BinaryStreamReader.cpp \
  $(llvm_lib_support_DIR)/BinaryStreamRef.cpp \
  $(llvm_lib_support_DIR)/BinaryStreamWriter.cpp \
  $(llvm_lib_support_DIR)/BlockFrequency.cpp \
  $(llvm_lib_support_DIR)/BranchProbability.cpp \
  $(llvm_lib_support_DIR)/COM.cpp \
  $(llvm_lib_support_DIR)/CachePruning.cpp \
  $(llvm_lib_support_DIR)/Chrono.cpp \
  $(llvm_lib_support_DIR)/CodeGenCoverage.cpp \
  $(llvm_lib_support_DIR)/CommandLine.cpp \
  $(llvm_lib_support_DIR)/Compression.cpp \
  $(llvm_lib_support_DIR)/ConvertUTF.cpp \
  $(llvm_lib_support_DIR)/ConvertUTFWrapper.cpp \
  $(llvm_lib_support_DIR)/CrashRecoveryContext.cpp \
  $(llvm_lib_support_DIR)/DAGDeltaAlgorithm.cpp \
  $(llvm_lib_support_DIR)/DJB.cpp \
  $(llvm_lib_support_DIR)/DataExtractor.cpp \
  $(llvm_lib_support_DIR)/Debug.cpp \
  $(llvm_lib_support_DIR)/DebugCounter.cpp \
  $(llvm_lib_support_DIR)/DeltaAlgorithm.cpp \
  $(llvm_lib_support_DIR)/DynamicLibrary.cpp \
  $(llvm_lib_support_DIR)/Errno.cpp \
  $(llvm_lib_support_DIR)/Error.cpp \
  $(llvm_lib_support_DIR)/ErrorHandling.cpp \
  $(llvm_lib_support_DIR)/FileOutputBuffer.cpp \
  $(llvm_lib_support_DIR)/FileUtilities.cpp \
  $(llvm_lib_support_DIR)/FoldingSet.cpp \
  $(llvm_lib_support_DIR)/FormatVariadic.cpp \
  $(llvm_lib_support_DIR)/FormattedStream.cpp \
  $(llvm_lib_support_DIR)/GlobPattern.cpp \
  $(llvm_lib_support_DIR)/GraphWriter.cpp \
  $(llvm_lib_support_DIR)/Hashing.cpp \
  $(llvm_lib_support_DIR)/Host.cpp \
  $(llvm_lib_support_DIR)/InitLLVM.cpp \
  $(llvm_lib_support_DIR)/IntEqClasses.cpp \
  $(llvm_lib_support_DIR)/IntervalMap.cpp \
  $(llvm_lib_support_DIR)/JSON.cpp \
  $(llvm_lib_support_DIR)/JamCRC.cpp \
  $(llvm_lib_support_DIR)/KnownBits.cpp \
  $(llvm_lib_support_DIR)/LEB128.cpp \
  $(llvm_lib_support_DIR)/LineIterator.cpp \
  $(llvm_lib_support_DIR)/Locale.cpp \
  $(llvm_lib_support_DIR)/LockFileManager.cpp \
  $(llvm_lib_support_DIR)/LowLevelType.cpp \
  $(llvm_lib_support_DIR)/MD5.cpp \
  $(llvm_lib_support_DIR)/ManagedStatic.cpp \
  $(llvm_lib_support_DIR)/MathExtras.cpp \
  $(llvm_lib_support_DIR)/Memory.cpp \
  $(llvm_lib_support_DIR)/MemoryBuffer.cpp \
  $(llvm_lib_support_DIR)/Mutex.cpp \
  $(llvm_lib_support_DIR)/NativeFormatting.cpp \
  $(llvm_lib_support_DIR)/Options.cpp \
  $(llvm_lib_support_DIR)/Parallel.cpp \
  $(llvm_lib_support_DIR)/Path.cpp \
  $(llvm_lib_support_DIR)/PluginLoader.cpp \
  $(llvm_lib_support_DIR)/PrettyStackTrace.cpp \
  $(llvm_lib_support_DIR)/Process.cpp \
  $(llvm_lib_support_DIR)/Program.cpp \
  $(llvm_lib_support_DIR)/RWMutex.cpp \
  $(llvm_lib_support_DIR)/RandomNumberGenerator.cpp \
  $(llvm_lib_support_DIR)/Regex.cpp \
  $(llvm_lib_support_DIR)/SHA1.cpp \
  $(llvm_lib_support_DIR)/ScaledNumber.cpp \
  $(llvm_lib_support_DIR)/ScopedPrinter.cpp \
  $(llvm_lib_support_DIR)/Signals.cpp \
  $(llvm_lib_support_DIR)/SmallPtrSet.cpp \
  $(llvm_lib_support_DIR)/SmallVector.cpp \
  $(llvm_lib_support_DIR)/SourceMgr.cpp \
  $(llvm_lib_support_DIR)/SpecialCaseList.cpp \
  $(llvm_lib_support_DIR)/Statistic.cpp \
  $(llvm_lib_support_DIR)/StringExtras.cpp \
  $(llvm_lib_support_DIR)/StringMap.cpp \
  $(llvm_lib_support_DIR)/StringPool.cpp \
  $(llvm_lib_support_DIR)/StringRef.cpp \
  $(llvm_lib_support_DIR)/StringSaver.cpp \
  $(llvm_lib_support_DIR)/SystemUtils.cpp \
  $(llvm_lib_support_DIR)/TarWriter.cpp \
  $(llvm_lib_support_DIR)/TargetParser.cpp \
  $(llvm_lib_support_DIR)/TargetRegistry.cpp \
  $(llvm_lib_support_DIR)/ThreadLocal.cpp \
  $(llvm_lib_support_DIR)/ThreadPool.cpp \
  $(llvm_lib_support_DIR)/Threading.cpp \
  $(llvm_lib_support_DIR)/Timer.cpp \
  $(llvm_lib_support_DIR)/ToolOutputFile.cpp \
  $(llvm_lib_support_DIR)/TrigramIndex.cpp \
  $(llvm_lib_support_DIR)/Triple.cpp \
  $(llvm_lib_support_DIR)/Twine.cpp \
  $(llvm_lib_support_DIR)/Unicode.cpp \
  $(llvm_lib_support_DIR)/UnicodeCaseFold.cpp \
  $(llvm_lib_support_DIR)/Valgrind.cpp \
  $(llvm_lib_support_DIR)/VersionTuple.cpp \
  $(llvm_lib_support_DIR)/Watchdog.cpp \
  $(llvm_lib_support_DIR)/WithColor.cpp \
  $(llvm_lib_support_DIR)/YAMLParser.cpp \
  $(llvm_lib_support_DIR)/YAMLTraits.cpp \
  $(llvm_lib_support_DIR)/circular_raw_ostream.cpp \
  $(llvm_lib_support_DIR)/raw_os_ostream.cpp \
  $(llvm_lib_support_DIR)/raw_ostream.cpp \
  $(llvm_lib_support_DIR)/regcomp.c \
  $(llvm_lib_support_DIR)/regerror.c \
  $(llvm_lib_support_DIR)/regexec.c \
  $(llvm_lib_support_DIR)/regfree.c \
  $(llvm_lib_support_DIR)/regstrlcpy.c \
  $(llvm_lib_support_DIR)/xxhash.cpp
  
llvm_lib_tablegen_DIR=$(LLVM_ROOT_PATH)/lib/TableGen

llvm_lib_tablegen_SRC_FILES:= \
  $(llvm_lib_tablegen_DIR)/Error.cpp \
  $(llvm_lib_tablegen_DIR)/JSONBackend.cpp \
  $(llvm_lib_tablegen_DIR)/Main.cpp \
  $(llvm_lib_tablegen_DIR)/Record.cpp \
  $(llvm_lib_tablegen_DIR)/SetTheory.cpp \
  $(llvm_lib_tablegen_DIR)/StringMatcher.cpp \
  $(llvm_lib_tablegen_DIR)/TGLexer.cpp \
  $(llvm_lib_tablegen_DIR)/TGParser.cpp \
  $(llvm_lib_tablegen_DIR)/TableGenBackend.cpp

llvm_utils_tablegen_DIR := $(LLVM_ROOT_PATH)/utils/TableGen

llvm_utils_tablegen_SRC_FILES:= \
  $(llvm_utils_tablegen_DIR)/AsmMatcherEmitter.cpp \
  $(llvm_utils_tablegen_DIR)/AsmWriterEmitter.cpp \
  $(llvm_utils_tablegen_DIR)/AsmWriterInst.cpp \
  $(llvm_utils_tablegen_DIR)/Attributes.cpp \
  $(llvm_utils_tablegen_DIR)/CTagsEmitter.cpp \
  $(llvm_utils_tablegen_DIR)/CallingConvEmitter.cpp \
  $(llvm_utils_tablegen_DIR)/CodeEmitterGen.cpp \
  $(llvm_utils_tablegen_DIR)/CodeGenDAGPatterns.cpp \
  $(llvm_utils_tablegen_DIR)/CodeGenHwModes.cpp \
  $(llvm_utils_tablegen_DIR)/CodeGenInstruction.cpp \
  $(llvm_utils_tablegen_DIR)/CodeGenMapTable.cpp \
  $(llvm_utils_tablegen_DIR)/CodeGenRegisters.cpp \
  $(llvm_utils_tablegen_DIR)/CodeGenSchedule.cpp \
  $(llvm_utils_tablegen_DIR)/CodeGenTarget.cpp \
  $(llvm_utils_tablegen_DIR)/DAGISelEmitter.cpp \
  $(llvm_utils_tablegen_DIR)/DAGISelMatcher.cpp \
  $(llvm_utils_tablegen_DIR)/DAGISelMatcherEmitter.cpp \
  $(llvm_utils_tablegen_DIR)/DAGISelMatcherGen.cpp \
  $(llvm_utils_tablegen_DIR)/DAGISelMatcherOpt.cpp \
  $(llvm_utils_tablegen_DIR)/DFAPacketizerEmitter.cpp \
  $(llvm_utils_tablegen_DIR)/DisassemblerEmitter.cpp \
  $(llvm_utils_tablegen_DIR)/FastISelEmitter.cpp \
  $(llvm_utils_tablegen_DIR)/FixedLenDecoderEmitter.cpp \
  $(llvm_utils_tablegen_DIR)/GlobalISelEmitter.cpp \
  $(llvm_utils_tablegen_DIR)/InfoByHwMode.cpp \
  $(llvm_utils_tablegen_DIR)/InstrDocsEmitter.cpp \
  $(llvm_utils_tablegen_DIR)/InstrInfoEmitter.cpp \
  $(llvm_utils_tablegen_DIR)/IntrinsicEmitter.cpp \
  $(llvm_utils_tablegen_DIR)/OptParserEmitter.cpp \
  $(llvm_utils_tablegen_DIR)/PredicateExpander.cpp \
  $(llvm_utils_tablegen_DIR)/PseudoLoweringEmitter.cpp \
  $(llvm_utils_tablegen_DIR)/RISCVCompressInstEmitter.cpp \
  $(llvm_utils_tablegen_DIR)/RegisterBankEmitter.cpp \
  $(llvm_utils_tablegen_DIR)/RegisterInfoEmitter.cpp \
  $(llvm_utils_tablegen_DIR)/SDNodeProperties.cpp \
  $(llvm_utils_tablegen_DIR)/SearchableTableEmitter.cpp \
  $(llvm_utils_tablegen_DIR)/SubtargetEmitter.cpp \
  $(llvm_utils_tablegen_DIR)/SubtargetFeatureInfo.cpp \
  $(llvm_utils_tablegen_DIR)/TableGen.cpp \
  $(llvm_utils_tablegen_DIR)/Types.cpp \
  $(llvm_utils_tablegen_DIR)/WebAssemblyDisassemblerEmitter.cpp \
  $(llvm_utils_tablegen_DIR)/X86DisassemblerTables.cpp \
  $(llvm_utils_tablegen_DIR)/X86EVEX2VEXTablesEmitter.cpp \
  $(llvm_utils_tablegen_DIR)/X86FoldTablesEmitter.cpp \
  $(llvm_utils_tablegen_DIR)/X86ModRMFilters.cpp \
  $(llvm_utils_tablegen_DIR)/X86RecognizableInstr.cpp

include $(CLEAR_VARS)

LOCAL_SRC_FILES := \
  $(llvm_lib_support_SRC_FILES) \
  $(llvm_lib_tablegen_SRC_FILES) \
  $(llvm_utils_tablegen_SRC_FILES) \
  to_string.cpp
  
LOCAL_MODULE:= llvm-tblgen
LOCAL_C_INCLUDES := \
  $(LOCAL_PATH)/$(LLVM_ROOT_PATH)/include   \
  $(LOCAL_PATH)/../../common-llvm/include \
  $(LOCAL_C_INCLUDES) 

LOCAL_CFLAGS := \
  -D_GNU_SOURCE   \
  -D__STDC_LIMIT_MACROS   \
  -D__STDC_CONSTANT_MACROS    \
  -Os \
  -fomit-frame-pointer    \
  -mthumb \
  -DLLVM_BUILD_GLOBAL_ISEL \
  -DLLVM_VERSION_STRING=\"7.0.1\" \
  $(LOCAL_CFLAGS)

LOCAL_CFLAGS += \
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

X_LOCAL_LDLIBS += \
  -l$(PWD)/../lib/libLLVMSupport.so \
  -l$(PWD)/../lib/libLLVMTableGen.so

LOCAL_CPPFLAGS +=-frtti -fexceptions -std=c++11
include $(BUILD_EXECUTABLE)
