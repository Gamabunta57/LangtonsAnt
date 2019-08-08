package com.langtonsant.persistancy;

import com.langtonsant.application.element.cell.CellType;
import com.langtonsant.application.element.grid.IGrid;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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
        int width = grid.getWidth();
        int height = grid.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics2D graphic = image.createGraphics();
        graphic.clearRect(0,0,width, height);

        for(int y = 0; y < height;y++){
            for(int x = 0; x < width; x++){
                Color c = GetColorFromCellType(grid.getCellAt(x,y).getCellType());
                graphic.setColor(c);
                graphic.drawRect(x,y,0,0);
            }
        }

        graphic.dispose();

        FileOutputStream os = new FileOutputStream(outputPath);
        ImageIO.write(image, "png", os);
        os.close();
    }

    /**
     * Return an arbitrary color based on the given cell type
     *
     * @param cellType The cell type on which to define the color
     * @return the color matching the cell type
     */
    private Color GetColorFromCellType(CellType cellType){
        switch (cellType){
            case Black:
                return Color.black;
            case White:
                return Color.white;
        }
        throw new NotImplementedException();
    }
}
