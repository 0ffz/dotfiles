package commands.packages

import com.lordcodes.turtle.ShellScript

val ShellScript.pacman get() = Pacman(this)

class Pacman(val shell: ShellScript): PackageManager {
    override fun install(packages: List<String>) {
        shell.command("pacman", listOf("-S", "--noconfirm") + packages)
    }

    override fun getInstalled(): List<String> {
        return shell.command("pacman", listOf("-Q")).split(" ")
    }
}
