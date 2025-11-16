import org.gradle.jvm.tasks.Jar

plugins {
    id("application")
    id("org.openjfx.javafxplugin") version "0.1.0"
}

javafx{
    version = "21.0.2"
    modules = listOf("javafx.controls", "javafx.fxml")
}

sourceSets {
    main {
        java {
            setSrcDirs(listOf("src/main"))
        }
        resources {
            srcDirs(listOf("src/recursos_davila"))
        }
    }
}

dependencies {
    implementation(project(":utils"))
}

application {
    mainClass.set("Main")
}

tasks.named<org.gradle.jvm.tasks.Jar>("jar") {
    manifest {
        attributes["Main-Class"] = "Main"
    }
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    from({
        configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
    })
}

