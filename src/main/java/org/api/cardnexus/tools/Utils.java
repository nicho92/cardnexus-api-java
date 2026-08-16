package org.api.cardnexus.tools;

import java.awt.Image;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.imageio.ImageIO;

import org.api.cardnexus.configuration.NexusConfig;

public class Utils {

	public static String format(Double d)
	{
	    	if(d==null)
	    	    return "";
		var otherSymbols = new DecimalFormatSymbols(Locale.getDefault());
		return new DecimalFormat("#0.0#", otherSymbols).format(d);
	}
	
	public static String format(Date d, boolean time)
	{
		return new SimpleDateFormat(NexusConfig.REQ_DATE_PATTERN +(time?" HH:mm:ss":"")).format(d);
	}
	
	public static Image getNexusImage()
	{
	    try {
		return ImageIO.read(Utils.class.getResource("/icon.png"));
	    } catch (IOException e) {
		e.printStackTrace();
		return null;
	    }
	}
	
	
	
}
