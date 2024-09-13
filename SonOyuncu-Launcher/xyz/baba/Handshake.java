/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package xyz.baba;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import xyz.baba.JsonSOGameRequest;

public class Handshake {
    public static final Gson gson = new Gson();

    public static String yuh() {
        JsonSOGameRequest soGameRequest = new JsonSOGameRequest(Handshake.getMinecraftCHC(), Handshake.getLauncherCHC(), "sl", Handshake.getHWID(), Handshake.sig());
        return gson.toJson(soGameRequest);
    }

    public static String sig() {
        try {
            String key = Handshake.randomKey(16);
            long now = Handshake.getTime();
            long now500 = now + 500L;
            String string = Handshake.random() + now + Handshake.random() + now500 + Handshake.random() + Handshake.hashCodes();
            SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(1, (Key)secretKeySpec, new IvParameterSpec(new byte[16]));
            String encrypted = Base64.getEncoder().encodeToString(cipher.doFinal(string.getBytes(StandardCharsets.UTF_8)));
            String out = Handshake.random() + key + Handshake.random() + encrypted;
            return out;
        } catch (Exception e) {
            e.printStackTrace();
            return "err";
        }
    }

    public static String hashCodes() {
        return "1304836502" + Handshake.random() + "977993101" + Handshake.random() + "796404283" + Handshake.random() + "516747497" + Handshake.random() + "2096171631" + Handshake.random() + "1129871032";
    }

    public static String random() {
        return "*?*";
    }

    public static long getTime() {
        return new Date().getTime();
    }

    public static String randomKey(int n) {
        return UUID.randomUUID().toString().substring(0, n);
    }

    public static String getMinecraftCHC() {
        String chc = "a163409ce959fc705c77aa65c331630128f0e3ad";
        try {
            URL url = new URL("https://launcher.sonoyuncu.network/minecraft/versions/sonoyuncu/1.8.9-Optifine-Ultra_.json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String allLines = Arrays.toString(reader.lines().toArray());
            return allLines.substring(allLines.indexOf("\"client\": {")).split("\"sha1\": \"")[1].split("\",")[0];
        } catch (Exception e) {
            e.printStackTrace();
            return chc;
        }
    }

    public static String getLauncherCHC() {
        AtomicReference<String> chc = new AtomicReference<String>("eb76b206ec6614abd450b736e2dd5eff65dd1229");
        try {
            URL url = new URL("https://launcher.sonoyuncu.network/bootstrap.new.json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            reader.lines().forEach(s -> {
                if (s.contains("launcher_jar_checksum")) {
                    chc.set(s.split("\"launcher_jar_checksum\": \"")[1].split("\",")[0]);
                }
            });
            return chc.get();
        } catch (Exception e) {
            e.printStackTrace();
            return chc.get();
        }
    }

    public static String getHWID() {
        return UUID.randomUUID().toString();
    }
}

