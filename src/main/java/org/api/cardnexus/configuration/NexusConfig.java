package org.api.cardnexus.configuration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.api.cardnexus.listener.URLCallListener;

public class NexusConfig {

    private NexusConfig() {
	/* This utility class should not be instantiated */
    }

    // --- Immutable API constants -------------------------------------------------
    public static final String API_BASE_URL = "https://public-api.cardnexus.com/v1";
    public static final String API_VERSION = "0.13.0";
    public static final String ENV_TOKEN_KEY = "CARDNEXUS_API_KEY";
    public static final String REQ_DATE_PATTERN = "yyyy-MM-dd";
    public static final int INVENTORY_CREATION_LIMIT = 1000;
    
    // --- Mutable configuration state
    // ----------------------------------------------
    private static volatile File tempDirectory = new File(System.getProperty("user.home"));
    private static volatile boolean gsonPrettyPrint = false;
    private static volatile int limitListResults = 200;
    private static volatile boolean checksumMd5Feed = true;
    private static volatile String defaultGameValue;
    private static volatile String acceptLanguage = "en";
    
    private static URLCallListener listener;
    private static volatile String token;
    private static volatile Integer feedRententionDurationDays = 1;

    public static void loadTokenFromEnv() throws IOException {
	var t = System.getenv(ENV_TOKEN_KEY);

	if (t == null)
	    throw new IOException("No Key found in env " + ENV_TOKEN_KEY);

	setToken(t);
    }

    public static void loadTokenFromFile(File f) throws IOException {
	setToken(Files.readString(f.toPath()));
    }

    public static File getTempDirectory() {
	return tempDirectory;
    }

    public static boolean isGsonPrettyPrint() {
	return gsonPrettyPrint;
    }

    public static int getLimitListResults() {
	return limitListResults;
    }

    public static boolean isChecksumMd5Feed() {
	return checksumMd5Feed;
    }

    public static String getDefaultGameValue() {
	return defaultGameValue;
    }

    public static URLCallListener getListener() {
    	return listener;
    }
    public static String getAcceptLanguage() {
		return acceptLanguage;
	}
    
    public static String getToken() {
    	return token;
    }

    public static Integer getFeedRententionDurationDays() {
	return feedRententionDurationDays;
    }
    
    public static void setAcceptLanguage(String acceptLanguage) {
	NexusConfig.acceptLanguage = acceptLanguage;
    }
    
    
    public static void setFeedRententionDurationDays(Integer feedRententionDurationDays) {
	NexusConfig.feedRententionDurationDays = feedRententionDurationDays;
    }
    public static void setTempDirectory(File directory) {
	NexusConfig.tempDirectory = directory;
    }

    public static void setGsonPrettyPrint(boolean gsonPrettyPrint) {
	NexusConfig.gsonPrettyPrint = gsonPrettyPrint;
    }

    public static void setLimitListResults(int limitListResults) {
	NexusConfig.limitListResults = limitListResults;
    }

    public static void setChecksumMd5Feed(boolean checksumMd5Feed) {
	NexusConfig.checksumMd5Feed = checksumMd5Feed;
    }

    public static void setDefaultGameValue(String defaultGameValue) {
	NexusConfig.defaultGameValue = defaultGameValue;
    }

    public static void setListener(URLCallListener listener) {
	NexusConfig.listener = listener;
    }

    public static void setToken(String t) {
	NexusConfig.token = t;
    }

}
