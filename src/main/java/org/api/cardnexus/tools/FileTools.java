package org.api.cardnexus.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.zip.GZIPInputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.input.BoundedInputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.api.cardnexus.configuration.NexusConfig;
import org.api.cardnexus.model.enums.EnumFeedKey;

public class FileTools {
    
    private FileTools() {
        /* This utility class should not be instantiated */
    }


    private static Logger logger = LogManager.getLogger(FileTools.class);
    
    public static File download(URL url,EnumFeedKey key) throws IOException
    {
	
	try (var urlStream = url.openStream(); BoundedInputStream stream = BoundedInputStream.builder().setInputStream(urlStream).get()) 
	{
	    var f = new File(NexusConfig.getFileDirectory(), key.name() + ".gz");
	    FileUtils.copyInputStreamToFile(stream, f);
	    logger.debug("Downloaded {} bytes", f.length());
	    return f;
	}
    }
	
    public static File ungzip(File gzFile, File destination) throws IOException {

	 if (!destination.exists()) {
	        destination.mkdirs();
	    }

	    String fileName = gzFile.getName().replaceFirst("\\.gz$", ".ndjson");
	    File output = new File(destination, fileName);

	    try (var gis = new GZIPInputStream(new FileInputStream(gzFile));var fos = new FileOutputStream(output)) 
	    {
	        gis.transferTo(fos);
	    }

	    Files.delete(gzFile.toPath());
	    logger.debug("ungzip {} to {}", gzFile, output);
	    return output;	}
    
}
