// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript{
    val kotlin_version by extra("1.9.20")
    dependencies{
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.5")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
    }
    repositories {
        mavenCentral()
    }
}
plugins {
    id("com.android.application") version "8.1.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
}