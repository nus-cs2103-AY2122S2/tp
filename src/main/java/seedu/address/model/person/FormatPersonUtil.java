package seedu.address.model.person;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;

import seedu.address.commons.util.JsonUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.module.Module;

public class FormatPersonUtil {
    public static final String JSON_FORMAT = "json";
    public static final String CSV_FORMAT = "csv";
    public static final String DEFAULT_FORMAT = "default";

    public static final String MESSAGE_CONSTRAINTS = "Invalid format. Valid formats are: "
            + JSON_FORMAT + ", " + CSV_FORMAT + ", " + DEFAULT_FORMAT + "\n";

    private final String format;

    public FormatPersonUtil() {
        this.format = DEFAULT_FORMAT;
    }

    public FormatPersonUtil(String format) {
        this.format = format;
    }

    /**
     * Returns true if format specified is valid
     * @param format
     * @return True if valid
     */
    public static boolean isValidFormat(String format) {
        return format.equals(JSON_FORMAT) || format.equals(CSV_FORMAT) || format.equals(DEFAULT_FORMAT);
    }

    /**
     * Returns a String containing formatted person
     * @param person person to be formatted
     * @param prefixes list of prefixes to be used
     * @return String containing formatted person
     */
    public String formatPerson(Person person, List<Prefix> prefixes) throws JsonProcessingException {
        if (format.equals(JSON_FORMAT)) {
            return JsonUtil.toJsonString(formatPersonJson(person, prefixes));

        } else if (format.equals(CSV_FORMAT)) {
            return formatPersonCsv(person, prefixes);

        } else {
            return formatPersonDefault(person, prefixes);
        }
    }

    /**
     * Returns a String containing formatted addressbook
     * @param persons person to be formatted
     * @param prefixes list of prefixes to be used
     * @return String containing formatted addressbook
     */
    public String formatAddressBook(List<Person> persons, List<Prefix> prefixes) throws JsonProcessingException {
        if (format.equals(JSON_FORMAT)) {
            return formatAddressBookJson(persons, prefixes);
        } else if (format.equals(CSV_FORMAT)) {
            return formatAddressBookCsv(persons, prefixes);
        } else {
            return formatAddressBookDefault(persons, prefixes);
        }
    }

    //=========== Default format ==================================================================================

    private String formatPersonDefault(Person person, List<Prefix> prefixes) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < prefixes.size(); i++) {
            builder.append(getPersonField(person, prefixes.get(i)));
            if (i != prefixes.size() - 1) {
                builder.append("\n");
            }
        }
        return builder.toString();
    }

    private String formatAddressBookDefault(List<Person> persons, List<Prefix> prefixes) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < persons.size(); i++) {
            builder.append(formatPersonDefault(persons.get(i), prefixes));
            if (i != persons.size() - 1) {
                builder.append("\n");
            }
        }
        return builder.toString();
    }

    //=========== Csv format ==================================================================================

    private String formatPersonCsv(Person person, List<Prefix> prefixes) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < prefixes.size() - 1; i++) {
            builder.append(getPersonField(person, prefixes.get(i)));
            builder.append(" | ");
        }
        builder.append(getPersonField(person, prefixes.get(prefixes.size() - 1)));
        return builder.toString();
    }

    private String formatHeaderCsv(List<Prefix> prefixes) {
        StringBuilder builder = new StringBuilder();
        List<String> headers = prefixes.stream()
                .map(Prefix::getDescription)
                .collect(Collectors.toList());
        for (int i = 0; i < headers.size() - 1; i++) {
            builder.append(headers.get(i));
            builder.append(" | ");
        }
        builder.append(headers.get(headers.size() - 1));
        builder.append("\n");
        return builder.toString();
    }

    private String formatAddressBookCsv(List<Person> persons, List<Prefix> prefixes) {
        StringBuilder builder = new StringBuilder();
        builder.append(formatHeaderCsv(prefixes));
        for (int i = 0; i < persons.size(); i++) {
            builder.append(formatPersonCsv(persons.get(i), prefixes));
            if (i != persons.size() - 1) {
                builder.append("\n");
            }
        }
        return builder.toString();
    }

    //=========== Json format ==================================================================================

    private Map<String, Object> formatPersonJson(Person person, List<Prefix> prefixes) {
        Map<String, Object> personMap = new LinkedHashMap<>();
        for (Prefix prefix : prefixes) {
            if (prefix.equals(PREFIX_MODULE)) {
                List<String> modules = person.getModules().stream()
                                .map(Module::getModuleName)
                                .collect(Collectors.toList());
                personMap.put(prefix.getDescription(), modules);

            } else {
                personMap.put(prefix.getDescription(), getPersonField(person, prefix));
            }
        }
        return personMap;
    }

    private String formatAddressBookJson(List<Person> person, List<Prefix> prefixes) throws JsonProcessingException {
        List<Map<String, Object>> mapList = new ArrayList<>();
        for (Person p : person) {
            mapList.add(formatPersonJson(p, prefixes));
        }
        Map<String, Object> addressBookStructure = new HashMap<>();
        addressBookStructure.put("persons", mapList);
        return JsonUtil.toJsonString(addressBookStructure);
    }

    public static String getPersonField(Person person, Prefix prefix) {
        if (prefix.equals(PREFIX_NAME)) {
            return person.getName().toString();

        } else if (prefix.equals(PREFIX_PHONE)) {
            return person.getPhone().toString();

        } else if (prefix.equals(PREFIX_EMAIL)) {
            return person.getEmail().toString();

        } else if (prefix.equals(PREFIX_ADDRESS)) {
            return person.getAddress().toString();

        } else if (prefix.equals(PREFIX_STATUS)) {
            return person.getStatus().toString();

        } else if (prefix.equals(PREFIX_MODULE)) {
            return person.getModules().toString();

        } else if (prefix.equals(PREFIX_COMMENT)) {
            return person.getComment().toString();

        } else {
            assert false;
            return "";
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FormatPersonUtil)) {
            return false;
        }

        // state check
        FormatPersonUtil e = (FormatPersonUtil) other;
        return format.equals(e.format);
    }
}
