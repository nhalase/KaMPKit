import org.jetbrains.kotlin.config.KotlinCompilerVersion

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    id("org.jmailen.kotlinter")
    id("de.mannodermaus.android-junit5")
}

android {
    compileSdkVersion(Versions.compile_sdk)
    buildToolsVersion = Versions.build_tools_version
    defaultConfig {
        applicationId = "co.touchlab.kampstarter"
        minSdkVersion(Versions.min_sdk)
        targetSdkVersion(Versions.target_sdk)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunnerArgument("runnerBuilder", "de.mannodermaus.junit5.AndroidJUnit5Builder")
    }
    packagingOptions {
        exclude("META-INF/*.kotlin_module")
        exclude("META-INF/LICENSE*")
    }
    buildTypes {
        getByName("release")  {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation(kotlin("stdlib-jdk8", KotlinCompilerVersion.VERSION))
    implementation(project(":shared"))
    implementation("androidx.recyclerview:recyclerview:1.1.0")
    implementation(Deps.app_compat_x)
    implementation(Deps.core_ktx)
    implementation(Deps.Ktor.android_core)
    implementation(Deps.constraint_layout)
    implementation(Deps.SqlDelight.runtime_jdk)
    implementation(Deps.SqlDelight.driver_android)
    implementation(Deps.Coroutines.jdk)
    implementation(Deps.Coroutines.android)
    implementation(Deps.multiplatform_settings)
    implementation(Deps.koin_core)
    testImplementation(Deps.AndroidXTest.runner)
    testImplementation(Deps.AndroidXTest.junit)
    testImplementation(Deps.junit_jupiter_api)
    testImplementation(Deps.AndroidXTest.core_junit5)
    testImplementation(Deps.KotlinTest.junit)
    testImplementation(Deps.KotlinTest.annotations)
    testImplementation(Deps.KotlinTest.reflect)
    androidTestRuntimeOnly(Deps.junit_vintage_engine)
    androidTestRuntimeOnly(Deps.AndroidXTest.runner_junit5)
}
