package com.svajdlenka.travelpostcard.tools.boundaries;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageProcessor {
    private static final String IMAGES_DIR = "images/";
    private ImageProperties properties;
    private BufferedImage image;
    private String projectname;
    private int minX = Integer.MAX_VALUE;
    private int maxX = 0;
    private int minY = Integer.MAX_VALUE;
    private int maxY = 0;

    public ImageProcessor(String projectname, ImageProperties properties) {
        this.projectname = projectname;
        this.properties = properties;
        readImageFile();
    }

    public void processImageBounds() {
        minX = Integer.MAX_VALUE;
        maxX = 0;
        minY = Integer.MAX_VALUE;
        maxY = 0;
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

    public void processInlineRectangle() {
        // Najdi point x ktory sa dotyka na minY
        int y = minY;
        int x;
        for (x = minX; x <= maxX; ++x) {
            if (image.getRGB(x,y) != -1) {
                break;
            }
        }
        System.out.println("x = "+x);
    }

    private void readImageFile() {
        String imageFilename = Main.ROOT_DIR+IMAGES_DIR+projectname+".bmp";
        File imageFile = new File(imageFilename);
        try {
            image = ImageIO.read(imageFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
