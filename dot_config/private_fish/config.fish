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

function envsource
  for line in (cat $argv | grep -v '^#')
    set item (string split -m 1 '=' $line)
    set -gx $item[1] $item[2]
    # echo "Exported key $item[1]"
  end
end

if test -f ~/.env
    envsource ~/.env
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

