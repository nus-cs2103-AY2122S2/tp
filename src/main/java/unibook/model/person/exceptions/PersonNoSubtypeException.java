package unibook.model.person.exceptions;

/**
 * Thrown when a person object is not one of the proper subtypes, Professor or Student.
 * TODO: when commands have been updated, can just make Person class abstract.
 */
public class PersonNoSubtypeException extends RuntimeException {
    static final String DEFAULT_MESSAGE = "Person object is not a subtype of Person class";

    public PersonNoSubtypeException() {
        super(DEFAULT_MESSAGE);
    }
}
