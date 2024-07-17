package UserServiceAssignment3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class userLoginApplication {

	public static void main(String[] args) {

		User[] users = new User[4];
		Scanner scanner = new Scanner(System.in);
		File relativeFile = new File("src/UserServiceAssignment3/data.txt");

		try (BufferedReader fileReader = new BufferedReader(new FileReader(relativeFile))) {

			String line;
			int i = 0;
			while ((line = fileReader.readLine()) != null) {
				String[] userData = line.split(",");
				String username = userData[0];
				String password = userData[1];
				String name = userData[2];
				User user = new User(username, password, name);
				users[i] = user;
				i++;
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found" + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			extracted(e);
		}

		int loginCounter = 0;
		int maxAttempts = 5;
		boolean loggedIn = false;

		while (loginCounter < maxAttempts && !loggedIn) {
			System.out.println("Enter your username:");
			String userInputUsername = scanner.nextLine();
			System.out.println("Enter your password:");
			String userInputPassword = scanner.nextLine();

			for (User user : users) {
				if (user.getUsername().equalsIgnoreCase(userInputUsername)) {
					if (user.getPassword().equals(userInputPassword)) {
						System.out.println("Hello " + user.getName());
						loggedIn = true;
						break;
					} else {
						System.out.println("Wrong password. Please try again.");
					}
				}
			}

			if (!loggedIn) {
				loginCounter++;
				System.out.println("Login attempt " + loginCounter + " of " + maxAttempts);
			}
		}

		if (!loggedIn) {
			System.out.println("You are locked out");
		}

		scanner.close();
	}

	private static void extracted(IOException e) {
		throw new RuntimeException(e);
	}
}
