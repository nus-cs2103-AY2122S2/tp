package unibook.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";
    public static final String MESSAGE_INVALID_MODULE_DISPLAYED_INDEX = "The module index provided is invalid";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_CHANGE_TO_MODULE_PAGE = "Changed page to Module Page!";
    public static final String MESSAGE_CHANGE_TO_PERSON_PAGE = "Changed page to Person Page!";
    public static final String MESSAGE_CHANGE_TO_GROUP_PAGE = "Changed page to Group Page!";
    public static final String MESSAGE_MODULE_CODE_NOT_EXIST = "Module Code does not exist: %1$s";

    //ListCommand
    public static final String MESSAGE_ALREADY_ON_PEOPLE_PAGE = "You are already on the People view!";
    public static final String MESSAGE_ALREADY_ON_MODULE_PAGE = "You are already on the Modules view!";
    public static final String MESSAGE_ALREADY_ON_GROUP_PAGE = "You are already on the Groups view!";
    public static final String MESSAGE_LISTED_PEOPLE_WITH_MODULE = "Listed all people in module %s!";
    public static final String MESSAGE_LISTED_MODULE = "Listed module %s!";
    public static final String MESSAGE_LISTED_ALL_STUDENTS = "Listed all students!";
    public static final String MESSAGE_LISTED_ALL_PROFESSORS = "Listed all professors!";
    public static final String MESSAGE_LISTED_ALL_TYPE_IN_MODULE = "Listed all %s in module %s!";
    public static final String MESSAGE_LISTED_MODULE_GROUP = "Listed group %s in module %s!";
    public static final String MESSAGE_LISTED_GROUP_WITH_NAME = "Listed groups with name %s!";
    public static final String MESSAGE_WRONG_VIEW = "The command requires you to switch to the %s view.";
    public static final String MESSAGE_WRONG_TYPE = "The type field must be professors or students.";
    public static final String MESSAGE_INVALID_VIEW = "The view field must be modules or people.\n";
    public static final String MESSAGE_INVALID_LIST_OPTION = "The option field must be view, module, type or group.";
    public static final String MESSAGE_MODULE_FIELD_MISSING = "The m/<MODULECODE> field is missing!";
    public static final String MESSAGE_TYPE_FIELD_MISSING = "The ty/<TYPE> field is missing!";
    public static final String MESSAGE_GROUP_FIELD_MISSING = "The g/<GROUPNAME> field is missing!";
    public static final String MESSAGE_VIEW_FIELD_MISSING = "The v/<VIEW> field is missing!";

}
