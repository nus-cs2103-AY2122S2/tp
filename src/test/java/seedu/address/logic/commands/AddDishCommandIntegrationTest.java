package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.dish.Dish;
import seedu.address.testutil.DishBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddDishCommand}.
 */
public class AddDishCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newDish_success() {
        Dish validDish = new DishBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addDish(validDish);

        assertCommandSuccess(new AddDishCommand(validDish), model,
                String.format(AddDishCommand.MESSAGE_SUCCESS, validDish), expectedModel);
    }

    @Test
    public void execute_duplicateDish_throwsCommandException() {
        Dish dishInList = model.getAddressBook().getDishList().get(0);

        assertCommandFailure(new AddDishCommand(dishInList), model,
                AddDishCommand.MESSAGE_DUPLICATE_DISH);
    }

}
