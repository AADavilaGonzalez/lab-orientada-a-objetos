plugins {
    id("application")
}

sourceSets {
    main {
        java { setSrcDirs(listOf("src")) }
    }
}

dependencies {
    implementation(project(":utils"))
}

application {
    mainClass.set("Main")
}
