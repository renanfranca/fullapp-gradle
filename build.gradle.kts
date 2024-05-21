import com.github.gradle.node.npm.task.NpmTask
// jhipster-needle-gradle-imports

plugins {
  java
  checkstyle
  alias(libs.plugins.protobuf)
  jacoco
  alias(libs.plugins.spring.boot)
  alias(libs.plugins.sonarqube)
  alias(libs.plugins.jib)
  alias(libs.plugins.git.properties)
  alias(libs.plugins.node.gradle)
  // jhipster-needle-gradle-plugins
}

val nodeVersionValue by extra("20.13.1")
val npmVersionValue by extra("10.8.0")
val springProfilesActive by extra("")
// jhipster-needle-gradle-properties

java {
  toolchain {
    languageVersion = JavaLanguageVersion.of(21)
  }
}

checkstyle {
  configFile = rootProject.file("checkstyle.xml")
  toolVersion = libs.versions.checkstyle.get()
}


protobuf {
  protoc {
    artifact = "com.google.protobuf:protoc:${libs.versions.protobuf.asProvider().get()}"
  }
}


jacoco {
  toolVersion = libs.versions.jacoco.get()
}

tasks.jacocoTestReport {
  dependsOn("test", "integrationTest")
  reports {
    xml.required.set(true)
    html.required.set(true)
  }
  executionData.setFrom(fileTree(layout.buildDirectory).include("**/jacoco/test.exec", "**/jacoco/integrationTest.exec"))
}

tasks.jacocoTestCoverageVerification {
  dependsOn("jacocoTestReport")
  violationRules {

      rule {
          element = "CLASS"

          limit {
              counter = "LINE"
              value = "COVEREDRATIO"
              minimum = "1.00".toBigDecimal()
          }

          limit {
              counter = "BRANCH"
              value = "COVEREDRATIO"
              minimum = "1.00".toBigDecimal()
          }
      }
  }
  executionData.setFrom(fileTree(layout.buildDirectory).include("**/jacoco/test.exec", "**/jacoco/integrationTest.exec"))
}


defaultTasks("bootRun")

springBoot {
  mainClass = "tech.jhipster.fullapp.FullappApp"
}


fun loadSonarProperties(): Map<String, List<String>> {
    val properties = mutableMapOf<String, List<String>>()
    File("sonar-project.properties").forEachLine { line ->
        if (!line.startsWith("#") && line.contains("=")) {
            val (key, value) = line.split("=", limit = 2)
            properties[key.trim()] = value.split(",").map { it.trim() }
        }
    }
    return properties
}

sonarqube {
    properties {
      loadSonarProperties().forEach { (key, value) ->
        property(key, value)
      }
      property("sonar.coverage.jacoco.xmlReportPaths", "build/reports/jacoco/test/jacocoTestReport.xml")
      property("sonar.junit.reportPaths", "build/test-results/test,build/test-results/integrationTest")
    }
}


jib {
  from {
    image = "eclipse-temurin:21-jre-jammy"
    platforms {
      platform {
        architecture = "amd64"
        os = "linux"
      }
    }
  }
  to {
    image = "fullapp:latest"
  }
  container {
    entrypoint = listOf("bash", "-c", "/entrypoint.sh")
    ports = listOf("8081")
    environment = mapOf(
     "SPRING_OUTPUT_ANSI_ENABLED" to "ALWAYS",
     "JHIPSTER_SLEEP" to "0"
    )
    creationTime = "USE_CURRENT_TIMESTAMP"
    user = "1000"
  }
  extraDirectories {
    paths {
      path {
        setFrom("src/main/docker/jib")
      }
    }
    permissions = mapOf("/entrypoint.sh" to "755")
  }
}

gitProperties {
  failOnNoGitDirectory = false
  keys = listOf("git.branch", "git.commit.id.abbrev", "git.commit.id.describe", "git.build.version")
}


node {
  download.set(true)
  version.set(nodeVersionValue)
  npmVersion.set(npmVersionValue)
  workDir.set(file(layout.buildDirectory))
  npmWorkDir.set(file(layout.buildDirectory))
}

val buildTaskUsingNpm = tasks.register<NpmTask>("buildNpm") {
  description = "Build the frontend project using NPM"
  group = "Build"
  dependsOn("npmInstall")
  npmCommand.set(listOf("run", "build"))
  environment.set(mapOf("APP_VERSION" to project.version.toString()))
}

val testTaskUsingNpm = tasks.register<NpmTask>("testNpm") {
  description = "Test the frontend project using NPM"
  group = "verification"
  dependsOn("npmInstall", "buildNpm")
  npmCommand.set(listOf("run", "test:coverage"))
  ignoreExitValue.set(false)
  workingDir.set(projectDir)
  execOverrides {
    standardOutput = System.out
  }
}

tasks.bootJar {
  dependsOn("buildNpm")
  from("build/classes/static") {
      into("BOOT-INF/classes/static")
  }
}

// jhipster-needle-gradle-plugins-configurations

repositories {
  mavenCentral()
  // jhipster-needle-gradle-repositories
}

group = "tech.jhipster.fullapp"
version = "0.0.1-SNAPSHOT"

val profiles = (project.findProperty("profiles") as String? ?: "")
  .split(",")
  .map { it.trim() }
  .filter { it.isNotEmpty() }
if (profiles.isEmpty() || profiles.contains("local")) {
  apply(plugin = "profile-local")
}
// jhipster-needle-profile-activation

dependencies {
  implementation(libs.protobuf.java)
  implementation(platform(libs.spring.boot.dependencies))
  implementation(libs.spring.boot.starter)
  implementation(libs.spring.boot.configuration.processor)
  implementation(libs.commons.lang3)
  implementation(libs.spring.boot.starter.validation)
  implementation(libs.spring.boot.starter.web)
  implementation(libs.spring.boot.starter.actuator)
  implementation(libs.logstash.logback.encoder)
  implementation(libs.spring.boot.starter.cache)
  implementation(libs.caffeine)
  implementation(libs.spring.boot.starter.security)
  implementation(libs.jjwt.api)
  implementation(libs.springdoc.openapi.starter.webmvc.ui)
  implementation(libs.springdoc.openapi.starter.webmvc.api)
  implementation(libs.spring.boot.starter.data.jpa)
  implementation(libs.hikariCP)
  implementation(libs.hibernate.core)
  implementation(libs.liquibase.core)
  implementation(libs.cache.api)
  implementation(libs.ehcache)
  implementation(libs.hibernate.jcache)
  // jhipster-needle-gradle-implementation-dependencies
  // jhipster-needle-gradle-compile-dependencies
  runtimeOnly(libs.spring.boot.devtools)
  runtimeOnly(libs.jjwt.impl)
  runtimeOnly(libs.jjwt.jackson)
  runtimeOnly(libs.postgresql)
  // jhipster-needle-gradle-runtime-dependencies

  testImplementation(libs.approvaltests)
  testImplementation(libs.jqwik)
  testImplementation(libs.protobuf.java.util)
  testImplementation(libs.spring.boot.starter.test)
  testImplementation(libs.reflections)
  testImplementation(libs.archunit.junit5.api)
  testImplementation(libs.spring.security.test)
  testImplementation(libs.cucumber.junit.platform.engine)
  testImplementation(libs.cucumber.java)
  testImplementation(libs.cucumber.spring)
  testImplementation(libs.junit.platform.suite)
  testImplementation(libs.testcontainers.postgresql)
  // jhipster-needle-gradle-test-dependencies
}


tasks.build {
  dependsOn("processResources")
}

tasks.processResources {
  filesMatching("**/*.yml") {
    filter { it.replace("@spring.profiles.active@", springProfilesActive) }
  }
  filesMatching("**/*.properties") {
    filter { it.replace("@spring.profiles.active@", springProfilesActive) }
  }
}

// jhipster-needle-gradle-free-configuration-blocks

tasks.test {
  filter {
    includeTestsMatching("**Test*")
    excludeTestsMatching("**IT*")
    excludeTestsMatching("**CucumberTest*")
  }
  useJUnitPlatform()
  finalizedBy("jacocoTestCoverageVerification")
  dependsOn("testNpm")
  // jhipster-needle-gradle-tasks-test
}

val integrationTest = task<Test>("integrationTest") {
  description = "Runs integration tests."
  group = "verification"
  shouldRunAfter("test")
  filter {
    includeTestsMatching("**IT*")
    includeTestsMatching("**CucumberTest*")
  }
  useJUnitPlatform()
}
