import java.util.*;

class Event {
    private String title;
    private String time;
    private String description;

    public Event(String title, String time, String description) {
        this.title = title;
        this.time = time;
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format("%s at %s: %s", title, time, description);
    }
}

public class EventCalendar {
    private TreeMap<String, List<Event>> calendar = new TreeMap<>();

    public void addEvent(String date, Event event) {
        calendar.computeIfAbsent(date, k -> new ArrayList<>()).add(event);
    }

    public void removeEvent(String date, String eventTitle) {
        if (calendar.containsKey(date)) {
            calendar.get(date).removeIf(event -> event.toString().contains(eventTitle));
        }
    }

    public void displayEventsForDate(String date) {
        if (calendar.containsKey(date)) {
            System.out.println("Events on " + date + ":");
            for (Event event : calendar.get(date)) {
                System.out.println("  " + event);
            }
        } else {
            System.out.println("No events scheduled for " + date);
        }
    }

    public void displayUpcomingEvents() {
        if (calendar.isEmpty()) {
            System.out.println("No upcoming events");
            return;
        }
        System.out.println("Upcoming Events:");
        for (Map.Entry<String, List<Event>> entry : calendar.entrySet()) {
            System.out.println("\n" + entry.getKey() + ":");
            for (Event event : entry.getValue()) {
                System.out.println("  " + event);
            }
        }
    }

    public static void main(String[] args) {
        EventCalendar calendar = new EventCalendar();
        
        calendar.addEvent("2023-12-25", new Event("Christmas Party", "18:00", "Family gathering"));
        calendar.addEvent("2023-12-31", new Event("New Year's Eve", "21:00", "Celebration"));
        calendar.addEvent("2023-12-25", new Event("Gift Exchange", "10:00", "Morning activity"));
        
        calendar.displayUpcomingEvents();
        
        System.out.println("\nEvents on Christmas:");
        calendar.displayEventsForDate("2023-12-25");
    }
}
