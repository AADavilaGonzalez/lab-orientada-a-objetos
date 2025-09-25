plugins {
    id("application")
}

sourceSets {
    main {
        java { setSrcDirs(listOf("src")) }
    }
}

application {
    mainClass.set("HolaMundoAD")
}
