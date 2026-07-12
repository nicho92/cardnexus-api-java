package org.api.cardnexus.configuration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class NexusConfig {
	
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
