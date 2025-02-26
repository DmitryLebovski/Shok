plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
    id("kotlin-kapt")
}
java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
    }
}
dependencies {
    implementation(project(":domain:user"))
    implementation(project(":domain:notifications"))
    implementation (libs.retrofit)
    implementation(libs.converter.gson)
    implementation(project(":core:error"))

    implementation(libs.dagger)
    implementation(project(":domain:auth"))
    implementation(project(":core:settings"))
    kapt(libs.dagger.compiler)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1")
}

kapt {
    correctErrorTypes = true
}

