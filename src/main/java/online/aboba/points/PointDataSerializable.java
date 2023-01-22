package online.aboba.points;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class PointDataSerializable {
    private final String name;
    private final double x;
    private final double y;
    private final double z;
    private final float yaw;
    private final float pitch;
    private final String world;

    public PointDataSerializable(String name, double x, double y, double z, float yaw, float pitch, String world) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
        this.world = world;
    }

    public PointData toData() {
        return new PointData(this.name, new Location(Bukkit.getWorld(this.world), this.x, this.y, this.z, this.yaw, this.pitch));
    }
}
