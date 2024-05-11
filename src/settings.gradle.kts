pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
         maven{
             url=uri("https://jitpack.io")
         }
        google()
        mavenCentral()

        //maven { url "https://jitpack.io" }

    }
}

rootProject.name = "Eatsy"
include(":app")
 