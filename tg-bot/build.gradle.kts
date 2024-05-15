plugins {
    id("application")
    id("java")
//     id ("org.springframework.boot") version "3.2.3"
//     id ("io.spring.dependency-management") version "1.1.4"
}

repositories {
    mavenCentral()
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

dependencies {
    // implementation ("org.springframework.boot:spring-boot-starter")
    implementation("org.telegram:telegrambots:6.9.7.1")
    implementation("org.reflections:reflections:0.9.12")
    // testImplementation ("org.springframework.boot:spring-boot-starter-test")
}

application {
    mainClass.set("org.example.Main")

}
