package com.anz.eventplanner.model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class User {	
	
	private String LANId;
	
	public User() {
		setLANId(findLANId());
	}
	
	public String getLANId() {
		return LANId;
	}

	public void setLANId(String lANId) {
		LANId = lANId;
	}
	
	private String findLANId() {
		String methodName = "getUsername";
		String osName = System.getProperty("os.name").toLowerCase();
		String className = null;
		String LANId = "";

		if (osName.contains("windows")) {
			className = "com.sun.security.auth.module.NTSystem";
			methodName = "getName";
		} else if (osName.contains("linux")) {
			className = "com.sun.security.auth.module.UnixSystem";
		} else if (osName.contains("solaris") || osName.contains("sunos")) {
			className = "com.sun.security.auth.module.SolarisSystem";
		}

		if (className != null) {
			Class<?> c;
			try {
				c = Class.forName(className);
				Method method = c.getDeclaredMethod(methodName);
				Object o = c.newInstance();
				LANId = (String) method.invoke(o);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}

		return LANId;
	}
	
}
