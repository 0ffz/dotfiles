package commands.packages

import co.touchlab.kermit.Logger

interface PackageManager {
    fun install(packages: List<String>)

    fun getInstalled(): List<String>

    fun installNecessary(packages: List<String>) {
        Logger.i("Checking necessary packages...")
        val needsInstall = packages - getInstalled().toSet()
        if (needsInstall.isEmpty()) {
            Logger.i("All necessary packages are installed.")
            return
        }
        Logger.i("Installing ${needsInstall.size} packages...")
        install(needsInstall)
    }
}
