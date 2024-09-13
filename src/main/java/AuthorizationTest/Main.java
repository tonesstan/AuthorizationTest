package AuthorizationTest;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Title with email: ");
        String title = sc.nextLine();
        System.out.println("Email: " + title.replaceAll(".*<([^>]+)>.*", "$1"));
        sc.close();
    }
}