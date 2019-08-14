package com.langtonsant.persistancy;

import com.langtonsant.application.element.grid.IGrid;
import com.langtonsant.math.Rectangle;
import com.langtonsant.math.Vector2;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;

public class GridPNGWriter implements IGridWriter {

    private final String outputPath;

    /**
     * Constructor
     *
     * @param outputPath a valid path where to write the PNG file
     */
    public GridPNGWriter(String outputPath){
        this.outputPath = outputPath;
    }

    @Override
    public void writeGrid(IGrid grid) throws IOException {
        int cellPixelSize = 20;
        Rectangle croppedZone = grid.GetArea();

        BufferedImage image = new BufferedImage(croppedZone.getWidth() * cellPixelSize, croppedZone.getHeight() * cellPixelSize, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphic = image.createGraphics();

        graphic.setColor(Color.WHITE);
        graphic.fillRect(0,0,croppedZone.getWidth() * cellPixelSize, croppedZone.getHeight() * cellPixelSize);

        graphic.setColor(Color.BLACK);
        graphic.setBackground(Color.BLACK);
        for (Vector2 position : grid) {
            graphic.fillRect((position.x - croppedZone.getX()) * cellPixelSize, (position.y  - croppedZone.getY())* cellPixelSize, cellPixelSize, cellPixelSize);
        }

        graphic.dispose();

        FileOutputStream os = new FileOutputStream(outputPath);
        ImageIO.write(image, "png", os);
        os.close();
    }
}
