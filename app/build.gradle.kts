plugins {
    application
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.11.2") //JUnit 5
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.11.2") //JUnit 5
    testImplementation("org.mockito:mockito-core:5.14.1") //Mockito
    implementation("xom:xom:1.3.9") //XOM
}

// Apply a specific Java toolchain to ease working on different environments.
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

application {
    mainClass = "org.UserInterface"
}

tasks.named<Test>("test") {
    useJUnitPlatform()
    testLogging {
        events("passed", "failed", "skipped")
    }
}
