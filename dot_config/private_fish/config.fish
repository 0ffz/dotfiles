# Fish config for both containers and host machine

# Use brew if present
if test -f /home/linuxbrew/.linuxbrew/bin/brew
    eval (/home/linuxbrew/.linuxbrew/bin/brew shellenv)
end

clear

# Remove fish greeting
function fish_greeting
    # do nothing
end

set EDITOR micro
set SHELL /usr/bin/fish

# Aliases
alias t "distrobox enter"
alias d distrobox
alias cm chezmoi
alias cma "chezmoi apply"
alias hm home-manager
alias code "flatpak run com.visualstudio.code"
# alias assignments "~/projects/canvas-due-date-exporter/build/install/canvas-due-date-exporter/bin/canvas-due-date-exporter"
# alias abyss "docker compose -f /opt/docker/data/minecraft/docker-compose.yml"
# alias abyss-edit "$EDITOR /opt/docker/data/minecraft/docker-compose.yml"
# Define the main function

if type -q eza
    alias ll "eza -l"
end

# Path
fish_add_path /var/home/offz/.local/bin
fish_add_path ~/bin
fish_add_path ~/.cargo/bin

# Use starship if present
if type -q starship
    starship init fish | source
    # source /run/.containerenv
end

function serv
    set service_name $argv[1]
    set -e argv[1]
    docker compose -f ~/services/$service_name/docker-compose.yml $argv
end
