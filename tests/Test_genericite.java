class Box <T, H> {
    // T stands for "Type"
    private T t;

    public void set(T _t) { t = _t; }
    public T get() { return t; }
}

public class Test_genericite {
    public static void main(String[] args) {
         Box<int, int> b;
    }
}
