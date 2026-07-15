package org.api.cardnexus.tools;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.api.cardnexus.configuration.NexusConfig;

public class Formatter {

	public static String format(Double d)
	{
		var otherSymbols = new DecimalFormatSymbols(Locale.getDefault());
		return new DecimalFormat("#0.0#", otherSymbols).format(d);
	}
	
	public static String format(Date d, boolean time)
	{
		return new SimpleDateFormat(NexusConfig.REQ_DATE_PATTERN +(time?" HH:mm:ss":"")).format(d);
	}
	
	
	
}
