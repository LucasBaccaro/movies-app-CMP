import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    kotlin("plugin.serialization") version "1.9.20"
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "11"
            }
        }
    }

    js(IR) {
        moduleName = "BaccaroLucas"
        browser() {

            //Tool bundler for converting kotlin code to js code
            commonWebpackConfig() {
                outputFileName = "BaccaroLucas.js"
                devServer = (devServer
                    ?: org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig.DevServer()).copy()
            }
            binaries.executable() //it will generate executable js files
        }
    }

    jvm("desktop")

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    sourceSets {
        val desktopMain by getting

        androidMain.dependencies {
            implementation(libs.compose.ui.tooling.preview)
            implementation(libs.androidx.activity.compose)
            api("androidx.activity:activity-compose:1.7.2")
            api("androidx.appcompat:appcompat:1.6.1")
            api("androidx.core:core-ktx:1.10.1")
            //ktor
            implementation("io.ktor:ktor-client-okhttp:2.3.3")
            //koin
            implementation("io.insert-koin:koin-android:3.5.0")
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.materialIconsExtended)
            implementation(compose.material3)
            implementation(compose.material)
            @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
            implementation(compose.components.resources)
            api(compose.animation)
            //ktor
            implementation("io.ktor:ktor-client-core:2.3.3")
            implementation("io.ktor:ktor-client-content-negotiation:2.3.3")
            implementation("io.ktor:ktor-client-logging:2.3.3")
            implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.3")
            //koin
            implementation("io.insert-koin:koin-compose:1.1.0")
            implementation("io.insert-koin:koin-core:3.5.0")
            //datetime
            implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.3.1")
            //images
            api("io.github.qdsfdhvh:image-loader:1.7.1")
            //navigation & viewmodels
            api("moe.tlaster:precompose:1.5.7")
            api("moe.tlaster:precompose-viewmodel:1.5.7")
            api("moe.tlaster:precompose-koin:1.5.7")
        }
        iosMain.dependencies {
            //ktor
            implementation("io.ktor:ktor-client-darwin:2.3.3")
        }
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
        }
    }
}

android {
    namespace = "baccaro.lucas.com"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = "baccaro.lucas.com"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    dependencies {
        debugImplementation(libs.compose.ui.tooling)
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "baccaro.lucas.com"
            packageVersion = "1.0.0"
        }
    }
}

compose.experimental {
    web.application {}
}

