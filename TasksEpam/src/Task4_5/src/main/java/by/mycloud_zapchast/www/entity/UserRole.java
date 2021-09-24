package by.mycloud_zapchast.www.entity;

import java.util.HashMap;
import java.util.Map;

public enum UserRole {
	ADMIN("admin"), EMPLOYER("employer"), MASTER("master"), SUPPLIER("supplier"), USER("user");

	

	private String value;

	private UserRole(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

    private static final Map<String, UserRole> lookup = new HashMap<>();
	static
    {
        for(UserRole userRole : UserRole.values())
        {
            lookup.put(userRole.getValue(), userRole);
        }
    }
	
	public static UserRole of(String value) {
		return lookup.get(value);
	}

}
