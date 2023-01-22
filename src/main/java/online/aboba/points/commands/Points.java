package online.aboba.points.commands;

import com.google.common.collect.Lists;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import online.aboba.points.PointData;
import online.aboba.points.PointsFile;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Points extends AbstractCommand {
    private final Server server;
    private final PointsFile pointsFile;

    public Points(PluginCommand pluginCommand, PointsFile pointsFile) {
        super(pluginCommand);
        this.server = pluginCommand.getPlugin().getServer();

        this.pointsFile = pointsFile;
    }

    protected boolean hasPermissions(CommandSender sender) {
        return sender.isOp() || sender.hasPermission("points.control");
    }

    @Override
    public void execute(CommandSender sender, String label, String[] args) {
        if (!this.hasPermissions(sender)) {
            sender.sendMessage("You don't has permissions to run this command!");
            return;
        }

        if (args.length > 2) {
            sender.sendMessage("Bad arguments");
            return;
        }

        if (!(sender instanceof Player)) {
            sender.sendMessage("You are not a player");
            return;
        }

        Player player = (Player) sender;

        if (args[0].equals("add")) {
            pointsFile.add(new PointData(args[1], player.getLocation()));
            sender.sendMessage("Success!");

        } else if (args[0].equals("remove")) {
            PointData pointData = PointsFile.getPoint(args[1]);

            if (pointData == null) {
                sender.sendMessage("Point not found");

            } else {
                pointsFile.remove(pointData);
                sender.sendMessage("Success!");
            }
        } else {
            sender.sendMessage("Bad argument");
        }
    }

    @Override
    public List<String> complete(CommandSender sender, String[] args) {
        if (!hasPermissions(sender)) return Lists.newArrayList();

        else if (args.length == 1) return Lists.newArrayList("add", "remove");

        else if (args.length == 2 && args[1].equalsIgnoreCase("remove")) return PointsFile.getPointsNames();

        return Lists.newArrayList();
    }
}