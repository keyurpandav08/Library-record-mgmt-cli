package com.library.service;

import com.library.model.*;
import java.util.*;

public class Library {
    private List<Book> books = new ArrayList<>();
    private List<User> users = new ArrayList<>();
    private Librarian librarian;

    public Library(Librarian librarian) {
        this.librarian = librarian;
    }

    // Add Book (Librarian only)
    public void addBook(Librarian lib, Book book) {
        if (lib.equals(this.librarian)) {
            books.add(book);
            System.out.println("Book added: " + book);
        } else {
            System.out.println("Only librarian can add books!");
        }
    }

    // Add User (Librarian only)
    public void addUser(Librarian lib, User user) {
        if (lib.equals(this.librarian)) {
            users.add(user);
            System.out.println("User added: " + user);
        } else {
            System.out.println("Only librarian can add users!");
        }
    }

    // Issue Book
    public void issueBook(int userId, int bookId) {
        User user = findUser(userId);
        Book book = findBook(bookId);

        if (user != null && book != null && book.getQuantity() > 0) {
            user.borrowBook(book);
            book.setQuantity(book.getQuantity() - 1);
            System.out.println(user.getName() + " borrowed " + book.getTitle());
        } else {
            System.out.println("Book not available or User not found.");
        }
    }

    // Return Book
    public void returnBook(int userId, int bookId) {
        User user = findUser(userId);
        Book book = findBook(bookId);

        if (user != null && book != null && user.getBorrowedBooks().contains(book)) {
            user.returnBook(book);
            book.setQuantity(book.getQuantity() + 1);
            System.out.println(user.getName() + " returned " + book.getTitle());
        } else {
            System.out.println("Invalid return request.");
        }
    }

    // Show All Books
    public void showBooks() {
        books.forEach(System.out::println);
    }

    // Show Users and Borrowed Books
    public void showUsers() {
        for (User u : users) {
            System.out.println(u);
            for (Book b : u.getBorrowedBooks()) {
                System.out.println("   -> " + b.getTitle());
            }
        }
    }

    // Helper Methods
    private User findUser(int id) {
        return users.stream().filter(u -> u.getUserId() == id).findFirst().orElse(null);
    }

    private Book findBook(int id) {
        return books.stream().filter(b -> b.getId() == id).findFirst().orElse(null);
    }
}
