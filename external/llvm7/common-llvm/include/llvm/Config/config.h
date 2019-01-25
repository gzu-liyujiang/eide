#ifndef CONFIG_H
#define CONFIG_H

/* Exported configuration */
#include "llvm/Config/llvm-config.h"

/* Bug report URL. */
#define BUG_REPORT_URL "http://llvm.org/bugs/"

/* Define to 1 to enable backtraces, and to 0 otherwise. */
#define ENABLE_BACKTRACES 0

/* Define to 1 to enable crash overrides, and to 0 otherwise. */
#define ENABLE_CRASH_OVERRIDES 0

/* Define to 1 if you have the `backtrace' function. */
//#cmakedefine HAVE_BACKTRACE ${HAVE_BACKTRACE}

//#define BACKTRACE_HEADER <${BACKTRACE_HEADER}>

/* Define to 1 if you have the <CrashReporterClient.h> header file. */
//#cmakedefine HAVE_CRASHREPORTERCLIENT_H

/* can use __crashreporter_info__ */
//#cmakedefine01 HAVE_CRASHREPORTER_INFO

/* Define to 1 if you have the declaration of `arc4random', and to 0 if you
   don't. */
#define HAVE_DECL_ARC4RANDOM 1

/* Define to 1 if you have the declaration of `FE_ALL_EXCEPT', and to 0 if you
   don't. */
#define HAVE_DECL_FE_ALL_EXCEPT 1

/* Define to 1 if you have the declaration of `FE_INEXACT', and to 0 if you
   don't. */
#define HAVE_DECL_FE_INEXACT 1

/* Define to 1 if you have the declaration of `strerror_s', and to 0 if you
   don't. */
#define HAVE_DECL_STRERROR_S 0

/* Define to 1 if you have the DIA SDK installed, and to 0 if you don't. */
#define LLVM_ENABLE_DIA_SDK 0

/* Define to 1 if you have the <dlfcn.h> header file. */
#define HAVE_DLFCN_H 1

/* Define if dlopen() is available on this platform. */
#define HAVE_DLOPEN 1

/* Define if dladdr() is available on this platform. */
#define HAVE_DLADDR 1

/* Define to 1 if you have the <errno.h> header file. */
#define HAVE_ERRNO_H 1

/* Define to 1 if you have the <fcntl.h> header file. */
#define HAVE_FCNTL_H 1

/* Define to 1 if you have the <fenv.h> header file. */
#define HAVE_FENV_H 1

/* Define if libffi is available on this platform. */
//#cmakedefine HAVE_FFI_CALL ${HAVE_FFI_CALL}

/* Define to 1 if you have the <ffi/ffi.h> header file. */
//#cmakedefine HAVE_FFI_FFI_H ${HAVE_FFI_FFI_H}

/* Define to 1 if you have the <ffi.h> header file. */
//#cmakedefine HAVE_FFI_H ${HAVE_FFI_H}

/* Define to 1 if you have the `futimens' function. */
//#cmakedefine HAVE_FUTIMENS ${HAVE_FUTIMENS}

/* Define to 1 if you have the `futimes' function. */
//#cmakedefine HAVE_FUTIMES ${HAVE_FUTIMES}

/* Define to 1 if you have the `getpagesize' function. */
#define HAVE_GETPAGESIZE 1

/* Define to 1 if you have the `getrlimit' function. */
#define HAVE_GETRLIMIT 1

/* Define to 1 if you have the `getrusage' function. */
#define HAVE_GETRUSAGE 1

/* Define to 1 if you have the `isatty' function. */
#define HAVE_ISATTY 1

/* Define to 1 if you have the `edit' library (-ledit). */
//#cmakedefine HAVE_LIBEDIT ${HAVE_LIBEDIT}

/* Define to 1 if you have the `pfm' library (-lpfm). */
//#cmakedefine HAVE_LIBPFM ${HAVE_LIBPFM}

/* Define to 1 if you have the `psapi' library (-lpsapi). */
//#cmakedefine HAVE_LIBPSAPI ${HAVE_LIBPSAPI}

/* Define to 1 if you have the `pthread' library (-lpthread). */
//#cmakedefine HAVE_LIBPTHREAD ${HAVE_LIBPTHREAD}

/* Define to 1 if you have the `pthread_getname_np' function. */
//#cmakedefine HAVE_PTHREAD_GETNAME_NP ${HAVE_PTHREAD_GETNAME_NP}

/* Define to 1 if you have the `pthread_setname_np' function. */
//#cmakedefine HAVE_PTHREAD_SETNAME_NP ${HAVE_PTHREAD_SETNAME_NP}

/* Define to 1 if you have the `z' library (-lz). */
#define HAVE_LIBZ 1

/* Define to 1 if you have the <link.h> header file. */
#define HAVE_LINK_H 1

/* Define to 1 if you have the `lseek64' function. */
#define HAVE_LSEEK64 1

/* Define to 1 if you have the <mach/mach.h> header file. */
//#cmakedefine HAVE_MACH_MACH_H ${HAVE_MACH_MACH_H}

/* Define to 1 if you have the `mallctl' function. */
//#cmakedefine HAVE_MALLCTL ${HAVE_MALLCTL}

/* Define to 1 if you have the `mallinfo' function. */
#define HAVE_MALLINFO 1

/* Define to 1 if you have the <malloc.h> header file. */
#define HAVE_MALLOC_H 1

/* Define to 1 if you have the <malloc/malloc.h> header file. */
//#cmakedefine HAVE_MALLOC_MALLOC_H ${HAVE_MALLOC_MALLOC_H}

/* Define to 1 if you have the `malloc_zone_statistics' function. */
//#cmakedefine HAVE_MALLOC_ZONE_STATISTICS ${HAVE_MALLOC_ZONE_STATISTICS}

/* Define to 1 if you have the `posix_fallocate' function. */
//#cmakedefine HAVE_POSIX_FALLOCATE ${HAVE_POSIX_FALLOCATE}

/* Define to 1 if you have the `posix_spawn' function. */
//#cmakedefine HAVE_POSIX_SPAWN ${HAVE_POSIX_SPAWN}

/* Define to 1 if you have the `pread' function. */
#define HAVE_PREAD 1

/* Have pthread_getspecific */
#define HAVE_PTHREAD_GETSPECIFIC 1

/* Define to 1 if you have the <pthread.h> header file. */
#define HAVE_PTHREAD_H 1

/* Have pthread_mutex_lock */
#define HAVE_PTHREAD_MUTEX_LOCK 1

/* Have pthread_rwlock_init */
#define HAVE_PTHREAD_RWLOCK_INIT 1

/* Define to 1 if you have the `realpath' function. */
#define HAVE_REALPATH 1

/* Define to 1 if you have the `sbrk' function. */
#define HAVE_SBRK 1

/* Define to 1 if you have the `setenv' function. */
#define HAVE_SETENV 1

/* Define to 1 if you have the `sched_getaffinity' function. */
//#cmakedefine HAVE_SCHED_GETAFFINITY ${HAVE_SCHED_GETAFFINITY}

/* Define to 1 if you have the `CPU_COUNT' macro. */
//#cmakedefine HAVE_CPU_COUNT ${HAVE_CPU_COUNT}

/* Define to 1 if you have the `setrlimit' function. */
#define HAVE_SETRLIMIT 1

/* Define to 1 if you have the `sigaltstack' function. */
#define HAVE_SIGALTSTACK 1

/* Define to 1 if you have the <signal.h> header file. */
#define HAVE_SIGNAL_H 1

/* Define to 1 if you have the `strerror' function. */
#define HAVE_STRERROR 1

/* Define to 1 if you have the `strerror_r' function. */
#define HAVE_STRERROR_R 1

/* Define to 1 if you have the `sysconf' function. */
#define HAVE_SYSCONF 1

/* Define to 1 if you have the <sys/ioctl.h> header file. */
#define HAVE_SYS_IOCTL_H 1

/* Define to 1 if you have the <sys/mman.h> header file. */
#define HAVE_SYS_MMAN_H 1

/* Define to 1 if you have the <sys/param.h> header file. */
#define HAVE_SYS_PARAM_H 1

/* Define to 1 if you have the <sys/resource.h> header file. */
#define HAVE_SYS_RESOURCE_H 1

/* Define to 1 if you have the <sys/stat.h> header file. */
#define HAVE_SYS_STAT_H 1

/* Define to 1 if you have the <sys/time.h> header file. */
#define HAVE_SYS_TIME_H 1

/* Define to 1 if you have the <sys/types.h> header file. */
#define HAVE_SYS_TYPES_H 1

/* Define if the setupterm() function is supported this platform. */
//#cmakedefine HAVE_TERMINFO ${HAVE_TERMINFO}

/* Define if the xar_open() function is supported this platform. */
//#cmakedefine HAVE_LIBXAR ${HAVE_LIBXAR}

/* Define to 1 if you have the <termios.h> header file. */
#define HAVE_TERMIOS_H 1

/* Define to 1 if you have the <unistd.h> header file. */
#define HAVE_UNISTD_H 1

/* Define to 1 if you have the <valgrind/valgrind.h> header file. */
//#cmakedefine HAVE_VALGRIND_VALGRIND_H ${HAVE_VALGRIND_VALGRIND_H}

/* Define to 1 if you have the <zlib.h> header file. */
#define HAVE_ZLIB_H 1

/* Have host's _alloca */
//#cmakedefine HAVE__ALLOCA ${HAVE__ALLOCA}

/* Define to 1 if you have the `_chsize_s' function. */
//#cmakedefine HAVE__CHSIZE_S ${HAVE__CHSIZE_S}

/* Define to 1 if you have the `_Unwind_Backtrace' function. */
//#cmakedefine HAVE__UNWIND_BACKTRACE ${HAVE__UNWIND_BACKTRACE}

/* Have host's __alloca */
//#cmakedefine HAVE___ALLOCA ${HAVE___ALLOCA}

/* Have host's __ashldi3 */
//#cmakedefine HAVE___ASHLDI3 ${HAVE___ASHLDI3}

/* Have host's __ashrdi3 */
//#cmakedefine HAVE___ASHRDI3 ${HAVE___ASHRDI3}

/* Have host's __chkstk */
//#cmakedefine HAVE___CHKSTK ${HAVE___CHKSTK}

/* Have host's __chkstk_ms */
//#cmakedefine HAVE___CHKSTK_MS ${HAVE___CHKSTK_MS}

/* Have host's __cmpdi2 */
//#cmakedefine HAVE___CMPDI2 ${HAVE___CMPDI2}

/* Have host's __divdi3 */
//#cmakedefine HAVE___DIVDI3 ${HAVE___DIVDI3}

/* Have host's __fixdfdi */
//#cmakedefine HAVE___FIXDFDI ${HAVE___FIXDFDI}

/* Have host's __fixsfdi */
//#cmakedefine HAVE___FIXSFDI ${HAVE___FIXSFDI}

/* Have host's __floatdidf */
//#cmakedefine HAVE___FLOATDIDF ${HAVE___FLOATDIDF}

/* Have host's __lshrdi3 */
//#cmakedefine HAVE___LSHRDI3 ${HAVE___LSHRDI3}

/* Have host's __main */
//#cmakedefine HAVE___MAIN ${HAVE___MAIN}

/* Have host's __moddi3 */
//#cmakedefine HAVE___MODDI3 ${HAVE___MODDI3}

/* Have host's __udivdi3 */
//#cmakedefine HAVE___UDIVDI3 ${HAVE___UDIVDI3}

/* Have host's __umoddi3 */
//#cmakedefine HAVE___UMODDI3 ${HAVE___UMODDI3}

/* Have host's ___chkstk */
//#cmakedefine HAVE____CHKSTK ${HAVE____CHKSTK}

/* Have host's ___chkstk_ms */
//#cmakedefine HAVE____CHKSTK_MS ${HAVE____CHKSTK_MS}

/* Linker version detected at compile time. */
#define HOST_LINK_VERSION "0.1"

/* Target triple LLVM will generate code for by default */
/* Doesn't use `cmakedefine` because it is allowed to be empty. */
#define LLVM_DEFAULT_TARGET_TRIPLE "arm-none-linux-android"

/* Define if zlib compression is available */
#define LLVM_ENABLE_ZLIB 1

/* Define if overriding target triple is enabled */
//#cmakedefine LLVM_TARGET_TRIPLE_ENV "${LLVM_TARGET_TRIPLE_ENV}"

/* LLVM version information */
#define LLVM_VERSION_INFO "llvm-7.0.1"

/* Whether tools show host and target info when invoked with --version */
#define LLVM_VERSION_PRINTER_SHOW_HOST_TARGET_INFO 1

/* Define if libxml2 is supported on this platform. */
//#cmakedefine LLVM_LIBXML2_ENABLED ${LLVM_LIBXML2_ENABLED}

/* Define to the extension used for shared libraries, say, ".so". */
//#cmakedefine LTDL_SHLIB_EXT "${LTDL_SHLIB_EXT}"

/* Define to the address where bug reports for this package should be sent. */
#define PACKAGE_BUGREPORT "202983447@qq.com"

/* Define to the full name of this package. */
#define PACKAGE_NAME "llvm"

/* Define to the full name and version of this package. */
#define PACKAGE_STRING "llvm-7.0.1"

/* Define to the version of this package. */
#define PACKAGE_VERSION "7.0.1"

/* Define to the vendor of this package. */
#define PACKAGE_VENDOR "none"

/* Define as the return type of signal handlers (`int' or `void'). */
#define RETSIGTYPE void

/* Define to a function implementing stricmp */
//#cmakedefine stricmp ${stricmp}

/* Define to a function implementing strdup */
//#cmakedefine strdup ${strdup}

/* Whether GlobalISel rule coverage is being collected */
//#cmakedefine01 LLVM_GISEL_COV_ENABLED

/* Define to the default GlobalISel coverage file prefix */
//#cmakedefine LLVM_GISEL_COV_PREFIX "${LLVM_GISEL_COV_PREFIX}"

#endif
