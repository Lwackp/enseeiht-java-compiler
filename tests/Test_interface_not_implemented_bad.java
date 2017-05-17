interface Chocolat {
    String getCouleur();
}

class ChocolatImpl implements Chocolat {
    private String couleur;

    public ChocolatImpl(String _couleur) {
        couleur = _couleur;
    }
}

public class Test_interface_not_implemented_bad {
    public static void main(String[] args) {

    }
}
