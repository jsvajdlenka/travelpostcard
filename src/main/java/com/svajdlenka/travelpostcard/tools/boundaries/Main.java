package com.svajdlenka.travelpostcard.tools.boundaries;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Main {
    public static final String ROOT_DIR = "src/test/resources/boundaries/";

    private ImageProperties properties;
    private ImageProcessor processor;

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
        processor = new ImageProcessor(projectname, properties);
    }

    private void processImage() {
        processor.processImageBounds();
        processor.processInlineRectangle();
    }
    private static void usage() {
        System.out.println("Usage: Main propertiesfile");
    }
}
