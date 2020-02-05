package hollowrealm.studios.launcher;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class PluginManager {

    public PluginManager(File file) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        ArrayList<URL> jars = listJars(file);
        URLClassLoader child = new URLClassLoader(new URL[]{jars.get(0)}, this.getClass().getClassLoader());
        Class<?> classToLoad = Class.forName("test.test.Test", true, child);
        System.out.println(Arrays.toString(classToLoad.getDeclaredMethods()));
        Method method = classToLoad.getDeclaredMethod("onEnable");
        method.invoke(null);
        //Object instance = classToLoad.newInstance();
        //Object result = method.invoke(instance);
    }

    private ArrayList<URL> listJars(File file) {
        ArrayList<URL> urls = new ArrayList<>();
        Arrays.stream(Objects.requireNonNull(file.listFiles())).forEach(file1 -> {
            System.out.println(FilenameUtils.getExtension(file1.getName()));
            if (FilenameUtils.getExtension(file1.getName()).equals("jar")) {
                try {
                    urls.add(file1.toURI().toURL());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        });
        return urls;
    }

}
