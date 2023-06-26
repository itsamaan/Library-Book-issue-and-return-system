import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Book {
    private int bookId;
    private String title;
    private boolean available;

    public Book(int bookId, String title) {
        this.bookId = bookId;
        this.title = title;
        this.available = true;
    }

    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}

class Library {
    private Map<Integer, Book> books;

    public Library() {
        this.books = new HashMap<>();
    }

    public void addBook(Book book) {
        books.put(book.getBookId(), book);
    }

    public List<Book> getBooks() {
        return new ArrayList<>(books.values());
    }

    public void issueBook(int bookId) {
        Book book = books.get(bookId);
        if (book != null && book.isAvailable()) {
            book.setAvailable(false);
            System.out.println("Book with ID " + bookId + " has been issued.");
        } else {
            System.out.println("Book with ID " + bookId + " is not available.");
        }
        System.out.println();
    }

    public void returnBook(int bookId) {
        Book book = books.get(bookId);
        if (book != null && !book.isAvailable()) {
            book.setAvailable(true);
            System.out.println("Book with ID " + bookId + " has been returned.");
        } else {
            System.out.println("Invalid book ID or the book is already available.");
        }
        System.out.println();
    }
}

class LibraryAppGUI extends JFrame {
    private Library library;
    private JTextArea outputTextArea;

    public LibraryAppGUI() {
        library = new Library();

        // Add sample books to the library
        library.addBook(new Book(1, "To Kill a MAn"));
        library.addBook(new Book(2, "1989"));
        library.addBook(new Book(3, "The Great Statue"));
        // Add more books with realistic names
        library.addBook(new Book(4, "Pride"));
        library.addBook(new Book(5, "The Catcher in the Forest"));
        // Add 100 books with realistic names
        library.addBook(new Book(6, "The Hobbit"));
        library.addBook(new Book(7, "Brave New World"));
        library.addBook(new Book(8, "The Tempest"));
        library.addBook(new Book(9, "The New World"));
        library.addBook(new Book(10, "The Ship"));
        library.addBook(new Book(11, "Brave Man"));
        library.addBook(new Book(12, "The Hobbit"));
        library.addBook(new Book(13, "Innocent Man"));
        library.addBook(new Book(14, "The life"));
        library.addBook(new Book(15, "Turning life"));
        library.addBook(new Book(16, "Hard Earned Respect"));
        library.addBook(new Book(17, "Tech and Life"));
        library.addBook(new Book(18, "Technological Life"));
        library.addBook(new Book(19, "Ships and Lands"));
        library.addBook(new Book(20, "The Actress"));
        library.addBook(new Book(21, "Brave Woman"));
        library.addBook(new Book(22, "The frong"));
        library.addBook(new Book(23, "Environmental Sci"));
        library.addBook(new Book(24, "Science and Tech."));
        library.addBook(new Book(25, "Royal Life"));

        setTitle("Library Book Issue and Return System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        createComponents();
    }

    private void createComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        outputTextArea = new JTextArea();
        outputTextArea.setEditable(false);
        outputTextArea.setFont(new Font("Arial", Font.PLAIN, 14)); // Custom font style and size
        JScrollPane scrollPane = new JScrollPane(outputTextArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton displayBooksButton = new JButton("Display Available Books");
        displayBooksButton.setFont(new Font("Arial", Font.BOLD, 16)); // Custom font style and size
        displayBooksButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayAvailableBooks();
            }
        });
        buttonPanel.add(displayBooksButton);
        JButton issueBookButton = new JButton("Issue a Book");
        issueBookButton.setFont(new Font("Arial", Font.BOLD, 16)); // Custom font style and size
        issueBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                issueBook();
            }
        });
        buttonPanel.add(issueBookButton);

        JButton returnBookButton = new JButton("Return a Book");
        returnBookButton.setFont(new Font("Arial", Font.BOLD, 16)); // Custom font style and size
        returnBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                returnBook();
            }
        });
        buttonPanel.add(returnBookButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        add(panel);
    }

    private void displayAvailableBooks() {
        StringBuilder sb = new StringBuilder();
        sb.append("Available Books:\n");
        for (Book book : library.getBooks()) {
            if (book.isAvailable()) {
                sb.append("Book ID: ").append(book.getBookId()).append(", Title: ").append(book.getTitle()).append("\n");
            }
        }
        sb.append("\n");
        outputTextArea.setText(sb.toString());
    }

    private void issueBook() {
        String input = JOptionPane.showInputDialog(this, "Enter the Book ID to issue:");
        if (input != null && !input.isEmpty()) {
            try {
                int bookId = Integer.parseInt(input);
                library.issueBook(bookId);
                displayAvailableBooks();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid Book ID.");
            }
        }
    }

    private void returnBook() {
        String input = JOptionPane.showInputDialog(this, "Enter the Book ID to return:");
        if (input != null && !input.isEmpty()) {
            try {
                int bookId = Integer.parseInt(input);
                library.returnBook(bookId);
                displayAvailableBooks();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid Book ID.");
            }
        }
    }
}

public class LibraryApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                LibraryAppGUI app = new LibraryAppGUI();
                app.setVisible(true);
            }
        });
    }
}

