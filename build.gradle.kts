plugins {
    kotlin("jvm") version "1.6.21"
    application
}

group = "family.haschka"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val kotest_version = "5.3.0"

dependencies {
    testImplementation("io.kotest:kotest-runner-junit5:$kotest_version")
    testImplementation("io.kotest:kotest-assertions-core:$kotest_version")
    testImplementation("io.kotest:kotest-framework-datatest:$kotest_version")
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

application {
    mainClass.set("MainKt")
}