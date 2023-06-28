package com.svajdlenka.travelpostcard.tools.boundaries;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Main {
    public static final String ROOT_DIR = "src/test/resources/boundaries/";
    private static final String IMAGES_DIR = "images/";

    private ImageProperties properties;
    private BufferedImage image;

    public static void main(String[] args) {
        if (args.length != 1) {
            usage();
            System.exit(100);
        }
        Main main = new Main(args[0]);
        main.processImage();
    }

    public Main(String projectname) {
        properties = new ImageProperties(projectname);
        readImageFile(projectname);
    }

    private void processImage() {
        int minX = Integer.MAX_VALUE;
        int maxX = 0;
        int minY = Integer.MAX_VALUE;
        int maxY = 0;
        for (int x = 0; x < image.getWidth(); ++x) {
            for (int y = 0; y < image.getHeight(); ++y) {
                int rgb = image.getRGB(x,y);
                if (rgb != -1) {
                    if (minX > x) {
                        minX = x;
                    }
                    if (maxX < x) {
                        maxX = x;
                    }
                    if (minY > y) {
                        minY = y;
                    }
                    if (maxY < y) {
                        maxY = y;
                    }
                }
            }
        }
        properties.setImageBounds(image.getWidth(), image.getHeight(), minX, maxX, minY, maxY);
        System.out.println("x: "+minX+"-"+maxX+" y:"+minY+"-"+maxY);
    }

    private void readImageFile(String filename) {
        String imageFilename = ROOT_DIR+IMAGES_DIR+filename+".bmp";
        File imageFile = new File(imageFilename);
        try {
            image = ImageIO.read(imageFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void usage() {
        System.out.println("Usage: Main propertiesfile");
    }
}
