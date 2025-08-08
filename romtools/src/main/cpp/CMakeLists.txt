cmake_minimum_required(VERSION 3.22.1)

# ROM Tools Native Library
project("romtools-native")

# Set C++ standard
set(CMAKE_CXX_STANDARD 20)
set(CMAKE_CXX_STANDARD_REQUIRED ON)

# ROM Tools native library
add_library(romtools-native SHARED
    romtools_native.cpp
    # Add more native source files here when ready
    # rom_analyzer.cpp
    # boot_img_parser.cpp
    # partition_manager.cpp
)

# Include directories
target_include_directories(romtools-native PRIVATE
    ${CMAKE_CURRENT_SOURCE_DIR}
)

# Link libraries
target_link_libraries(romtools-native
    android
    log
)

# Compiler definitions
target_compile_definitions(romtools-native PRIVATE
    ROM_TOOLS_BUILD=1
    ANDROID_PLATFORM=${ANDROID_PLATFORM}
)
