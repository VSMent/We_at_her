package pr.eleks.we_at_her.exceptions;

public class WrongApiResponseException extends Exception {
    public WrongApiResponseException(String expectedClass) {
        super("Obtained wrong api response. Expected class: " + expectedClass);
    }
}
