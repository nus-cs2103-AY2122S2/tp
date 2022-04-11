package unibook.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import unibook.commons.core.index.Index;
import unibook.commons.util.StringUtil;
import unibook.logic.parser.exceptions.ParseException;
import unibook.model.module.Module;
import unibook.model.module.ModuleCode;
import unibook.model.module.ModuleKeyEvent;
import unibook.model.module.ModuleKeyEvent.KeyEventType;
import unibook.model.module.ModuleName;
import unibook.model.module.group.Group;
import unibook.model.person.Email;
import unibook.model.person.Name;
import unibook.model.person.Office;
import unibook.model.person.Phone;
import unibook.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_WRONG_MT_FORMAT = "To edit meeting times, must include index of meeting time. \n"
            + "For example, to edit the first index of meeting times to 12th December 2022 4.45pm: "
            + "mt/1 2020-12-12 16:45";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     *
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }


    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }


    // find a way to find the module code inside the storage database then update

    /**
     * Parses a {@code String moduleName} into an {@code moduleName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code moduleName} is invalid.
     */
    public static ModuleName parseModuleName(String moduleName) throws ParseException {
        requireNonNull(moduleName);
        String trimmedModule = moduleName.trim();
        if (!ModuleName.isValidModuleName(trimmedModule)) {
            throw new ParseException(ModuleName.MESSAGE_CONSTRAINTS);
        }
        return new ModuleName(trimmedModule);
    }

    /**
     * Parses a {@code String moduleCode} into an {@code moduleCode}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code moduleCode} is invalid.
     */
    public static ModuleCode parseModuleCode(String moduleCode) throws ParseException {
        requireNonNull(moduleCode);
        String trimmedCode = moduleCode.trim().toUpperCase(Locale.ROOT);
        if (!ModuleCode.isValidModuleCode(trimmedCode)) {
            throw new ParseException(ModuleCode.MESSAGE_CONSTRAINTS);
        }
        return new ModuleCode(trimmedCode);
    }


    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses {@code Collection<String> modules} into a single {@code Set<Module>}.
     */
    public static Set<Module> parseModules(Collection<String> modules) throws ParseException {
        requireNonNull(modules);
        final Set<Module> moduleSet = new HashSet<Module>();
        if (modules.toArray().length == 0) {
            return moduleSet;
        }
        String moduleName = (String) modules.toArray()[0];
        ModuleName modName = new ModuleName(moduleName);
        String moduleCode = (String) modules.toArray()[1];
        ModuleCode modCode = new ModuleCode(moduleCode);
        Module newmod = new Module(modName, modCode);
        moduleSet.add(newmod);
        return moduleSet;
    }

    /**
     * Parses {@code Collection<String> modules} into a {@code Set<ModuleCode>}.
     */
    public static Set<ModuleCode> parseMultipleModules(Collection<String> moduleCodes) throws ParseException {
        requireNonNull(moduleCodes);
        final Set<ModuleCode> moduleSet = new HashSet<>();
        if (moduleCodes.toArray().length == 0) {
            return moduleSet;
        }
        for (String moduleCode : moduleCodes) {
            if (!ModuleCode.isValidModuleCode(moduleCode)) {
                throw new ParseException(ModuleCode.MESSAGE_CONSTRAINTS);
            }
            ModuleCode mc = new ModuleCode(moduleCode.toUpperCase());
            moduleSet.add(mc);
        }
        return moduleSet;
    }

    /**
     * Parses {@code Collection<String> modules} into a {@code Set<ModuleCode>}.
     */
    public static Set<ModuleCode> parseMultipleModulesAndGroups(Collection<String> moduleCodesAndGroups)
        throws ParseException {
        requireNonNull(moduleCodesAndGroups);
        final Set<ModuleCode> moduleSet = new LinkedHashSet<>();
        ModuleCode mc;
        if (moduleCodesAndGroups.toArray().length == 0) {
            return moduleSet;
        }
        for (String moduleCode : moduleCodesAndGroups) {
            if (moduleCode.contains(" g/")) {
                String[] strArr = moduleCode.split(" g/");
                if (!ModuleCode.isValidModuleCode(strArr[0])) {
                    throw new ParseException(ModuleCode.MESSAGE_CONSTRAINTS);
                }
                mc = new ModuleCode(strArr[0].toUpperCase());
            } else {
                if (!ModuleCode.isValidModuleCode(moduleCode)) {
                    throw new ParseException(ModuleCode.MESSAGE_CONSTRAINTS);
                }
                mc = new ModuleCode(moduleCode.toUpperCase());
            }
            moduleSet.add(mc);
        }
        return moduleSet;
    }

    /**
     * Parses {@code String office} into an {@code Office}.
     */
    public static Office parseOffice(String office) throws ParseException {
        requireNonNull(office);
        String trimmedOffice = office.trim();
        if (!Office.isValidOffice(trimmedOffice)) {
            throw new ParseException(Office.MESSAGE_CONSTRAINTS);
        }
        return new Office(trimmedOffice);
    }

    /**
     * Parses {@code Collection<String> modules} into a {@code Set<ModuleCode>}.
     */
    public static ArrayList<LinkedHashSet<String>> parseMultipleGroups(Collection<String> moduleCodesAndGroups)
        throws ParseException {
        requireNonNull(moduleCodesAndGroups);
        final ArrayList<LinkedHashSet<String>> groupList = new ArrayList<LinkedHashSet<String>>();
        if (moduleCodesAndGroups.toArray().length == 0) {
            return groupList;
        }
        for (String moduleCodeAndGroup : moduleCodesAndGroups) {
            LinkedHashSet<String> groupNamesSet = new LinkedHashSet<>();
            if (moduleCodeAndGroup.contains("g/")) {
                String[] strArr = moduleCodeAndGroup.split(" g/");
                if (strArr.length == 1) {
                    throw new ParseException(Group.NAME_CONSTRAINT_MESSAGE);
                }
                for (int i = 1; i < strArr.length; i++) {
                    if (!Group.isValidName(strArr[i])) {
                        throw new ParseException(Group.NAME_CONSTRAINT_MESSAGE);
                    }
                    groupNamesSet.add(strArr[i]);
                }
            }
            groupList.add(groupNamesSet);
        }
        return groupList;
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static List<Object> parseMeetingTime(String meetingTime) throws ParseException {
        requireNonNull(meetingTime);
        String trimmedTag = meetingTime.trim();
        List<Object> list = new ArrayList<>();
        String[] arr = meetingTime.split(" ");

        if (arr.length != 3) {
            throw new ParseException(MESSAGE_WRONG_MT_FORMAT);
        }


        int idxOfMeetingTime = Integer.parseInt(meetingTime.substring(0 , 1));
        String actualMeetingTime = meetingTime.substring(2);
        LocalDateTime ldt = parseDateTime(actualMeetingTime);
        list.add(idxOfMeetingTime);
        list.add(ldt);
        return list;
    }


    /**
     * Parses a {@code String tag} into a {@code dateTime}.
     * Leading and trailing whitespaces will be trimmed.
     * Parses {@code String groupName} into a {@code Group}.
     */
    public static Group parseGroup(String groupName)
            throws ParseException {

        return new Group(groupName);
    }

    /**
     * Parses a {@code String tag} into a {@code dateTime}.
     * Leading and trailing whitespaces will be trimmed.
     * Parses {@code String groupName} into a {@code Group}.
     */
    public static String parseGroupString(String groupName) {
        return groupName.trim();
    }

    /**
     * Parses a date string with YYYY-MM-DD into LocalDate object
     *
     * @param date
     * @return LocalDate object representing the given string.
     * @throws ParseException
     */
    public static LocalDate parseDate(String date) throws ParseException {
        requireNonNull(date);
        String trimmedDate = date.trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd")
                .withResolverStyle(ResolverStyle.STRICT);
        try {
            return LocalDate.parse(trimmedDate, formatter);
        } catch (Exception e) {
            throw new ParseException("Date format accepts the following:\n"
                + "yyyy-MM-dd");
        }
    }

    /**
     * Parses {@code Collection<String> keyEventAndDate} and
     * {@code Module module}into a {@code ArrayList<ModuleKeyEvent>}.
     */
    public static ArrayList<ModuleKeyEvent> parseModuleKeyEvent(Collection<String> keyEventAndDate, Module module)
        throws ParseException {
        requireNonNull(keyEventAndDate);
        final ArrayList<ModuleKeyEvent> moduleKeyEventList = new ArrayList<>();
        if (keyEventAndDate.toArray().length == 0) {
            return moduleKeyEventList;
        }
        for (String eventAndDate : keyEventAndDate) {
            if (!eventAndDate.contains("dt/")) {
                throw new ParseException(ModuleKeyEvent.MESSAGE_CONSTRAINTS_MISSINGDT);
            }
            String[] strArr = eventAndDate.split("dt/");
            if (strArr.length == 1 || strArr.length == 0) {
                throw new ParseException(ModuleKeyEvent.MESSAGE_CONSTRAINTS_TYPE);
            }
            ModuleKeyEvent.KeyEventType keyEventType = parseKeyEventType(strArr[0]);
            LocalDateTime dateTime = parseDateTime(strArr[1]);
            ModuleKeyEvent moduleKeyEvent = new ModuleKeyEvent(keyEventType, dateTime, module);
            moduleKeyEventList.add(moduleKeyEvent);
        }
        return moduleKeyEventList;
    }

    /**
     * Parses {@code String dateTime} into a {@code LocalDateTime}.
     */
    public static LocalDateTime parseDateTime(String dateTime) throws ParseException {
        requireNonNull(dateTime);
        String trimmedDateTime = dateTime.trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm")
                .withResolverStyle(ResolverStyle.STRICT);
        try {
            return LocalDateTime.parse(trimmedDateTime, formatter);
        } catch (Exception e) {
            throw new ParseException("DateTime format accepts the following:\n"
                + "yyyy-MM-dd HH:mm. Please ensure you have entered a valid date and time!");
        }
    }

    /**
     * Parses {@code String keyEventType} into a {@code KeyEventType}.
     */
    public static ModuleKeyEvent.KeyEventType parseKeyEventType(String keyEventType) throws ParseException {
        requireNonNull(keyEventType);
        String keyEventTypeTrimmed = keyEventType.trim();
        switch (keyEventTypeTrimmed) {
        case "1":
            return ModuleKeyEvent.KeyEventType.EXAM;
        case "2":
            return ModuleKeyEvent.KeyEventType.QUIZ;
        case "3":
            return ModuleKeyEvent.KeyEventType.ASSIGNMENT_RELEASE;
        case "4":
            return ModuleKeyEvent.KeyEventType.ASSIGNMENT_DUE;
        default:
            throw new ParseException(ModuleKeyEvent.MESSAGE_CONSTRAINTS_TYPE);
        }
    }

    /**
     * Parses {@code List<String> allDateTimes} into an {@code ArrayList<LocalDateTime>}.
     */
    public static ArrayList<LocalDateTime> parseAllDateTimes(List<String> allDateTimes) throws ParseException {
        ArrayList<LocalDateTime> dateTimeList = new ArrayList<>();
        for (String dateTimeStr : allDateTimes) {
            LocalDateTime dateTime = parseDateTime(dateTimeStr);
            if (dateTimeList.contains(dateTime)) {
                throw new ParseException("You cannot add duplicate meeting times to the group!");
            }
            dateTimeList.add(dateTime);
        }
        return dateTimeList;
    }

    /**
     * Parses a {@code String } into a {@code keyeventtype}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code LocalDateTime} is invalid.
     */
    public static KeyEventType parseKeyEventTypeString(String type) throws ParseException {
        requireNonNull(type);
        String trimmedType = type.trim();
        return KeyEventType.valueOf(trimmedType.toUpperCase());
    }
}
