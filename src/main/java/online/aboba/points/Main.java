package online.aboba.points;

import online.aboba.points.commands.Point;
import online.aboba.points.commands.Points;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public final class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        getDataFolder().mkdir();

        PointsFile pointsFile = new PointsFile(new File(getDataFolder(), "points.json"));
        pointsFile.load();

        new Point(getCommand("point"));
        new Points(getCommand("points"), pointsFile);
    }
}
