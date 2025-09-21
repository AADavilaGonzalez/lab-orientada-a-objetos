plugins {
    application
}

java {
    sourceSets {
        named("main") {
            java.srcDirs("src")
        }
    }
}

dependencies {
    implementation(project(":utils"))
}

application {
    mainClass.set("Main")
}
