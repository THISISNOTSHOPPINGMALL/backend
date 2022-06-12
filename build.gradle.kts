import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        mavenCentral()
    }
}

plugins {
    val kotlinVersion = "1.5.31"

    id("org.springframework.boot") version "2.5.5" apply false
    id("io.spring.dependency-management") version "1.0.11.RELEASE" apply false
    kotlin("jvm") version kotlinVersion apply false
    kotlin("plugin.spring") version kotlinVersion apply false
    kotlin("kapt") version kotlinVersion apply false
    kotlin("plugin.jpa") version kotlinVersion apply false
}

extra["slf4j.version"] = "1.7.20"

allprojects {
    group = "com.hindsight"
    version = "1.0-SNAPSHOT"
    val kotestVersion = "5.2.1"
    extra["slf4j.version"] = "1.7.20"
    extra["kotlin-coroutines.version"] = "1.6.0"

    apply {
        plugin("kotlin")
        plugin("org.springframework.boot")
        plugin("io.spring.dependency-management")
        plugin("org.jetbrains.kotlin.jvm")
        plugin("org.jetbrains.kotlin.plugin.spring")
    }

    repositories {
        mavenCentral()
    }

    dependencies {
        val implementation by configurations
        val testImplementation by configurations
        implementation("org.springframework.boot:spring-boot-starter-webflux")
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
        implementation("io.github.microutils:kotlin-logging:2.1.21")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("io.projectreactor:reactor-test")
        testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test")
        testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
        testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
        testImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.1")
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "11"
    }

    tasks.withType<Test>().configureEach {
        useJUnitPlatform()
    }
}
