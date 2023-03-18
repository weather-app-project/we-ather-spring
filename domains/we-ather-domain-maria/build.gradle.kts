plugins {
    kotlin("kapt")
    kotlin("plugin.jpa")
}

noArg {
    annotation("jakarta.persistence.Entity")
}

allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.mariadb.jdbc:mariadb-java-client")
    implementation("com.github.f4b6a3:ulid-creator:${property("ulidCreatorVersion")}")
}

kotlin.sourceSets.main {
    setBuildDir("$buildDir")
}