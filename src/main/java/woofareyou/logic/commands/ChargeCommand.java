package woofareyou.logic.commands;

import static woofareyou.commons.util.CollectionUtil.requireAllNonNull;

import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;

import woofareyou.commons.core.Messages;
import woofareyou.commons.core.index.Index;
import woofareyou.logic.commands.exceptions.CommandException;
import woofareyou.model.Model;
import woofareyou.model.attendance.AttendanceEntry;
import woofareyou.model.charge.Charge;
import woofareyou.model.pet.AttendanceHashMap;
import woofareyou.model.pet.Name;
import woofareyou.model.pet.Pet;


/**
 * Computes a month's charge of a pet identified using it's displayed index from WoofAreYou.
 */
public class ChargeCommand extends Command {

    public static final String COMMAND_WORD = "charge";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Computes a month's charge of the pet identified "
            + "by the index number used in the last pet listing.\n"
            + "Parameters: INDEX (must be a positive integer)"
            + "m/[MM-yyyy] c/[COST]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "m/03-2022 c/200";
    public static final String MESSAGE_INVALID_DATE_FORMAT = "Charge date should be formatted as MM-yyyy!";
    public static final String MESSAGE_COMPUTE_CHARGE_SUCCESS = "%s should be charged $%.2f for %s %d.";
    public static final String MESSAGE_ATTENDANCE = "\nHere are the days %s was present: ";
    public static final String MESSAGE_NO_ATTENDANCE = "\n%s was not present on any day.";
    private final Index index;
    private final YearMonth chargeDate;
    private final Charge charge;

    /**
     * @param index of the pet in the filtered pets list to compute the charges of.
     * @param chargeDate the month in the specified year to calculate amount chargeable.
     */
    public ChargeCommand(Index index, YearMonth chargeDate, Charge charge) {
        requireAllNonNull(index, chargeDate);
        this.index = index;
        this.chargeDate = chargeDate;
        this.charge = charge;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Pet> lastShownList = model.getFilteredPetList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PET_DISPLAYED_INDEX);
        }

        Pet petToCharge = lastShownList.get(index.getZeroBased());
        double amountChargeable = 0.0;
        // check current charge
        if (this.charge.getCharge() == null) {
            throw new CommandException(String.format(Messages.MESSAGE_NO_CHARGE_SET, ChargeCommand.MESSAGE_USAGE));
        }

        // calculate charge based on number of days in month
        AttendanceHashMap map = petToCharge.getAttendanceHashMap();
        LocalDate day = this.chargeDate.atDay(1);
        LocalDate firstDayNextMonth = this.chargeDate.atEndOfMonth().plusDays(1);
        while (day.isBefore(firstDayNextMonth)) {
            boolean hasAttendance = map.getAttendance(day).isPresent();
            if (hasAttendance) {
                AttendanceEntry entry = map.getAttendance(day).get();
                boolean isPresent = entry.getIsPresent().orElse(false);
                if (isPresent) {
                    amountChargeable += this.charge.getCharge();
                }
            }
            day = day.plusDays(1);
        }

        return new CommandResult(generateSuccessMessage(petToCharge.getName(),
                amountChargeable, getMonthName(), chargeDate.getYear(), getAttendance(petToCharge)));
    }

    /**
     * Returns the attendance the charge on the pet is based on
     * @return attendance as a String.
     */
    public String getAttendance(Pet pet) {
        double amountChargeable = 0.0;
        String attendance = String.format(MESSAGE_ATTENDANCE, pet.getName().toString());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        AttendanceHashMap map = pet.getAttendanceHashMap();
        LocalDate day = this.chargeDate.atDay(1);
        LocalDate firstDayNextMonth = this.chargeDate.atEndOfMonth().plusDays(1);
        while (day.isBefore(firstDayNextMonth)) {
            boolean hasAttendance = map.getAttendance(day).isPresent();
            AttendanceEntry entry = map.getAttendance(day).orElse(null);
            boolean isPresent = entry == null ? false : entry.getIsPresent().orElse(false);
            if (hasAttendance && isPresent) {
                amountChargeable += this.charge.getCharge();
                attendance += "\n" + entry.getAttendanceDate().format(formatter);
            }
            day = day.plusDays(1);
        }
        if (amountChargeable == 0) {
            attendance = String.format(MESSAGE_NO_ATTENDANCE, pet.getName().toString());
        }
        if (amountChargeable == this.charge.getCharge()) {
            attendance = attendance.replace("are the days", "is the day");
        }
        return attendance;
    }

    /**
     * Returns the month to charge the pet on
     * @return month as a String.
     */
    public String getMonthName() {
        DateFormatSymbols symbol = new DateFormatSymbols();
        return symbol.getMonths()[this.chargeDate.getMonthValue() - 1];
    }

    /**
     * Generates a command execution success message
     * {@code petToEdit}.
     */
    private String generateSuccessMessage(Name petName, double amountChargeable, String month, int year,
                                          String attendance) {
        return String.format(MESSAGE_COMPUTE_CHARGE_SUCCESS, petName.toString(), amountChargeable, month, year)
                + attendance;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ChargeCommand)) {
            return false;
        }

        // state check
        ChargeCommand e = (ChargeCommand) other;
        return index.equals(e.index)
                && chargeDate.equals(e.chargeDate)
                && charge.equals(e.charge);
    }

}
