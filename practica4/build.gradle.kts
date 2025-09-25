plugins {
    application
}

sourceSets {
    main {
        java { setSrcDirs(listOf("src/main")) }
    }
    test {
        java { setSrcDirs(listOf("src/test")) }
    }
}


dependencies {
    implementation(project(":utils"))
}

application {
    mainClass = "Main"
}
