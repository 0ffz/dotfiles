{ config, pkgs, ... }:

let
  pkgs-unstable = import <nixpkgs-unstable> {};
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
    jetbrains-mono
    (nerdfonts.override { fonts = [ "JetBrainsMono" ]; })
    jetbrains-toolbox
    nixd
    nil
    graalvm-ce
    # Gnome extensions
    gnomeExtensions.middle-click-to-close-in-overview
    gnomeExtensions.just-perfection
  ]) ++ (with pkgs-unstable; [
  	zed-editor
  ]);

  nixpkgs.config = {
      allowUnfree = true;
  };

  programs.git = {
    enable = true;
    userEmail = "dan.voznyy@gmail.com";
    userName = "Danielle Voznyy";
  };

  home.sessionVariables = {
    EDITOR = "micro";
    JDK_HOME = "/var/home/offz/.jdks/graalvm-jdk-22.0.2/";
  };

  # Let Home Manager install and manage itself.
  programs.home-manager.enable = true;

  # Add nix applications to Gnome app menu
  xdg.systemDirs.data = [ "${config.home.homeDirectory}/.nix-profile/share/applications" ];

  # Gnome preferences
  dconf.settings = {
    "org/gnome/desktop/interface" = {
      color-scheme = "prefer-dark";
      enable-hot-corners = false;
      clock-format = "12h";
      text-scaling-factor = 1.5;
    };
    "org/gnome/shell" = {
      disable-user-extensions = false;
    };
    # Set console font preference
    "org/gnome/Console" = {
      custom-font = "Jetbrains Mono 12";
      use-system-font = false;
    };
    # Swap alt-tab to show window previews instead of icons
    "org/gnome/desktop/wm/keybindings" = {
      switch-applications = [];
      switch-applications-backward = [];
      switch-windows = ["<Alt>Tab"];
      switch-windows-backward = ["<Shift><Alt>Tab"];
    };
  };
}
