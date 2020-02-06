package hollowrealm.studios.launcher;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class PluginUtils {

    public static File[] listFolders(File file) {
        return Arrays.stream(Objects.requireNonNull(file.listFiles())).filter(File::isDirectory).toArray(File[]::new);
    }

    public static boolean isValidPluginFolder(File file) {
        boolean hasInfo = false;
        boolean hasJar = false;
        for (File folder : Objects.requireNonNull(file.listFiles())) {
            if(folder.getName().equals("info.xml")) hasInfo = true;
            if(FilenameUtils.getExtension(folder.getName()).equals("jar")) hasJar = true;
        }
        return hasInfo && hasJar;
    }

    public static URL[] listJars(File file) {
        ArrayList<URL> urls = new ArrayList<>();
        Arrays.stream(Objects.requireNonNull(file.listFiles())).forEach(file1 -> {
            if (FilenameUtils.getExtension(file1.getName()).equals("jar")) {
                try {
                    urls.add(file1.toURI().toURL());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        });
        return urls.toArray(new URL[]{});
    }

    public static Class<?> findStarterClass(String[] names) {
        ArrayList<Class<?>> classes = new ArrayList<>();
        Arrays.stream(names).forEach(name -> {
            try {
                classes.add(Class.forName(name));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        Class<?> clazz = null;
        for (Class<?> c : classes) {
            System.out.println(Arrays.toString(c.getDeclaredMethods()));
            Method methodToFind = null;
            try {
                methodToFind = c.getMethod("enable", (Class<?>[]) null);
                clazz = c;
                break;
            } catch (NoSuchMethodException | SecurityException e) {
                e.printStackTrace();
            }
        }
        return clazz;
    }

}
