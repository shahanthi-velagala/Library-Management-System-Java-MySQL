package main;

import java.util.Scanner;

public class AdminLogin {

    public static boolean login() {

        Scanner sc = new Scanner(System.in);

        System.out.print("Username: ");
        String username = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        return username.equals("admin")
                && password.equals("admin123");
    }
}