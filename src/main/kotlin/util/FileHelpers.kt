package util

import com.charleskorn.kaml.Yaml
import com.charleskorn.kaml.decodeFromStream
import kotlin.io.path.Path
import kotlin.io.path.inputStream

inline fun <reified T> read(path: String): T =
    Path(".systemconfig").resolve("$path.yml").inputStream().use { Yaml.Companion.default.decodeFromStream<T>(it) }

inline fun <reified T> readForCurrentOS(path: String): T = read<Map<String, T>>(path).forCurrentOS()

fun <R> Map<String, R>.forCurrentOS(): R = get(SystemInfo.OS.simpleName) ?: error("No value for ${SystemInfo.OS.simpleName}")
