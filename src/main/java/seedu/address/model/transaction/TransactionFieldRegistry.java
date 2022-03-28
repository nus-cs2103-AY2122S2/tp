package seedu.address.model.transaction;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.TransactionFieldParser;

public class TransactionFieldRegistry {
    public static final Map<Prefix, TransactionFieldParser<? extends TransactionField>> PARSERS;
    public static final Prefix[] REQUIRED_PREFIXES;
    public static final Prefix[] PREFIXES;
    public static final Prefix[] FLAGS;
    static {
        HashMap<Prefix, TransactionFieldParser<? extends TransactionField>> parsers = new HashMap<>();
        // --- Do not modify above this line unless you know what you're doing. (Trust me, you don't.) ---

        parsers.put(Amount.PREFIX, Amount::new);
        parsers.put(DueDate.PREFIX, DueDate::new);
        parsers.put(TransactionDate.PREFIX, TransactionDate::new);
        parsers.put(Note.PREFIX, Note::new);
        parsers.put(Status.PREFIX, Status::new);

        // --- Do not modify below this line unless you know what you're doing. (Trust me, you don't.) ---
        PARSERS = Collections.unmodifiableMap(parsers);
        PREFIXES = PARSERS.keySet().stream().filter(p -> !p.isFlag()).toArray(Prefix[]::new);
        FLAGS = PARSERS.keySet().stream().filter(Prefix::isFlag).toArray(Prefix[]::new);
        REQUIRED_PREFIXES = Arrays.stream(PREFIXES).filter(Prefix::isRequired).toArray(Prefix[]::new);
    }
}


