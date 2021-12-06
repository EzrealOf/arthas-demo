package com.example.demo.loader;

import com.example.demo.support.SPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.regex.Pattern;

public class ExtensionLoader<T> {
    private static final Logger logger = LoggerFactory.getLogger(ExtensionLoader.class);

    private final Class<?> type;

    private final String classLoaderPolicy;

    private static final ConcurrentMap<Class<?>, ExtensionLoader<?>> EXTENSION_LOADERS = new ConcurrentHashMap<>();
    private final ConcurrentMap<String, Holder<Object>> cachedInstances = new ConcurrentHashMap<>();
    private final Holder<Map<String, Class<?>>> cachedClasses = new Holder<>();
    private static final Pattern NAME_SEPARATOR = Pattern.compile("\\s*[,]+\\s*");
    private String cachedDefaultName;


    public ExtensionLoader(Class<?> type, String classLoaderPolicy) {
        this.type = type;
        this.classLoaderPolicy = classLoaderPolicy;
    }

    private static <T> boolean withExtensionAnnotation(Class<T> type) {
        return type.isAnnotationPresent(SPI.class);
    }

    @SuppressWarnings("unchecked")
    public static <T> ExtensionLoader<T> getExtensionLoader(Class<T> type, String classLoaderPolicy) {
        if (type == null) throw new IllegalArgumentException("Extension type == null");
        if (!type.isInterface()) {
            throw new IllegalArgumentException("Extension type(" + type + ") is not interface!");
        }
        if (!withExtensionAnnotation(type)) {
            throw new IllegalArgumentException("Extension type(" + type + ") is not extension, because WITHOUT @"
                    + SPI.class.getSimpleName() + " Annotation!");
        }
        ExtensionLoader<T> loader = (ExtensionLoader<T>) EXTENSION_LOADERS.get(type);
        if (loader == null) {
            EXTENSION_LOADERS.putIfAbsent(type, new ExtensionLoader<T>(type, classLoaderPolicy));
            loader = (ExtensionLoader<T>) EXTENSION_LOADERS.get(type);
        }
        return loader;
    }

    @SuppressWarnings("unchecked")
    public T getExtension(String name, String spiDir, String standbyDir) {
        if (name == null || name.length() == 0) throw new IllegalArgumentException("Extension name == null");

        Holder<Object> holder = cachedInstances.get(name);
        if (holder == null) {
            cachedInstances.putIfAbsent(name, new Holder<>());
            holder = cachedInstances.get(name);
        }
        Object instance = holder.get();
        if (instance == null) {
            synchronized (holder) {
                instance = holder.get();
                if (instance == null) {
                    instance = createExtension(name, spiDir, standbyDir);
                    holder.set(instance);
                }
            }
        }
        return (T) instance;

    }


//    @SuppressWarnings("unchecked")
    public T createExtension(String name, String spiDir, String standbyDir) {
        Class<?> clazz = getExtensionClasses(spiDir, standbyDir).get(name);
        // TODO: 2021/12/6
        return null;
    }

    private Map<String, Class<?>> getExtensionClasses(String spiDir, String standbyDir) {
        Map<String, Class<?>> classes = cachedClasses.get();
        if (classes == null) {
            synchronized (cachedClasses) {
                classes = cachedClasses.get();
                if (classes == null) {
                    classes = loadExtensionClasses(spiDir, standbyDir);
                    cachedClasses.set(classes);
                }
            }
        }

        return classes;
    }

    private Map<String, Class<?>> loadExtensionClasses(String spiDir, String standbyDir) {
        final SPI defaultAnnotation = type.getAnnotation(SPI.class);
        if (defaultAnnotation != null) {
            String value = defaultAnnotation.value();
            if ((value = value.trim()).length() > 0) {
                String[] names = NAME_SEPARATOR.split(value);
                if (names.length > 1) {
                    throw new IllegalStateException("more than 1 default extension name on extension " + type.getName()
                            + ": " + Arrays.toString(names));
                }
                if (names.length == 1) cachedDefaultName = names[0];
            }
        }

        Map<String, Class<?>> extensionClasses = new HashMap<>();

        if (spiDir != null && standbyDir != null) {
            // 1. plugin folder，customized extension classLoader
            // （jar_dir/plugin）
            String dir = File.separator + this.getJarDirectoryPath() + spiDir; // +
            // "plugin";

            File externalLibDir = new File(dir);
            if (!externalLibDir.exists()) {
                externalLibDir = new File(File.separator + this.getJarDirectoryPath() + standbyDir);
            }
            logger.info("extension classpath dir: " + externalLibDir.getAbsolutePath());
// TODO: 2021/12/6  
        }

        return null;
    }

    private String getJarDirectoryPath() {
        URL url = Thread.currentThread().getContextClassLoader().getResource("");
        String dirtyPath;
        if (url != null) {
            dirtyPath = url.toString();
        } else {
            File file = new File("");
            dirtyPath = file.getAbsolutePath();
        }
        String jarPath = dirtyPath.replaceAll("^.*file:/", ""); // removes
        // file:/ and
        // everything
        // before it
        jarPath = jarPath.replaceAll("jar!.*", "jar"); // removes everything
        // after .jar, if .jar
        // exists in dirtyPath
        jarPath = jarPath.replaceAll("%20", " "); // necessary if path has
        // spaces within
        if (!jarPath.endsWith(".jar")) { // this is needed if you plan to run
            // the app using Spring Tools Suit play
            // button.
            jarPath = jarPath.replaceAll("/classes/.*", "/classes/");
        }
        Path path = Paths.get(jarPath).getParent(); // Paths - from java 8
        if (path != null) {
            return path.toString();
        }
        return null;
    }


    private static class Holder<T> {

        private volatile T value;

        private void set(T value) {
            this.value = value;
        }

        private T get() {
            return value;
        }

    }
}
