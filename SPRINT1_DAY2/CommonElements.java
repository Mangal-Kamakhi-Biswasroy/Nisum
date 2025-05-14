import java.util.ArrayList;
import java.util.List;

public class CommonElements {
    public static <T> List<T> findCommonElements(List<T> list1, List<T> list2) {
        List<T> common = new ArrayList<>(list1);
        common.retainAll(list2);
        return common;
    }

    public static void main(String[] args) {
        List<Integer> list1 = List.of(1, 2, 3, 4, 5);
        List<Integer> list2 = List.of(4, 5, 6, 7, 8);
        
        List<Integer> common = findCommonElements(list1, list2);
        System.out.println("Common elements: " + common);
    }
}
