package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.TreeMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Block;
import seedu.address.model.person.Faculty;
import seedu.address.model.person.Person;

/**
 * Summarise all the students by faculty and show how many students in that faculty and block are positive,
 * negative or on HRN.
 */
public class SummariseCommand extends Command {

    public static final String COMMAND_WORD = "summarise";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Summarise all data in the address book and presents an overview of the covid situation in school.\n";

    public static final String MESSAGE_SUMMARISE_PERSON_SUCCESS = "Summarising all students in this hall: \n";

    public static final String MESSAGE_SUMMARISE_PERSON_FAILURE = "Unable to summarise \n";

    private static final String FACULTY_SUMMARY_FORM = "\nIn %s faculty with %d student(s),\n"
            + "Covid Positive: %d student(s)\n"
            + "Covid Negative: %d student(s)\n"
            + "Health Risk Notice: %d student(s)\n"
            + "%.2f percent of student(s) here are suffering...\n";
    private static final String BLOCK_SUMMARY_FORM = "\nIn %s block with %d student(s),\n"
            + "Covid Positive: %d student(s)\n"
            + "Covid Negative: %d student(s)\n"
            + "Health Risk Notice: %d student(s)\n"
            + "%.2f percent of student(s) here are suffering...\n";
    private static final String HALL_SUMMARY_FORM = "\nIn this hall, %d of %d student(s) are covid positive.\n"
            + "The breakdowns by Block level and Faculty level are given below:\n";

    private static final List<String> FACULTIES = Faculty.getFacultyEnumAsList();
    private static final List<String> BLOCKS = Block.getBlockEnumAsList();

    private static final Predicate<Person> BY_POSITIVE = person -> person.getStatusAsString().equals("POSITIVE");
    private static final Predicate<Person> BY_NEGATIVE = person -> person.getStatusAsString().equals("NEGATIVE");
    private static final Predicate<Person> BY_HRN = person -> person.getStatusAsString().equals("HRN");
    private static final TreeMap<String, TreeMap<String, Double>> covidStatsByBlockDataList = new TreeMap<>();
    private static final TreeMap<String, Double> positiveStatsByFacultyData = new TreeMap<>();


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        List<Person> lastShownList = model.getFilteredPersonList();
        // Summarises the contact list by Block first then by Faculty
        String answer = summariseAll(lastShownList) + filterByBlock(lastShownList) + filterByFaculty(lastShownList);

        if (lastShownList.isEmpty()) {
            covidStatsByBlockDataList.clear();
            positiveStatsByFacultyData.clear();
        }

        if (answer.isEmpty()) {
            // Returns a message indicating Tracey is unable to summarise her contact list.
            return new CommandResult(MESSAGE_SUMMARISE_PERSON_FAILURE);
        } else {
            return new CommandResult(MESSAGE_SUMMARISE_PERSON_SUCCESS + answer,
                    false, false, true);
        }
    }

    /**
     * Filter entire list by faculties to provide overview of covid situation in each faculty.
     *
     * @param list the unfiltered entire list in the database
     * @return The summarised overview for all students by faculties
     */
    public String filterByFaculty(List<Person> list) {
        StringBuilder ans = new StringBuilder();

        for (String facultyName : FACULTIES) {
            Predicate<Person> byFaculty = person -> person.getFacultyAsString().equals(facultyName);
            // Create a list of students in the said faculty.
            List<Person> students = list.stream().filter(byFaculty).collect(Collectors.toList());
            if (students.size() <= 0) {
                // Faculties with no students will not be summarised.
                continue;
            }
            ans.append(summariseFaculty(students, facultyName));
        }
        return ans.toString();
    }

    /**
     * Filter entire list by the hall block to provide overview of covid situation in each block.
     *
     * @param list the unfiltered entire list in the database
     * @return The summarised overview for all students by blocks
     */
    public String filterByBlock(List<Person> list) {
        StringBuilder ans = new StringBuilder();

        for (String blockLetter : BLOCKS) {
            Predicate<Person> byBlock = person -> person.getBlockAsString().equals(blockLetter);
            // Create a list of students in the said Hall Block.
            List<Person> students = list.stream().filter(byBlock).collect(Collectors.toList());
            if (students.size() <= 0) {
                // Hall Block with no students will not be summarised.
                continue;
            }
            ans.append(summariseBlock(students, blockLetter));
        }
        return ans.toString();
    }

    /**
     * Returns a standardised form with the breakdown of students by covid status within a specified faculty.
     *
     * @param result a list of students in said faculty
     * @param facultyName the faculty in which students are from
     * @return A string form containing the respective number of students with certain covid status
     */
    private String summariseFaculty(List<Person> result, String facultyName) {
        int totalNumberOfStudents = result.size();
        int numberOfPositive = (int) result.stream().filter(BY_POSITIVE).count();
        int numberOfNegative = (int) result.stream().filter(BY_NEGATIVE).count();
        int numberOfHrn = (int) result.stream().filter(BY_HRN).count();
        double percentagePositive = (double) numberOfPositive / totalNumberOfStudents * 100;
        // Collating this faculty's covid statuses in a TreeMap used for making of PieCharts.
        positiveStatsByFacultyData.put(facultyName, (double) numberOfPositive);
        return String.format(FACULTY_SUMMARY_FORM, facultyName, totalNumberOfStudents, numberOfPositive,
                numberOfNegative, numberOfHrn, percentagePositive);
    }

    /**
     * Returns a standardised form with the breakdown of students by covid status within a specified block.
     *
     * @param result a list of students in said block
     * @param blockLetter the block in which students are from
     * @return A string form containing the respective number of students with certain covid status
     */
    private String summariseBlock(List<Person> result, String blockLetter) {
        int totalNumberOfStudents = result.size();
        int numberOfPositive = (int) result.stream().filter(BY_POSITIVE).count();
        int numberOfNegative = (int) result.stream().filter(BY_NEGATIVE).count();
        int numberOfHrn = (int) result.stream().filter(BY_HRN).count();
        double percentagePositive = (double) numberOfPositive / totalNumberOfStudents * 100;
        TreeMap<String, Double> covidStatsByBlockData = new TreeMap<>();
        covidStatsByBlockData.put("Positive", (double) numberOfPositive);
        covidStatsByBlockData.put("Negative", (double) numberOfNegative);
        covidStatsByBlockData.put("HRN", (double) numberOfHrn);
        covidStatsByBlockDataList.put(blockLetter, covidStatsByBlockData);
        return String.format(BLOCK_SUMMARY_FORM, blockLetter, totalNumberOfStudents, numberOfPositive,
                numberOfNegative, numberOfHrn, percentagePositive);
    }

    /**
     * Filter entire list to provide overview of covid situation in the hall.
     *
     * @param list the unfiltered entire list in the database
     * @return The summarised overview for all students
     */
    public String summariseAll(List<Person> list) {
        StringBuilder ans = new StringBuilder();
        int numberOfStudents = list.size();

        // Create a list of students in the Hall that are covid positive.
        List<Person> students = list.stream().filter(BY_POSITIVE).collect(Collectors.toList());
        int numberOfPositive = students.size();

        return String.format(HALL_SUMMARY_FORM, numberOfPositive, numberOfStudents);
    }

    /**
     * Returns all the relevant data present in a Block.
     *
     * @return A TreeMap with the block and its students categorised by covid statuses
     */
    public static TreeMap<String, TreeMap<String, Double>> getCovidStatsByBlockDataList() {
        return covidStatsByBlockDataList;
    }

    /**
     * Returns all the relevant data present in a Faculty.
     *
     * @return A TreeMap with the faculty and its number of Covid Positive students
     */
    public static TreeMap<String, Double> getPositiveStatsByFacultyData() {
        return positiveStatsByFacultyData;
    }

    /**
     * Returns true if both tree map are not empty and the pie chart window should be open, returns false otherwise.
     * @return boolean for whether the pie chart window should be opened
     */
    public static boolean shouldOpenPieChartWindow() {
        return !(positiveStatsByFacultyData.isEmpty() && covidStatsByBlockDataList.isEmpty());
    }
}


