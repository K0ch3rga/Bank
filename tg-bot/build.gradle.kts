plugins {
    id("application")
    id("java")
//     id ("org.springframework.boot") version "3.2.3"
//     id ("io.spring.dependency-management") version "1.1.4"
}

repositories {
    mavenCentral()
}

dependencies {
    // implementation ("org.springframework.boot:spring-boot-starter")
    implementation("org.telegram:telegrambots:6.9.7.1")

    // testImplementation ("org.springframework.boot:spring-boot-starter-test")
}

application {
    mainClass.set("org.example.Main")
}