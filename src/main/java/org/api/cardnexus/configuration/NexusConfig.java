package org.api.cardnexus.configuration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class NexusConfig {
    
        public static final String API_BASE_URL="https://public-api.cardnexus.com/v1"; 
        public static final String API_VERSION="1.3.0";
        public static final int LIMIT_LIST_RESULTS=200;
        public static final String REQ_DATE_PATTERN="YYYY-MM-dd";
     
	private static String token;
	private static File fileDirectory = new File(System.getProperty("user.home"));
	
	public static File getFileDirectory() {
	    return fileDirectory;
	}
	
	public static void setFileDirectory(File fileDirectory) {
	    NexusConfig.fileDirectory = fileDirectory;
	}
	
	public static String getToken()
	{
		return token;
	}
	
	public static void setToken(String t)
	{
		token=t;
	}
	
	public static void loadTokenFromFile(File f) throws IOException
	{
		setToken(Files.readString(f.toPath()));
	}
	
	
	
}
