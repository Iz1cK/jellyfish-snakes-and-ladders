package Model;

import com.google.gson.JsonObject;

public class Admin {
	private String adminName;
	private String password;
	public Admin(String adminName, String password) {
		super();
		this.adminName = adminName;
		this.password = password;
	}
	
	public Admin(JsonObject jsonObject) {
		try {
			fromJSON(jsonObject);
		} catch (Exception e) {

		}
	}

	public void fromJSON(JsonObject jsonObject) {
		this.adminName = jsonObject.get("adminName").getAsString();
		this.password = jsonObject.get("password").getAsString();
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Admin [adminName=" + adminName + ", password=" + password + "]" + '\n';
	}
	
	
}