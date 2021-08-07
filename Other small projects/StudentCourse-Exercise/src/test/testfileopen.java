package test;

import java.io.IOException;

public class testfileopen {
	public static void main(String args[]) {

		try {
			Runtime runtime = Runtime.getRuntime();
			runtime.exec("C:\\Program Files\\Notepad++\\notepad++.exe C:\\Users\\joker\\Desktop\\ght.txt");
		} catch (IOException ioe) {
			System.err.println("Unable to open: ");
		}

	}
}
