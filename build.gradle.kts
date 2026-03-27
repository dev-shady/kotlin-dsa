
plugins {
    // Downgraded to 1.9.24 as requested
    kotlin("jvm") version "1.9.24"
    application
}

repositories {
    mavenCentral()
}

application {
    // Note the .set() syntax in Kotlin DSL
    mainClass.set("HelloKt") 
}

dependencies {
    implementation(kotlin("stdlib"))

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.2")
}

tasks.test {
    useJUnitPlatform()
}

sourceSets {
    main {
        kotlin.setSrcDirs(listOf("lang", "dsa"))
    }

    test {
        kotlin.setSrcDirs(listOf("test"))
    }
}
