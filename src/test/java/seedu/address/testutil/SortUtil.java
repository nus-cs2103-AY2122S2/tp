package seedu.address.testutil;

import seedu.address.logic.commands.SortCommand;
import seedu.address.model.person.Address;
import seedu.address.model.person.Birthday;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;

public class SortUtil {
    public static final String DESC_STRING = " " + SortCommand.DESCENDING_KEYWORD;

    // field sort order
    public static final SortCommand.FieldSortOrder NAME_SORT_DEFAULT = new SortCommand.FieldSortOrder(
            Name.PREFIX,
            false);
    public static final SortCommand.FieldSortOrder NAME_SORT_DESC = new SortCommand.FieldSortOrder(Name.PREFIX, true);

    public static final SortCommand.FieldSortOrder PHONE_SORT_DEFAULT = new SortCommand.FieldSortOrder(
            Phone.PREFIX,
            false);
    public static final SortCommand.FieldSortOrder PHONE_SORT_DESC = new SortCommand.FieldSortOrder(Phone.PREFIX,
            true);

    public static final SortCommand.FieldSortOrder EMAIL_SORT_DEFAULT = new SortCommand.FieldSortOrder(
            Email.PREFIX,
            false);
    public static final SortCommand.FieldSortOrder EMAIL_SORT_DESC = new SortCommand.FieldSortOrder(Email.PREFIX,
            true);

    public static final SortCommand.FieldSortOrder ADDRESS_SORT_DEFAULT = new SortCommand.FieldSortOrder(
            Address.PREFIX, false);
    public static final SortCommand.FieldSortOrder ADDRESS_SORT_DESC = new SortCommand.FieldSortOrder(Address.PREFIX,
            true);

    public static final SortCommand.FieldSortOrder REMARK_SORT_DEFAULT = new SortCommand.FieldSortOrder(
            Remark.PREFIX,
            false);
    public static final SortCommand.FieldSortOrder REMARK_SORT_DESC = new SortCommand.FieldSortOrder(Remark.PREFIX,
            true);

    public static final SortCommand.FieldSortOrder BIRTHDAY_SORT_DEFAULT = new SortCommand.FieldSortOrder(
            Birthday.PREFIX, false);
    public static final SortCommand.FieldSortOrder BIRTHDAY_SORT_DESC = new SortCommand.FieldSortOrder(Birthday.PREFIX,
            true);
}
