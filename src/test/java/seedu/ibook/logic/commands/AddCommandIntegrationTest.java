package seedu.ibook.logic.commands;

import static seedu.ibook.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.ibook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.ibook.testutil.TypicalProducts.getTypicalIBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.ibook.model.Model;
import seedu.ibook.model.ModelManager;
import seedu.ibook.model.UserPrefs;
import seedu.ibook.model.product.Product;
import seedu.ibook.testutil.ProductBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalIBook(), new UserPrefs());
    }

    @Test
    public void execute_newProduct_success() {
        Product validProduct = new ProductBuilder().build();

        Model expectedModel = new ModelManager(model.getIBook(), new UserPrefs());
        expectedModel.addProduct(validProduct);

        assertCommandSuccess(new AddCommand(validProduct), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validProduct), expectedModel);
    }

    @Test
    public void execute_duplicateProduct_throwsCommandException() {
        Product productInList = model.getIBook().getProductList().get(0);
        assertCommandFailure(new AddCommand(productInList), model, AddCommand.MESSAGE_DUPLICATE_PRODUCT);
    }
}
