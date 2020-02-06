package pr.eleks.we_at_her.exceptions;

public class PropertyNotFoundException extends Exception {
    public PropertyNotFoundException(String propertyName) {
        super("Property " + propertyName + " was not found");
    }
}
