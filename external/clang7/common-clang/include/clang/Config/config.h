/* This generated file is for internal use. Do not include it from headers. */

#ifndef CLANG_CONFIG_H
#define CLANG_CONFIG_H

/* Bug report URL. */
#define define BUG_REPORT_URL "http://llvm.org/bugs/"

/* Default linker to use. */
#define CLANG_DEFAULT_LINKER ""

/* Default C/ObjC standard to use. */
//#cmakedefine CLANG_DEFAULT_STD_C LangStandard::lang_${CLANG_DEFAULT_STD_C}

/* Default C++/ObjC++ standard to use. */
//#cmakedefine CLANG_DEFAULT_STD_CXX LangStandard::lang_${CLANG_DEFAULT_STD_CXX}

/* Default C++ stdlib to use. */
#define CLANG_DEFAULT_CXX_STDLIB "libstdc++"

/* Default runtime library to use. */
#define CLANG_DEFAULT_RTLIB ""

/* Default objcopy to use */
#define CLANG_DEFAULT_OBJCOPY ""

/* Default OpenMP runtime used by -fopenmp. */
#define CLANG_DEFAULT_OPENMP_RUNTIME ""

/* Default architecture for OpenMP offloading to Nvidia GPUs. */
#define CLANG_OPENMP_NVPTX_DEFAULT_ARCH ""

/* Multilib suffix for libdir. */
#define CLANG_LIBDIR_SUFFIX ""

/* Relative directory for resource files */
#define CLANG_RESOURCE_DIR ""

/* Directories clang will search for headers */
#define C_INCLUDE_DIRS ""

/* Directories clang will search for configuration files */
//#cmakedefine CLANG_CONFIG_FILE_SYSTEM_DIR "${CLANG_CONFIG_FILE_SYSTEM_DIR}"
//#cmakedefine CLANG_CONFIG_FILE_USER_DIR "${CLANG_CONFIG_FILE_USER_DIR}"

/* Default <path> to all compiler invocations for --sysroot=<path>. */
#define DEFAULT_SYSROOT ""

/* Directory where gcc is installed. */
#define GCC_INSTALL_PREFIX ""

/* Define if we have libxml2 */
//#cmakedefine CLANG_HAVE_LIBXML ${CLANG_HAVE_LIBXML}

/* Define if we have z3 and want to build it */
//#cmakedefine CLANG_ANALYZER_WITH_Z3 ${CLANG_ANALYZER_WITH_Z3}

/* Define if we have sys/resource.h (rlimits) */
//#cmakedefine CLANG_HAVE_RLIMITS ${CLANG_HAVE_RLIMITS}

/* The LLVM product name and version */
#define BACKEND_PACKAGE_STRING "llvm-7.0.1"

/* Linker version detected at compile time. */
//#cmakedefine HOST_LINK_VERSION "${HOST_LINK_VERSION}"

/* pass --build-id to ld */
//#cmakedefine ENABLE_LINKER_BUILD_ID

/* enable x86 relax relocations by default */
#define ENABLE_X86_RELAX_RELOCATIONS 0

/* Enable the experimental new pass manager by default */
#define ENABLE_EXPERIMENTAL_NEW_PASS_MANAGER 0

/* Enable each functionality of modules */
#define CLANG_ENABLE_ARCMT 0
#define CLANG_ENABLE_OBJC_REWRITER 0
#define CLANG_ENABLE_STATIC_ANALYZER 0

#endif