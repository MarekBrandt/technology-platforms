package com.rushkappa;

import org.apache.commons.lang3.tuple.Pair;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class ImageEditor {

    public static Pair<Path, BufferedImage> loadImage(Path path) {
        try {
            BufferedImage image = ImageIO.read(path.toFile());
            return Pair.of(path.getFileName(), image);
        } catch (IOException e) {
            System.out.println("Couldn't open image file!");
            e.printStackTrace();
        }
        return null;
    }


    public static Pair<Path, BufferedImage> defaultEdit(Pair<Path, BufferedImage> imagePair) {

        BufferedImage outputImage = new BufferedImage(imagePair.getRight().getWidth(), imagePair.getRight().getHeight(),
                imagePair.getRight().getType() );

        for (int i = 0; i < imagePair.getRight().getWidth(); i++) {
            for (int j = 0; j < imagePair.getRight().getHeight(); j++) {
                int rgb = imagePair.getRight().getRGB(i, j);

                Color color = new Color(rgb);
                int grey = (int)(color.getRed() * 0.299 + color.getBlue() * 0.587 + color.getGreen() * (1-0.299-0.587));
                Color outColor = new Color(grey, grey, grey);
                int outRgb = outColor.getRGB();
                outputImage.setRGB(i, j, outRgb);
            }
        }

        Path path = Path.of("edited", imagePair.getLeft().getFileName().toString());
        return Pair.of(path, outputImage);
    }
    public static void saveEdited(Pair<Path, BufferedImage> imageData) {
        try {
            //System.out.println("Saving to " + imageData.getLeft().toString());
            File outputFile = new File(imageData.getLeft().toString());
            outputFile.getParentFile().mkdirs();
            ImageIO.write(imageData.getRight(), "jpg", outputFile);
            //System.out.println(outputFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
