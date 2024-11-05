#!/bin/bash

# Check if /nix directory exists
if [ -d "/nix" ]
then
    exit
fi

# Source: https://gist.github.com/queeup/1666bc0a5558464817494037d612f094
# Install nix on Fedora silverblue
curl --proto '=https' --tlsv1.2 -sSf -L https://install.determinate.systems/nix | \
    sh -s -- install ostree --no-confirm --persistence=/var/lib/nix

# Fix sudo
echo "Defaults  secure_path = /nix/var/nix/profiles/default/bin:/nix/var/nix/profiles/default/sbin:$(sudo printenv PATH)" | sudo tee /etc/sudoers.d/nix-sudo-en

# Source nix
. /nix/var/nix/profiles/default/etc/profile.d/nix-daemon.sh

# Setup home-manager https://julianhofer.eu/blog/01-silverblue-nix/
nix-channel --add https://nixos.org/channels/nixpkgs-unstable
nix-channel --add https://github.com/nix-community/home-manager/archive/master.tar.gz home-manager
nix-channel --update
nix-shell '<home-manager>' -A install
