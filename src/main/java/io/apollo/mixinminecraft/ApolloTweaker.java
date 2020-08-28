package io.apollo.mixinminecraft;

import net.minecraft.launchwrapper.ITweaker;
import net.minecraft.launchwrapper.LaunchClassLoader;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Mixins;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ApolloTweaker implements ITweaker {
    private List<String> args2 = new ArrayList<>();

    @Override
    public void acceptOptions(List<String> args, File gameDir, File assetsDir, String profile) {
        this.args2.addAll(args);

        if(!args.contains("--version") && profile != null) {
            args2.add("--version");
            args2.add(profile);
        }

        if(!args.contains("--assetDir") && assetsDir != null) {
            args2.add("--assetDir");
            args2.add(assetsDir.getAbsolutePath());
        }

        if(!args.contains("--gameDir") && gameDir != null) {
            args2.add("--gameDir");
            args2.add(gameDir.getAbsolutePath());
        }
    }

    @Override
    public void injectIntoClassLoader(LaunchClassLoader classLoader) {
        System.out.println("Loading MixinBootstrap.");
        MixinBootstrap.init();

        MixinEnvironment env = MixinEnvironment.getDefaultEnvironment();
        Mixins.addConfiguration("mixins.apollo.json");

        if (env.getObfuscationContext() == null) {
            env.setObfuscationContext("notch");
        }

        env.setSide(MixinEnvironment.Side.CLIENT);
    }

    @Override
    public String getLaunchTarget() {
        return "net.minecraft.client.main.Main";
    }

    @Override
    public String[] getLaunchArguments() {
        return args2.toArray(new String[0]);
    }
}
