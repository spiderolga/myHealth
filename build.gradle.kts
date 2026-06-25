plugins {
    java
    application
    id("org.springframework.boot") version "4.0.0"
    id("io.spring.dependency-management") version "1.1.4"
}

group = "ee.spiderolga"
version = "1.0.0"
java.sourceCompatibility = JavaVersion.VERSION_21
java.targetCompatibility = JavaVersion.VERSION_21

repositories {
    mavenCentral()
}

dependencies {
    // Spring Boot 4.0.0 - Latest with Java 21 support
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    
    // PostgreSQL Database Driver
    runtimeOnly("org.postgresql:postgresql:42.7.1")
    
    // Database Connection Pooling
    implementation("com.zaxxer:HikariCP:5.1.0")
    
    // Flyway for Database Migration
    implementation("org.flywaydb:flyway-core:9.22.3")
    
    // YAML processing
    implementation("org.yaml:snakeyaml:2.2")
    
    // JSON processing
    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.0")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.17.0")
    
    // Logging
    implementation("org.slf4j:slf4j-api:2.0.11")
    implementation("ch.qos.logback:logback-classic:1.5.0")
    
    // Monitoring & Metrics
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("io.micrometer:micrometer-core:1.13.0")
    
    // Testing
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.1")
    testImplementation("org.mockito:mockito-core:5.7.0")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.projectreactor:reactor-test:2024.01.0")
    testImplementation("org.testcontainers:testcontainers:1.19.3")
    testImplementation("org.testcontainers:postgresql:1.19.3")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<JavaCompile> {
    options.release.set(21)
    options.encoding = "UTF-8"
}

application {
    mainClass.set("ee.spiderolga.myhealth.HealthAnalyticsApplication")
}
