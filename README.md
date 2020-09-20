[![Discord](https://canary.discordapp.com/api/guilds/740991579342503936/widget.png) ](https://discord.gg/QWAunms)

# Apollo

Apollo is a Hypixel oriented pvp client for 1.8.9 that uses the [SpongeAPI](https://github.com/SpongePowered/Mixin) to hook into game events and inject into Minecraft. Apollo will have countless modules enjoyed by the forge community as well as an SDK to develop and profit off your own modules and cosmetics!

- [Development Setup](#development-setup)
- [Contribution Guidelines](#contribution-guidlines)
- [License / Copyright](#copyright)

### Dev Checklist

- [x] Module Backend
- [x] Event Backend
- [x] Hypixel Api Wrapper(Slothpixel)
- [x] Add Discord Webhook to this Repository
- [ ] Add a lot of Events
- [ ] Addon System
- [ ] Main Module GUI
- [ ] Color Codes / Text Wrap in FontRenderer

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
| ClientChatReceivedEvent | Fired when the client receives a chat message. |
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

To stay organized with the rest of the team and keep a consistent code base I have laid out a few formatting rules all Apollo source must follow to be implemented. For a more in depth look into the code style we are looking for check out classes that have already been implemented and try to keep it mostly the same.

### Classes
All classes should be headed with a short description of the class purpose, the author (with contact) and the version it was implemented in. As you can see below descriptions are compact and written with correct punctuation.

```java
/** MixinBootstrap events for Keybinding.class.
 * @author Icovid | Icovid#3888
 * @since 1.0.0 **/
@Mixin(KeyBinding.class) public class MixinKeybinding {

}

/** Various functions to draw gui objects using GL11 and WorldRenderer.
 * @author Icovid | Icovid#3888
 * @since 1.0.0 **/
public class GLRenderer {

}
```

### Variables
Variables should be split in groups based on functionality and be given descriptions if applicable. Name depends on the type of variable but try to stay close to the format below.

```java
    // Public fonts used in Apollo Guis.
    public static String ROBOTO = "/fonts/Roboto-Regular.ttf";
    public static String ROBOTO_MEDIUM = "/fonts/Roboto-Medium.ttf";

    public final String name;
    public final String description;
    public final Category category;

    // Public client instance used to retrieve any aspect of Apollo.
    public static final Apollo INSTANCE = new Apollo();
    public final ModuleManager moduleManager;
    public final SettingManager settingManager;
```

### Functions
all functions should have a correctly punctuated description and `@param` description for each in all lowercase. A `@return` description should be provided in same way if applicable. Functions should not contain `@author` unless author is different than that of the class and should be compact like shown below.

```java 
     /** {@link GL11} scissor using minecraft screen positions.
     * @param xPosition X start location
     * @param yPosition Y start location
     * @param width scissor width
     * @param height scissor height **/
      public static void glScissor(double xPosition, double yPosition, double width, double height) {
        ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
        GL11.glScissor((int) ((xPosition * Minecraft.getMinecraft().displayWidth) / scaledResolution.getScaledWidth()),
                (int) (((scaledResolution.getScaledHeight() - (yPosition + height)) * Minecraft.getMinecraft().displayHeight) / scaledResolution.getScaledHeight()),
                (int) (width * Minecraft.getMinecraft().displayWidth / scaledResolution.getScaledWidth()),
                (int) (height * Minecraft.getMinecraft().displayHeight / scaledResolution.getScaledHeight()));
    }
     
```

### Event Functions
Event functions are similar to most functions implemented in Apollo with one key difference. You must provide a description, each param correctly formatted and a link or `@see` to the Event class.

```java
    /** Called on every key press.
     * @param key key code of key pressed 
     * @see io.Apollo.event.impl.KeybindEvent **/
    @Inject(method = "onTick", at = @At("HEAD"))
    private static void keyHook(int key) {
        new KeybindEvent.call(key);
    }
```

### JavaDoc / Inline Comments
JavaDoc and inline comments should be more compacted than normal to avoid wasted space and personally I just think it looks better. Comments also should be correctly punctuated with a capital letter to start and ending with a period.

```java
/** Basic setting class. 
 * <p>All setting types will be extended from this.<p>
 * @author Icovid | Icovid#3888
 * @since 1.0.0 **/
 
/** Cancelable event class.
 * <p>Events that extend this class will be cancelable.
 * This means that if {@code cancel} is called,
 * the action that triggered the event will not happen.</p>
 * @author Nora Cos | Nora#0001
 * @since 1.0.0 **/
 
 /** Sets the priority of all methods with the specified event
     * in <b>all instances</b> of the specified object.
     * @param any object to change the priority of
     * @param event event
     * @param newPriority the new priority of the method **/

// Apollo information used for display title and credits info.
// Public client instance used to retrieve any aspect of Apollo.
```

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
