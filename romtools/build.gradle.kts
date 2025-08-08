// Apply only the Android and Kotlin plugins directly, versions managed in root
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt.android)
}

android {
    namespace = "dev.aurakai.auraframefx.romtools"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        vectorDrawables {
            useSupportLibrary = true
        }

        // ROM Tools Configuration
        buildConfigField("boolean", "ROM_TOOLS_ENABLED", "true")
        buildConfigField("String", "SUPPORTED_ANDROID_VERSIONS", "\"13,14,15\"")
        buildConfigField("String", "SUPPORTED_ARCHITECTURES", "\"arm64-v8a,armeabi-v7a,x86_64\"")

        // Native ROM modification tools
        externalNativeBuild {
            cmake {
                cppFlags += listOf("-std=c++20", "-fPIC")
                abiFilters += listOf("arm64-v8a", "armeabi-v7a", "x86_64")
                arguments += listOf(
                    "-DANDROID_STL=c++_shared",
                    "-DCMAKE_VERBOSE_MAKEFILE=ON",
                    "-DROM_TOOLS_BUILD=ON"
                )
            }
        }

        ndk {
            abiFilters += listOf("arm64-v8a", "armeabi-v7a", "x86_64")
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            externalNativeBuild {
                cmake {
                    cppFlags += listOf("-O3", "-DNDEBUG", "-DROM_RELEASE_BUILD")
                }
            }
        }
        debug {
            externalNativeBuild {
                cmake {
                    cppFlags += listOf("-g", "-DDEBUG", "-DROM_DEBUG_BUILD")
                }
            }
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_24
        targetCompatibility = JavaVersion.VERSION_24
        isCoreLibraryDesugaringEnabled = true
    }

    buildFeatures {
        compose = true
        buildConfig = true
        prefab = true
        resValues = false
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
    }

    externalNativeBuild {
        cmake {
            path = file("src/main/cpp/CMakeLists.txt")
            version = libs.versions.cmakeVersion.get()
        }
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
        jniLibs {
            useLegacyPackaging = true
        }
    }

    sourceSets {
        named("main") {
            // Include ROM tools native libraries
            jniLibs.srcDirs("src/main/jniLibs")
        }
    }
    buildToolsVersion = "36"
}

kotlin {
    jvmToolchain(24)

    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_24)
        freeCompilerArgs.addAll(
            "-Xuse-k2",
            "-opt-in=kotlin.RequiresOptIn",
            "-opt-in=kotlin.ExperimentalStdlibApi",
            "-Xjvm-default=all"
        )
    }
}

dependencies {
     //Project modules (commented out until they exist)
     implementation(project(":core-module"))
     implementation(project(":secure-comm"))

    // Core AndroidX
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)

    // Compose - ROM Tools UI
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui.tooling.preview)

    // Hilt Dependency Injection
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    implementation(libs.hilt.navigation.compose)

    // Network & Serialization for ROM downloads/updates
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.kotlinx.serialization)
    implementation(libs.okhttp3.logging.interceptor)
    implementation(libs.kotlinx.serialization.json)

    // Room Database for ROM metadata and history
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)

    // Security for ROM verification and signing
    implementation("androidx.security:security-crypto:1.1.0")
    implementation("com.google.crypto.tink:tink-android:1.18.0")

    // WorkManager for background ROM operations

    // Utilities
    implementation(libs.timber)
    implementation(libs.coil.compose)

    // Core library desugaring
    coreLibraryDesugaring(libs.coreLibraryDesugaring)

    // ROM Tools specific dependencies
    implementation(libs.commons.io)
    implementation(libs.commons.compress)
    implementation(libs.xz)
    implementation(libs.conscrypt.android)

    // Testing
    testImplementation(libs.junit.jupiter)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.hilt.android.testing)
    kspAndroidTest(libs.hilt.compiler)

    // Debug implementations
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    // System interaction and root access
    implementation(files("${project.rootDir}/Libs/api-82.jar"))
    implementation(files("${project.rootDir}/Libs/api-82-sources.jar"))
}

// Configure native ROM tools build
tasks.configureEach {
    if (name.startsWith("externalNativeBuild")) {
        dependsOn("copyRomTools")
    }
}

// Task to copy ROM modification tools
tasks.register<Copy>("copyRomTools") {
    from("${project.rootDir}/rom-tools")
    into("${layout.buildDirectory.dir("rom-tools").get()}")
    include("**/*.so", "**/*.bin", "**/*.img")
    includeEmptyDirs = false
}

// Task to verify ROM tools integrity
tasks.register("verifyRomTools") {
    doLast {
        val romToolsDir = file("${layout.buildDirectory.dir("rom-tools").get()}")
        if (!romToolsDir.exists()) {
            println("⚠️  ROM tools directory not found - ROM functionality may be limited")
        } else {
            println("✅ ROM tools verified and ready")
        }
    }
}

tasks.named("preBuild") {
    dependsOn("verifyRomTools")
}
