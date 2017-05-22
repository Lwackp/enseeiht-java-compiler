class Box <T, H> {
    private T t;
    private H h;

    public Box(T _t, H _h) {
        t = _t;
        h = _h;
    }

    public void setT(T _t) {
        t = _t;
    }

    public T getT() {
        return t;
    }
}

public class Test_genericite_00 {
    public static void main(String[] args) {
        Box<int,int> b = new Box<int, int>(42, 7);

        print b.getT();
    }
}
