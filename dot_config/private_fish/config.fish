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
alias t "toolbox enter"

# Path
fish_add_path /var/home/offz/.local/bin
fish_add_path ~/bin
fish_add_path ~/.cargo/bin

# Use starship if present
if type -q starship
    starship init fish | source
    # source /run/.containerenv
end

