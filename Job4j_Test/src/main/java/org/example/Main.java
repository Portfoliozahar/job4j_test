package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();
        Operation operation = new Operation();
        while (true) {
            System.out.println("Меню:");
            System.out.println("1. Показать библиотеку");
            System.out.println("2. Добавить книгу");
            System.out.println("3. Редактировать книгу");
            System.out.println("4. Удалить книгу");
            System.out.println("5. Выход");
            System.out.print("Выберите опцию: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> operation.displayLibrary(library);
                    case 2 -> operation.addBook(scanner, library);
                    case 3 -> operation.editBook(scanner, library);
                    case 4 -> operation.removeBook(scanner, library);
                    case 5 -> {
                        System.out.println("Выход из приложения...");
                        System.exit(0);
                    }
                    default -> System.out.println("Пожалуйста, введите число.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Пожалуйста, введите доступное число.");
                scanner.nextLine();
            }
        }
    }
}
