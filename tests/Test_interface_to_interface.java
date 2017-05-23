interface I1 {
    int getI1();
}

interface I2 extends I1 {
    int getI2();
}

class C implements I2 {
    public int getI1() {
        return 1;
    }

    public int getI2() {
        return 2;
    }
}

public class Test_interface_to_interface {
    public static void main(String[] args) {
        C c = new C();
        I2 i2 = c;
        I1 i1 = i2;

        print i1.getI1();
    }
}
