rootProject.name = "we-ather"

include(
    "apps:we-ather-app-api",
    "we-ather-core",
    "tests:api-docs",
    "domains:we-ather-domain-maria",
    "modules:we-ather-yaml-importer",
    "clients:client-core",
    "clients:we-ather-client-openapi"
)

pluginManagement {
    val kotlinVersion: String by settings
    val springBootVersion: String by settings
    val springDependencyManagementVersion: String by settings
    val ktlintVersion: String by settings

    resolutionStrategy {
        eachPlugin {
            when (requested.id.id) {
                "org.jetbrains.kotlin.jvm" -> useVersion(kotlinVersion)
                "org.jetbrains.kotlin.plugin.spring" -> useVersion(kotlinVersion)
                "org.jetbrains.kotlin.kapt" -> useVersion(kotlinVersion)
                "org.jetbrains.kotlin.plugin.jpa" -> useVersion(kotlinVersion)
                "org.springframework.boot" -> useVersion(springBootVersion)
                "org.jlleitschuh.gradle.ktlint" -> useVersion(ktlintVersion)
                "io.spring.dependency-management" -> useVersion(springDependencyManagementVersion)
            }
        }
    }
}