import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    kotlin("kapt") apply false
    kotlin("plugin.jpa") apply false
    kotlin("plugin.spring") apply false
    id("org.springframework.boot") apply false
    id("org.jlleitschuh.gradle.ktlint") apply false
    id("io.spring.dependency-management")
}

java.sourceCompatibility = JavaVersion.VERSION_17
java.targetCompatibility = JavaVersion.VERSION_17

allprojects {
    group = "${property("projectGroup")}"
    version = "${property("applicationVersion")}"

    repositories {
        mavenCentral()
        maven("https://jitpack.io")
    }
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.jlleitschuh.gradle.ktlint")

    dependencies {
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("io.github.microutils:kotlin-logging-jvm:${property("kotlinLoggingVersion")}")
        testImplementation("io.kotest:kotest-assertions-core:${property("kotlinTestVersion")}")
        testImplementation("io.kotest:kotest-property:${property("kotlinTestVersion")}")
        testImplementation("io.kotest:kotest-runner-junit5:${property("kotlinTestVersion")}")
        testImplementation("io.kotest.extensions:kotest-extensions-spring:${property("kotlinTestExtensionVersion")}")
        testImplementation("io.mockk:mockk:${property("mockkVersion")}")
        testImplementation("org.springframework.boot:spring-boot-starter-test") {
            exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
        }
        annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    }

    configurations {
        compileOnly {
            extendsFrom(configurations.annotationProcessor.get())
        }
    }

    configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
        verbose.set(true)
        disabledRules.set(
            setOf(
                "import-ordering",
                "no-wildcard-imports",
                "final-newline",
                "insert_final_newline",
                "max_line_length"
            )
        )
    }

    tasks.getByName("bootJar") {
        enabled = false
    }


    tasks.getByName("jar") {
        enabled = true
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "17"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

configure(subprojects.filter { project -> project.name != "we-ather-core" }) {
    dependencies {
        implementation(project(":we-ather-core"))
    }
}

configure(subprojects.filter { project -> project.name.contains(Regex("we-ather-client-*")) }) {
    dependencies {
        implementation(project(":clients:client-core"))
    }
}