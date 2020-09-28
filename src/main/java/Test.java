import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        ArrayList<Integer> integers = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            integers.add(i + 1);
        }
        int[] ints = integers.stream().mapToInt(k -> k).toArray();
        System.out.println(ints);
    }
}
