plugins {
    kotlin("jvm")
}

dependencies {
    implementation(project(":common"))
    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
    implementation("org.springframework.security:spring-security-core")
    implementation("dev.miku:r2dbc-mysql")
    implementation("mysql:mysql-connector-java")
}
