interface Animal {
    String bruit();
}

class Chien implements Animal {
    private String cri;

    public Chien(String _cri) {
        cri = _cri;
    }

    public String bruit() {
        return "Le chien " + cri;
    }
}

class Chat implements Animal {
    private String cri;

    public Chat(String _cri) {
        cri = _cri;
    }

    public String bruit() {
        return "Le chat " + cri + ".";
    }
}

public class Test_method_interface {
    public static void main(String[] args) {
        boolean isChat = true;

        Animal animal;
        if (isChat) {
            animal = new Chat("miaule");
        } else {
            animal = new Chien("aboie");
        }

        print animal.bruit();
    }
}
