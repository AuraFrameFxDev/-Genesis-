@echo off
"C:\\Users\\Wehtt\\AppData\\Local\\Android\\Sdk\\cmake\\3.22.1\\bin\\cmake.exe" ^
  "-HC:\\Genesis\\romtools\\src\\main\\cpp" ^
  "-DCMAKE_SYSTEM_NAME=Android" ^
  "-DCMAKE_EXPORT_COMPILE_COMMANDS=ON" ^
  "-DCMAKE_SYSTEM_VERSION=33" ^
  "-DANDROID_PLATFORM=android-33" ^
  "-DANDROID_ABI=arm64-v8a" ^
  "-DCMAKE_ANDROID_ARCH_ABI=arm64-v8a" ^
  "-DANDROID_NDK=C:\\Users\\Wehtt\\AppData\\Local\\Android\\Sdk\\ndk\\27.0.12077973" ^
  "-DCMAKE_ANDROID_NDK=C:\\Users\\Wehtt\\AppData\\Local\\Android\\Sdk\\ndk\\27.0.12077973" ^
  "-DCMAKE_TOOLCHAIN_FILE=C:\\Users\\Wehtt\\AppData\\Local\\Android\\Sdk\\ndk\\27.0.12077973\\build\\cmake\\android.toolchain.cmake" ^
  "-DCMAKE_MAKE_PROGRAM=C:\\Users\\Wehtt\\AppData\\Local\\Android\\Sdk\\cmake\\3.22.1\\bin\\ninja.exe" ^
  "-DCMAKE_CXX_FLAGS=-std=c++20 -fPIC -g -DDEBUG -DROM_DEBUG_BUILD" ^
  "-DCMAKE_LIBRARY_OUTPUT_DIRECTORY=C:\\Genesis\\romtools\\build\\intermediates\\cxx\\Debug\\n6v4xl2r\\obj\\arm64-v8a" ^
  "-DCMAKE_RUNTIME_OUTPUT_DIRECTORY=C:\\Genesis\\romtools\\build\\intermediates\\cxx\\Debug\\n6v4xl2r\\obj\\arm64-v8a" ^
  "-DCMAKE_BUILD_TYPE=Debug" ^
  "-DCMAKE_FIND_ROOT_PATH=C:\\Genesis\\romtools\\.cxx\\Debug\\n6v4xl2r\\prefab\\arm64-v8a\\prefab" ^
  "-BC:\\Genesis\\romtools\\.cxx\\Debug\\n6v4xl2r\\arm64-v8a" ^
  -GNinja ^
  "-DANDROID_STL=c++_shared" ^
  "-DCMAKE_VERBOSE_MAKEFILE=ON" ^
  "-DROM_TOOLS_BUILD=ON"
