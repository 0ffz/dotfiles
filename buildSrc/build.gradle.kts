plugins {
//    id(libs.plugins.kotlin.jvm.get().pluginId)
    kotlin("jvm") version "2.3.0"
    `java-gradle-plugin`
}

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(25)
}

//dependencies {
//    compileOnly("org.jetbrains.kotlin:kotlin-gradle-plugin:${libs.versions.kotlin.get()}")
//    implementation(gradleApi())
//}

//gradlePlugin {
//    plugins {
//        create("tasks") {
//            id = "me.dvyy.tasks"
//            implementationClass = "me.dvyy.sqlite.codegen.SqliteCodegenPlugin"
//        }
//    }
//}
//
//kotlin {
//    compilerOptions {
//        freeCompilerArgs.add("-Xcontext-parameters")
//    }
//}
