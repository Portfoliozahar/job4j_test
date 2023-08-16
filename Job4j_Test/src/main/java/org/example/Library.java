package org.example;



import com.opencsv.*;
import com.opencsv.exceptions.CsvValidationException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Books> books;
    private static final String CSV_FILE_PATH = "library.csv";

    public Library() {
        books = new ArrayList<>();
        loadB();
    }

    public void addBook(Books book) {
        books.add(book);
        saveB();
    }

    public void removeBook(int index) {
        if (index >= 0 && index < books.size()) {
            books.remove(index);
            saveB();
        }
    }

    public List<Books> getBooks() {
        return books;
    }


    private void loadB() {
        try {
            File file = new File(CSV_FILE_PATH);
            if (!file.exists()) {
                file.createNewFile();
            }

            CSVParser parser = new CSVParserBuilder().withSeparator(';').build();

            try (CSVReader reader = new CSVReaderBuilder(new FileReader(CSV_FILE_PATH))
                    .withSkipLines(1)
                    .withCSVParser(parser)
                    .build()) {
                String[] nextLine;
                while ((nextLine = reader.readNext()) != null) {
                    String title = nextLine[0];
                    String author = nextLine[1];
                    int year = Integer.parseInt(nextLine[2]);
                    int page = Integer.parseInt(nextLine[3]);
                    books.add(new Books(title, author, year, page));
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (CsvValidationException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    void saveB() {
        try (CSVWriter writer = (CSVWriter) new CSVWriterBuilder(new FileWriter(CSV_FILE_PATH)).withSeparator(';').build()) {

            String[] header = {"Title", "Author", "Year", "Page"};
            writer.writeNext(header);

            for (Books book : books) {
                String[] data = {book.getTitle(), book.getAuthor(), String.valueOf(book.getYear()), String.valueOf(book.getPage())};
                writer.writeNext(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
