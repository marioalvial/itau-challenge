val ktorVersion: String by project
val koinVersion: String by project

plugins {
    application
    id("jacoco")
    kotlin("jvm") version "1.4.10"
    id("com.github.johnrengelman.shadow") version "5.2.0"
    id("io.gitlab.arturbosch.detekt") version "1.14.2"
    id("org.jmailen.kotlinter") version "2.1.2"
    id("com.adarshr.test-logger") version "2.0.0"
}

repositories {
    jcenter()
}

dependencies {
    kotlin("bom")
    kotlin("stlib-jdk8")

    // Ktor
    implementation("io.ktor:ktor-server-core:$ktorVersion")
    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("io.ktor:ktor-jackson:$ktorVersion")

    // Dependency Injection
    implementation("org.koin:koin-ktor:$koinVersion")
    testImplementation("org.koin:koin-test:$koinVersion")

    // Logger
    api("ch.qos.logback:logback-classic:1.2.3")
    implementation("net.logstash.logback:logstash-logback-encoder:5.2")

    // Validation
    implementation("org.glassfish:javax.el:3.0.1-b11")
    implementation("org.hibernate:hibernate-validator:6.1.2.Final")

    // Serialization
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.10.3")

    // Mock Framework
    testImplementation("io.mockk:mockk:1.10.2")

    // HTTP Client
    testImplementation("com.github.kittinunf.fuel:fuel:2.3.0")

    // Assert Framework
    testImplementation("org.assertj:assertj-core:3.15.0")
    testImplementation("net.javacrumbs.json-unit:json-unit:2.21.0")
    testImplementation("net.javacrumbs.json-unit:json-unit-assertj:2.21.0")

    //Test Engine
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.6.1")
}

application {
    mainClassName = "itau.challenge.ApplicationKt"
}

tasks {
    assemble { dependsOn(shadowJar) }
    assemble { dependsOn(formatKotlin) }

    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "11"
        }
    }

    test {
        useJUnitPlatform()

        System.getProperty("test.type")?.let {
            if (it == "unit") exclude("**/*integration*")
            if (it == "integration") exclude("**/*unit*")
        }
    }

    detekt {
        failFast = true
        buildUponDefaultConfig = true
        input = files("src/main/kotlin", "src/test/kotlin")
        config = files("$projectDir/detekt/config.yml")

        reports {
            xml.enabled = true
            html.enabled = false
            txt.enabled = false
        }
    }

    jacocoTestReport {
        reports {
            xml.isEnabled = true
            csv.isEnabled = false
        }
    }

    testlogger {
        showStackTraces = false
        showCauses = false
        showSimpleNames = true
        showExceptions = true
        showPassed = false
        showStandardStreams = false
    }
}
