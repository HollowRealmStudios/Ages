package hollowrealm.studios.launcher;

import javafx.scene.layout.Region;
import org.apache.commons.io.FilenameUtils;
import org.javatuples.Pair;

import java.io.File;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static javafx.scene.layout.Region.USE_PREF_SIZE;

public class LauncherUtils {

    public static boolean isInvalidInput(String s) {
        return !Pattern.matches("[1-9][0-9]*", s);
    }

    public static <T extends Region> T usePref(T t, int width, int height) {
        t.setMinWidth(USE_PREF_SIZE);
        t.setMinHeight(USE_PREF_SIZE);
        t.setMaxWidth(USE_PREF_SIZE);
        t.setMaxHeight(USE_PREF_SIZE);
        t.setPrefWidth(width);
        t.setPrefHeight(height);
        return t;
    }

    public static <T extends Region> T usePref(T t, int width) {
        t.setMinWidth(USE_PREF_SIZE);
        t.setMinHeight(USE_PREF_SIZE);
        t.setMaxWidth(USE_PREF_SIZE);
        t.setMaxHeight(USE_PREF_SIZE);
        t.setPrefWidth(width);
        return t;
    }

    public static <K, V> ArrayList<Pair<K, V>> toTupleList(HashMap<K, V> map) {
        ArrayList<Pair<K, V>> list = new ArrayList<>();
        map.forEach((key, value) -> list.add(new Pair<>(key, value)));
        return list;
    }

    public static File[] listJars(File file) {
        List<File> files = Arrays.stream(Objects.requireNonNull(file.listFiles())).filter(f -> FilenameUtils.getExtension(f.getName()).equals("jar")).collect(Collectors.toList());
        return files.toArray(new File[]{});
    }

}
