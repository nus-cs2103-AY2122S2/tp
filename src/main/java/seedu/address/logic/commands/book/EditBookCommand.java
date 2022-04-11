package seedu.address.logic.commands.book;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AUTHOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ISBN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_BOOKS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.book.Author;
import seedu.address.model.book.Book;
import seedu.address.model.book.BookName;
import seedu.address.model.book.BookStatus;
import seedu.address.model.book.Isbn;
import seedu.address.model.patron.Patron;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing book in LibTask.
 */
public class EditBookCommand extends Command {

    public static final String MESSAGE_USAGE = BOOK_COMMAND_GROUP + " " + EDIT_COMMAND_WORD
            + ": Edits the details of the book at a specified index "
            + "by the index number used in the displayed book list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_ISBN + "ISBN] "
            + "[" + PREFIX_AUTHOR + "AUTHOR] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "INDEX must be a positive integer and "
            + "does not exceed the largest index number in the displayed book list\n"
            + "Example: " + BOOK_COMMAND_GROUP + " " + EDIT_COMMAND_WORD + " 1 "
            + PREFIX_NAME + "Harry Potter "
            + PREFIX_ISBN + "978-71617-018-8-5 "
            + PREFIX_AUTHOR + "J. K. Rowling "
            + PREFIX_TAG + "Magic";

    public static final String MESSAGE_EDIT_BOOK_SUCCESS = "Edited Book: %1$s\n";
    public static final String MESSAGE_NOT_EDITED = "At least ISBN, author or category must be provided.";
    public static final String MESSAGE_OTHER_BOOKS_EDITED = "Some other books with the same isbn are also edited.\n";
    public static final String MESSAGE_SAME_ISBN = "You cannot change a book's isbn into another existing isbn!";

    private final Index index;
    private final EditBookDescriptor editBookDescriptor;

    /**
     * @param index of the book in the filtered book list to edit
     * @param editBookDescriptor details to edit the book with
     */
    public EditBookCommand(Index index, EditBookDescriptor editBookDescriptor) {
        requireNonNull(index);
        requireNonNull(editBookDescriptor);

        this.index = index;
        this.editBookDescriptor = new EditBookDescriptor(editBookDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        ObservableList<Book> lastShownList = model.getFilteredBookList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_BOOK_DISPLAYED_INDEX);
        }

        Book bookToEdit = lastShownList.get(index.getZeroBased()); //change to bookList
        Book editedBook = createEditedBook(bookToEdit, editBookDescriptor);

        // Do not allow editing isbn of this book if some other books already has the new isbn
        if (!editedBook.hasSameIsbn(bookToEdit) && model.hasSameIsbn(editedBook)) {
            throw new CommandException(MESSAGE_SAME_ISBN);
        }

        boolean hasSomeOtherBooksEdited = model.setAndEditBook(bookToEdit, editedBook);
        String additionalInfo = hasSomeOtherBooksEdited ? MESSAGE_OTHER_BOOKS_EDITED : "";

        model.updateFilteredBookList(PREDICATE_SHOW_ALL_BOOKS);
        return new CommandResult(String.format(MESSAGE_EDIT_BOOK_SUCCESS, editedBook) + additionalInfo);
    }

    /**
     * Creates and returns a {@code Book} with the details of {@code bookToEdit}
     * edited with {@code editBookDescriptor}.
     */
    private static Book createEditedBook(Book bookToEdit, EditBookDescriptor editBookDescriptor) {
        assert bookToEdit != null;

        BookName updatedName = editBookDescriptor.getBookName().orElse(bookToEdit.getBookName());
        Isbn updatedIsbn = editBookDescriptor.getIsbn().orElse(bookToEdit.getIsbn());
        List<Author> updatedAuthor = editBookDescriptor.getAuthors().orElse(bookToEdit.getAuthors());
        Set<Tag> updatedTags = editBookDescriptor.getTags().orElse(bookToEdit.getTags());
        long updatedTimeAdded = bookToEdit.getTimeAdded();
        BookStatus bookStatus = bookToEdit.getBookStatus();
        Set<Patron> requesters = bookToEdit.getRequesters();

        return new Book(updatedName, updatedIsbn, updatedAuthor, updatedTags, updatedTimeAdded, bookStatus, requesters);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditBookCommand)) {
            return false;
        }

        // state check
        EditBookCommand e = (EditBookCommand) other;
        return index.equals(e.index)
                && editBookDescriptor.equals(e.editBookDescriptor);
    }

    /**
     * Stores the details to edit the book with. Each non-empty field value will replace the
     * corresponding field value of the book.
     */
    public static class EditBookDescriptor {
        private BookName bookName;
        private Isbn isbn;
        private List<Author> authors;
        private Set<Tag> tags;

        public EditBookDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditBookDescriptor(EditBookDescriptor toCopy) {
            setBookName(toCopy.bookName);
            setIsbn(toCopy.isbn);
            setAuthors(toCopy.authors);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(bookName, isbn, authors, tags);
        }

        public void setBookName(BookName bookname) {
            this.bookName = bookname;
        }

        public Optional<BookName> getBookName() {
            return Optional.ofNullable(bookName);
        }

        public void setIsbn(Isbn isbn) {
            this.isbn = isbn;
        }

        public Optional<Isbn> getIsbn() {
            return Optional.ofNullable(isbn);
        }

        public void setAuthors(List<Author> authors) {
            this.authors = authors;
        }

        public Optional<List<Author>> getAuthors() {
            return Optional.ofNullable(authors);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditBookDescriptor)) {
                return false;
            }

            // state check
            EditBookDescriptor e = (EditBookDescriptor) other;

            return getBookName().equals(e.getBookName())
                    && getIsbn().equals(e.getIsbn())
                    && getAuthors().equals(e.getAuthors())
                    && getTags().equals(e.getTags());
        }
    }
}
