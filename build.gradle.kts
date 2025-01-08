plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "cn.lunadeer"
version = "3.0-rc.1"

repositories {
    mavenCentral()
    maven ("https://repo.papermc.io/repository/maven-public/")
    maven ("https://oss.sonatype.org/content/groups/public/")
    maven("https://jitpack.io") // for VaultAPI
    maven("https://repo.codemc.org/repository/maven-public") // for VaultUnlockedAPI
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.20.1-R0.1-SNAPSHOT")
    compileOnly("com.github.MilkBowl:VaultAPI:1.7")
    compileOnly("net.milkbowl.vault:VaultUnlockedAPI:2.7")
    implementation("net.kyori:adventure-platform-bukkit:4.3.3")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

tasks.processResources {
    outputs.upToDateWhen { false }
    // copy languages folder from PROJECT_DIR/languages to core/src/main/resources
    from(file("${rootProject.projectDir}/languages")) {
        into("languages")
    }
    // replace @version@ in plugin.yml with project version
    filesMatching("**/plugin.yml") {
        filter {
            it.replace("@version@", rootProject.version.toString())
        }
    }
}

tasks.shadowJar {
    archiveClassifier.set("")
    archiveVersion.set(project.version.toString())
    dependsOn(tasks.withType<ProcessResources>())
}
