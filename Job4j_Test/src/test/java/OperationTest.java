import org.example.Books;
import org.example.Library;
import org.junit.Rule;
import org.junit.Test;
import org.example.Operation;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import java.io.ByteArrayInputStream;
import java.util.Scanner;
import static org.junit.Assert.assertEquals;



public class OperationTest {
    private final String lineSeparator = System.lineSeparator();
    private ByteArrayInputStream testIn;

    @Rule
    public final TextFromStandardInputStream systemInMock = TextFromStandardInputStream.emptyStandardInputStream();

    @Test
    public void testAddBook() {
        String input = "Title\nAuthor\n2000\n300\n";
        systemInMock.provideText(input);
        Library library = new Library();
        Operation operation = new Operation();
        operation.addBook(new Scanner(System.in), library);
        assertEquals(1, library.getBooks().size());
    }

    @Test
    public void testEditBook() {
        String input = "1" + lineSeparator +
                "qwe" + lineSeparator +
                "я" + lineSeparator +
                "2023" + lineSeparator +
                "123" + lineSeparator;

        testIn = new ByteArrayInputStream(input.getBytes());
        System.setIn(testIn);

        Library library = new Library();
        Operation operation = new Operation();
        library.addBook(new Books("qwe", "qwe", 2000, 300));

        Scanner scanner = new Scanner(System.in);

        operation.editBook(scanner, library);

        Books editedBook = library.getBooks().get(0);
        assertEquals("qwe", editedBook.getTitle());
        assertEquals("я", editedBook.getAuthor());
        assertEquals(2023, editedBook.getYear());
        assertEquals(123, editedBook.getPage());
    }

    @Test
    public void testRemoveBook() {
        String input = "1\n";
        systemInMock.provideText(input);
        Library library = new Library();
        Operation operation = new Operation();
        int initialSize = library.getBooks().size();
        operation.removeBook(new Scanner(System.in), library);
        assertEquals(initialSize - 1, library.getBooks().size());
    }
}