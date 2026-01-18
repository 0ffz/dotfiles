package commands.packages

import com.lordcodes.turtle.ShellScript

val ShellScript.flatpak get() = Flatpak(this)

class Flatpak(val shell: ShellScript) : PackageManager {
    override val name = "flatpak"

    fun remove(packageName: String) {
        shell.command("flatpak", listOf("remove", "--noninteractive", packageName))
    }

    override fun install(packages: List<String>) {
        shell.command("flatpak", listOf("install", "--noninteractive", "flathub") + packages)
    }

    override fun getInstalled(): List<String> {
        return shell.command("flatpak", listOf("list", "--app", "--columns", "application")).split("\n")
    }
}
