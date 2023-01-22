package online.aboba.points.commands;

import com.google.common.collect.Lists;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import online.aboba.points.PointData;
import online.aboba.points.PointsFile;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Point extends AbstractCommand {
    private final Server server;

    public Point(PluginCommand pluginCommand) {
        super(pluginCommand);
        this.server = pluginCommand.getPlugin().getServer();
    }

    protected boolean hasPermissions(CommandSender sender) {
        return sender.isOp() || sender.hasPermission("points.tp.other");
    }

    @Override
    public void execute(CommandSender sender, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("Bad arguments");
            return;
        }

        if (!sender.hasPermission("points.tp")) {
            sender.sendMessage("You don't has permissions to run this command!");
            return;
        }

        PointData pointData = PointsFile.getPoint(args[0]);

        if (pointData == null) {
            sender.sendMessage("Point not found");

        } else if (args.length == 2) {
            if (!hasPermissions(sender)){
                sender.sendMessage("You don't has permissions to run this command!");
                return;
            }

            Player teleportingPlayer = this.server.getPlayer(args[1]);

            if (teleportingPlayer == null) {
                sender.sendMessage("Player not found.");

            } else {
                teleportingPlayer.sendMessage(Component.text("Teleporting you to " + pointData.getName() + "...").color(NamedTextColor.GOLD));
                sender.sendMessage(Component.text("Teleporting " + args[1] + " to " + pointData.getName() + "...").color(NamedTextColor.GOLD));
                teleportingPlayer.teleport(pointData.getLocation());
            }

        } else {
            if (!(sender instanceof Player)) {
                sender.sendMessage("You are not a player!");

            } else {
                Player teleportingPlayer = (Player) sender;
                teleportingPlayer.sendMessage(Component.text("Teleporting you to " + pointData.getName() + "...").color(NamedTextColor.GOLD));
                teleportingPlayer.teleport(pointData.getLocation());
            }
        }
    }

    @Override
    public List<String> complete(CommandSender sender, String[] args) {
        if(args.length == 1) return PointsFile.getPointsNames();

        else if(hasPermissions(sender) && args.length == 2) {
            List<String> list = new ArrayList<String>();
            for (Player p : Bukkit.getOnlinePlayers()) {
                list.add(p.getName());
            }
            return list;
        }

        return Lists.newArrayList();
    }
}