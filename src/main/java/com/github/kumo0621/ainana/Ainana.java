package com.github.kumo0621.ainana;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public final class Ainana extends JavaPlugin implements org.bukkit.event.Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    boolean start = false;

    static String text = null;
    static String name = null;


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (command.getName().equals("ai")) {
            if (sender instanceof Player) {
                if (args.length == 0) {
                    sender.sendMessage("/ai ");
                } else {
                    switch (args[0]) {
                        case "start":
                            start = true;
                            sender.sendMessage("AIの返答を開始しました。");
                            break;
                        case "end":
                            start = true;
                            sender.sendMessage("AIの返答を終了しました。");
                            break;
                        default:
                            Bukkit.broadcastMessage("<NANA_0321> " + args[0]);
                            break;
                    }
                }
            }
        }
        if (command.getName().equals("nana")) {
            if (sender instanceof Player) {
                if (args.length == 0) {
                    sender.sendMessage("/nana メッセージ");
                } else {
                    if (start) {
                        text = args[0];
                        name = sender.getName();
                        if (!OpenAIAsync.run) {
                            String result;
                            result = String.valueOf(OpenAIAsync.openaiAsync(text, name));
                        }

                    }
                }
            }
        }
        return super.onCommand(sender, command, label, args);
    }
}




