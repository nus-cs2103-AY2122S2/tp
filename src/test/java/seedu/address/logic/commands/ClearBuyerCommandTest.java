package seedu.address.logic.commands;

import static seedu.address.logic.commands.BuyerCommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.model.BuyerAddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.SellerAddressBook;
import seedu.address.model.UserPrefs;

public class ClearBuyerCommandTest {

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearBuyerCommand(), model, ClearBuyerCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        Model model = new ModelManager(new UserPrefs(), new SellerAddressBook(),
                new BuyerAddressBook());
        Model expectedModel = new ModelManager(new UserPrefs(), new SellerAddressBook(),
                new BuyerAddressBook());
        expectedModel.setBuyerAddressBook(new BuyerAddressBook());

        assertCommandSuccess(new ClearBuyerCommand(), model, ClearBuyerCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
