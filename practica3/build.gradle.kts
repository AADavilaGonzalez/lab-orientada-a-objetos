plugins {
    id("application")
}

sourceSets {
    main {
        java { setSrcDirs(listOf("src/main")) }
    }
    test {
        java { setSrcDirs(listOf("src/test")) }
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":utils"))
    testImplementation(platform("org.junit:junit-bom:5.13.4"))
	testImplementation("org.junit.jupiter:junit-jupiter")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

application {
    mainClass.set("Main")
}
