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

application {
    mainClass.set("HolaMundoAD")
}
