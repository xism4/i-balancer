plugins {
    id("java")
    id("io.github.goooler.shadow") version "8.1.8" // Support Java 21
}

group = "es.xism4.software"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("com.fazecast:jSerialComm:2.11.0")
}

tasks.test {
    useJUnitPlatform()
}

val targetJavaVersion = 17

java {
    val javaVersion = JavaVersion.toVersion(targetJavaVersion)
    sourceCompatibility = javaVersion
    targetCompatibility = javaVersion
    if (JavaVersion.current() < javaVersion) {
        toolchain.languageVersion.set(JavaLanguageVersion.of(targetJavaVersion))
    }
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"

    if (targetJavaVersion >= 10 || JavaVersion.current().isJava10Compatible) {
        options.release.set(targetJavaVersion)
    }
}

tasks.withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar> {
    archiveBaseName.set("i-balancer")
    archiveVersion.set("0.1")
    archiveClassifier.set("all")
    mergeServiceFiles()

    relocate("com.fazecast", "es.xism4.software.libs")
    relocate("org.junit", "es.xism4.software.libs")
}