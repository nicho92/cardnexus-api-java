package org.api.cardnexus.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.util.HexFormat;
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
    
    public static boolean md5(String md5,File f) 
    {
    	if(!NexusConfig.CHECKSUM_MD5_FEED)
    	{
    		logger.warn("Carreful, MD5 checksum for {} is disabled. You can change value by set CHECKSUM_MD5_FEED=true",f);
    		return true;
    	}
    	
    	try {
    	 	MessageDigest digest = MessageDigest.getInstance("MD5");
    	    byte[] hash = digest.digest(Files.readAllBytes(f.toPath()));
    	    return HexFormat.of().formatHex(hash).toUpperCase().equalsIgnoreCase(md5);
    	}
    	catch(Exception ex)
    	{
    		logger.error(ex);
    		return false;
    	}
    }
    
    
    
    public static File download(URL url,EnumFeedKey key) throws IOException
    {
	var f = new File(NexusConfig.getFileDirectory(), key.name() + ".gz");
	
	try (var urlStream = url.openStream(); BoundedInputStream stream = BoundedInputStream.builder().setInputStream(urlStream).get()) 
	{
	    FileUtils.copyInputStreamToFile(stream, f);
	    logger.debug("Downloaded {} bytes", f.length());
	    return f;
	}
    }
	
    public static File ungzip(File gzFile, File destination) throws IOException {

	 if (!destination.exists()) {
	        destination.mkdirs();
	    }

	    var fileName = gzFile.getName().replaceFirst("\\.gz$", ".ndjson");
	    var output = new File(destination, fileName);

	    try (var gis = new GZIPInputStream(new FileInputStream(gzFile));var fos = new FileOutputStream(output)) 
	    {
	        gis.transferTo(fos);
	    }

	    Files.delete(gzFile.toPath());
	    logger.debug("ungzip {} to {}", gzFile, output);
	    return output;	
	    }
    
}
