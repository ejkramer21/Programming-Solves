import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class bigboxes {

    public static void main(String[] args) {
        Solution();
    }

    /*
    * Input:
    * n k
    * n integers w
    *
    * n = number of items
    * k = number of groups
    * w = weight of each item in order
    *
    * Note: No sorting allowed due to problem requirements
    *
    * Output:
    * minimum weight of heaviest box
     */
    public static void Solution() {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        int i;
        int numItems = in.nextInt(); //number of total items
        int numBoxes = in.nextInt(); //number of boxes to use
        int boxesUsed = 0; //counter
        long weights[] = new long[numItems]; //store weight of each item
        long max = 0; //if all items go in one box
        int currItem = 0; //track where in weights[]
        long boxWeights[] = new long[numBoxes]; //weight in each box based on max
        long min = (int)Math.pow(10,4); //smallest item alone in box
        long left,right,mid; //bisection points


        //read weights and get min/max
        for (i=0;i<numItems;i++) {
            weights[i]=in.nextLong();
            max+=weights[i];
            if (weights[i]<=min){
                min=weights[i];
            }
        }

        left = 0;
        right = max;
        mid = (long)Math.floor((left+right)/2);

        while(min != max) {
            if (boxesUsed==numBoxes){
                if (currItem==numItems){ //is possible so test smaller
                    right = mid;
                    mid = (long) Math.floor((left+right)/2);
                } else { //not possible, must be bigger
                    left = mid;
                    mid = (long) Math.floor((left+right)/2);
                    if (right == left+1 || right == left) {
                        mid=right;
                        break;
                    }
                }

                //reset values for next run
                boxesUsed=0;
                currItem=0;
                boxWeights = new long[numBoxes];
            }
            if (currItem<numItems && (boxWeights[boxesUsed]+weights[currItem])<=mid ) { //check if we can add next item
                boxWeights[boxesUsed] += weights[currItem++];
            } else{ //next box
                boxesUsed++;
            }
        }

        if (min==max) { //only one item
            System.out.println(max);
        } else { //general case
            System.out.println(mid);
        }

    }
}
