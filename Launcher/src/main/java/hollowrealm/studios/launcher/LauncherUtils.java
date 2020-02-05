package hollowrealm.studios.launcher;

import javafx.scene.layout.Region;

import java.util.regex.Pattern;

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

}