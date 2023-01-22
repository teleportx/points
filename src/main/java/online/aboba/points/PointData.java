package online.aboba.points;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class PointData {
    private final String name;
    private final Location location;

    public PointData(String name, Location location) {
        this.name = name;
        this.location = location;
    }


    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public PointDataSerializable toSerializable() {
        return new PointDataSerializable(this.name, this.location.getX(), this.location.getY(), this.location.getZ(),
                                         this.location.getYaw(), this.location.getPitch(),
                                         this.location.getWorld().getName());
    }
}
