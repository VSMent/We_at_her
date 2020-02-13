package pr.eleks.we_at_her.exceptions;

public class WrongApiResponseException extends Exception {
    public WrongApiResponseException(String expactedClass) {
        super("Obtained wrong api response type. Expected class: " + expactedClass + ", Obtained class: " );
    }
}
