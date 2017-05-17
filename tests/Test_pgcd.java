public class Test_pgcd {
  public static void main(String[] args) {
    int[] c = {30, 40};
    int test = 0;
    int a = c[0];
    int b = c[1];
    while (a * b != test) {
      if (a > b) {
        int na = a - b;
        a = na;
      } else {
        int nb = b - a;
        b = nb;
      }
    }
    int res = a;
    if (res == test) {
      res = b;
    }
    print res;
  }
}