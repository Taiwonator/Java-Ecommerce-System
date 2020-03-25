package user.control;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import user.control.*;

public class LoginFunctions {
	
	public static User ValidateUsername(String username) {
		List<User> user_list = new ArrayList<>(user.control.UserAccountFunctions.getUserAccounts());
	    User user = null;
	    for(int i = 0; i < user_list.size(); i++) {
	    	if(user_list.get(i).getUsername().equals(username)) {
	    		user = user_list.get(i);
	    	} 
	    }
	    
	    if(user != null) {
	    	System.out.println("Access granted to: " + username);
	    	return user;
	    } else {
	    	System.out.println("Access denied to: " + username);
	    	return null;
	    }
		
	}

}
