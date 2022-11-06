plugins {
    kotlin("jvm") version "1.7.20"
    id("io.gitlab.arturbosch.detekt") version "1.21.0"
}

group = "ad.kata"
version = "1.0"

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    // JUnit 5
    testImplementation(platform("org.junit:junit-bom:5.9.0"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.0")
    // AssertJ
    testImplementation("org.assertj:assertj-core:3.23.1")
}

sourceSets.main { kotlin.srcDirs("src/") }
sourceSets.test { kotlin.srcDirs("test/") }

/* Detekt */
detekt {
    source = files("src/", "test/")
    config = files("detekt.yml")
}

/* Check with JUnit 5 and jqwik */
tasks.test {
    useJUnitPlatform {
        includeEngines("junit-jupiter")
        excludeEngines("junit-vintage")
    }
}

/* Gradle wrapper */
tasks.withType<Wrapper> {
    gradleVersion = "7.5.1"
    distributionType = Wrapper.DistributionType.BIN
}