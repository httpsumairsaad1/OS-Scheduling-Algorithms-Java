import java.util.Scanner;

public class RoundRobinWithPriority {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n, i, qt, count = 0, temp, sq = 0;
        int bt[], wt[], tat[], rem_bt[], priority[], at[];

        bt = new int[10];
        wt = new int[10];
        tat = new int[10];
        rem_bt = new int[10];
        priority = new int[10];
        at = new int[10];

        System.out.print("Enter the number of processes (maximum 10): ");
        n = scanner.nextInt();

        System.out.println("Enter the burst time, priority, and arrival time of the processes:");
        for (i = 0; i < n; i++) {
            System.out.print("Burst time for process " + (i + 1) + ": ");
            bt[i] = scanner.nextInt();
            System.out.print("Priority for process " + (i + 1) + ": ");
            priority[i] = scanner.nextInt();
            System.out.print("Arrival time for process " + (i + 1) + ": ");
            at[i] = scanner.nextInt();
            rem_bt[i] = bt[i];
        }

        System.out.print("Enter the time quantum: ");
        qt = scanner.nextInt();

        while (true) {
            boolean done = true;
            for (i = 0; i < n; i++) {
                if (rem_bt[i] > 0) {
                    done = false;
                    if (rem_bt[i] > qt) {
                        sq += qt;
                        rem_bt[i] -= qt;
                    } else {
                        sq += rem_bt[i];
                        rem_bt[i] = 0;
                        wt[i] = sq - bt[i];
                    }
                }
            }
            if (done)
                break;
        }

        for (i = 0; i < n; i++) {
            tat[i] = bt[i] + wt[i];
        }

        float awt = 0, atat = 0;
        for (i = 0; i < n; i++) {
            awt += wt[i];
            atat += tat[i];
        }
        awt /= n;
        atat /= n;

        System.out.println("Process\tBurst Time\tWaiting Time\tTurnaround Time");
        for (i = 0; i < n; i++) {
            System.out.println((i + 1) + "\t\t" + bt[i] + "\t\t" + wt[i] + "\t\t" + tat[i]);
        }
        System.out.println("Average Waiting Time: " + awt);
        System.out.println("Average Turnaround Time: " + atat);
    }
}
