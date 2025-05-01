package commands.packages

import com.lordcodes.turtle.ShellScript
import kotlinx.serialization.json.Json

val ShellScript.rpmOstree get() = RpmOstree(this)

class RpmOstree(val shell: ShellScript) : PackageManager {
    override val name = "ostree"

    override fun install(packages: List<String>) {
        shell.command("rpm-ostree", listOf("install") + packages)
    }

    override fun getInstalled(): List<String> = getLayeredPackages()

    fun getLayeredPackages() = Json.decodeFromString<List<List<String>>>(
        shell.command("rpm-ostree", "status --booted --jsonpath $.deployments[0].packages".split(" "))
    )[0]
}
