package pr.eleks.we_at_her.exceptions;

public class PropertyNotFoundException extends Exception {
    public PropertyNotFoundException(String message) {
        super("Property " + message + " was not found");
    }
}
