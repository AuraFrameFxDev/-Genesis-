@file:Suppress("UnstableApiUsage")

// Genesis Protocol - Enable Gradle Features
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
enableFeaturePreview("STABLE_CONFIGURATION_CACHE")

pluginManagement {
    repositories {
        // 🩸 BLEEDING-EDGE: Alpha/Beta repositories for cutting-edge versions
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        gradlePluginPortal()
        mavenCentral()
        
        // 🩸 BLEEDING-EDGE: Alpha/Preview repositories
        maven("https://androidx.dev/storage/compose-compiler/repository/") {
            name = "AndroidX Compose Compiler Preview"
        }
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev") {
            name = "Jetbrains Compose Dev"
        }
        maven("https://jitpack.io") {
            name = "JitPack"
        }
        
        // 🩸 BLEEDING-EDGE: Enable snapshot repositories
        maven("https://oss.sonatype.org/content/repositories/snapshots/") {
            name = "Sonatype Snapshots"
        }
        maven("https://s01.oss.sonatype.org/content/repositories/snapshots/") {
            name = "Sonatype S01 Snapshots"
        }
    }
}

plugins {
    // Java Toolchain Auto-detect
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_PROJECT)
    repositories {
        // 🩸 BLEEDING-EDGE: Primary repositories with alpha/beta access
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        
        // 🩸 BLEEDING-EDGE: Compose and Kotlin bleeding-edge
        maven("https://androidx.dev/storage/compose-compiler/repository/") {
            name = "AndroidX Compose Compiler Preview"
        }
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev") {
            name = "Jetbrains Compose Dev"
        }
        maven("https://jitpack.io") {
            name = "JitPack"
        }
        
        // 🩸 BLEEDING-EDGE: Snapshot repositories for latest builds
        maven("https://oss.sonatype.org/content/repositories/snapshots/") {
            name = "Sonatype Snapshots"
        }
        maven("https://s01.oss.sonatype.org/content/repositories/snapshots/") {
            name = "Sonatype S01 Snapshots"
        }
        
        // Genesis Protocol - AI Backend Dependencies
        maven("https://repo1.maven.org/maven2/") {
            name = "Maven Central Mirror"
        }
    }
}

// Genesis Protocol - Project Configuration
rootProject.name = "Genesis-Os"

// Genesis Protocol - Core Modules
include(":app")

// Genesis Protocol - AI Ecosystem Modules 
include(":core-module")
include(":feature-module") 
include(":datavein-oracle-native")
include(":oracle-drive-integration")
include(":secure-comm")
include(":sandbox-ui")
include(":collab-canvas")
include(":colorblendr")
include("romtools")
