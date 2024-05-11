import java.util.Scanner;

public class Startup {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Scanner scan = new Scanner(System.in);
        while(true)
        {
            String input =  scan.nextLine();
            System.out.println("Input: "+input);
        }
    }

}