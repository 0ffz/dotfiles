package commands.dconf

import co.touchlab.kermit.Logger
import com.lordcodes.turtle.ShellScript
import util.commandCatching
import util.logShellFailure

val ShellScript.gsettings get() = GSettings(this)

class GSettings(val shell: ShellScript) {
    fun set(path: String, key: String, value: String) {
        shell.commandCatching("gsettings", listOf("set", path, key, value))
            .logShellFailure("Failed to set $path.$key=$value")
    }

    fun set(map: Map<String, Map<String, String>>) {
        Logger.i { "Setting ${map.size} gsettings entries" }
        map.forEach { (path, map) ->
            map.forEach { (key, value) -> set(path, key, value) }
        }
    }

    fun get(path: String, key: String): String {
        return shell.command("gsettings", listOf("get", path, key)).trim()
    }
}
