import java.util.HashSet;
import java.util.Scanner;

public class EmailDeduplicator {
    private HashSet<String> emailSet = new HashSet<>();

    public void addEmails(String[] emails) {
        for (String email : emails) {
            emailSet.add(email.toLowerCase()); // Case insensitive
        }
    }

    public void displayUniqueEmails() {
        if (emailSet.isEmpty()) {
            System.out.println("No emails in the list");
            return;
        }
        System.out.println("Unique email addresses:");
        for (String email : emailSet) {
            System.out.println(email);
        }
    }

    public static void main(String[] args) {
        EmailDeduplicator deduplicator = new EmailDeduplicator();
        String[] emails = {
            "user@example.com",
            "admin@example.com",
            "user@example.com", // Duplicate
            "USER@example.com", // Case variation
            "test@domain.com"
        };
        
        deduplicator.addEmails(emails);
        deduplicator.displayUniqueEmails();
    }
}
