import org.gradle.api.artifacts.ExternalModuleDependency
import org.gradle.kotlin.dsl.exclude

object Versions {
    const val min_sdk = 21
    const val target_sdk = 29
    const val compile_sdk = 29

    const val kotlin_version = "1.3.61"
    const val android_x = "1.1.0"
    const val android_x_junit5 = "1.2.0"
    const val build_tools_version = "29.0.0"
    const val sql_delight = "1.2.1"
    const val ktor = "1.2.6"
    const val stately = "0.9.5"
    const val multiplatform_settings = "0.5"
    const val coroutines = "1.3.3-native-mt"
    const val koin = "3.0.1-khan-SNAPSHOT"
    const val junit5 = "5.6.0"
    const val junit5_vintage = "5.6.0"
}

object Deps {
    const val junit_jupiter_api = "org.junit.jupiter:junit-jupiter-api:${Versions.junit5}"
    const val junit_jupiter_engine = "org.junit.jupiter:junit-jupiter-engine:${Versions.junit5}"
    const val junit_jupiter_params = "org.junit.jupiter:junit-jupiter-params:${Versions.junit5}"
    const val junit_vintage_engine = "org.junit.vintage:junit-vintage-engine:${Versions.junit5_vintage}"
    const val app_compat_x = "androidx.appcompat:appcompat:${Versions.android_x}"
    const val core_ktx = "androidx.core:core-ktx:${Versions.android_x}"
    const val constraint_layout = "androidx.constraintlayout:constraintlayout:${Versions.android_x}"
    const val stately = "co.touchlab:stately:${Versions.stately}"
    const val multiplatform_settings = "com.russhwolf:multiplatform-settings:${Versions.multiplatform_settings}"
    const val multiplatform_settings_test = "com.russhwolf:multiplatform-settings-test:${Versions.multiplatform_settings}"
    const val koin_core = "co.touchlab:koin-core:${Versions.koin}"

    object AndroidXTest {
        const val core = "androidx.test:core:${Versions.android_x}"
        const val core_junit5 = "de.mannodermaus.junit5:android-test-core:${Versions.android_x_junit5}"
        const val junit = "androidx.test.ext:junit:${Versions.android_x}"
        const val runner = "androidx.test:runner:${Versions.android_x}"
        const val runner_junit5 = "de.mannodermaus.junit5:android-test-runner:${Versions.android_x_junit5}"
        // const val rules = "androidx.test:rules:${Versions.android_x}"
    }

    object KotlinTest {
        const val common = "org.jetbrains.kotlin:kotlin-test-common:${Versions.kotlin_version}"
        const val annotations = "org.jetbrains.kotlin:kotlin-test-annotations-common:${Versions.kotlin_version}"
        const val jvm = "org.jetbrains.kotlin:kotlin-test:${Versions.kotlin_version}"
        const val junit = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlin_version}"
        const val reflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin_version}"
    }

    object Coroutines {
        const val common = "org.jetbrains.kotlinx:kotlinx-coroutines-core-common:${Versions.coroutines}"
        const val jdk = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
        const val native = "org.jetbrains.kotlinx:kotlinx-coroutines-core-native:${Versions.coroutines}"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
    }

    object SqlDelight {
        const val runtime = "com.squareup.sqldelight:runtime:${Versions.sql_delight}"
        const val runtime_jdk = "com.squareup.sqldelight:runtime-jvm:${Versions.sql_delight}"
        const val driver_ios = "com.squareup.sqldelight:ios-driver:${Versions.sql_delight}"
        const val driver_android = "com.squareup.sqldelight:android-driver:${Versions.sql_delight}"
    }

    object Ktor {
        const val common_core = "io.ktor:ktor-client-core:${Versions.ktor}"
        const val common_json = "io.ktor:ktor-client-json:${Versions.ktor}"
        const val jvm_core = "io.ktor:ktor-client-core-jvm:${Versions.ktor}"
        const val android_core = "io.ktor:ktor-client-okhttp:${Versions.ktor}"
        const val jvm_json = "io.ktor:ktor-client-json-jvm:${Versions.ktor}"
        const val ios = "io.ktor:ktor-client-ios:${Versions.ktor}"
        const val ios_core = "io.ktor:ktor-client-core-native:${Versions.ktor}"
        const val ios_json = "io.ktor:ktor-client-json-native:${Versions.ktor}"
        const val common_serialization = "io.ktor:ktor-client-serialization:${Versions.ktor}"
        const val android_serialization = "io.ktor:ktor-client-serialization-jvm:${Versions.ktor}"
        const val ios_serialization = "io.ktor:ktor-client-serialization-native:${Versions.ktor}"
    }

    val coroutinesExcludeNative: ExternalModuleDependency.() -> Unit = {
        exclude(group = "org.jetbrains.kotlinx", module = "kotlinx-coroutines-core-native")
    }
}
