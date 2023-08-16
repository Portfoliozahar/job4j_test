package org.example;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Operation implements OperationInterface {

    @Override

    public   void displayLibrary(Library library) {
        List<Books> books = library.getBooks();
        if (books.isEmpty()) {
            System.out.println("Библиотека пуста.");
        } else {
            System.out.println("У нас есть ");
            for (int i = 0; i < books.size(); i++) {
                System.out.println("Книга ID " + (i + 1) + ":\n" + books.get(i));
            }
        }
    }
    @Override
    public  void addBook(Scanner scanner, Library library) {
        System.out.print("Введите название: ");
        String title = scanner.nextLine();
        System.out.print("Введите автора: ");
        String author = scanner.nextLine();

        int year;
        while (true) {
            System.out.print("Введите год издания: ");
            try {
                year = scanner.nextInt();
                scanner.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Ошибка: Введите корректный год.");
                scanner.nextLine();
            }
        }

        int pageCount;
        while (true) {
            System.out.print("Введите количество страниц: ");
            try {
                pageCount = scanner.nextInt();
                if (pageCount < 0) {
                    System.out.println("Ошибка: Количество страниц не может быть отрицательным.");
                } else {
                    scanner.nextLine();
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Ошибка: Введите корректное количество страниц.");
                scanner.nextLine(); // Очищаем буфер после ошибки
            }
        }

        LocalDate currentDate = LocalDate.now();
        LocalDate inputDate = LocalDate.of(year, 1, 1);
        if (inputDate.isAfter(currentDate)) {
            System.out.println("Ошибка: Дата публикации не может быть в будущем.");
            return;
        }

        library.addBook(new Books(title, author, year, pageCount));
        System.out.println("Книга добавлена успешно.");
    }
    @Override
    public void editBook(Scanner scanner, Library library) {
        displayLibrary(library);
        System.out.print("Введите ID книги для редактирования: ");
        int index = scanner.nextInt();
        scanner.nextLine();

        if (index >= 1 && index <= library.getBooks().size()) {
            Books book = library.getBooks().get(index - 1);
            System.out.println("Редактирование книги " + index + ":");
            System.out.println(book);

            System.out.print("Введите новое название: ");
            String newTitle = scanner.nextLine();
            System.out.print("Введите нового автора: ");
            String newAuthor = scanner.nextLine();

            int newYear;
            while (true) {
                System.out.print("Введите новую дату: ");
                try {
                    newYear = scanner.nextInt();
                    scanner.nextLine();
                    break;
                } catch (Exception e) {
                    System.out.println("Ошибка: Введите корректный год.");
                    scanner.nextLine();
                }
            }

            int newPageCount;
            while (true) {
                System.out.print("Введите новое количество страниц: ");
                try {
                    newPageCount = scanner.nextInt();
                    if (newPageCount < 0) {
                        System.out.println("Ошибка: Количество страниц не может быть отрицательным.");
                    } else {
                        scanner.nextLine();
                        break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Ошибка: Введите корректное количество страниц.");
                    scanner.nextLine();
                }
            }

            LocalDate currentDate = LocalDate.now();
            LocalDate inputDate = LocalDate.of(newYear, 1, 1);
            if (inputDate.isAfter(currentDate)) {
                System.out.println("Ошибка: Дата публикации не может быть в будущем.");
                return;
            }

            book.setTitle(newTitle);
            book.setAuthor(newAuthor);
            book.setYear(newYear);
            book.setPageCount(newPageCount);

            library.saveBooksToCSV();

            System.out.println("Книга отредактирована успешно.");
        } else {
            System.out.println("Неверный ID книги.");
        }
    }

    @Override
    public  void removeBook(Scanner scanner, Library library) {
        displayLibrary(library);
        System.out.print("Введите ID книги для удаления: ");
        int index = scanner.nextInt();
        scanner.nextLine();

        if (index >= 1 && index <= library.getBooks().size()) {
            library.removeBook(index - 1);
            System.out.println("Книга удалена успешно.");
        } else {
            System.out.println("Неврный ID книги.");
        }
    }
}
