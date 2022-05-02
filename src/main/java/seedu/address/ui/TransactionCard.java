package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.transaction.DueDate;
import seedu.address.model.transaction.Note;
import seedu.address.model.transaction.Transaction;



/**
 * An UI component that displays information of a {@code Person}.
 */
public class TransactionCard extends UiPart<Region> {

    private static final String FXML = "TransactionListCard.fxml";
    private static final String STATUS_PAID_ICON = "/images/paid.png";
    private static final String STATUS_NOT_PAID_ICON = "/images/unpaid.png";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Transaction transaction;

    @FXML
    private HBox cardPane;
    @FXML
    private Label amount;
    @FXML
    private Label transactionDate;
    @FXML
    private Label dueDate;
    @FXML
    private Label note;
    @FXML
    private Label identifier;
    @FXML
    private Label index;
    @FXML
    private ImageView status;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public TransactionCard(Transaction transaction, int displayedIndex) {
        super(FXML);
        this.transaction = transaction;

        // Required fields.
        identifier.setText("Transaction ID #" + transaction.getTransactionId());
        index.setText(displayedIndex + ".");
        amount.setText("Amount: $" + transaction.getAmount().getValue());
        transactionDate.setText(transaction.getTransactionDate().toString());

        // Optional fields.
        dueDate.setText(transaction.getDueDate()
                .map(DueDate::toString)
                .orElse(DueDate.EMPTY_DUE_DATE));

        note.setText(transaction.getNote()
                .map(Note::toString)
                .orElse(Note.EMPTY_NOTE));
        Image image;
        if (transaction.isPaid()) {
            image = new Image(STATUS_PAID_ICON);
        } else {
            image = new Image(STATUS_NOT_PAID_ICON);
        }
        status.setImage(image);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TransactionCard)) {
            return false;
        }

        // state check
        TransactionCard card = (TransactionCard) other;
        return transaction.equals(card.transaction);
    }
}
