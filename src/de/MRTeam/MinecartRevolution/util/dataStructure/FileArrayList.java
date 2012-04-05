
package de.MRTeam.MinecartRevolution.util.dataStructure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.RandomAccess;

public class FileArrayList<E> extends ArrayList<E> implements List<E>, RandomAccess, Cloneable, Serializable {

    public FileArrayList(int size, String name) {

        super(size);
        create(name);
    }

    public FileArrayList(String name) {

        super();
        create(name);
    }

    public FileArrayList(Collection<? extends E> collection, String name) {

        super(collection);
        create(name);
    }

    private void create(String name) {

        this.name = name;
        saveFile = new File("plugins" + File.separator + "MinecartRevolution" + File.separator + "saves" + File.separator + "lists" + File.separator + this.name + ".list");
        if (!saveFile.exists()) {
            new File("plugins" + File.separator + "MinecartRevolution" + File.separator + "saves" + File.separator + "lists").mkdirs();
            save();
        }
    }

    public boolean add(E value) {

        boolean returnValue = super.add(value);
        if (isAutoSave()) {
            save();
        }
        return returnValue;
    }

    public void add(int position, E value) {

        super.add(position, value);
        if (isAutoSave()) {
            save();
        }
    }

    public boolean remove(Object value) {

        boolean returnValue = super.remove(value);
        if (isAutoSave()) {
            save();
        }
        return returnValue;
    }

    public E remove(int position) {

        E returnValue = super.remove(position);
        if (isAutoSave()) {
            save();
        }
        return returnValue;
    }

    public void save(File file) {

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            String save = "";
            for (int counter = 0; counter < size(); counter++) {
                E value = get(counter);
                save += value;
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
            for (String data : reader.readLine().split(",")) {
                add((E) data);
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
