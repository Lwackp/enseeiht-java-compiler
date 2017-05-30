interface I1 {
    int getI1();
}

interface I2 {
    int getI2();
}

interface I3 extends I2, I1 {
    int getI3();
}

interface I4 extends I1, I3 {
    int getI4();
}

class C implements I4 {
    public int getI1() {
        return 1;
    }

    public int getI2() {
        return 2;
    }

    public int getI3() {
        return 3;
    }

    public int getI4() {
        return 4;
    }
}

public class Test_interface_inheritance_02 {
    public static void main(String[] args) {
        C c = new C();

        println c.getI1();
        println c.getI2();
    }
}
