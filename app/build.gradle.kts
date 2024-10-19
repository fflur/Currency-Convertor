plugins {
    application
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.11.2") //JUnit 5
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.11.2") //JUnit 5
    testImplementation("org.mockito:mockito-core:5.14.1") //Mockito

    implementation("com.github.rvesse:airline:3.0.0") //Airline
    implementation("org.jsoup:jsoup:1.18.1") //JSoup
}

// Apply a specific Java toolchain to ease working on different environments.
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

application {
    mainClass = "org.cli.UserInterface"
}

tasks.named<Test>("test") {
    useJUnitPlatform()
    testLogging {
        events("passed", "failed", "skipped")
    }
}
