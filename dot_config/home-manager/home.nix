{ config, pkgs, ... }:

{
  home.username = "offz";
  home.homeDirectory = "/var/home/offz";
  home.stateVersion = "24.05";

  home.packages = with pkgs; [
    starship
    micro
    eza
    chezmoi
    bitwarden-cli
    lazydocker
    go-task
    tectonic
  ];

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
#  programs.zed-editor = {
#   enable = true;
#   extensions = [ "nix" ];
#  };
}
