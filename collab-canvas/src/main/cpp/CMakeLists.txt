cmake_minimum_required(VERSION 3.22.1)
project(CollabCanvasNative)

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
find_library(jnigraphics-lib jnigraphics)

# Include directories
include_directories(${CMAKE_CURRENT_SOURCE_DIR})

# Add source files (create placeholder if they don't exist)
set(NATIVE_SOURCES
    collab_canvas_native.cpp
)

# Create placeholder source file if it doesn't exist
foreach(source ${NATIVE_SOURCES})
    if(NOT EXISTS "${CMAKE_CURRENT_SOURCE_DIR}/${source}")
        file(WRITE "${CMAKE_CURRENT_SOURCE_DIR}/${source}" 
             "#include <jni.h>\n\n// Placeholder for ${source}\nextern \"C\" JNIEXPORT jstring JNICALL\nJava_com_genesis_collabcanvas_NativeLib_getVersion(JNIEnv *env, jobject /* this */) {\n    return env->NewStringUTF(\"CollabCanvas Native 1.0\");\n}\n")
    endif()
endforeach()

# Create shared library
add_library(collab-canvas-native SHARED ${NATIVE_SOURCES})

# Link libraries
target_link_libraries(collab-canvas-native
    ${log-lib}
    ${android-lib}
    ${jnigraphics-lib}
)

# Set library properties
set_target_properties(collab-canvas-native PROPERTIES
    VERSION 1.0
    SOVERSION 1
)
