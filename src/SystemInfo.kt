import com.lordcodes.turtle.shellRun
import util.OperatingSystem

object SystemInfo {
    val OS = when(val os = shellRun("cat", listOf("/etc/os-release"))
        .lineSequence()
        .first { it.startsWith("ID=") }
        .substringAfter("=").trim('"')
    ) {
        "fedora" -> OperatingSystem.FEDORA
        "arch" -> OperatingSystem.ARCH
        else -> error("Unknown OS: $os")
    }
}
