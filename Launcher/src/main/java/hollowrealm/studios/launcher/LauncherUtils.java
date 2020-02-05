package hollowrealm.studios.launcher;

import java.util.regex.Pattern;

public class LauncherUtils {

    public static boolean isInvalidInput(String s) {
        return !Pattern.matches("[1-9][0-9]*", s);
    }

}
