# Apollo

Apollo is an Hypixel oriented pvp client for 1.8.9 that uses the [SpongeAPI](https://www.spongepowered.org) to hook into game events and inject into Minecraft.

### Screenshots:
![Player Gui](https://media.discordapp.net/attachments/740994991240773692/741816914912870442/Screen_Shot_2020-08-08_at_5.03.44_PM.png?width=1530&height=861)

![Modules Gui](https://media.discordapp.net/attachments/740994991240773692/741817166378172416/Screen_Shot_2020-08-08_at_4.51.43_PM.png)

## Development Setup

First you will need to setup a development workspace with the Apollo source code. You can do this by downloading the repository, opening the `build.gradle` as a project in intelij and running the following commands from terminal or gradle window.

```bash
./gradlew setupDecompWorkspace
./gradlew genIntiljRuns
```
![Image of project](https://media.discordapp.net/attachments/747901986770518047/750929662795972740/Screen_Shot_2020-09-02_at_11.06.15_PM.png)

This will download the dependencies and setup the run configuration for the project allowing you to run the client from your IDE. If the game will still not run after you have completed these steps make sure `Minecraft Client` run configuration is set to the module `Apollo.main`.

![Apollo.main](https://media.discordapp.net/attachments/747901986770518047/750931022694318120/Screen_Shot_2020-09-02_at_11.11.31_PM.png)

## Development

```python
import foobar

foobar.pluralize('word') # returns 'words'
foobar.pluralize('goose') # returns 'geese'
foobar.singularize('phenomena') # returns 'phenomenon'
```

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.
