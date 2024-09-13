/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package xyz.baba;

import java.io.File;
import java.net.URL;
import java.util.UUID;
import xyz.baba.load.SoClassLoader;

public class Main {
    public static void main(String[] args) throws Exception {
        String appdata = System.getenv("APPDATA");
        String string = appdata + "\\.groaxd\\natives";
        System.setProperty("org.lwjgl.librarypath", string);
        System.setProperty("net.java.games.input.librarypath", string);
        System.setProperty("java.library.path", string);
        System.out.println("starting");
        SoClassLoader classLoader = new SoClassLoader(new URL[]{new URL(String.format("file:///%s\\.groaxd\\sorefix\\Myth.jar", appdata))}, ClassLoader.getSystemClassLoader());
        String nick = "realdev1337";
        Object[] mArgs = new Object[]{Main.args(nick)};
        System.out.println("session generated");
        Thread.currentThread().setContextClassLoader(classLoader);
        classLoader.loadClass("net.minecraft.client.main.Main").getMethod("main", String[].class).invoke(null, mArgs);
    }

    public static String[] args(String name) {
        File sonoyuncuFolder = new File(System.getenv("APPDATA"), "\\.sonoyuncu\\");
        String[] stringArray = "--username ${auth_player_name} --version ${version_name} --gameDir ${game_directory} --assetsDir ${assets_root} --assetIndex ${assets_index_name} --uuid ${auth_uuid} --accessToken ${auth_access_token} --userProperties ${user_properties} --userType ${user_type}".split(" ");
        for (int i = 0; i < stringArray.length; ++i) {
            String string2 = stringArray[i];
            if (string2.startsWith("--")) continue;
            if (string2.equalsIgnoreCase("${auth_player_name}")) {
                stringArray[i] = name;
            }
            if (string2.equalsIgnoreCase("${version_name}")) {
                stringArray[i] = "1.8.9-Optifine-Ultra_";
            }
            if (string2.equalsIgnoreCase("${game_directory}")) {
                stringArray[i] = new File(sonoyuncuFolder, "game-directories/public").getAbsolutePath();
            }
            if (string2.equalsIgnoreCase("${game_assets}")) {
                stringArray[i] = new File(sonoyuncuFolder, "assets/virtual").getAbsolutePath();
            }
            if (string2.equalsIgnoreCase("${assets_root}")) {
                stringArray[i] = new File(sonoyuncuFolder, "assets").getAbsolutePath();
            }
            if (string2.equalsIgnoreCase("${assets_index_name}")) {
                stringArray[i] = "1.8";
            }
            if (string2.equalsIgnoreCase("${auth_uuid}")) {
                stringArray[i] = UUID.nameUUIDFromBytes(("OfflinePlayer:" + name).getBytes()).toString();
            }
            if (string2.equalsIgnoreCase("${auth_access_token}")) {
                stringArray[i] = "00000000000000000000000000000000";
            }
            if (string2.equalsIgnoreCase("${user_properties}")) {
                stringArray[i] = "{}";
            }
            if (string2.equalsIgnoreCase("${user_type}")) {
                stringArray[i] = "mojang";
            }
            if (!string2.equalsIgnoreCase("${version_type}")) continue;
            stringArray[i] = "release";
        }
        return stringArray;
    }
}

