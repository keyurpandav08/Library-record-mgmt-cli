package com.library;

import com.library.model.*;
import com.library.service.Librarian;
import com.library.service.Library;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Librarian librarian = new Librarian("Mr. Sharma");
        Library library = new Library(librarian);

        int choice;
        do {
            System.out.println("\n========= Library Menu =========");
            System.out.println("1. Add Book (Librarian only)");
            System.out.println("2. Add User (Librarian only)");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Show All Books");
            System.out.println("6. Show Users and Borrowed Books");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter Book ID: ");
                    int id = sc.nextInt();
                    sc.nextLine(); // consume newline
                    System.out.print("Enter Book Title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter Author: ");
                    String author = sc.nextLine();
                    System.out.print("Enter Quantity: ");
                    int qty = sc.nextInt();

                    Book book = new Book(id, title, author, qty);
                    library.addBook(librarian, book);
                }
                case 2 -> {
                    System.out.print("Enter User ID: ");
                    int uid = sc.nextInt();
                    sc.nextLine(); // consume newline
                    System.out.print("Enter User Name: ");
                    String uname = sc.nextLine();

                    User user = new User(uid, uname);
                    library.addUser(librarian, user);
                }
                case 3 -> {
                    System.out.print("Enter User ID: ");
                    int uid = sc.nextInt();
                    System.out.print("Enter Book ID: ");
                    int bid = sc.nextInt();
                    library.issueBook(uid, bid);
                }
                case 4 -> {
                    System.out.print("Enter User ID: ");
                    int uid = sc.nextInt();
                    System.out.print("Enter Book ID: ");
                    int bid = sc.nextInt();
                    library.returnBook(uid, bid);
                }
                case 5 -> library.showBooks();
                case 6 -> library.showUsers();
                case 0 -> System.out.println("Exiting Library System... Goodbye!");
                default -> System.out.println("❌ Invalid choice. Try again.");
            }
        } while (choice != 0);

        sc.close();
    }
}
