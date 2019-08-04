package com.langtonsant.factories;

import com.langtonsant.persistancy.GridTextWriter;
import com.langtonsant.persistancy.IGridWriter;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.activation.MimetypesFileTypeMap;

/**
 * This class is a factory that gives us a IGridWriter made for registering a IGrid into a file
 */
public class GridWriterFactory {

    /**
     * Returns a IGridWriter depending on the detected mimetype of the given path
     *
     * @param outputPath the path to write the output file. It is also used to determine the mimetype and the IGridWriter to construct
     * @return The IGridWriter that match the mimetype detected
     */
    public static IGridWriter getGridWriter(String outputPath) {

        MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();
        String mimeType = mimeTypesMap.getContentType(outputPath);

        if (mimeType.equals("text/plain")) {
            return new GridTextWriter(outputPath);
        }

        throw new NotImplementedException();
    }
}
