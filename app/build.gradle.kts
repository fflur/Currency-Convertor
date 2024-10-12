plugins {
    // Apply the application plugin to add support for building a CLI application in Java.
    application
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.11.2")
    // This dependency is used by the application.
    implementation(libs.guava)
    implementation("org.codehaus.jackson:jackson-core-asl:1.9.13") //Jackson dependency.
}

// Apply a specific Java toolchain to ease working on different environments.
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

application {
    // Define the main class for the application.
    mainClass = "org.UserInterface"
}
