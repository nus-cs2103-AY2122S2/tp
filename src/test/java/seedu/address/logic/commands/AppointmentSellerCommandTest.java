package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.SellerCommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.SellerCommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_SELLER;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_SELLER;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.BuyerAddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.client.Appointment;
import seedu.address.model.seller.Seller;
import seedu.address.testutil.SellerBuilder;
import seedu.address.testutil.TypicalSellers;

public class AppointmentSellerCommandTest {

    private Model model = new ModelManager(new UserPrefs(),
            TypicalSellers.getTypicalSellerAddressBook(), new BuyerAddressBook());


    @Test
    public void execute_validIndexUnfilteredList_success() {
        String testAppointmentValidString = "2025-01-01-10-20";
        assertTrue(Appointment.isValidAppointment(testAppointmentValidString));
        Seller testSeller = model.getFilteredSellerList().get(INDEX_FIRST_SELLER.getZeroBased());
        AppointmentSellerCommand appointmentSellerCommand = new AppointmentSellerCommand(INDEX_FIRST_SELLER,
                new Appointment(testAppointmentValidString));
        String expectedMessage = String.format(AppointmentSellerCommand.MESSAGE_ADD_APPOINTMENT_SUCCESS,
                new SellerBuilder(testSeller).withAppointment(testAppointmentValidString).build());

        ModelManager expectedModel = new ModelManager(new UserPrefs(),
                TypicalSellers.getTypicalSellerAddressBook(), new BuyerAddressBook());
        expectedModel.setSeller(testSeller,
                new SellerBuilder(testSeller).withAppointment(testAppointmentValidString).build());

        assertCommandSuccess(appointmentSellerCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        String testAppointmentValidString = "2025-01-01-10-20";
        assertTrue(Appointment.isValidAppointment(testAppointmentValidString));
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredSellerList().size() + 1);
        AppointmentSellerCommand appointmentSellerCommand = new AppointmentSellerCommand(outOfBoundIndex,
                new Appointment(testAppointmentValidString));
        assertCommandFailure(appointmentSellerCommand, model, Messages.MESSAGE_INVALID_SELLER_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        Appointment appointmentOne = new Appointment("2025-09-09-09-09");
        Appointment appointmentOneCopy = new Appointment("2025-09-09-09-09");
        Appointment appointmentTwo = new Appointment("2023-06-07-10-10");
        Appointment appointmentEmpty = new Appointment("");

        AppointmentSellerCommand appointmentSellerCommandOne =
                new AppointmentSellerCommand(INDEX_FIRST_SELLER, appointmentOne);
        AppointmentSellerCommand appointmentSellerCommandTwo =
                new AppointmentSellerCommand(INDEX_FIRST_SELLER, appointmentOneCopy);
        AppointmentSellerCommand appointmentSellerCommandThree =
                new AppointmentSellerCommand(INDEX_FIRST_SELLER, appointmentEmpty);
        AppointmentSellerCommand appointmentSellerCommandFour =
                new AppointmentSellerCommand(INDEX_SECOND_SELLER, appointmentOne);
        AppointmentSellerCommand appointmentSellerCommandFive =
                new AppointmentSellerCommand(INDEX_SECOND_SELLER, appointmentEmpty);

        assertEquals(appointmentSellerCommandOne, appointmentSellerCommandTwo);
        assertNotEquals(appointmentSellerCommandOne, appointmentSellerCommandThree);
        assertNotEquals(appointmentSellerCommandOne, appointmentSellerCommandFour);
        assertNotEquals(appointmentSellerCommandThree, appointmentSellerCommandFive);
    }
}
