package hollowrealm.studios.game;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Paths;
import java.util.Objects;

public class Plugin {

    private final File folder;
    private String name, author, version, main;
    private Class<?> starter;

    @Override
    public boolean equals(Object o) {
        if(o instanceof String) {
            return o.equals(name);
        }
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plugin plugin = (Plugin) o;
        return name.equals(plugin.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public Plugin(File folder) {
        this.folder = folder;
        String[] properties = getPluginProperties(getXmlFile(folder));
        name = properties[0];
        author = properties[1];
        version = properties[2];
        main = properties[3];
        try {
            load();
        } catch (MalformedURLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void load() throws MalformedURLException, ClassNotFoundException {
        URLClassLoader classLoader = new URLClassLoader(new URL[]{getJarFile(folder).toURI().toURL()}, this.getClass().getClassLoader());
        starter = classLoader.loadClass(main);
    }

    public Class<?> getStarer() {
        return starter;
    }

    public String getAuthor() {
        return author;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public String getMain() {
        return main;
    }

    private File getJarFile(File folder) {
        return Paths.get(folder.getAbsolutePath(), getPluginProperties(getXmlFile(folder))[0] + ".jar").toFile();
    }

    private File getXmlFile(File folder) {
        return Paths.get(folder.getAbsolutePath(), "info.xml").toFile();
    }

    private String[] getPluginProperties(File xml) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(false);
        factory.setIgnoringElementContentWhitespace(true);
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        Document doc = null;
        try {
            doc = Objects.requireNonNull(builder).parse(xml);
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
        Element root = Objects.requireNonNull(doc).getDocumentElement();
        return new String[]{
                root.getElementsByTagName("name").item(0).getTextContent(),
                root.getElementsByTagName("author").item(0).getTextContent(),
                root.getElementsByTagName("version").item(0).getTextContent(),
                root.getElementsByTagName("main").item(0).getTextContent(),
        };
    }

    public Class<?> getStarter() {
        return starter;
    }

    public void start() {
        try {
            starter.getDeclaredMethod("start").invoke(null);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        try {
            starter.getDeclaredMethod("stop").invoke(null);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Plugin{" +
                "folder=" + folder +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", version='" + version + '\'' +
                ", main='" + main + '\'' +
                ", starter=" + starter +
                '}';
    }
}
