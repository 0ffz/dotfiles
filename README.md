# Dotfiles

Dotfiles managed across my machines using chezmoi

## Install

- Install [Chezmoi](https://www.chezmoi.io/install/#one-line-package-install)
- Install [Taskfile](https://taskfile.dev/installation/) for tasksfile support

## Init

```properties
chezmoi init https://github.com/0ffz/dotfiles.git
```

## System info

- Currently running Fedora Silverblue with some layered packages for Docker
- Brew for packages that don't need to be layered on the host OS
- Appimages managed through `https://github.com/zyrouge/pho`
