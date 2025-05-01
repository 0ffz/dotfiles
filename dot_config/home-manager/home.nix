{ config, pkgs, ... }:

let
  # pkgs-unstable = import <nixpkgs-unstable> {};
in
{
  home.username = "offz";
  home.homeDirectory = "/home/offz";
  home.stateVersion = "24.11";

  home.packages = (with pkgs; [
    starship
    micro
    eza
    chezmoi
    bitwarden-cli
    lazydocker
    go-task
    tectonic
    fish
    nerd-fonts.jetbrains-mono
    jetbrains-toolbox
    nixd
    nil
    graalvm-ce
    # Gnome extensions
    # gnomeExtensions.middle-click-to-close-in-overview
    # gnomeExtensions.just-perfection
  ]);
# ++ (with pkgs-unstable; [
  	# zed-editor
  # ]);

  nixpkgs.config = {
      allowUnfree = true;
  };

  # Let Home Manager install and manage itself.
  programs.home-manager.enable = true;

  # Add nix applications to Gnome app menu
  xdg.systemDirs.data = [ "${config.home.homeDirectory}/.nix-profile/share/applications" ];
}
