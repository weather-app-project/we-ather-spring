tasks.getByName("bootJar") {
    enabled = true
}

tasks.getByName("jar") {
    enabled = false
}

dependencies {
    implementation(project(":clients:we-ather-client-openapi"))
    implementation(project(":domains:we-ather-domain-maria"))
//    implementation(project(":supports:yaml-importer"))
    implementation(project(":tests:api-docs"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("io.jsonwebtoken:jjwt-api:${property("jwtVersion")}")
    implementation("io.jsonwebtoken:jjwt-impl:${property("jwtVersion")}")
    implementation("io.jsonwebtoken:jjwt-jackson:${property("jwtVersion")}")
}