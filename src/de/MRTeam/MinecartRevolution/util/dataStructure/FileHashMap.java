
package de.MRTeam.MinecartRevolution.util.dataStructure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class FileHashMap<K, V> extends HashMap<K, V> implements Map<K, V>, Cloneable, Serializable {

    public FileHashMap(int paramInt, float paramFloat, String name) {

        super(paramInt, paramFloat);
        create(name);
    }

    public FileHashMap(int paramInt, String name) {

        super(paramInt);
        create(name);
    }

    public FileHashMap(String name) {

        super();
        create(name);
    }

    public FileHashMap(Map<? extends K, ? extends V> paramMap, String name) {

        super(paramMap);
        create(name);
    }

    private void create(String name) {

        this.name = name;
        saveFile = new File("plugins" + File.separator + "MinecartRevolution" + File.separator + "saves" + File.separator + "maps" + File.separator + this.name + ".map");
        if (!saveFile.exists()) {
            new File("plugins" + File.separator + "MinecartRevolution" + File.separator + "saves" + File.separator + "maps").mkdirs();
            save();
        }
    }

    public V put(K key, V value) {

        V newValue = super.put(key, value);
        if (isAutoSave()) {
            save();
        }
        return newValue;
    }

    public V remove(Object key) {

        V newValue = super.remove(key);
        if (isAutoSave()) {
            save();
        }
        return newValue;
    }

    public void save(File file) {

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            String save = "";
            int counter = 0;
            for (java.util.Map.Entry<K, V> entry : entrySet()) {
                K key = entry.getKey();
                V value = entry.getValue();
                save += key + ":" + value;
                counter++;
                if (counter < size()) {
                    save += ",";
                }
            }
            writer.write(save);
            writer.close();
        }
        catch (IOException ex) {
        }
    }

    public void save() {

        save(saveFile);
    }

    @SuppressWarnings ("unchecked")
    public void load(File file) {

        try {
            clear();
            BufferedReader reader = new BufferedReader(new FileReader(file));
            for (String dataKV : reader.readLine().split(",")) {
                String[] data = dataKV.split(":");
                put((K) data[0], (V) data[1]);
            }
            reader.close();
        }
        catch (IOException ex) {
        }
    }

    public void load() {

        load(saveFile);
    }

    public boolean isAutoSave() {

        return autoSave;
    }

    public void setAutoSave(boolean autoSave) {

        this.autoSave = autoSave;
    }

    private String            name;
    private File              saveFile;
    private boolean           autoSave         = true;

    private static final long serialVersionUID = 1L;

}
