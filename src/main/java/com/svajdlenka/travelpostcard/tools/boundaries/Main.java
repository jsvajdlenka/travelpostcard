package com.svajdlenka.travelpostcard.tools.boundaries;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Main {
    private static final String ROOT_DIR = "src/test/resources/boundaries/";
    private static final String PROPERTIES_DIR = "properties/";
    private static final String IMAGES_DIR = "images/";

    private Properties properties;
    private BufferedImage image;

    public static void main(String[] args) {
        if (args.length != 1) {
            usage();
            System.exit(100);
        }
        Main main = new Main();
        main.readPropertiesFile(args[0]);
        main.readImageFile(args[0]);
        main.processImage();
    }

    private void processImage() {
        for (int x = 0; x < image.getWidth(); ++x) {
            for (int y = 0; y < image.getHeight(); ++y) {
                int rgb = image.getRGB(x,y);
                if (rgb != -1) {
                    System.out.println(x+"/"+y+": "+rgb);
                }
            }
        }
    }

    private void readImageFile(String filename) {
        String propertiesFilename = ROOT_DIR+IMAGES_DIR+filename+".bmp";
        File imageFile = new File(propertiesFilename);
        try {
            image = ImageIO.read(imageFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(image.getHeight());
    }

    private void readPropertiesFile(String filename) {
        String propertiesFilename = ROOT_DIR+PROPERTIES_DIR+filename+".properties";
        File propertiesFile = new File(propertiesFilename );
        properties = new Properties();
        try {
            properties.load(new FileInputStream(propertiesFile));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(properties.getProperty("name"));
    }

    private static void usage() {
        System.out.println("Usage: Main propertiesfile");
    }
}
