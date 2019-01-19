import com.android.build.gradle.TestedExtension
import org.jetbrains.kotlin.gradle.internal.AndroidExtensionsExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import serg.chuprin.khub.AndroidProjectConfigurator

buildscript {
    repositories {
        jcenter()
        google()
        maven("http://dl.bintray.com/creati8e/maven")
    }
    dependencies {
        classpath(BuildScript.Plugins.realm)
        classpath(BuildScript.Plugins.kotlin)
        classpath(BuildScript.Plugins.android)
    }
}

allprojects {
    repositories {
        jcenter()
        google()
        maven("https://jitpack.io")
        maven("http://dl.bintray.com/creati8e/maven")
    }
}

subprojects {
    enableInlineClasses()
    forceDependencyVersions()
    afterEvaluate {
        extensions
            .findByType(TestedExtension::class.java)
            ?.apply {
                AndroidProjectConfigurator.configure(this)
                enableExperimentalKotlinExtensions()
            }
    }
}

// region Configuration extensions.

fun Project.enableExperimentalKotlinExtensions() {
    if (plugins.hasPlugin("kotlin-android-extensions")) {
        extensions.getByType(AndroidExtensionsExtension::class)
            .configure(delegateClosureOf<AndroidExtensionsExtension> {
                isExperimental = true
            })
    }
}

fun Project.forceDependencyVersions() {
    configurations
        .all {
            resolutionStrategy {
                force(Libraries.kotlin)
            }
        }
}

fun Project.enableInlineClasses() {
    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions {
            val args = kotlinOptions.freeCompilerArgs
            kotlinOptions.freeCompilerArgs = args + listOf("-XXLanguage:+InlineClasses")
        }
    }
}

tasks.register("clean", Delete::class.java) {
    delete(rootProject.buildDir)
}