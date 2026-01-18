package commands.packages

import co.touchlab.kermit.Logger

interface PackageManager {
    val name: String

    fun install(packages: List<String>)

    fun getInstalled(): List<String>

    fun installNecessary(packages: List<String>) {
        val logger = Logger.withTag(name)
        logger.i("Checking necessary packages...")
        val needsInstall = packages - getInstalled().toSet()
        if (needsInstall.isEmpty()) {
            logger.i("All necessary packages are installed")
            return
        }
        logger.i("Installing ${needsInstall.size} packages...")
        install(needsInstall)
    }
}
