pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/ThawaniMobile/Lamsa-SDK")
            credentials {
                username = "ThawaniMobile"
                password = "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"
            }
        }
    }
}

rootProject.name = "Lamsa Sample"
include(":app")
