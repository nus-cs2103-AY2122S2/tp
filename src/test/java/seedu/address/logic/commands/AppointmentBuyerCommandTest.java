package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.BuyerCommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.BuyerCommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_BUYER;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_BUYER;
import static seedu.address.testutil.TypicalIndexes.INDEX_SEVENTH_BUYER;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.SellerAddressBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.buyer.Buyer;
import seedu.address.model.client.Appointment;
import seedu.address.testutil.BuyerBuilder;
import seedu.address.testutil.TypicalBuyers;
//import seedu.address.testutil.TypicalClients;

public class AppointmentBuyerCommandTest {

    private Model model = new ModelManager(new UserPrefs(),
            new SellerAddressBook(), TypicalBuyers.getTypicalBuyerAddressBook());


    @Test
    public void execute_validIndexUnfilteredList_success() {
        String testAppointmentValidString = "2025-01-01-10-20";
        assertTrue(Appointment.isValidAppointment(testAppointmentValidString));
        Buyer testBuyer = model.getFilteredBuyerList().get(INDEX_SEVENTH_BUYER.getZeroBased());
        AppointmentBuyerCommand appointmentBuyerCommand = new AppointmentBuyerCommand(INDEX_SEVENTH_BUYER,
                new Appointment(testAppointmentValidString));
        String expectedMessage = String.format(AppointmentBuyerCommand.MESSAGE_ADD_APPOINTMENT_SUCCESS,
                new BuyerBuilder(testBuyer).withAppointment(testAppointmentValidString).build());

        ModelManager expectedModel = new ModelManager(new UserPrefs(), new SellerAddressBook(),
                TypicalBuyers.getTypicalBuyerAddressBook());
        expectedModel.setBuyer(testBuyer,
                new BuyerBuilder(testBuyer).withAppointment(testAppointmentValidString).build());

        assertCommandSuccess(appointmentBuyerCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        String testAppointmentValidString = "2025-01-01-10-20";
        assertTrue(Appointment.isValidAppointment(testAppointmentValidString));
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredBuyerList().size() + 1);
        AppointmentBuyerCommand appointmentBuyerCommand = new AppointmentBuyerCommand(outOfBoundIndex,
                new Appointment(testAppointmentValidString));
        assertCommandFailure(appointmentBuyerCommand, model, Messages.MESSAGE_INVALID_BUYER_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        Appointment appointmentOne = new Appointment("2025-09-09-09-09");
        Appointment appointmentOneCopy = new Appointment("2025-09-09-09-09");
        Appointment appointmentTwo = new Appointment("2023-06-07-10-10");
        Appointment appointmentEmpty = new Appointment("");

        AppointmentBuyerCommand appointmentBuyerCommandOne =
                new AppointmentBuyerCommand(INDEX_FIRST_BUYER, appointmentOne);
        AppointmentBuyerCommand appointmentBuyerCommandTwo =
                new AppointmentBuyerCommand(INDEX_FIRST_BUYER, appointmentOneCopy);
        AppointmentBuyerCommand appointmentBuyerCommandThree =
                new AppointmentBuyerCommand(INDEX_FIRST_BUYER, appointmentEmpty);
        AppointmentBuyerCommand appointmentBuyerCommandFour =
                new AppointmentBuyerCommand(INDEX_SECOND_BUYER, appointmentOne);
        AppointmentBuyerCommand appointmentBuyerCommandFive =
                new AppointmentBuyerCommand(INDEX_SECOND_BUYER, appointmentEmpty);

        assertEquals(appointmentBuyerCommandOne, appointmentBuyerCommandTwo);
        assertNotEquals(appointmentBuyerCommandOne, appointmentBuyerCommandThree);
        assertNotEquals(appointmentBuyerCommandOne, appointmentBuyerCommandFour);
        assertNotEquals(appointmentBuyerCommandThree, appointmentBuyerCommandFive);
    }
}
