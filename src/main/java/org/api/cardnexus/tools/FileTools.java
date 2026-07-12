package org.api.cardnexus.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.input.BoundedInputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.api.cardnexus.configuration.NexusConfig;
import org.api.cardnexus.model.enums.EnumFeedKey;

public class FileTools {

    private static Logger logger = LogManager.getLogger(FileTools.class);

    
    
    
    public static File download(URL url,EnumFeedKey key) throws IOException
    {
	var stream = BoundedInputStream.builder().setInputStream(url.openStream()).get();
	var f=  new File(NexusConfig.getFileDirectory(),key.name()+".zip");
	FileUtils.copyInputStreamToFile(stream, f);
	return f;
    }
	
    public static void unZipIt(File src, File dst) {
	logger.debug("unzip : {} to {}", src.getAbsoluteFile(),dst.getAbsoluteFile());
	var buffer = new byte[1024];
	try (var zis = new ZipInputStream(new FileInputStream(src))) {
		var ze = zis.getNextEntry();
		while (ze != null) {
			try (var fos = new FileOutputStream(dst)) {
			    	int len;
				while ((len = zis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}
				ze = zis.getNextEntry();
			}
		}
		
		boolean del = FileUtils.deleteQuietly(src);
		logger.debug("removing {}={}", src, del);
		
	} catch (IOException ex) {
		logger.error(ex);
	}



}
    
}
