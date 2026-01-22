{
  description = "Default set of packages for my machines";

  inputs = {
    nixpkgs.url = "github:nixos/nixpkgs/nixos-25.11";
    # nixpkgs.url = "github:nixos/nixpkgs/nixos-unstable";
  };

  outputs =
    { self, nixpkgs }:
    let
      system = "x86_64-linux";
      # "aarch64-linux"
      # "x86_64-darwin"
      # "aarch64-darwin"

      pkgs = nixpkgs.legacyPackages.${system};
    in
    {
      packages.${system}.default = pkgs.buildEnv {
        name = "my-tools";
        paths = with pkgs; [
          starship
          fish
          micro
          eza
          chezmoi
          bitwarden-cli
          lazydocker
          tectonic
          nixd
          nil
          nerd-fonts.jetbrains-mono
          # intelli-shell
        ];
      };
      formatter.${system} = pkgs.nixpkgs-fmt;
    };
}
