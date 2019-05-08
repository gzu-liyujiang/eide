# Invalid NDK_TOOLCHAIN_VERSION value: 4.9. GCC is no longer supported.
# See https://android.googlesource.com/platform/ndk/+/master/docs/ClangMigration.md.
NDK_TOOLCHAIN_VERSION := clang
# gnustl_static is no longer supported. Please switch to either c++_static or c++_shared.
# See https://developer.android.com/ndk/guides/cpp-support.html for more information.
APP_STL := c++_static
APP_ABI := armeabi-v7a
APP_PLATFORM := android-28
APP_OPTIM := release
