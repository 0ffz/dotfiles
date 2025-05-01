import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.Context

class Init: CliktCommand() {
    override fun help(context: Context) = "Initialize a new system (setup nix, etc...)"

    override fun run() {
        //TODO nix install once fixed for fedora atomic 42
    }
}
