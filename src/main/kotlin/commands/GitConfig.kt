package commands

import co.touchlab.kermit.Logger
import com.lordcodes.turtle.ShellScript
import util.commandCatching
import util.logShellFailure

val ShellScript.gitConfig get() = GitConfig(this)

class GitConfig(val shell: ShellScript) {
    operator fun set(key: String, value: String) {
        shell.commandCatching("git", listOf("config", "--global", key, value))
            .logShellFailure("Failed to set git key $key=$value")
    }

    operator fun get(key: String): String = shell.command("git", listOf("config", "--global", key)).trim()

    fun putAll(map: Map<String, String>) {
        Logger.i { "Setting ${map.size} git config options" }
        map.forEach { (key, value) -> set(key, value) }
    }
}
