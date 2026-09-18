import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        System.out.println("Connection Established");
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Enter the Numerator : ");
            int n = sc.nextInt();

            System.out.print("Enter the Denominator : ");
            int d = sc.nextInt();

            int res = n/d;
            System.out.println("Result is " + res);

        } catch (Exception e){
            System.out.println("An Exception occur : " + e);
        }


        System.out.println("Connection Terminated");
    }
}