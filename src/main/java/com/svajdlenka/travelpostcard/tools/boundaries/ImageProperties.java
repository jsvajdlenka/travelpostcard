package com.svajdlenka.travelpostcard.tools.boundaries;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ImageProperties {
    private static final String PROPERTIES_DIR = "properties/";
    private Properties properties;
    private String projectname;

    public ImageProperties(String projectname) {
        this.projectname = projectname;
        readPropertiesFile();
    }

    public void readPropertiesFile() {
        String propertiesFilename = Main.ROOT_DIR+PROPERTIES_DIR+projectname+".properties";
        File propertiesFile = new File(propertiesFilename );
        properties = new Properties();
        if (propertiesFile.exists()) {
            try {
                properties.load(new FileInputStream(propertiesFile));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println(projectname + "properties was read");
        }
    }

    public void savePropertiesFile(String comment) {
        properties.setProperty(PropertyKey.NAME.getKey(), projectname);
        String propertiesFilename = Main.ROOT_DIR+PROPERTIES_DIR+projectname+".properties";
        File propertiesFile = new File(propertiesFilename );
        try {
            properties.store(new FileOutputStream(propertiesFile), comment);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(projectname + "properties was saved");
    }

    public void setImageBounds(int width, int height, int minX, int maxX, int minY, int maxY) {
        properties.setProperty(PropertyKey.WIDTH.getKey(), ""+width);
        properties.setProperty(PropertyKey.HEIGHT.getKey(), ""+height);
        properties.setProperty(PropertyKey.X_MIN.getKey(), ""+minX);
        properties.setProperty(PropertyKey.X_MAX.getKey(), ""+maxX);
        properties.setProperty(PropertyKey.Y_MIN.getKey(), ""+minY);
        properties.setProperty(PropertyKey.Y_MAX.getKey(), ""+maxY);
        savePropertiesFile("Set Image Bounds");
    }


}
