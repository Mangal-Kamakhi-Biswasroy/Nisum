import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyCounter {
    public Map<String, Integer> countWordFrequencies(String text) {
        String[] words = text.toLowerCase().split("\\W+");
        Map<String, Integer> frequencyMap = new HashMap<>();
        
        for (String word : words) {
            frequencyMap.merge(word, 1, Integer::sum);
        }
        
        return frequencyMap;
    }

    public void displaySortedFrequencies(String text) {
        Map<String, Integer> frequencies = countWordFrequencies(text);
        
        // Sort by value descending, then by key ascending
        List<Map.Entry<String, Integer>> sortedEntries = frequencies.entrySet()
            .stream()
            .sorted(Map.Entry.<String, Integer>comparingByValue().reversed()
                .thenComparing(Map.Entry.comparingByKey()))
            .collect(Collectors.toList());
        
        System.out.println("Word Frequencies:");
        for (Map.Entry<String, Integer> entry : sortedEntries) {
            System.out.printf("%s: %d\n", entry.getKey(), entry.getValue());
        }
    }

    public static void main(String[] args) {
        WordFrequencyCounter counter = new WordFrequencyCounter();
        String text = "Hello world! Hello Java. Java is great. World is big.";
        counter.displaySortedFrequencies(text);
    }
}
