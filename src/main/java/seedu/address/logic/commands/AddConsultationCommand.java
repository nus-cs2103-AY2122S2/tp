package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.stream.IntStream;

import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TESTS_TAKEN_AND_RESULTS;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.consultation.Consultation;
import seedu.address.model.person.Nric;


public class AddConsultationCommand extends Command {
    public static final String COMMAND_WORD = "create";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a consultation of a patient in Medbook. "
            + "Parameters: "
            + PREFIX_NRIC + "NRIC "
            + PREFIX_DATE + "PHONE "
            + PREFIX_TIME + "EMAIL "
            + PREFIX_NOTES + "ADDRESS "
            + "Optional Parameters: "
            + PREFIX_PRESCRIPTION + "PRESCRIPTION "
            + PREFIX_TESTS_TAKEN_AND_RESULTS + "TESTS TAKEN AND RESULTS"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NRIC + "T12345678Z "
            + PREFIX_DATE + "22-09-2021 "
            + PREFIX_TIME + "18-00"
            + PREFIX_NOTES + "Hacking up his lungs."
            + PREFIX_PRESCRIPTION + "Cough syrup."
            + PREFIX_TESTS_TAKEN_AND_RESULTS + "Stethoscope, laboured breathing.";

    public static final String MESSAGE_SUCCESS = "New contact added: %1$s";
    public static final String MESSAGE_DUPLICATE_CONSULTATION = "This consultation already exists in patient consultation list";
    public static final String MESSAGE_MISSING_CONSULTATION = "This consultation does not exists in Medbook";

    // Identifier
    private final Nric nric;
    private final Consultation toAdd;

    /**
     * Creates an AddContactCommand to add the specified {@code Patient}
     */
    public AddConsultationCommand(Nric nric, Consultation consultation) {
        requireNonNull(nric);
        requireNonNull(consultation);
        toAdd = consultation;
        this.nric = nric;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Consultation> consultationList = model.getAddressBook().getList();

        int index = IntStream.range(0, consultationList.size())
                .filter(i -> consultationList.get(i).getNric().equals(nric))
                .findFirst()
                .orElse(-1);

        if (index == -1) {
            throw new CommandException(MESSAGE_MISSING_CONSULTATION);
        }

        Consultation consultation = consultationlist.get(index);

        if (person.hasContact(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_CONSULTATION);
        }

        person.setContact(toAdd);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }
}