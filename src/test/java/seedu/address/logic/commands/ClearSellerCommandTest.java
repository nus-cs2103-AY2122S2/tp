package seedu.address.logic.commands;

import static seedu.address.logic.commands.SellerCommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.model.BuyerAddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.SellerAddressBook;
import seedu.address.model.UserPrefs;

public class ClearSellerCommandTest {

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearSellerCommand(), model, ClearSellerCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        Model model = new ModelManager(new UserPrefs(), new SellerAddressBook(),
                new BuyerAddressBook());
        Model expectedModel = new ModelManager(new UserPrefs(), new SellerAddressBook(),
                new BuyerAddressBook());
        expectedModel.setSellerAddressBook(new SellerAddressBook());

        assertCommandSuccess(new ClearSellerCommand(), model, ClearSellerCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
