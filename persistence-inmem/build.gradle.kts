plugins {
    kotlin("jvm") version "2.0.21"
    id("com.diffplug.spotless") version "6.25.0"
}

apply(plugin = "java")
apply(plugin = "kotlin")

group = "com.github.ytake.persistence"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    val pekkoVersion = "1.1.2"
    implementation("org.apache.pekko:pekko-actor-typed_2.13:$pekkoVersion")
    implementation("org.apache.pekko:pekko-persistence-typed_2.13:$pekkoVersion")
    implementation("ch.qos.logback:logback-classic:1.5.8")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.14.3")

    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.junit.vintage:junit-vintage-engine")
    testImplementation(kotlin("test"))
    testImplementation("org.apache.pekko:pekko-serialization-jackson_2.13:$pekkoVersion")
    testImplementation("org.apache.pekko:pekko-actor-testkit-typed_2.13:$pekkoVersion")

}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

kotlin {
    jvmToolchain(15)
}

spotless {
    java {
        googleJavaFormat()
    }
    kotlin {
        target("**/*.kt")
        ktlint()
            .editorConfigOverride(mapOf(
                "experimental" to "true",
                "indent_size" to "2",
                "trim_trailing_whitespace" to "true",
                "max_line_length" to "120"
            ))
        trimTrailingWhitespace()
        endWithNewline()
    }
}