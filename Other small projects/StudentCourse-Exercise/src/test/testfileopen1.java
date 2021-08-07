package test;

import java.io.IOException;

public class testfileopen1 {

	public static void main(String[] args) throws IOException, InterruptedException {

		var processBuilder = new ProcessBuilder();

		processBuilder.command("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");

		var process = processBuilder.start();

		var ret = process.waitFor();

		System.out.printf("Program exited with code: %d", ret);
	}
}