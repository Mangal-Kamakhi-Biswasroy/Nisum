import java.util.Stack;

public class BrowserHistory {
    private Stack<String> history = new Stack<>();
    private String currentPage;

    public void visitPage(String url) {
        if (currentPage != null) {
            history.push(currentPage);
        }
        currentPage = url;
        System.out.println("Visited: " + url);
    }

    public void goBack() {
        if (!history.isEmpty()) {
            currentPage = history.pop();
            System.out.println("Went back to: " + currentPage);
        } else {
            System.out.println("No more pages in history");
        }
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void viewHistory() {
        System.out.println("Browser History:");
        for (String url : history) {
            System.out.println(url);
        }
        if (currentPage != null) {
            System.out.println("Current: " + currentPage);
        }
    }

    public static void main(String[] args) {
        BrowserHistory browser = new BrowserHistory();
        browser.visitPage("google.com");
        browser.visitPage("github.com");
        browser.visitPage("stackoverflow.com");
        
        browser.viewHistory();
        
        browser.goBack();
        browser.goBack();
        System.out.println("Current page: " + browser.getCurrentPage());
    }
}
