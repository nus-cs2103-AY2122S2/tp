package seedu.address.model.person;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import seedu.address.logic.parser.FieldParser;
import seedu.address.logic.parser.Prefix;

public class FieldRegistry {
    public static final Map<Prefix, FieldParser<? extends Field>> PARSERS;
    public static final Prefix[] REQUIRED_PREFIXES;
    public static final Prefix[] PREFIXES;
    static {
        HashMap<Prefix, FieldParser<? extends Field>> parsers = new HashMap<>();
        // --- Do not modify above this line unless you know what you're doing. (Trust me, you don't.) ---

        parsers.put(Name.PREFIX, Name::new);
        parsers.put(Phone.PREFIX, Phone::new);
        parsers.put(Email.PREFIX, Email::new);
        parsers.put(Address.PREFIX, Address::new);
        parsers.put(Remark.PREFIX, Remark::new);
        parsers.put(Birthday.PREFIX, Birthday::new);

        // --- Do not modify below this line unless you know what you're doing. (Trust me, you don't.) ---
        PARSERS = Collections.unmodifiableMap(parsers);
        PREFIXES = PARSERS.keySet().toArray(new Prefix[0]);
        REQUIRED_PREFIXES = Arrays.stream(PREFIXES).filter(Prefix::isRequired).toArray(Prefix[]::new);
    }
}


