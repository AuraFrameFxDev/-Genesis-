cmake_minimum_required(VERSION 3.22.1)
project(OracleDriveIntegration)

# Set C++ standard
set(CMAKE_CXX_STANDARD 23)
set(CMAKE_CXX_STANDARD_REQUIRED ON)

# Compiler-specific options
if(CMAKE_CXX_COMPILER_ID STREQUAL "Clang")
    set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++23 -fPIC")
elseif(CMAKE_CXX_COMPILER_ID STREQUAL "GNU")
    set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++23 -fPIC")
endif()

# Find required packages
find_library(log-lib log)
find_library(android-lib android)

# Include directories
include_directories(${CMAKE_CURRENT_SOURCE_DIR})

# Add source files (create placeholder if they don't exist)
set(NATIVE_SOURCES
    oracle_drive_native.cpp
    drive_connector.cpp
    file_handler.cpp
)

# Create placeholder source files if they don't exist
foreach(source ${NATIVE_SOURCES})
    if(NOT EXISTS "${CMAKE_CURRENT_SOURCE_DIR}/${source}")
        file(WRITE "${CMAKE_CURRENT_SOURCE_DIR}/${source}" 
             "#include <jni.h>\n#include <string>\n\n// Placeholder for ${source}\nextern \"C\" JNIEXPORT jstring JNICALL\nJava_com_genesis_oracledrive_NativeLib_getVersion(JNIEnv *env, jobject /* this */) {\n    return env->NewStringUTF(\"Oracle Drive Integration 1.0\");\n}\n")
    endif()
endforeach()

# Create shared library
add_library(oracle-drive-native SHARED ${NATIVE_SOURCES})

# Link libraries
target_link_libraries(oracle-drive-native
    ${log-lib}
    ${android-lib}
)

# Set library properties
set_target_properties(oracle-drive-native PROPERTIES
    VERSION 1.0
    SOVERSION 1
)

