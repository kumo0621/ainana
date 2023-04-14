package com.github.kumo0621.ainana;
import org.bukkit.Bukkit;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.CompletableFuture;
public class OpenAIAsync {
    static boolean run = false;
    public static CompletableFuture<String> openaiAsync(String text, String name) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return openai(text, name);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        });
    }

    static String openai(String text, String name) throws Exception {
        run=true;
        String hostname = "localhost"; // Pythonサーバーのホスト名
        int port = 1111; // Pythonサーバーのポート番号

        Socket socket = new Socket(hostname, port);

        // データの送信
        OutputStream output = socket.getOutputStream();
        PrintWriter writer = new PrintWriter(output, true);
        writer.println(text);

        // データの受信
        InputStream input = socket.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        byte[] response = reader.readLine().getBytes();
        String spawn = new String(response, java.nio.charset.Charset.forName("SHIFT_JIS"));
        System.out.println("Pythonからの応答：" + spawn);

        // ソケットのクローズ
        socket.close();
        run=false;
        Bukkit.broadcastMessage("<" + name + "> " + text);
        Bukkit.broadcastMessage("<NANA_0321> " + spawn);
        return spawn;
    }
}
