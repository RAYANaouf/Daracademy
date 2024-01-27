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
        google()
        mavenCentral()

        maven("https://s01.oss.sonatype.org/content/repositories/snapshots")  // build.gradle.kts


    }
}

rootProject.name = "Daracademy"
include(":app")
 