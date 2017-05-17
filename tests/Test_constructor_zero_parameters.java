class Zero {
    private int val;

    public Zero() {
        this.val = 0;
    }

    public int get() {
        return this.val;
    }
}


public class Test_constructor_zero_parameters {

    public static void main(String[] args) {
        Zero z = new Zero();
        print z.get();
    }
}
