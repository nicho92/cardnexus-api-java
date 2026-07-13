package org.api.cardnexus.configuration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class NexusConfig {
        private NexusConfig() {
                /* This utility class should not be instantiated */
        }

    
        public static final String API_BASE_URL="https://public-api.cardnexus.com/v1"; 
        public static final String API_VERSION="0.4.0";
        public static final String ENV_TOKEN_KEY="CARDNEXUS_API_KEY";
        public static final int LIMIT_LIST_RESULTS=200;
        public static final String REQ_DATE_PATTERN="YYYY-MM-dd";
     
	private static String token;
	private static File fileDirectory = new File(System.getProperty("user.home"));
	
	public static File getFileDirectory() {
	    return fileDirectory;
	}
	
	public static void setFileDirectory(File f) {
	    fileDirectory = f;
	}
	
	public static String getToken()
	{
		return token;
	}
	
	
	public static void setToken(String t)
	{
		token=t;
	}
	
	public static void loadTokenFromEnv() throws IOException
	{
	    var t = System.getenv(ENV_TOKEN_KEY);
	    
	  	if(t==null)
	  	    throw new IOException("No Key found in env "+ENV_TOKEN_KEY);
	    
		setToken(t);
	}
	
	
	public static void loadTokenFromFile(File f) throws IOException
	{
		setToken(Files.readString(f.toPath()));
	}
	
	
	
}
