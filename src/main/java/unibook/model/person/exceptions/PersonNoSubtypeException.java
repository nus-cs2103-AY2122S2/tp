package unibook.model.person.exceptions;

/**
 * Thrown when a person object is not one of the proper subtypes, Professor or Student.
 */
public class PersonNoSubtypeException extends RuntimeException {
    static final String DEFAULT_MESSAGE = "Person object is not a subtype of Person class";

    public PersonNoSubtypeException() {
        super(DEFAULT_MESSAGE);
    }
}
