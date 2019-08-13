package com.langtonsant.persistancy;

import com.langtonsant.application.element.cell.CellType;
import com.langtonsant.application.element.grid.IGrid;
import com.langtonsant.math.Rectangle;
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
        int cellPixelSize = 20;
        Rectangle croppedZone = GetCroppedAreaFromGrid(grid);

        BufferedImage image = new BufferedImage(croppedZone.getWidth() * cellPixelSize, croppedZone.getHeight() * cellPixelSize, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphic = image.createGraphics();

        for(int y = 0; y < croppedZone.getHeight();y++){
            for(int x = 0; x < croppedZone.getWidth(); x++){
                Color c = GetColorFromCellType(grid.getCellAt(x + croppedZone.getX(),y + croppedZone.getY()).getCellType());
                graphic.setColor(c);
                graphic.setBackground(c);
                graphic.fillRect(x*cellPixelSize,y*cellPixelSize,cellPixelSize,cellPixelSize);
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

    /**
     * Define an area that can be used as a cropped area of the grid.
     * The cropped area is an area that surrounds all the colored cells (non white).
     *
     * @param grid the grid from which to define the crop
     * @return a Rectangle matching the area surrounding all colored cells
     */
    private Rectangle GetCroppedAreaFromGrid(IGrid grid){

        int startX=grid.getWidth();
        int startY=grid.getWidth();
        int endX = 0;
        int endY = 0;

        for(int y = 0; y < grid.getHeight(); y++){
            for(int x = 0; x < grid.getWidth(); x++){
                CellType cellType = grid.getCellAt(x,y).getCellType();
                if(cellType == CellType.White)
                    continue;

                if(startX > x)
                    startX = x;
                if (startY > y)
                    startY = y;
                if(endX < x)
                    endX = x;
                if(endY < y)
                    endY = y;
            }
        }
        return new Rectangle(startX,startY,endX - startX,endY - startY);
    }
}
