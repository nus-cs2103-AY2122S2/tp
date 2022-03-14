package seedu.contax.ui.onboarding;

import seedu.contax.logic.commands.AddCommand;
import seedu.contax.logic.commands.Command;
import seedu.contax.logic.commands.exceptions.CommandException;
import seedu.contax.logic.parser.AddressBookParser;
import seedu.contax.logic.parser.exceptions.ParseException;
import seedu.contax.model.Model;
import seedu.contax.model.onboarding.OnboardingStep;
import seedu.contax.model.person.Person;
import seedu.contax.model.util.SampleDataUtil;


/**
 * This class provides utilities functions for the onboarding window.
 */
public class OnboardingUtil {

    private static final AddressBookParser parser = new AddressBookParser();
    private static final String INVALID_COMMAND = "Invalid format! "
            + "Follow the format 'add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS'"
            + "\n\nExample: add n/Johnny p/91234567 e/Johnny@j.com a/Johnny street";

    /**
     * Populates the given Model with the sample data
     * @param model the Model to be populated
     */
    public static void populateWithSample(Model model) {
        Person[] samplePersons = SampleDataUtil.getSamplePersons();
        for (Person p : samplePersons) {
            model.addPerson(p);
        }
    }

    /**
     * Returns the last Person in the given Model
     * @param model the Model to be searched
     * @return Person object of the last person
     */
    public static Person getLatestPerson(Model model) {
        return model.getFilteredPersonList()
                .get(model.getAddressBook().getPersonList().size() - 1);
    }

    /**
     * Returns the name string of the last person in the given Model
     * @param model the UniquePersonList to be searched
     * @return name of the last person
     */
    public static String getLastestPersonName(Model model) {
        return getLatestPerson(model).getName().toString();
    }

    /**
     * Checks if the given command is valid. Updates the label with error message if it is not.
     * @param command command string to be validated
     * @param instructionLabel label to print error message on
     */
    public static boolean isCommandValid(String command, OnboardingInstruction instructionLabel) {
        try {
            parser.parseCommand(command);
            return true;
        } catch (ParseException e) {
            instructionLabel.setText(INVALID_COMMAND);
            return false;
        }
    }

    /**
     * Processes a given user command
     * @param step OnboardingStep containing details to be processed
     * @param commandString commandString to be processd
     * @param instructionLabel instructionLabel to be updated
     * @param model model used in the onboarding window
     * @return
     */
    public static int processCommand(OnboardingStep step, String commandString,
                                     OnboardingInstruction instructionLabel, Model model) {
        Command command = null;
        try {
            command = parser.parseCommand(commandString);
        } catch (ParseException e) {
            instructionLabel.setText(INVALID_COMMAND);
        }

        if (step.getOperationId() == 6) {
            if (!(command instanceof AddCommand)) {
                instructionLabel.setText("Please use a add command");
                return -1;
            }

            try {
                command.execute(model);
            } catch (CommandException e) {
                if (e.getMessage().equals(AddCommand.MESSAGE_DUPLICATE_PERSON)) {
                    instructionLabel.setText("Person name already exists! Add someone else");
                }
            }
        }
        return 0;
    }

}
