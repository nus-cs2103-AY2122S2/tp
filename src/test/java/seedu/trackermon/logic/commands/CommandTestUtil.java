package seedu.trackermon.logic.commands;

import static seedu.trackermon.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.trackermon.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.trackermon.logic.parser.CliSyntax.PREFIX_TAG;

/**
 * Contains helper methods for testing commands.
 */

public class CommandTestUtil {

    public static final String PREAMBLE_WHITESPACE = " ";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final String VALID_NAME_YOU = "You";
    public static final String VALID_NAME_ALICE_IN_WONDERLAND = "Alice in WonderLand";
    public static final String VALID_NAME_GONE = "gone";
    public static final String VALID_NAME_ME = "ME";
    public static final String VALID_STATUS_COMPLETED = "completed";
    public static final String VALID_STATUS_WATCHING = "watching";
    public static final String VALID_TAG_MOVIE = "movie";
    public static final String VALID_TAG_SERIES = "series";
    public static final String VALID_TAG_YURI = "yuri";
    public static final String VALID_TAG_HENTAI = "hentai";

    public static final String NAME_DESC_ALICE_IN_WONDERLAND = " " + PREFIX_NAME + VALID_NAME_ALICE_IN_WONDERLAND;
    public static final String NAME_DESC_GONE = " " + PREFIX_NAME + VALID_NAME_GONE;
    public static final String STATUS_DESC_COMPLETED = " " + PREFIX_STATUS + VALID_STATUS_COMPLETED;
    public static final String STATUS_DESC_WATCHING = " " + PREFIX_STATUS + VALID_STATUS_WATCHING;
    public static final String TAG_DESC_MOVIE = " " + PREFIX_TAG + VALID_TAG_MOVIE;
    public static final String TAG_DESC_YURI = " " + PREFIX_TAG + VALID_TAG_YURI;
    public static final String TAG_DESC_HENTAI = " " + PREFIX_TAG + VALID_TAG_HENTAI;


    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "PicoNoPiko&"; // '&' not allowed in names
    public static final String INVALID_STATUS_DESC = " "
            + PREFIX_STATUS + "Watch"; // Status have to be completed or watching
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags
}
