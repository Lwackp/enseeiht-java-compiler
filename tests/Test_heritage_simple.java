class Humain {
    protected String age;

    public Humain(String _age) {
        age = _age;
    }

    public String printAge() {
        return "Il a " + age + " an(s)";
    }
}

class Bebe extends Humain {
	
    public Bebe() {
        age= "1";
    }
    
    public String printAge() {
        return "C'est un bebe";
    }
}

public class Test_heritage_simple {
    public static void main(String[] args) {
        boolean isBebe = true;

        Humain pierre;
        pierre = new Bebe();

        print pierre.printAge();
    }
}