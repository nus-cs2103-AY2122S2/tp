package seedu.address.logic.commands;

import static seedu.address.logic.commands.SellerCommandTestUtil.assertCommandSuccess;
//import static seedu.address.testutil.TypicalClients.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.BuyerAddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.SellerAddressBook;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListSellerCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(new UserPrefs(), new SellerAddressBook(),
                new BuyerAddressBook());
        expectedModel = new ModelManager(new UserPrefs(), new SellerAddressBook(),
                new BuyerAddressBook());
    }

    @Test
    public void execute_sellerListIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListSellerCommand(), model, ListSellerCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
