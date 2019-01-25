LOCAL_PATH:= $(call my-dir)

include $(CLEAR_VARS)

python_DIR  := ../../Python-3.7.1

python_SRC_FILES :=   \
  $(python_DIR)/Modules/_abc.c \
  $(python_DIR)/Modules/_codecsmodule.c \
  $(python_DIR)/Modules/_collectionsmodule.c \
  $(python_DIR)/Modules/_functoolsmodule.c \
  $(python_DIR)/Modules/_math.c \
  $(python_DIR)/Modules/_operator.c \
  $(python_DIR)/Modules/_posixsubprocess.c \
  $(python_DIR)/Modules/_randommodule.c \
  $(python_DIR)/Modules/_sre.c \
  $(python_DIR)/Modules/_stat.c \
  $(python_DIR)/Modules/_struct.c \
  $(python_DIR)/Modules/_threadmodule.c \
  $(python_DIR)/Modules/_tracemalloc.c \
  $(python_DIR)/Modules/_weakref.c \
  $(python_DIR)/Modules/atexitmodule.c \
  $(python_DIR)/Modules/errnomodule.c \
  $(python_DIR)/Modules/faulthandler.c \
  $(python_DIR)/Modules/gcmodule.c \
  $(python_DIR)/Modules/getbuildinfo.c \
  $(python_DIR)/Modules/getpath.c \
  $(python_DIR)/Modules/hashtable.c \
  $(python_DIR)/Modules/itertoolsmodule.c \
  $(python_DIR)/Modules/main.c \
  $(python_DIR)/Modules/mathmodule.c \
  $(python_DIR)/Modules/md5module.c \
  $(python_DIR)/Modules/posixmodule.c \
  $(python_DIR)/Modules/pwdmodule.c \
  $(python_DIR)/Modules/selectmodule.c \
  $(python_DIR)/Modules/sha1module.c \
  $(python_DIR)/Modules/sha256module.c \
  $(python_DIR)/Modules/sha512module.c \
  $(python_DIR)/Modules/signalmodule.c \
  $(python_DIR)/Modules/symtablemodule.c \
  $(python_DIR)/Modules/timemodule.c \
  $(python_DIR)/Modules/xxsubtype.c \
  $(python_DIR)/Modules/zipimport.c \
  $(python_DIR)/Modules/_io/_iomodule.c \
  $(python_DIR)/Modules/_io/bufferedio.c \
  $(python_DIR)/Modules/_io/bytesio.c \
  $(python_DIR)/Modules/_io/fileio.c \
  $(python_DIR)/Modules/_io/iobase.c \
  $(python_DIR)/Modules/_io/stringio.c \
  $(python_DIR)/Modules/_io/textio.c \
  $(python_DIR)/Parser/metagrammar.c \
  $(python_DIR)/Parser/acceler.c \
  $(python_DIR)/Parser/grammar1.c \
  $(python_DIR)/Parser/listnode.c \
  $(python_DIR)/Parser/node.c \
  $(python_DIR)/Parser/parser.c \
  $(python_DIR)/Parser/bitset.c \
  $(python_DIR)/Parser/myreadline.c \
  $(python_DIR)/Parser/firstsets.c \
  $(python_DIR)/Parser/grammar.c \
  $(python_DIR)/Parser/pgen.c \
  $(python_DIR)/Parser/parsetok.c \
  $(python_DIR)/Parser/tokenizer.c \
  $(python_DIR)/Python/Python-ast.c \
  $(python_DIR)/Python/_warnings.c \
  $(python_DIR)/Python/ast_opt.c \
  $(python_DIR)/Python/asdl.c \
  $(python_DIR)/Python/ast.c \
  $(python_DIR)/Python/dynamic_annotations.c \
  $(python_DIR)/Python/ast_unparse.c \
  $(python_DIR)/Python/bltinmodule.c \
  $(python_DIR)/Python/ceval.c \
  $(python_DIR)/Python/compile.c \
  $(python_DIR)/Python/codecs.c \
  $(python_DIR)/Python/frozenmain.c \
  $(python_DIR)/Python/errors.c \
  $(python_DIR)/Python/getcompiler.c \
  $(python_DIR)/Python/future.c \
  $(python_DIR)/Python/getargs.c \
  $(python_DIR)/Python/bootstrap_hash.c \
  $(python_DIR)/Python/getcopyright.c \
  $(python_DIR)/Python/getplatform.c \
  $(python_DIR)/Python/getversion.c \
  $(python_DIR)/Python/graminit.c \
  $(python_DIR)/Python/import.c \
  $(python_DIR)/Python/importdl.c \
  $(python_DIR)/Python/marshal.c \
  $(python_DIR)/Python/modsupport.c \
  $(python_DIR)/Python/mysnprintf.c \
  $(python_DIR)/Python/mystrtoul.c \
  $(python_DIR)/Python/pathconfig.c \
  $(python_DIR)/Python/peephole.c \
  $(python_DIR)/Python/pyarena.c \
  $(python_DIR)/Python/pyctype.c \
  $(python_DIR)/Python/pyfpe.c \
  $(python_DIR)/Python/pyhash.c \
  $(python_DIR)/Python/pylifecycle.c \
  $(python_DIR)/Python/pymath.c \
  $(python_DIR)/Python/pystate.c \
  $(python_DIR)/Python/context.c \
  $(python_DIR)/Python/hamt.c \
  $(python_DIR)/Python/pythonrun.c \
  $(python_DIR)/Python/pytime.c \
  $(python_DIR)/Python/formatter_unicode.c \
  $(python_DIR)/Python/structmember.c \
  $(python_DIR)/Python/symtable.c \
  $(python_DIR)/Python/sysmodule.c \
  $(python_DIR)/Python/thread.c \
  $(python_DIR)/Python/traceback.c \
  $(python_DIR)/Python/getopt.c \
  $(python_DIR)/Python/pystrcmp.c \
  $(python_DIR)/Python/pystrtod.c \
  $(python_DIR)/Python/pystrhex.c \
  $(python_DIR)/Python/dtoa.c \
  $(python_DIR)/Python/dynload_shlib.c \
  $(python_DIR)/Python/frozen.c \
  $(python_DIR)/Programs/python.c \
  $(python_DIR)/Objects/boolobject.c \
  $(python_DIR)/Objects/abstract.c \
  $(python_DIR)/Objects/accu.c \
  $(python_DIR)/Objects/bytearrayobject.c \
  $(python_DIR)/Objects/bytes_methods.c \
  $(python_DIR)/Objects/namespaceobject.c \
  $(python_DIR)/Objects/bytesobject.c \
  $(python_DIR)/Objects/call.c \
  $(python_DIR)/Objects/cellobject.c \
  $(python_DIR)/Objects/classobject.c \
  $(python_DIR)/Objects/codeobject.c \
  $(python_DIR)/Objects/complexobject.c \
  $(python_DIR)/Objects/descrobject.c \
  $(python_DIR)/Objects/enumobject.c \
  $(python_DIR)/Objects/exceptions.c \
  $(python_DIR)/Objects/genobject.c \
  $(python_DIR)/Objects/fileobject.c \
  $(python_DIR)/Objects/floatobject.c \
  $(python_DIR)/Objects/frameobject.c \
  $(python_DIR)/Objects/funcobject.c \
  $(python_DIR)/Objects/iterobject.c \
  $(python_DIR)/Objects/listobject.c \
  $(python_DIR)/Objects/longobject.c \
  $(python_DIR)/Objects/dictobject.c \
  $(python_DIR)/Objects/odictobject.c \
  $(python_DIR)/Objects/memoryobject.c \
  $(python_DIR)/Objects/methodobject.c \
  $(python_DIR)/Objects/moduleobject.c \
  $(python_DIR)/Objects/rangeobject.c \
  $(python_DIR)/Objects/object.c \
  $(python_DIR)/Objects/obmalloc.c \
  $(python_DIR)/Objects/capsule.c \
  $(python_DIR)/Objects/sliceobject.c \
  $(python_DIR)/Objects/setobject.c \
  $(python_DIR)/Objects/tupleobject.c \
  $(python_DIR)/Objects/structseq.c \
  $(python_DIR)/Objects/typeobject.c \
  $(python_DIR)/Objects/unicodeobject.c \
  $(python_DIR)/Objects/unicodectype.c \
  $(python_DIR)/Objects/weakrefobject.c \
  Modules/config.c 
  
TARGET_ANDROID_VERSION :=$(strip $(subst android-,,$(APP_PLATFORM)))

## TARGET_ANDROID_VERSION >= 21
#python_SRC_FILES += \
#  $(python_DIR)/Modules/_localemodule.c \
#  $(python_DIR)/Python/fileutils.c  ;

## TARGET_ANDROID_VERSION < 21
python_SRC_FILES += \
  Modules/_localemodule.c \
  Python/fileutils.c  

LOCAL_SRC_FILES := $(python_SRC_FILES)

LOCAL_MODULE := python

LOCAL_C_INCLUDES := \
    $(LOCAL_PATH) \
    $(LOCAL_PATH)/$(python_DIR)/Include \
    $(LOCAL_C_INCLUDES) 
    
LOCAL_CFLAGS := \
  -std=c99 \
  -Os \
  -marm \
  -march=armv7-a \
  -mfpu=neon-fp16 \
  -fvisibility=hidden \
  -ffast-math \
  -fomit-frame-pointer    \
  -Wall   \
  -W  \
  -Wno-unused-parameter   \
  -Wwrite-strings \
  -DPy_BUILD_CORE \
  -DVERSION=\"3.7.1\" \
  -DPREFIX=\"\" \
  -DEXEC_PREFIX=\"\" \
  -DPYTHONPATH=\"\" \
  -DVPATH=\"\" \
  -DSOABI=\"\" \
  -D__ANDROID__=1 \
  $(LOCAL_CFLAGS)
    
include $(BUILD_EXECUTABLE)
