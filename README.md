[![Discord](https://canary.discordapp.com/api/guilds/740991579342503936/widget.png) ](https://discord.gg/QWAunms)

# Apollo

Apollo is a Hypixel oriented pvp client for 1.8.9 that uses the [Mixins](https://github.com/SpongePowered/Mixin) library to hook into game events and inject into Minecraft. Apollo will have countless modules enjoyed by the forge community as well as an SDK to develop and profit off your own modules and cosmetics!

- [Development Setup](#development-setup)
- [Contribution Guidelines](#contribution-guidlines)
- [License / Copyright](#copyright)

### Modules

All Modules must be documented as well. When adding any new modules create a new line to the table with the module name and a description of what it does.

| Name | Description |
| --- | --- |
| DiscordRP | Updates the Discord Rich Presence, based on what your doing|
| Windowed Fullscreen | Lets you use other monitors while in Fullscreen|
| AutoGG | Automatically says GG at the end of the game|
| Quickplay (W.I.P) | Quickly get into a game using a fancy menu|

### Events

All Events that are added to apollo must be documented below to help developers in future SDK. You must fill out the name of the event class and a description of when it is called.

| Name | Description |
| --- | --- |
| GameLoopEvent | Fired every tick. |
| LeftClickEvent | Fired when left mouse button is pressed. |
| RightClickEvent | Fired when right mouse button is pressed. |
| KeyPressedEvent | Fired when key is pressed. |
| KeyReleasedEvent | Fired when key is released. |
| ActionBarEvent | Fired when the Action Bar is updated. |
| ChatReceivedEvent | Fired when the client receives a chat packet. |
| PlayerChatEvent | Fired when the client sends a message to the server. |
| AttackEntityEvent | Fired when the client attacks an entity. |
| GuiSwitchEvent | Fired when a GUI is opened. |

### Forum Post

If you create any forum post for the client put the link below so we can keep track of our social presence.

- https://hypixel.net/threads/apollo-client-discord-new-hypixel-client.3219735/#post-22502168
- https://hypixel.net/threads/apollo-client-sdk-update-for-apollo-client-development.3292568/#post-23178676
- https://hypixel.net/threads/apollo-client-what-is-apollo-client-unique-1-8-9-pvp-client-for-hypixel-more.3304557/
- https://hypixel.net/threads/apollo-★-awesome-new-hypixel-pvp-client-★.3328295/

# Development Setup

First you will need to set up a development workspace with the Apollo source code. You can do this by downloading the repository, opening the `build.gradle` as a project in IntelliJ IDEA and running the following commands from terminal or gradle window.

```bash
./gradlew setupDecompWorkspace
./gradlew genIntellijRuns
```

![Image of project](https://media.discordapp.net/attachments/747901986770518047/750929662795972740/Screen_Shot_2020-09-02_at_11.06.15_PM.png)

This will download the dependencies and setup the run configuration for the project allowing you to run the client from your IDE. If the game will still not run after you have completed these steps make sure `Minecraft Client` run configuration is set to the module `Apollo.main`.

![Apollo.main](https://media.discordapp.net/attachments/747901986770518047/750931022694318120/Screen_Shot_2020-09-02_at_11.11.31_PM.png)

Lastly to run the client you will need to bypass errors involving the Lombok IntelliJ plugin we use to make writing Java more tolerable. You can do this by installing the Lombok IntelliJ plugin from [this link](https://plugins.jetbrains.com/plugin/6317-lombok) or the JetBrains plugin repository.

![plugin](https://media.discordapp.net/attachments/747901986770518047/751266941204693002/Screen_Shot_2020-09-03_at_9.26.33_PM.png)

# Contribution Guidelines

Contributions must follow Google Java Style guide. This can be found at https://google.github.io/styleguide/javaguide.html#s7.1-javadoc-formatting or you can autmaticall update youre project to latet guidlines using `./gradlew goJF` as shown below

![google](https://media.discordapp.net/attachments/741000859001028818/758008531877363772/Screen_Shot_2020-09-22_at_11.55.11_AM.png)

### Pull Request

Pull request should have a relevant title and an in depth description of anything it adds, removes, or improves.

## Copyright 

```
This program is free software: you can redistribute it and/or modify
it under the terms of the GNU Affero General Public License as published
by the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Affero General Public License for more details.

You should have received a copy of the GNU Affero General Public License
along with this program.  If not, see <https://www.gnu.org/licenses/>.
```
