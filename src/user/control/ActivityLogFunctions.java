package user.control;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ActivityLogFunctions {
	
	
	public static void writeToLog(ActivityLog log) throws IOException {
			
		File file = new File("text/ActivityLog.txt");
		String line;
		if(file.length() == 0) {
			line = formatLog(log); 
		} else {
			line = "\r\n" + formatLog(log); 
		}
		
	    FileOutputStream output_stream = new FileOutputStream("text/ActivityLog.txt", true);
	    byte[] str_to_bytes = line.getBytes();
	    output_stream.write(str_to_bytes);
	    output_stream.close();
	    
	}
	
	private static String formatLog(ActivityLog log) {
		String output = "";
		
		output += log.getUserId() + ", ";
		output += log.getPostcode() + ", ";
		output += log.getBarcode() + ", ";
		output += log.getRetailPrice() + ", ";
		output += log.getQuantity() + ", ";
		output += log.getStatus() + ", ";
		output += log.getPaymentMethod() + ", ";
		output += log.getDate();
		
		return output;
	}
	
	
}
