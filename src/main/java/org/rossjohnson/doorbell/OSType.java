package org.rossjohnson.doorbell;

public class OSType {
	
	public static boolean isMac() {
		return compareOS("mac");
	}

	public static boolean isWindows() {
		return compareOS("win");
	}
	
	public static boolean isLinux() {
		return compareOS("linux");
	}
	
	private static boolean compareOS(String os) {
		return System.getProperty("os.name").toLowerCase().contains(os);
	}
	
	public static void main(String[] args) {
		System.out.println("Am I windows? " + isWindows());
	}
}
