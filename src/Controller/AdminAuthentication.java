package Controller;

import Model.Sysdata;
import java.util.List;
import Model.Admin;

public class AdminAuthentication {

	public static boolean authenticateAdmin(String username, String password) {
		Sysdata.getInstance().readAdmins();
		List<Admin> admins = Sysdata.getInstance().getAdmins();
		if(admins==null)
			return false;
		for(Admin a: admins)
		{
			if (a.getAdminName().equals(username)&&a.getPassword().equals(password))
				return true;
		}
		return false;
		
	}
}

