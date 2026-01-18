package me.dvyy.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.gradle.process.ExecOperations
import javax.inject.Inject
import kotlin.io.path.Path
import kotlin.io.path.notExists

// Install nix as described in https://gist.github.com/queeup/1666bc0a5558464817494037d612f094
//TODO
abstract class InstallNix : DefaultTask() {
    @get:Inject
    abstract val execOps: ExecOperations

    init {
        onlyIf { Path("/nix").notExists() }
    }

    @TaskAction
    fun install() {
        execOps.exec {
            it.commandLine("echo", "TODO")
        }
    }
}