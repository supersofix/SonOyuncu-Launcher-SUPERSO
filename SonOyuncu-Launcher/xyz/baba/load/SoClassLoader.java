/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package xyz.baba.load;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;

public class SoClassLoader
extends URLClassLoader {
    public HashMap<String, Class<?>> overrides = new HashMap();

    public SoClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        if (this.overrides.containsKey(name)) {
            return this.overrides.get(name);
        }
        return super.findClass(name);
    }
}

