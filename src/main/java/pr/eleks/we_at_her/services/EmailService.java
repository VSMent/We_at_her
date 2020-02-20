package pr.eleks.we_at_her.services;

public interface EmailService {
    void sendEmail(String to, String subject, String text);
}
