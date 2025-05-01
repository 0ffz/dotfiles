import com.lordcodes.turtle.shellRun
import util.OperatingSystem

object SystemInfo {
    val OS = when(val os = shellRun("lsb_release", listOf("-is")).lowercase()) {
        "fedora" -> OperatingSystem.FEDORA
        "arch" -> OperatingSystem.ARCH
        else -> error("Unknown OS: $os")
    }
}
