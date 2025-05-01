package commands.packages

import com.lordcodes.turtle.ShellScript

val ShellScript.pacman get() = Pacman(this)

class Pacman(val shell: ShellScript): PackageManager {
    override val name = "pacman"

    override fun install(packages: List<String>) {
        shell.command("sudo", listOf("pacman", "-S", "--noconfirm") + packages)
    }

    override fun getInstalled(): List<String> {
        return shell.command("pacman", listOf("-Qq")).split("\n")
    }
}
