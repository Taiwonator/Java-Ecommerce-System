package user.control;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class UserAccountFunctions {

	public static List<User> getUserAccounts() {
		List<User> user_list = new ArrayList<User>();
		boolean error = false;
		
		try {
			File file = new File("text/UserAccounts.txt");
			Scanner file_scanner;
			file_scanner = new Scanner(file);
			//Keeps searching till file has no more lines
			while(file_scanner.hasNextLine()) {
				String data = file_scanner.nextLine();
				//Returns a line of data in an array
				List<String> data_listed = returnList(data);
				//Creates a user from the data in a line
				user_list.add(formatUserInfo(data_listed));
			}
			file_scanner.close();
		} catch (FileNotFoundException e) { // Error will be thrown if the file cannot be found
			e.printStackTrace();
			error = true;
		}
		
		if(!error) {
		    return user_list;
		} else {
		    return null;
		}

	}
	
	public static User formatUserInfo(List<String> data) {
		User user = new User();
		
        user.setID(Integer.parseInt(data.get(0)));
        user.setUsername(data.get(1));
        user.setSurname(data.get(2));
        user.setAddress(Integer.parseInt(data.get(3)), data.get(4), data.get(5));
        user.setUserType(data.get(6));    
        
		return user;
	}
	
	public static List<String> returnList(String line) {
		List<String> items = Arrays.asList(line.split("\\s*,\\s*"));
		return items;
	}
 
}
