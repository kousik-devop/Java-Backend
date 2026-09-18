import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class launchStream {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(4);
        list.add(12);
        list.add(8);
        list.add(9);

        list.stream().sorted().forEach((n)->System.out.println(n));
        List<Integer> newList = list.stream().sorted().map(n->n*2).collect(Collectors.toList());

        System.out.println(newList);
    }
}
