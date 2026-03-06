plugins {
    kotlin("jvm") version "2.3.10"
    id("io.ktor.plugin") version "3.4.0"
    kotlin("plugin.serialization") version "2.3.10"
}

group = "dom.cifra"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Servidor y Motor
    implementation("io.ktor:ktor-server-core-jvm")
    implementation("io.ktor:ktor-server-netty-jvm")
    implementation("io.ktor:ktor-server-auth-jvm")
    implementation("io.ktor:ktor-server-config-yaml-jvm")

    // Serialización JSON
    implementation("io.ktor:ktor-server-content-negotiation-jvm")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm")

    // Logging
    implementation("io.ktor:ktor-server-call-logging-jvm")
    implementation("ch.qos.logback:logback-classic:1.5.13")

    //Motor Exposed
    implementation("org.jetbrains.exposed:exposed-core:0.59.0")
    implementation("org.jetbrains.exposed:exposed-dao:0.59.0")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.59.0")

    //Driver Postgres
    implementation("org.postgresql:postgresql:42.7.2")

    //Gestión de pool de conexiones
    implementation("com.zaxxer:HikariCP:5.1.0")

    //DI
    implementation("io.insert-koin:koin-ktor:4.1.1")
    implementation("io.insert-koin:koin-logger-slf4j:4.1.1")

    //Security PASETO Support
    implementation("io.github.nbaars:paseto4j-version4:2024.3")

    // Testing
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
}

kotlin {
    jvmToolchain(17)
}

tasks.test {
    useJUnitPlatform()
}
