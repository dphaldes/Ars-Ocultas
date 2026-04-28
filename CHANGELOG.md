# Changelog

## [2.4.1] - 2026-04-28

### Fixed
- Add null check levels in tooltips. [see report](https://github.com/baileyholl/Ars-Nouveau/issues/2128)

## [2.4.0] - 2026-02-28

### Added

- Added tooltips to containment jars that hold Occultism spirits.
  - These tooltips are shown both on the containment jar item and inworld when looking at the jar.
- Added support for interactions with Occultism spirit gems. (Thanks @Qther)
  - You can now insert and remove mobs directly from the containment jar.

## [2.3.1] - 2026-02-27

### Added
- Added Chinese (zh_cn) translation (By @ChujikYahus).

## [2.3.0] - 2026-02-26

### Added

- Add item transfer support of traders in spirit jars (By @antessial).

## [2.2.1] - 2026-02-06

### Fixed

- Fix sacrificial altar loot table. It should drop as an item when broken.

## [2.2.0] - 2025-08-02

### Added

- Added support for Crystallizer spirits for auto item transfer to nearby inventories.
- Added Sacrificial Altar - New Block that can be used to sacrifice mobs from Mob jars. It consumes 5000 Source and must
  be placed under the golden sacrificial bowl

### Changed

- Bumped minimum version of Occultism and Neoforge

## [2.1.0] - 2024-12-23

### Added

- Smelter and Crushers spirits now try to push to adjacent inventories automatically.
  This is done to reduce item entity spawn lag.

### Changed

- Bumped minimum version for Neoforge, Occultism and Ars Nouveau
- Built against Minecraft 1.21.1

## [2.0.1] - 2024-07-24

### Fixed

- Fixed Recipes 

## [2.0.0] - 2024-07-22

Ported to NeoForge 1.21

### Changed

- Port to NeoForge
- Forge support is now dropped for 1.21+

## [1.2.2] - 2024-03-10

### Fixed

- Unlock Registrate version range

## [1.2.1] - 2024-01-24

### Fixed

- Fixed a bug in filter button not working in Transporter Spirit GUI (and Janitor).

## [1.2.0] - 2024-01-24

### Changed

- Janitor Spirit works more closer to an allay. Similar to allay it will automatically push to inventory next to it
- Spirit GUIs are functional now. Shift+RightClick with empty hand to open the GUI

## [1.1.0] - 2023-10-12

### Added

- Added Patchouli documentation
- Added transmutation recipes
- Added alternate recipe for Spirit Attuned Gems
- Added alternate recipe for Tier 4 stabilizer

### Changed

- Build against NeoForge and bump minimum version of Ars Nouveau.

## [1.0.1] - 2023-09-02

### Changed

- Switched back to Forge and use older version of Ars till NeoForge is ready.

## [1.0.0] - 2023-08-29

Port to 1.20.x and build for NeoForge.

### Added

- added Occultism integration
- crushers spirits can now work from inside Ars Nouveau containment jars
  - player can right click on jar with ore and drop it nearby
- Janitor Spirit can now push items to any inventory on top of the Jar
- Containment jar containing spirits now expose the spirit's item capability. Hoppers and pipes should work with Jars
  now.