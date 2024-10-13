plugins {
    application
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.11.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.11.2")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher:1.7.0")
    testImplementation("org.mockito:mockito-core:5.14.1") //Mockito dependency.
    implementation("org.codehaus.jackson:jackson-core-asl:1.9.13") //Jackson dependency.
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
        events("passed")
    }
}
