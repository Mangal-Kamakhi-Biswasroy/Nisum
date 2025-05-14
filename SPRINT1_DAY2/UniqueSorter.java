import java.util.Arrays;
import java.util.TreeSet;

public class UniqueSorter {
    public static TreeSet<Integer> getUniqueSorted(int[] numbers) {
        TreeSet<Integer> sortedUnique = new TreeSet<>();
        for (int num : numbers) {
            sortedUnique.add(num);
        }
        return sortedUnique;
    }

    public static void main(String[] args) {
        int[] numbers = {5, 2, 8, 2, 5, 1, 9, 8};
        TreeSet<Integer> result = getUniqueSorted(numbers);
        System.out.println("Sorted unique elements: " + result);
    }
}
