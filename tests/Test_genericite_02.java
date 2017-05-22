interface Integer {
    int getInteger();
}

class IntegerImpl implements Integer {
    private int i;

    public IntegerImpl(int _i) {
        i = _i;
    }

    public int getInteger() {
        return i;
    }
}

class Mask <T extends Integer> {
    private T t;

    public Mask(T _t) {
        t = _t;
    }

    public T getValue() {
        return t;
    }
}

public class Test_genericite_02 {
    public static void main(String[] args) {
        IntegerImpl ii = new IntegerImpl(42);
        Mask<Integer> i = new Mask<Integer>(ii);

        print i.getValue().getInteger();
    }
}
