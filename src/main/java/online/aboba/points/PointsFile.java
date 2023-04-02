package online.aboba.points;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.annotation.Nullable;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class PointsFile {
    private static List<PointData> POINTS = Lists.newArrayList();
    private static List<PointDataSerializable> POINTS_SERIALIZABLE = Lists.newArrayList();

    private final File pointsFile;

    private Gson gson;

    public static List<String> getPointsNames() {
        List<String> list = new ArrayList<String>();
        for (PointData point : POINTS) {
            list.add(point.getName());
        }

        return list;
    }

    public static List<PointData> getPoints() {
        return POINTS;
    }

    public static @Nullable PointData getPoint(String name) {
        for (PointData point : POINTS) {
            if (point.getName().equals(name)) {
                return point;
            }
        }
        return null;
    }

    public PointsFile(File pointsFile) {
        this.pointsFile = pointsFile;

        if (!pointsFile.exists()) {
            try (FileWriter dataFile_w = new FileWriter(pointsFile.getPath())) {
                dataFile_w.write("[]");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        this.gson = new Gson();
    }

    protected String readFile() {
        try {
            return new String(Files.readAllBytes(pointsFile.toPath()));
        } catch (IOException e) {
            return "[]";
        }
    }

    protected void writeFile(String write) {
        try (FileWriter dataFile_w = new FileWriter(pointsFile.getPath())) {
            dataFile_w.write(write);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load() {
        POINTS_SERIALIZABLE = gson.fromJson(this.readFile(), new TypeToken<ArrayList<PointDataSerializable>>() {}.getType());
        for (PointDataSerializable point : POINTS_SERIALIZABLE) {
            POINTS.add(point.toData());
        }
    }

    public int add(PointData point) {
        POINTS_SERIALIZABLE.add(point.toSerializable());
        POINTS.add(point);

        this.writeFile(gson.toJson(POINTS_SERIALIZABLE));

        return POINTS.size() - 1;
    }

    public void remove(PointData point) {
        POINTS_SERIALIZABLE.remove(point.toSerializable());
        POINTS.remove(point);
        this.writeFile(gson.toJson(POINTS_SERIALIZABLE));
    }
}
