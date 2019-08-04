package com.langtonsant.factories;

import com.langtonsant.persistancy.GridTextWriter;
import com.langtonsant.persistancy.IGridWriter;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.activation.MimetypesFileTypeMap;
import java.io.IOException;

public class GridWriterFactory {

    public static IGridWriter getGridWriter(String outputPath) throws IOException {

        MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();
        String mimeType = mimeTypesMap.getContentType(outputPath);

        if(mimeType.equals("text/plain"))
            return new GridTextWriter(outputPath);

        throw new NotImplementedException();
    }
}
