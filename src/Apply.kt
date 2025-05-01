import co.touchlab.kermit.Logger
import util.OperatingSystem.ARCH
import util.OperatingSystem.FEDORA
import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.Context
import commands.dconf.gsettings
import commands.gitConfig
import commands.packages.pacman
import commands.packages.rpmOstree
import util.read
import util.readForCurrentOS
import util.shell

class Apply : CliktCommand() {
    override fun help(context: Context) = "Apply config updates to the system"

    override fun run(): Unit = shell {
        Logger.i("Running for OS: ${SystemInfo.OS.simpleName}")

        val packageManager = when (SystemInfo.OS) {
            ARCH -> pacman
            FEDORA -> rpmOstree
        }

        val packages = readForCurrentOS<List<String>>("packages")
        packageManager.installNecessary(packages)

        val gsettingsEntries = read<Map<String, Map<String, String>>>("gsettings")
        gsettings.set(gsettingsEntries)

        gitConfig.putAll(read<Map<String, String>>("git"))

        Logger.i("Running chezmoi apply:")
        ProcessBuilder("chezmoi", "apply").inheritIO().start().waitFor()
    }
}

