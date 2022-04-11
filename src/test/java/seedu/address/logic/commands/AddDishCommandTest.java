package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.dish.Dish;
import seedu.address.testutil.DishBuilder;
import seedu.address.testutil.ModelStub;

public class AddDishCommandTest {

    @Test
    public void constructor_nullDish_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddDishCommand(null));
    }


    @Test
    public void execute_duplicateDish_throwsCommandException() {
        Dish validDish = new DishBuilder().build();

        AddDishCommand addDishCommand = new AddDishCommand(validDish);
        ModelStub modelStub = new ModelStubWithDish(validDish);
        assertThrows(CommandException.class, AddDishCommand.MESSAGE_DUPLICATE_DISH, () ->
                addDishCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Dish dishOne = new DishBuilder().withName("DishOne").build();
        Dish dishTwo = new DishBuilder().withName("DishTwo").build();

        AddDishCommand addOneCommand = new AddDishCommand(dishOne);
        AddDishCommand addTwoCommand = new AddDishCommand(dishTwo);

        // same object -> returns true
        assertTrue(addOneCommand.equals(addOneCommand));

        // same values -> returns true
        AddDishCommand addOneCommandCopy = new AddDishCommand(dishOne);
        assertTrue(addOneCommand.equals(addOneCommandCopy));

        // null -> returns false
        assertFalse(addOneCommand.equals(null));

        // different dish -> returns false
        assertFalse(addOneCommand.equals(addTwoCommand));
    }

    /**
     * A Model stub that contains a single dish.
     */
    private class ModelStubWithDish extends ModelStub {
        private final Dish dish;

        ModelStubWithDish(Dish dish) {
            requireNonNull(dish);
            this.dish = dish;
        }

        @Override
        public boolean hasDish(Dish dish) {
            requireNonNull(dish);
            return this.dish.isSameDish(dish);
        }
    }
}


