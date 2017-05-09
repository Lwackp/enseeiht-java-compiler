class Integer {
    private int in;

    public Integer(int _i) {
        in = _i;
    }

    public int get() {
        return in;
    }
}

public class Test_constructor_one_parameter {
    public static void main(String[] args) {
        Integer i = new Integer(42);
        print i.get();
    }
}