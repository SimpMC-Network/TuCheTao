TuCheTao [![Discord](https://img.shields.io/discord/1353293624238145626.svg?label=\&logo=discord\&logoColor=ffffff\&color=7389D8\&labelColor=6A7EC2)](https://discord.typicalsmc.me/discord) ![Supported server version](https://img.shields.io/badge/minecraft-1.16%2B-green)

Language: [Vietnamese](README_VN.md), **[English](README.md)**

Automatic crafting plugin that instantly crafts items when players have the required ingredients.

**Supports item sources:** Minecraft, MMOItems, ItemsAdder

# Download

* [Download the plugin here (v1.0)](https://github.com/SimpMC-Studio/TuCheTao/releases/tag/1.0)

# Features

* Automatically crafts items when requirements are met
* GUI displaying all available recipes
* Detailed info popup for each recipe
* Permission-based cooldown reduction
* Auto-detects and lists all server recipes
* Color codes with `&` formatting and easy GUI customization

> 🎥 **Demo video:** [Watch on YouTube](https://youtu.be/vqMgTj9-Oos)

# Commands & Permissions

## For Players

| Command        | Description                                                      | Permission             |
|----------------|------------------------------------------------------------------|------------------------|
| `/tuchetao`    | Toggle automatic crafting on/off                                 | `tuchetao.use`         |
| `/xemcongthuc` | Open the recipe GUI (only shows recipes you have permission for) | `tuchetao.xemcongthuc` |

## For Admins

| Command           | Description                 | Permission              |
|-------------------|-----------------------------|-------------------------|
| `/tuchetaoreload` | Reload plugin configuration | `tuchetao.admin.reload` |

## Crafting Time Permissions

| Permission                     | Description                                                           |
|--------------------------------|-----------------------------------------------------------------------|
| `tuchetao.timecraft.<seconds>` | Sets individual crafting cooldown. E.g. `tuchetao.timecraft.10` = 10s |

# Configuration

Default config folder: `./plugins/TuCheTao`

```
TuCheTao
│   config.yml
│   messages.yml
│
└───recipes
        example-recipe.yml
```

* Place your custom recipe files in `recipes/`
* GUI settings and cooldown options in `config.yml`
* Message texts and color codes in `messages.yml`

# Notes

* Currently migrating all messages to MiniMessage and using the Adventure API for modern Paper compatibility.

# Support

* Discord: `typical.smc`
* Open an issue on GitHub if you encounter any bugs or need assistance.
