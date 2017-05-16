public class Test_array {
    public static void main(String[] args) {
        int[] t = new int[5];
        t[3] = 4;
        print t[3];

        print "\n";

        int[] ta = {1, 2, 3, 4};
        ta[0] = ta[1];
        print ta[0];
    }
}