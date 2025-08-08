cmake_minimum_required(VERSION 3.22.1)
project(DataveinOracleNative)

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
    datavein_oracle_native.cpp
    oracle_connector.cpp
    data_processor.cpp
)

# Create placeholder source files if they don't exist
foreach(source ${NATIVE_SOURCES})
    if(NOT EXISTS "${CMAKE_CURRENT_SOURCE_DIR}/${source}")
        file(WRITE "${CMAKE_CURRENT_SOURCE_DIR}/${source}" 
             "#include <jni.h>\n#include <string>\n\n// Placeholder for ${source}\nextern \"C\" JNIEXPORT jstring JNICALL\nJava_com_genesis_datavein_NativeLib_getInfo(JNIEnv *env, jobject /* this */) {\n    return env->NewStringUTF(\"Datavein Oracle Native 1.0\");\n}\n")
    endif()
endforeach()

# Create shared library
add_library(datavein-oracle-native SHARED ${NATIVE_SOURCES})

# Link libraries
target_link_libraries(datavein-oracle-native
    ${log-lib}
    ${android-lib}
)

# Set library properties
set_target_properties(datavein-oracle-native PROPERTIES
    VERSION 1.0
    SOVERSION 1
)

