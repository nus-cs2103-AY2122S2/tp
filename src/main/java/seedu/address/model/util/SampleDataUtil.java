package seedu.address.model.util;

import static seedu.address.model.book.BookStatusType.BORROWED;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.LibTask;
import seedu.address.model.ReadOnlyLibTask;
import seedu.address.model.book.Author;
import seedu.address.model.book.Book;
import seedu.address.model.book.BookName;
import seedu.address.model.book.BookStatus;
import seedu.address.model.book.Isbn;
import seedu.address.model.patron.Email;
import seedu.address.model.patron.Id;
import seedu.address.model.patron.Name;
import seedu.address.model.patron.Patron;
import seedu.address.model.patron.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code LibTask} with sample data.
 */
public class SampleDataUtil {

    public static final long SAMPLE_BOOK_CREATED_TIME = 1646989653388L;
    public static final BookStatus SAMPLE_AVAILABLE_STATUS = BookStatus.createAvailableBookStatus();

    private static final BookStatus ALGORITHMS_STATUS = new BookStatus(BORROWED, Optional.of(getSamplePatrons()[0]),
            Optional.of("17-Mar-2022"), Optional.of("28-Mar-2022"));
    private static final BookStatus SEMAPHORE_STATUS = BookStatus.createAvailableBookStatus();
    private static final BookStatus MAZE_RUNNER_STATUS = BookStatus.createAvailableBookStatus();
    private static final BookStatus AI_STATUS = new BookStatus(BORROWED, Optional.of(getSamplePatrons()[0]),
            Optional.of("14-Mar-2022"), Optional.of("28-Mar-2022"));
    private static final BookStatus CINDERELLA_STATUS = BookStatus.createAvailableBookStatus();

    public static BookStatus getSampleBorrowedStatus() {
        return new BookStatus(BORROWED,
                Optional.ofNullable(getSamplePatrons()[0]),
                Optional.ofNullable("14-Feb-2022"),
                Optional.ofNullable("28-Feb-2022"));
    }

    public static Patron[] getSamplePatrons() {
        return new Patron[] {
            new Patron(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Id("A0123451H"),
                getTagSet("computing")),
            new Patron(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Id("A0123452H"),
                getTagSet("science", "graduate")),
            new Patron(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Id("A0123453H"),
                getTagSet("business")),
            new Patron(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Id("A0123454H"),
                getTagSet("fass")),
            new Patron(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Id("A0123455H"),
                getTagSet("computing")),
            new Patron(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Id("A0123456H"),
                getTagSet("science"))
        };
    }

    public static Book[] getSampleBooks() {
        return new Book[] {
            new Book(new BookName("Harry Potter and The Philosopher's Stone"), new Isbn("978-71617-018-8-5"),
                getAuthorList("J. K. Rowling"),
                getTagSet("Adventure", "Magic"), SAMPLE_BOOK_CREATED_TIME,
                    SAMPLE_AVAILABLE_STATUS, new HashSet<>()),
            new Book(new BookName("The Hunger Games: MockingJay"), new Isbn("9786029293883"),
                getAuthorList("Suzanne Collins"),
                getTagSet("Thriller", "Scifi", "Adventure"), SAMPLE_BOOK_CREATED_TIME,
                    getSampleBorrowedStatus(), new HashSet<>()),
            new Book(new BookName("Introduction to Algorithms"), new Isbn("978-03-71-88850-6"),
                getAuthorList("Cormen", "Leiserson", "Rivest", "Stein"),
                getTagSet("ComputerScience", "Mathematics"), SAMPLE_BOOK_CREATED_TIME,
                    ALGORITHMS_STATUS, getRequesters().get(0)),
            new Book(new BookName("The Little Book of Semaphores"), new Isbn("4992719864"),
                getAuthorList(),
                getTagSet("ComputerScience", "Technology"), SAMPLE_BOOK_CREATED_TIME,
                    SEMAPHORE_STATUS, getRequesters().get(1)),
            new Book(new BookName("The Maze Runner"), new Isbn("1-474-59282-1"),
                getAuthorList("James Dashner1", "James Dashner2", "James Dashner3"),
                getTagSet("Adventure", "Romance", "Scifi"), SAMPLE_BOOK_CREATED_TIME,
                    MAZE_RUNNER_STATUS, getRequesters().get(2)),
            new Book(new BookName("Artificial Intelligence: A Modern Approach"), new Isbn("9780131038059"),
                getAuthorList("Peter Norvig", "Stuart J. Russell"),
                getTagSet("Technology"), SAMPLE_BOOK_CREATED_TIME,
                    AI_STATUS, getRequesters().get(3)),
            new Book(new BookName("Cinderella"), new Isbn("9781409580454"),
                getAuthorList(),
                getTagSet(), SAMPLE_BOOK_CREATED_TIME,
                    CINDERELLA_STATUS, getRequesters().get(4))
        };
    }

    public static ReadOnlyLibTask getSampleLibTask() {
        LibTask sampleAb = new LibTask();
        for (Patron samplePatron : getSamplePatrons()) {
            sampleAb.addPatron(samplePatron);
        }
        for (Book sampleBook : getSampleBooks()) {
            sampleAb.addBook(sampleBook);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a list of authors containing the list of strings given.
     */
    public static ArrayList<Author> getAuthorList(String... strings) {
        return Arrays.stream(strings)
                .map(Author::new)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Returns a requester set containing the list of requesters given.
     */
    public static Set<Patron> getRequesterSet(Patron... requesters) {
        return Arrays.stream(requesters)
                .map(r -> r.copy())
                .collect(Collectors.toSet());
    }

    private static ArrayList<HashSet<Patron>> getRequesters() {
        ArrayList<HashSet<Patron>> listOfRequesters = new ArrayList<>();
        HashSet<Patron> algorithmsRequesters = new HashSet<>();
        algorithmsRequesters.add(getSamplePatrons()[1]);
        listOfRequesters.add(algorithmsRequesters);

        listOfRequesters.add(new HashSet<>()); // for "The Little Book of Semaphores"
        listOfRequesters.add(new HashSet<>()); // for "Maze Runner"

        HashSet<Patron> aiRequesters = new HashSet<>();
        aiRequesters.add(getSamplePatrons()[2]);
        listOfRequesters.add(aiRequesters);

        listOfRequesters.add(new HashSet<>()); // for "Cinderella"
        return listOfRequesters;
    }

}
