package org.api.cardnexus.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class NexusConfig {
	
	private static String token;

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
