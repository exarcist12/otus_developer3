rootProject.name = "otus-develop33"

pluginManagement {
    val jgitver: String by settings
    val dependencyManagement: String by settings
    val springframeworkBoot: String by settings
    val johnrengelmanShadow: String by settings
    val jib: String by settings
    val protobufVer: String by settings
    val sonarlint: String by settings
    val spotless: String by settings

    plugins {
        id("fr.brouillard.oss.gradle.jgitver") version jgitver
        id("io.spring.dependency-management") version dependencyManagement
        id("org.springframework.boot") version springframeworkBoot
        id("com.github.johnrengelman.shadow") version johnrengelmanShadow
        id("com.google.cloud.tools.jib") version jib
        id("com.google.protobuf") version protobufVer
        id("name.remal.sonarlint") version sonarlint
        id("com.diffplug.spotless") version spotless
    }
}
include("hw-jdbs18")
include("hw-jdbs18:demo")
findProject(":hw-jdbs18:demo")?.name = "demo"
include("hw-jdbs18:docker")
findProject(":hw-jdbs18:docker")?.name = "docker"
include("hw-jdbs18:homework")
findProject(":hw-jdbs18:homework")?.name = "homework"
include("hw-jdbs18")
include("hw-jdbs18:homework2")
findProject(":hw-jdbs18:homework2")?.name = "homework2"
include("hw-jdbs18:homework")
findProject(":hw-jdbs18:homework")?.name = "homework"
