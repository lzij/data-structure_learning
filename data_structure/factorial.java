import java.util.Scanner;

public class factorial {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        System.out.println(Math.log(getfactorial(n)));
    }
    private static int getfactorial(int n){
        if(n == 0 || n== 1){
            return 1;
        }
        else return n*getfactorial(n-1);
    }
}
