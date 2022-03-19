package seedu.address.model.person;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;

import seedu.address.commons.util.JsonUtil;
import seedu.address.logic.parser.Prefix;

public class FormatPersonUtil {
    private static final String JSON_FORMAT = "json";
    private static final String CSV_FORMAT = "csv";
    private static final String DEFAULT_FORMAT = "default";

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
     * @return
     */
    public static boolean isValidFormat(String format) {
        if (format.equals(JSON_FORMAT) || format.equals(CSV_FORMAT) || format.equals(DEFAULT_FORMAT)) {
            return true;
        }
        return false;
    }

    /**
     * Returns a String contained formatted person
     * @param person person to be formatted
     * @param prefixes list of prefixes to be used
     * @return
     */
    public String formatPerson(Person person, List<Prefix> prefixes) throws JsonProcessingException {
        if (format.equals(JSON_FORMAT)) {
            return formatPersonJson(person, prefixes);
        } else if (format.equals(CSV_FORMAT)) {
            return formatPersonCsv(person, prefixes);
        } else {
            return formatPersonDefault(person, prefixes);
        }
    }

    private String formatPersonDefault(Person person, List<Prefix> prefixes) {
        StringBuilder builder = new StringBuilder();
        for (Prefix prefix : prefixes) {
            builder.append(getPersonField(person, prefix));
            builder.append("\n");
        }
        return builder.toString();
    }

    private String formatPersonCsv(Person person, List<Prefix> prefixes) {
        List<String> copiedFields = new ArrayList<>();
        for (Prefix prefix : prefixes) {
            copiedFields.add(getPersonField(person, prefix));
        }
        String csv = copiedFields.toString();
        return csv.substring(1, csv.length() - 1);
    }

    private String formatPersonJson(Person person, List<Prefix> prefixes) throws JsonProcessingException {
        Map<String, String> map = new HashMap<>();
        for (Prefix prefix : prefixes) {
            map.put(prefix.getDescription(), getPersonField(person, prefix));
        }
        return JsonUtil.toJsonString(map);
    }

    private String getPersonField(Person person, Prefix prefix) {
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

        } else {
            return "";
        }
    }
}
