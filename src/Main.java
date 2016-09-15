import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {

    public static int[] twr1;
    public static int[] twr2;
    public static int[] twr3;
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {


        int disks = 0;
        while (disks < 1) {
            System.out.println("Enter the number of disks you wish to play with");
            Scanner sc = new Scanner(System.in);
            try {disks = sc.nextInt();}
            catch (InputMismatchException e) {
            System.out.println("Invalid value!");
            sc.next();
        }
        }
        twr1 = new int[disks];
        twr2 = new int[disks];
        twr3 = new int[disks];

        setTowers();


        int totalMoves = 0;

        System.out.println("Enter a move. format should be \"FromTo\" for example AB means from A to B");


        while(twr3[0] == 0) {

            display();
            input();
            totalMoves++;
        }
        display();
        System.out.println("YAY! You Win! Your total moves were " +totalMoves);



    }
    //Display
    static void display(){

        for (int i = 0; i < twr1.length; i++) {
            System.out.println(" "+ ((twr1[i] == 0) ? " " : twr1[i])
                    +"   " + ((twr2[i] == 0) ? " " : twr2[i])
                    +"   "+ ((twr3[i] == 0) ? " " : twr3[i]));
        }
        System.out.println("--- --- ---");
        System.out.println(" A   B   C");
    }

    //Evaluate String
    static void input() {
        System.out.println("Enter a move");
        Scanner sc = new Scanner(System.in);
        String testInput = sc.nextLine();
        switch (testInput.trim().toLowerCase()){
            case "ab":
                move(twr1, twr2);
                break;
            case "ac":
                move(twr1, twr3);
                break;
            case "ba":
                move(twr2, twr1);
                break;
            case "bc":
                move(twr2, twr3);
                break;
            case "ca":
                move(twr3, twr1);
                break;
            case "cb":
                move(twr3, twr2);
                break;
            case "solve":
                setTowers();
                solve(twr1.length, twr1,twr2,twr3, "A", "B", "C");
                setTowers();
                System.out.println("now you try!");
                break;
            default:
                System.out.println("invalid input. format should be \"FromTo\" for example AB means from A to B\"");
        }
    }

  //Moving
    static void move(int[] twrA, int[] twrB){

        int position = -1;
        int last = twrB.length-1;

        for (int i = 0; i < twrA.length; i++) {
            if(twrA[i]!=0) {
                position = i;
                break;
            }
        }
        if(position == -1) {
            System.out.println("No disks left");
            return;
        }

        for (int j = twrB.length - 1; j > -1; j--) {
            last = j;
            if(twrB[j] == 0)
            {
                //if(j != twrB.length - 1)
                  //  {last = j+1;}
                break;
            }
        }
        if(twrB[twrB.length - 1]!=0 && twrB[last+1] <= twrA[position])
        {
            System.out.println("illegal move");
        }
        else{
            twrB[last] = twrA[position];
            twrA[position] = 0;
        }
    }

    static void setTowers(){
        for (int j = 0; j < twr1.length; j++) {
            twr1[j]=j+1;
            twr2[j]=0;
            twr3[j]=0;
        }
    }

    //solution
    public static void solve(int n, int[] from, int[] using, int[] to, String fromName, String usingName, String toName) {
        if (n == 1) {
            move(from, to);
            display();
            System.out.println("disk moved form "+ fromName +" to "+ toName);
            System.out.println("press enter to continue");
            sc.nextLine();

        } else {
            solve(n - 1, from, to, using, fromName, toName, usingName);
            move(from, to);
            display();
            System.out.println("disk moved form " + fromName + " to " + toName);
            System.out.println("press enter to continue");
            sc.nextLine();
            solve(n - 1, using, from, to, usingName, fromName, toName);
        }
    }

}


