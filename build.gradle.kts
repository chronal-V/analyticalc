import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.21"
    application
    antlr
}

group = "family.haschka"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val kotest_version = "5.3.0"

dependencies {
    antlr("org.antlr:antlr4:4.9.2")

    testImplementation("io.kotest:kotest-runner-junit5:$kotest_version")
    testImplementation("io.kotest:kotest-assertions-core:$kotest_version")
    testImplementation("io.kotest:kotest-framework-datatest:$kotest_version")
}

tasks {
    generateGrammarSource {

        outputDirectory = project.layout.buildDirectory.file("generated-src/antlr/main/family/haschka/analyticalc").get().asFile
        arguments = arguments + listOf(
            "-visitor",
            "-long-messages",
            "-package", "family.haschka.analyticalc"
        )
    }

    withType<Test>().configureEach {
        useJUnitPlatform()
    }

    withType<KotlinCompile>() {
        dependsOn(generateGrammarSource)
    }
}

application {
    mainClass.set("MainKt")
}