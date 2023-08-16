package org.example;

import java.util.Scanner;

public interface OperationInterface {

    void displayLibrary(Library library);

    void addBook(Scanner scanner, Library library);

    void editBook(Scanner scanner, Library library);

    void removeBook(Scanner scanner, Library library);
}