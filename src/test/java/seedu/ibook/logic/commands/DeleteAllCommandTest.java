package seedu.ibook.logic.commands;

import static seedu.ibook.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.ibook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.ibook.testutil.TypicalProducts.getTypicalIBook;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.ibook.model.IBook;
import seedu.ibook.model.Model;
import seedu.ibook.model.ModelManager;
import seedu.ibook.model.UserPrefs;
import seedu.ibook.model.product.Product;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteAllCommand}.
 */
public class DeleteAllCommandTest {

    private Model model = new ModelManager(getTypicalIBook(), new UserPrefs());

    @Test
    public void execute_nonEmptyFilteredList_success() {
        List<Product> productsToDelete = model.getFilteredProductList();
        DeleteAllCommand deleteAllCommand = new DeleteAllCommand();

        String expectedMessage = DeleteAllCommand.MESSAGE_DELETE_ALL_PRODUCT_SUCCESS;
        for (Product product : productsToDelete) {
            expectedMessage += String.format(DeleteAllCommand.MESSAGE_DELETED_PRODUCT, product);
        }

        ModelManager expectedModel = new ModelManager(new IBook(), new UserPrefs());

        assertCommandSuccess(deleteAllCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_emptyFilteredList_throwsCommandException() {
        ModelManager emptyModel = new ModelManager(new IBook(), new UserPrefs());
        DeleteAllCommand deleteAllCommand = new DeleteAllCommand();

        assertCommandFailure(deleteAllCommand, emptyModel, DeleteAllCommand.MESSAGE_NO_PRODUCT_TO_DELETE);
    }

}
