package org.example;

import java.util.Scanner;

public interface LibraryInterface {

    void displayLibrary(Library library);

    void addBook(Scanner scanner, Library library);

    void editBook(Scanner scanner, Library library);

    void removeBook(Scanner scanner, Library library);
}