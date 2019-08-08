package com.langtonsant.factories;

import com.langtonsant.persistancy.GridPNGWriter;
import com.langtonsant.persistancy.GridTextWriter;
import com.langtonsant.persistancy.IGridWriter;

import javax.activation.MimetypesFileTypeMap;

/**
 * This class is a factory that gives us a IGridWriter made for registering a IGrid into a file
 */
public class GridWriterFactory {

    /**
     * Returns a IGridWriter depending on the detected mime type of the given path
     *
     * @param outputPath the path to write the output file. It is also used to determine the mime type and the IGridWriter to construct
     * @return The IGridWriter that match the mime type detected
     */
    public static IGridWriter getGridWriter(String outputPath) {

        MimetypesFileTypeMap mimeTypesMap = GetMimeTypesMap();
        String mimeType = mimeTypesMap.getContentType(outputPath);

        if (mimeType.equals(MimeType.TEXT)) {
            return new GridTextWriter(outputPath);
        }else if (mimeType.equals(MimeType.PNG)){
            return new GridPNGWriter(outputPath);
        }

        throw new IllegalArgumentException("The specified type of the file given in the path is not supported");
    }

    /**
     * Get a mime type mapper initialized with all supported mime types.
     *
     * @return an initialized MimeTypesFileTypeMap with all the supported mime types
     */
    private static MimetypesFileTypeMap GetMimeTypesMap(){
        MimetypesFileTypeMap mime = new MimetypesFileTypeMap();
        mime.addMimeTypes(MimeType.TEXT);
        mime.addMimeTypes(MimeType.PNG+ " png");
        return mime;
    }

    /**
     * Defines all supported mime types
     */
    private static final class MimeType{
        static final String TEXT = "text/plain";
        static final String PNG = "image/png";
    }
}
