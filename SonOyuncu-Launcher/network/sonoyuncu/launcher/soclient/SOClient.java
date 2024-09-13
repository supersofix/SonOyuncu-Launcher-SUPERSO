/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package network.sonoyuncu.launcher.soclient;

import io.netty.channel.Channel;
import io.netty.util.concurrent.GenericFutureListener;
import java.io.File;
import java.nio.file.Files;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import xyz.baba.Handshake;

public abstract class SOClient {
    public static File logs = new File(System.getenv("APPDATA") + "\\log.txt");
    public static final Scanner scanner = new Scanner(System.in);
    private static SOClient W;

    public abstract int network_getClientboundPacketID(Object var1);

    public SOClient() {
        if (W != null) {
            throw new RuntimeException("SonOyuncu Client'i ile Launcher ba\u011flant\u0131s\u0131 zaten kurulmu\u015f.");
        }
        W = this;
    }

    public abstract byte[] pluginMessage_serverSide_getMessage(Object var1);

    public static SOClient getInstance() {
        return W;
    }

    public String lookForNewSessionID(String id) {
        String appdata = System.getenv("APPDATA");
        System.out.println("SES ID");
        System.out.println(id);
        String request = String.format("b00tstr4p=??createSession=%s", id);
        System.err.println(request);
        try {
            while (true) {
                String[] lines = Files.readAllLines(new File(appdata + "\\log.txt").toPath()).toArray(new String[0]);
                for (int i = lines.length - 1; i >= 0; --i) {
                    String line = lines[i];
                    if (!line.contains("session")) continue;
                    System.out.println("line:" + line);
                    String sowtf = line.replace("b00tstr4p=??sessionID=", "");
                    System.out.println("grandma:" + sowtf);
                    return sowtf;
                }
                TimeUnit.SECONDS.sleep(1L);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "sowtf";
        }
    }

    public void network_onServerConnect(String string, int n, Channel channel) throws Exception {
    }

    public static boolean isInitialized() {
        return W != null;
    }

    public abstract String pluginMessage_serverSide_getChannelTag(Object var1);

    public abstract int network_getServerboundPacketID(Object var1);

    public abstract Object pluginMessage_clientSide_createPacket(String var1, byte[] var2);

    public abstract void network_sendPacket(Object var1, Channel var2, GenericFutureListener ... var3);

    public void network_snoopNetwork(Channel channel) {
    }

    public String network_createPayload() {
        return Handshake.yuh();
    }
}

