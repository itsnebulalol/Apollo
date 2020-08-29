/*
 * ****************************************************************
 *  Copyright (C) 2020-2021 developed by Icovid
 *  Class Authors : Nora [Nora#0001]
 *
 *  ApolloTweaker.java is part of Apollo Client. [8/28/20, 2:36 PM]
 *
 *  ApolloTweaker.java can not be copied and/or distributed without the express
 *  permission of Icovid
 *
 *  Contact: Icovid#3888
 * ****************************************************************
 */

package io.apollo.mixinminecraft;

import net.minecraft.launchwrapper.ITweaker;
import net.minecraft.launchwrapper.LaunchClassLoader;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Mixins;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* Tweaker Used to Start Mixin Bootstrap - called in Launch Arguments */
public class ApolloTweaker implements ITweaker {

    // List of launch arguments for getLaunchArguments[]
    private final List<String> launchArguments = new ArrayList<>();

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

    @Override public void injectIntoClassLoader(LaunchClassLoader classLoader) {
        MixinBootstrap.init();

        MixinEnvironment env = MixinEnvironment.getDefaultEnvironment();
        Mixins.addConfiguration("mixins.apollo.json");

        if (env.getObfuscationContext() == null) { env.setObfuscationContext("notch"); }

        env.setSide(MixinEnvironment.Side.CLIENT);
    }

    @Override public String getLaunchTarget() { return "net.minecraft.client.main.Main"; }

    @Override public String[] getLaunchArguments() { return launchArguments.toArray(new String[0]); }
}
