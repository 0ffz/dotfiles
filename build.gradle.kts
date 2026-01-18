import me.dvyy.tasks.InstallNix

plugins {
//    kotlin("jvm") version "2.3.0"
    id("org.jetbrains.kotlin.plugin.serialization") version "2.3.0"
}

//repositories {
//    mavenCentral()
//}

//dependencies {
//    implementation("com.lordcodes.turtle:turtle:0.10.0")
//    implementation("com.charleskorn.kaml:kaml:0.61.0")
//    implementation("com.github.ajalt.clikt:clikt:5.0.0")
//    implementation("co.touchlab:kermit:2.0.4")
//}

tasks {
    register<InstallNix>("installNix")
    register("test") { dependsOn("installNix") }
}