package com.mycompany.prog5121;

import java.util.Scanner;

public class PROG5121 {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Login login = new Login();
        User user = new User();

        System.out.println("======User Details Registration=====");

        System.out.print("Enter First Name: ");
        user.firstName = input.nextLine();

        System.out.print("Enter Last Name: ");
        user.lastName = input.nextLine();

        System.out.print("Enter Username: ");
        user.username = input.nextLine();

        if (login.checkUserName(user.username)) {
            System.out.println("Username successfully captured");
        } else {
            System.out.println("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than 5 characters in length.");
        }

        System.out.print("Enter Password: ");
        user.password = input.nextLine();

        if (login.checkPasswordComplexity(user.password)) {
            System.out.println("Password Successfully Captured");
        } else {
            System.out.println("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.");
        }

        System.out.print("Enter Cell Phone (+27...): ");
        user.contactNumber = input.nextLine();

        if (login.checkCellPhoneNumber(user.contactNumber)) {
            System.out.println("Cell phone number successfully added.");
        } else {
            System.out.println("Cell phone number incorrectly formatted or does not contain international code.");
        }

        System.out.println(login.registerUser(user));

        // LOGIN
        System.out.println("\n=== Login ===");

        System.out.print("Enter Username: ");
        String loginUser = input.nextLine();

        System.out.print("Enter Password: ");
        String loginPass = input.nextLine();

        boolean status = login.loginUser(loginUser, loginPass, user);

        System.out.println(login.returnLoginStatus(status, user));
    }
}

// USER CLASS
class User {
    String firstName;
    String lastName;
    String username;
    String password;
    String contactNumber;
}

// LOGIN CLASS
class Login {

    // Validation for the username - when creating
    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    // Validation for User Password when creating
    public boolean checkPasswordComplexity(String password) {
        return password.length() >= 8 &&
               password.matches(".*[A-Z].*") &&
               password.matches(".*[0-9].*") &&
               password.matches(".*[^a-zA-Z0-9].*");
    }

    // Validation for Contact Numbers (+27833374746)
    public boolean checkCellPhoneNumber(String contactNumber) {
        // Format Validation - Its in South Africa contact number format( +27)
        return contactNumber.matches("^\\+27\\d{9}$");
    }

    // User Registration Part
    public String registerUser(User user) {

        if (!checkUserName(user.username)) {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than 5 characters in length.";
        }

        if (!checkPasswordComplexity(user.password)) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }

        if (!checkCellPhoneNumber(user.contactNumber)) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }

        return "Registration successful!";
    }

    // Validation for User Logins
    public boolean loginUser(String inputUsername, String inputPassword, User user) {
        return inputUsername.equals(user.username) && inputPassword.equals(user.password);
    }

    // The Login Message in 2 Ways, Error & Welcome
    public String returnLoginStatus(boolean status, User user) {
        if (status) {
            return "Welcome " + user.firstName + ", " + user.lastName + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again";
        }
    }
}