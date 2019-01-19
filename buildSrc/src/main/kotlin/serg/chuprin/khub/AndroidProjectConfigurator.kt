package serg.chuprin.khub

import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.TestedExtension
import com.android.build.gradle.internal.dsl.BuildType
import org.gradle.api.JavaVersion
import org.gradle.kotlin.dsl.get
import java.io.File

/**
 * @author Sergey Chuprin
 */
object AndroidProjectConfigurator {

    fun configure(androidExtension: TestedExtension) {

        with(androidExtension) {

            defaultConfig {

                minSdkVersion(21)
                targetSdkVersion(28)
                versionCode = 1

                // Set versionName from config file.
                versionName = "1.0.0"
            }

            configureBuildTypes(androidExtension)

            compileSdkVersion(28)
            buildToolsVersion("28.0.3")

            sourceSets["main"].java.srcDir("src/main/kotlin")

            with(compileOptions) {
                sourceCompatibility = JavaVersion.VERSION_1_8
                targetCompatibility = JavaVersion.VERSION_1_8
            }
        }
    }

    // Set build types for android module.
    private fun configureBuildTypes(androidExtension: TestedExtension) {

        fun BuildType.configProguard(isLibrary: Boolean): BuildType {
            return if (isLibrary) {
                consumerProguardFile(File("proguard-rules.pro"))
            } else {
                proguardFiles(
                    androidExtension.getDefaultProguardFile("proguard-android.txt"),
                    File("proguard-rules.pro")
                )
            }
        }

        with(androidExtension) {

            val isLibrary = androidExtension is LibraryExtension

            buildTypes {
                maybeCreate("release").apply {
                    isDebuggable = false
                    isMinifyEnabled = true
                    configProguard(isLibrary)
                }
                maybeCreate("debug").apply {
                    isDebuggable = true
                    isMinifyEnabled = true
                    configProguard(isLibrary)
                }
                maybeCreate("dev").apply {
                    isDebuggable = true
                    signingConfig = signingConfigs.getByName("debug")
                }
            }
        }
    }

}