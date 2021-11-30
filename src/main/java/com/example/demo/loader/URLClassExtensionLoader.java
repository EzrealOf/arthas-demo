package com.example.demo.loader;

import java.net.URL;
import java.net.URLClassLoader;

public class URLClassExtensionLoader extends URLClassLoader {

    public URLClassExtensionLoader(URL[] urls) {
        super(urls);
    }
}
