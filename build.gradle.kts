
plugins {
    java
    application
    id("org.flywaydb.flyway") version "9.22.0"
    id("nu.studer.jooq") version "8.0"
}

group = "org.miniportfolio"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.slf4j:slf4j-simple:2.0.13")
    implementation("com.sparkjava:spark-core:2.9.4")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("org.postgresql:postgresql:42.6.0")
    implementation("org.jooq:jooq:3.18.6")
    implementation("com.zaxxer:HikariCP:5.0.1")
    implementation("io.lettuce:lettuce-core:6.2.0.RELEASE")
    testImplementation("org.spockframework:spock-core:2.3-groovy-3.0")
    testImplementation("org.testcontainers:postgresql:1.19.0")
}

flyway {
    url = "jdbc:postgresql://localhost:5432/postgres"
    user = "postgres"
    password = "pass"
    locations = arrayOf("filesystem:src/main/resources/db/migration")
    baselineOnMigrate = true
}