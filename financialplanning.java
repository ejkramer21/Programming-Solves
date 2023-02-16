import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Scanner;

class Investment implements Comparable<Investment> {
    double initial; //initial cost to invest
    double perDay; //returns per day
    double toProfit; //days to profit

    public Investment(double initial, double perDay, double toProfit) {
        this.initial = initial;
        this.perDay = perDay;
        this.toProfit = toProfit;
    }

    public int compareTo(Investment b) {
        if (toProfit == b.toProfit) {
            return 0;
        } else if (toProfit>b.toProfit) {
            return 1;
        }
        return -1;
    }
}

public class financialplanning {

    public static void main(String[] args) {
        Solution();
    }


    /*
    * n M
    * n lines of profit(p) and cost (c)
    *
    * n = number of investments
    * M = minimum amt of money to retire
    * p = profit
    * c = initial cost
     */
    public static void Solution() {

        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        long earned = 0,day=0,addPerDay=0; //amt made in profit, current day(output), profit/day
        long numInvestments = in.nextLong(); //number of investments
        long minNeeded = in.nextLong(); //minimum profit to retire
        double profit,init,toProfit; //info for ea investment
        Investment investment; //temp investment added to heap
        PriorityQueue<Investment> minHeap = new PriorityQueue<>(); //min heap to get earliest profit

        int i;

        //read in values
        for (i=0;i<numInvestments;i++) {
            profit=in.nextLong();
            init = in.nextLong();
            toProfit = init/profit;
            investment = new Investment(init,profit,Math.ceil(toProfit));
            minHeap.add(investment);
        }


        while (earned < minNeeded) {
            if (minHeap.size() == 0) {
                break;
            }
            investment = minHeap.remove(); //soonest profit

            while (day != investment.toProfit) { //get first day to profit
                day++;
                earned+=addPerDay; //add profit for other investments
                if (earned>=minNeeded) { //in case we don't need next investment
                    break;
                }
            }

            if (earned>=minNeeded) { //above
                break;
            }

            earned+=(day*investment.perDay) - (investment.initial); //earned until day of profit-pay to friend
            addPerDay+=investment.perDay; //profits are now added each day in whole

        }

        while (earned < minNeeded) {
            earned+=addPerDay;
            day++;
        }

        System.out.println(day);

    }
}
