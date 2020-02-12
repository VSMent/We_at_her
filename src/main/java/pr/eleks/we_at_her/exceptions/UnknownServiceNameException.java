package pr.eleks.we_at_her.exceptions;

public class UnknownServiceNameException extends Exception {
    public UnknownServiceNameException(String serviceName) {
        super("Unknown service name: " + serviceName);
    }
}
