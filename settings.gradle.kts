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
    }
}

rootProject.name = "Shok"
include(":app")
include(":data")
include(":domain")
include(":features")
include(":data:user")
include(":data:notifications")
include(":data:auth")
include(":domain:user")
include(":domain:notifications")
include(":domain:auth")
include(":features:user")
include(":features:notifications")
include(":features:auth")
include(":core:error")
include(":core:settings")
