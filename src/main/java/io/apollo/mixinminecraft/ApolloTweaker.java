/*⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤
 Copyright (C) 2020-2021 developed by Icovid and Apollo Development Team
 All Contributors can be found in the README.md
 
 ApolloTweaker.java is part of Apollo Client. 8/31/20, 8:51 PM
 
 ApolloTweaker.java can not be copied and/or distributed without the express
 permission of Icovid
 
 Contact: Icovid#3888 @ https://discord.com
 ⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤*/

package io.apollo.mixinminecraft;

import net.minecraft.launchwrapper.ITweaker;
import net.minecraft.launchwrapper.LaunchClassLoader;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Mixins;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/** Tweaker used to start mixin bootstrap - called in launch arguments.
 * @author Nora | Nora#0001
 * @since 1.0.0 **/
public class ApolloTweaker implements ITweaker {

    // List of launch arguments for getLaunchArguments[]
    private final List<String> launchArguments = new ArrayList<>();

    /** Accepts program run information.
     * @param args launch arguments
     * @param gameDir client working directory
     * @param assetsDir directory of client assets
     * @param profile client version **/
    @Override public void acceptOptions(List<String> args, File gameDir, File assetsDir, String profile) {
        this.launchArguments.addAll(args);

        if(!args.contains("--version") && profile != null) {
            launchArguments.add("--version");
            launchArguments.add(profile);
        }

        if(!args.contains("--assetDir") && assetsDir != null) {
            launchArguments.add("--assetDir");
            launchArguments.add(assetsDir.getAbsolutePath());
        }

        if(!args.contains("--gameDir") && gameDir != null) {
            launchArguments.add("--gameDir");
            launchArguments.add(gameDir.getAbsolutePath());
        }
    }

    /** Inject mixin class load into client.
     * @param classLoader {@link LaunchClassLoader} of mixin environment **/
    @Override public void injectIntoClassLoader(LaunchClassLoader classLoader) {
        MixinBootstrap.init();

        MixinEnvironment env = MixinEnvironment.getDefaultEnvironment();
        Mixins.addConfiguration("mixins.apollo.json");

        if (env.getObfuscationContext() == null) { env.setObfuscationContext("notch"); }

        env.setSide(MixinEnvironment.Side.CLIENT);
    }

    /** Get class to start client.
     * @return class path **/
    @Override public String getLaunchTarget() { return "net.minecraft.client.main.Main"; }

    /** Get client launch arguments.
     * @return array of arguments **/
    @Override public String[] getLaunchArguments() { return launchArguments.toArray(new String[0]); }
}
