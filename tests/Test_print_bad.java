class Integer {
   int i;

   public Integer() {
      i = 42;
   }
}

public class Test_print_bad {
   public static void main(String[] args) {
      Integer c = new Integer();
      print c;
   }
}
