class Triplet {
    private int x;
    private int y;
    private int z;

    public Triplet(int _x, int _y, int _z) {
        x = _x;
        y = _y;
        z = _z;
    }

    public Triplet(int _x, int _y) {
        x = _x;
        y = _y;
        z = 0;
    }

    public Triplet(int _x) {
        x = _x;
        y = 0;
        z = 0;
    }

    public Triplet() {
        x = 0;
        y = 0;
        z = 0;
    }

    public int getFirst() {
        return x;
    }
    
    public int getSecond() {
        return y;
    }
    
    public int getThird() {
        return z;
    }

    public void set(int _x) {
        x = _x;
    }

    public void set(int _x, int _y) {
        x = _x;
        y = _y;
    }

    public void set(int _x, int _y, int _z) {
        x = _x;
        y = _y;
        z = _z;
    }

    public int get(int _index) {
        if (_index == 1) {
            return x;
        } else {
            if(_index == 2) {
                return y;
            }
            else {
                if(_index == 3) {
                    return z;
                }
                else {
                    return 0;
                }
            }
        }
    }

    public int get() {
        return 0;
    }
}

public class Test_overload {
    public static void main(String[] args) {
        Triplet _triplet3 = new Triplet(1, 2, 3);
        println _triplet3.getFirst();
        println _triplet3.getSecond();
        println _triplet3.getThird();
        Triplet _triplet2 = new Triplet(4, 5);
        println _triplet2.getFirst();
        println _triplet2.getSecond();
        println _triplet2.getThird();
        
        println _triplet3.get(1);
        println _triplet3.get();
        _triplet3.set(10);
        println _triplet3.get(1);
        _triplet3.set(9, 8);
        println _triplet3.getFirst();
        println _triplet3.getSecond();
        println _triplet3.getThird();

        _triplet2.set(15, 13, 17);
        println _triplet2.getFirst();
        println _triplet2.getSecond();
        println _triplet2.getThird();

    }
}
