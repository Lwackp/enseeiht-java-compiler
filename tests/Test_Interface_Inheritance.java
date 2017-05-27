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

public class Test_Interface_Inheritance {
    public static void main(String[] args) {
        I1 c = new C();

        print c.getI1();
    }
}
