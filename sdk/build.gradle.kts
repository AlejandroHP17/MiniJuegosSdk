plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.compose)
    `maven-publish`
}

val githubOwner = "AlejandroHP17"
val githubRepo = "MiniJuegosSdk"
val publishVersion =
    providers.environmentVariable("PUBLISH_VERSION").orElse("1.0.0").get()

android {
    namespace = "pelkidev.com.mx.minijuegos.sdk"
    compileSdk {
        version = release(36) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        minSdk = 29
        consumerProguardFiles("consumer-rules.pro")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildFeatures {
        compose = true
    }

    publishing {
        singleVariant("release") {
            withSourcesJar()
        }
    }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.runtime)
}

publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = "pelkidev.com.mx.minijuegos"
            artifactId = "sdk"
            version = publishVersion

            afterEvaluate {
                from(components["release"])
            }
        }
    }
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/$githubOwner/$githubRepo")
            credentials {
                username = providers.gradleProperty("gpr.user")
                    .orElse(providers.environmentVariable("GITHUB_ACTOR"))
                    .orElse(githubOwner)
                    .get()
                password = providers.gradleProperty("gpr.token")
                    .orElse(providers.environmentVariable("GH_PACKAGES_TOKEN"))
                    .orElse(providers.environmentVariable("GITHUB_TOKEN"))
                    .orElse("")
                    .get()
            }
        }
    }
}
