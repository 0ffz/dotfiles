package util

import co.touchlab.kermit.Logger
import com.lordcodes.turtle.ShellRunException
import com.lordcodes.turtle.ShellScript

fun <T> shell(run: ShellScript.() -> T): T = ShellScript().run(run)

fun ShellScript.commandCatching(command: String, arguments: List<String> = listOf()): Result<String> {
    return runCatching { command(command, arguments) }
}

fun <T> Result<T>.onShellFailure(block: (ShellRunException) -> Unit): Result<T> = onFailure {
    if (it is ShellRunException) block(it) else throw it
}

fun <T> Result<T>.logShellFailure(prefix: String) = onShellFailure { Logger.e { "$prefix (${it.errorText})" } }
