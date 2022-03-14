package seedu.contax.ui.onboarding;

import javafx.collections.ObservableList;
import seedu.contax.logic.parser.AddressBookParser;
import seedu.contax.logic.parser.ArgumentMultimap;
import seedu.contax.logic.parser.ArgumentTokenizer;
import seedu.contax.logic.parser.exceptions.ParseException;
import seedu.contax.model.onboarding.OnboardingStep;
import seedu.contax.model.person.Address;
import seedu.contax.model.person.Email;
import seedu.contax.model.person.Name;
import seedu.contax.model.person.Person;
import seedu.contax.model.person.Phone;
import seedu.contax.model.person.UniquePersonList;
import seedu.contax.model.util.SampleDataUtil;

import java.util.HashSet;

import static seedu.contax.logic.parser.CliSyntax.*;

public class OnboardingUtil {

    private final static AddressBookParser parser = new AddressBookParser();
    private final static String INVALID_COMMAND = "Invalid format! "
            + "Follow the format 'add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS'"
            + "\n\nExample: add n/Johnny p/91234567 e/Johnny@j.com a/Johnny street";



    public static void populateWithSample(UniquePersonList persons) {
        Person samplePersons[] = SampleDataUtil.getSamplePersons();
        for (Person p : samplePersons) {
            persons.add(p);
        }
    }

    public static String getLastestPersonName(UniquePersonList persons) {
        ObservableList<Person> personObservableList = persons.asUnmodifiableObservableList();
        String newPersonName = personObservableList.get(personObservableList.size() - 1).getName().toString();
        return newPersonName;
    }

    public static Person getLatestPerson(UniquePersonList persons) {
        ObservableList<Person> personObservableList = persons.asUnmodifiableObservableList();
        Person person = personObservableList.get(personObservableList.size() - 1);
        return person;
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
     * Tokenizer to be used for onboaring guide. Tokenizes the given command based on the given Onboarding step.
     * @param step OnboardingStep to tokenize command on
     * @param command command string to be tokenized
     * @return
     */
    public static ArgumentMultimap tokenize(OnboardingStep step, String command) {
        if (step.getOperationId() == 6) {
            return ArgumentTokenizer.tokenize(command, PREFIX_NAME, PREFIX_PHONE,
                    PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG);
        }
        return ArgumentTokenizer.tokenize(command);
    }

    /**
     * Processes a given user command
     * @param step
     * @param command
     * @param instructionLabel
     * @param persons
     * @return
     */
    public static int processCommand(OnboardingStep step, String command,
                                     OnboardingInstruction instructionLabel, UniquePersonList persons) {
        ArgumentMultimap map = tokenize(step, command);
        if (step.getOperationId() == 6) {
            if (!map.getPreamble().equals("add")) {
                instructionLabel.setText("Please use a add command");
                return -1;
            }

            Person p = makePerson(map);
            if(persons.contains(p)) {
                instructionLabel.setText(p.getName() + " already exists! Add someone else");
                return -1;
            }

            persons.add(p);
        }
        return 0;
    }

    public static Person makePerson(ArgumentMultimap map) {
        Name name = new Name(map.getValue(PREFIX_NAME).get());
        Phone phone = new Phone(map.getValue(PREFIX_PHONE).get());
        Email email = new Email(map.getValue(PREFIX_EMAIL).get());
        Address address = new Address(map.getValue(PREFIX_ADDRESS).get());
        return new Person(name, phone, email, address, new HashSet<>());
    }

}
