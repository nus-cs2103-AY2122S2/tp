package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Faculty;
import seedu.address.model.person.Person;

/**
 * Summarise all the students by faculty and show how many students in that faculty are positive, negative, HRW, HRN
 */
public class SummariseCommand extends Command {

    public static final String COMMAND_WORD = "summarise";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Summarise all data in the address book and presents an overview of the covid situation in school.\n";

    public static final String MESSAGE_SUMMARISE_PERSON_SUCCESS = "Summarising all students: \n";

    private static final String FACULTY_SUMMARY_FORM = "\nIn %s with %d student(s),\n"
            + "Covid Positive: %d student(s)\n"
            + "Covid Negative: %d student(s)\n"
            + "Health Risk Warning: %d student(s)\n"
            + "Health Risk Notice: %d student(s)\n"
            + "%.2f percent of student(s) here are suffering...\n";

    private static final List<String> FACULTIES = Faculty.getFacultyEnumAsList();

    private static final Predicate<Person> BYPOSITIVE = person -> person.getStatusAsString().equals("POSITIVE");
    private static final Predicate<Person> BYNEGATIVE = person -> person.getStatusAsString().equals("NEGATIVE");
    private static final Predicate<Person> BYHRW = person -> person.getStatusAsString().equals("HRW");
    private static final Predicate<Person> BYHRN = person -> person.getStatusAsString().equals("HRN");


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        List<Person> lastShownList = model.getFilteredPersonList();
        StringBuilder answer = new StringBuilder(MESSAGE_SUMMARISE_PERSON_SUCCESS);

        for (String facultyName : FACULTIES) {
            Predicate<Person> byFaculty = person -> person.getFacultyAsString().equals(facultyName);
            List<Person> students = lastShownList.stream().filter(byFaculty).collect(Collectors.toList());
            if (students.size() <= 0) {
                continue;
            }
            answer.append(summariseByFaculty(students, facultyName));
        }
        return new CommandResult(answer.toString());
    }

    private String summariseByFaculty(List<Person> result, String facultyName) {
        int totalNumberOfStudents = result.size();
        int numberOfPositive = (int) result.stream().filter(BYPOSITIVE).count();
        int numberOfNegative = (int) result.stream().filter(BYNEGATIVE).count();
        int numberOfHRW = (int) result.stream().filter(BYHRW).count();
        int numberOfHRN = (int) result.stream().filter(BYHRN).count();
        double percentagePositive = (double) numberOfPositive / totalNumberOfStudents * 100;

        return String.format(FACULTY_SUMMARY_FORM, facultyName, totalNumberOfStudents, numberOfPositive,
                numberOfNegative, numberOfHRW, numberOfHRN, percentagePositive);
    }

}


