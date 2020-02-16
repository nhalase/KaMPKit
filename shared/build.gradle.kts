plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("kotlinx-serialization")
    id("com.android.library")
    id("com.squareup.sqldelight")
    id("co.touchlab.kotlinxcodesync")
    id("org.jmailen.kotlinter")
    id("de.mannodermaus.android-junit5")
}

android {
    compileSdkVersion(28)
    defaultConfig {
        minSdkVersion(Versions.min_sdk)
        targetSdkVersion(Versions.target_sdk)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunnerArgument("runnerBuilder", "de.mannodermaus.junit5.AndroidJUnit5Builder")
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    // JUnit 5 will bundle in files with identical paths; exclude them
    packagingOptions {
        exclude("META-INF/LICENSE*")
    }
}

kotlin {
    android()
    //Revert to just ios() when gradle plugin can properly resolve it
    val onPhone = System.getenv("SDK_NAME")?.startsWith("iphoneos") ?: false
    if (onPhone) {
        iosArm64("ios")
    } else {
        iosX64("ios")
    }

    version = "1.0"

    sourceSets["commonMain"].dependencies {
        implementation(kotlin("stdlib-common", Versions.kotlin_version))
        implementation(Deps.SqlDelight.runtime)
        implementation(Deps.Ktor.common_core)
        implementation(Deps.Ktor.common_json)
        implementation(Deps.Coroutines.common)
        implementation(Deps.stately)
        implementation(Deps.multiplatform_settings)
        implementation(Deps.koin_core)
        implementation(Deps.Ktor.common_serialization)

    }

    sourceSets["commonTest"].dependencies {
        implementation(Deps.multiplatform_settings_test)
        implementation(Deps.SqlDelight.runtime)
        implementation(Deps.KotlinTest.common)
        implementation(Deps.KotlinTest.annotations)
        implementation(Deps.Coroutines.jdk)
        implementation(Deps.Coroutines.common)
        implementation(Deps.Coroutines.test)
    }

    sourceSets["androidMain"].dependencies {
        implementation(kotlin("stdlib", Versions.kotlin_version))
        implementation(Deps.SqlDelight.driver_android)
        implementation(Deps.Ktor.jvm_core)
        implementation(Deps.Ktor.jvm_json)
        implementation(Deps.Coroutines.jdk)
        implementation(Deps.Coroutines.android)
        implementation(Deps.Ktor.android_serialization)
    }

    sourceSets["androidTest"].dependencies {
        implementation(Deps.AndroidXTest.runner)
        implementation(Deps.AndroidXTest.junit)
        implementation(Deps.junit_jupiter_api)
        implementation(Deps.AndroidXTest.core_junit5)
        implementation(Deps.KotlinTest.junit)
        implementation(Deps.KotlinTest.annotations)
        implementation(Deps.KotlinTest.reflect)
        runtimeOnly(Deps.junit_vintage_engine)
        runtimeOnly(Deps.AndroidXTest.runner_junit5)
        implementation("org.robolectric:robolectric:4.0")
    }

    sourceSets["iosMain"].dependencies {
        implementation(Deps.SqlDelight.driver_ios)
        implementation(Deps.Ktor.ios, Deps.coroutinesExcludeNative)
        implementation(Deps.Ktor.ios_core, Deps.coroutinesExcludeNative)
        implementation(Deps.Ktor.ios_json, Deps.coroutinesExcludeNative)
        implementation(Deps.Coroutines.native)
        implementation(Deps.Ktor.ios_serialization)
    }

    cocoapods {
        summary = "Common library for the KaMP starter kit"
        homepage = "https://github.com/touchlab/KaMPStarter"
    }

    xcodeSync {
        projectPath = "../ios/KaMPStarteriOS.xcodeproj"
        target = "KaMPStarteriOS"
    }
}

sqldelight {
    database("KampstarterDb") {
        packageName = "co.touchlab.kampstarter.db"
    }
}

val iOSTest: Task by tasks.creating {
    val device = project.findProperty("iosDevice")?.toString() ?: "iPhone 8"
    dependsOn("linkDebugTestIos")
    group = JavaBasePlugin.VERIFICATION_GROUP
    description = "Runs tests for target 'ios' on an iOS simulator"

    doLast {
        val binary =
            kotlin.targets.getByName<org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget>("ios").binaries.getTest(
                "DEBUG"
            ).outputFile
        exec {
            commandLine("xcrun", "simctl", "spawn", "--standalone", device, binary.absolutePath)
        }
    }
}
