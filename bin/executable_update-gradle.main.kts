#!/usr/bin/env kotlin

@file:DependsOn("com.squareup.okhttp3:okhttp:4.12.0")
@file:DependsOn("org.json:json:20231013")

import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.io.File
import java.nio.file.Path
import kotlin.system.exitProcess

fun getLatestGradleVersion(): String {
    val client = OkHttpClient()
    val request = Request.Builder()
        .url("https://services.gradle.org/versions/current")
        .build()

    client.newCall(request).execute().use { response ->
        if (!response.isSuccessful) {
            throw RuntimeException("Failed to fetch Gradle version: ${response.code}")
        }

        val json = JSONObject(response.body?.string() ?: "")
        return json.getString("version")
    }
}

fun updateGradleWrapper(version: String) {
    val wrapperFile = File("gradle/wrapper/gradle-wrapper.properties")

    if (!wrapperFile.exists()) {
        println("Error: gradle-wrapper.properties not found at ${wrapperFile.absolutePath}")
        exitProcess(1)
    }

    val content = wrapperFile.readText()
    val updatedContent = content.replace(
        Regex("""distributionUrl=https\\://services\.gradle\.org/distributions/gradle-[\d.]+-(bin|all)\.zip"""),
        "distributionUrl=https\\://services.gradle.org/distributions/gradle-$version-bin.zip"
    )

    if (content == updatedContent) {
        println("No update needed - already at version $version")
    } else {
        wrapperFile.writeText(updatedContent)
        println("Updated Gradle wrapper to version $version")
    }
}

try {
    println("Fetching latest Gradle version...")
    val latestVersion = getLatestGradleVersion()
    println("Latest Gradle version: $latestVersion")

    updateGradleWrapper(latestVersion)
} catch (e: Exception) {
    println("Error: ${e.message}")
    exitProcess(1)
}
