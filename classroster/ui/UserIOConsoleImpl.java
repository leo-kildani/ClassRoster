package classroster.ui;

import java.util.Scanner;

public class UserIOConsoleImpl implements UserIO {
	private Scanner scnr = new Scanner(System.in);
	
	public void print(String msg) {
		System.out.println(msg);
	}
	
	public String readString(String prompt) {
		System.out.print(prompt);
		return scnr.nextLine();
	}
	
	public String readString(String prompt, String target) {
		String res = this.readString(prompt);
		while (!res.equals(target)) {
			res = this.readString(prompt);
		}
		return res;
	}

	public int readInt(String prompt) {
		int res;
		while (true) {
			try {
				System.out.print(prompt);
				res = Integer.parseInt(scnr.nextLine());
				break;
			} catch (NumberFormatException e) {
				System.out.println("Input Error. Please try again.");
			}
		}
		return res;
	}

	public int readInt(String prompt, int min, int max) {
		int res;
		do {
			res = readInt(prompt);
		} while (res < min || res > max);
		return res;
	}

	public long readLong(String prompt) {
		long res;
		while (true) {
			try {
				System.out.print(prompt);
				res = Long.parseLong(scnr.nextLine());
				break;
			} catch (NumberFormatException e) {
				System.out.println("Input Error. Please try again.");
			}
		}
		return res;
	}

	public long readLong(String prompt, long min, long max) {
		long res;
		do {
			res = readLong(prompt);
		} while (res < min || res > max);
		return res;
	}

	public double readDouble(String prompt) {
		double res;
		while (true) {
			try {
				System.out.print(prompt);
				res = Double.parseDouble(scnr.nextLine());
				break;
			} catch (NumberFormatException e) {
				System.out.println("Input Error. Please try again.");
			}
		}
		return res;
	}

	public double readDouble(String prompt, int min, int max) {
		double res;
		do {
			res = readLong(prompt);
		} while (res < min || res > max);
		return res;
	}

	public float readFloat(String prompt) {
		float res;
		while (true) {
			try {
				System.out.print(prompt);
				res = Float.parseFloat(scnr.nextLine());
				break;
			} catch (NumberFormatException e) {
				System.out.println("Input Error. Please try again.");
			}
		}
		return res;
	}

	public float readFloat(String prompt, float min, float max) {
		float res;
		do {
			res = readLong(prompt);
		} while (res < min || res > max);
		return res;
	}

	public void close() {
		scnr.close();
	}

}
