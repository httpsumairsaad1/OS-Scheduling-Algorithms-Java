import java.util.Scanner;

public class SJF_with_Priority {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n;
        int[][] A = new int[100][5]; // Each row: Process ID, Arrival Time, Burst Time, Priority, Waiting Time, Turnaround Time
        int totalWaitingTime = 0;
        float avgWaitingTime, avgTurnaroundTime;

        System.out.println("Enter number of processes:");
        n = input.nextInt();

        System.out.println("Enter Arrival Time, Burst Time, and Priority for each process:");
        for (int i = 0; i < n; i++) {
            System.out.print("P" + (i + 1) + " Arrival Time: ");
            A[i][1] = input.nextInt();
            System.out.print("P" + (i + 1) + " Burst Time: ");
            A[i][2] = input.nextInt();
            System.out.print("P" + (i + 1) + " Priority: ");
            A[i][3] = input.nextInt();
            A[i][0] = i + 1; // Assigning process ID
        }

        // Sort processes based on Arrival Time and Priority
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                // if (c1 || c2 || c3)
                if (A[j][3] > A[j + 1][3]  //priority check
                || (A[j][3] == A[j + 1][3] && A[j][1] > A[j + 1][1]) //priority == another priority & AT check
                || (A[j][3] == A[j + 1][3] && A[j][1] == A[j + 1][1] && A[j][0] > A[j + 1][0])) 
                //priority = another priority & at = another at & process id check.
                {
                    // Swap processes based on Priority, Arrival Time, and Process ID
                    for (int k = 0; k < 5; k++) {
                        int temp = A[j][k];
                        A[j][k] = A[j + 1][k];
                        A[j + 1][k] = temp;
                    }
                }
            }
        }
        // Calculate Waiting Time and Turnaround Time
        A[0][4] = 0; // Waiting time for the first process is always 0
        for (int i = 1; i < n; i++) {
            int waitingTime = 0;
            for (int j = 0; j < i; j++) {
                waitingTime += A[j][2];  //burst time
            }
            A[i][4] = waitingTime - A[i][1]; // Waiting Time & at
            totalWaitingTime += A[i][4];
        }

        // Print process details
        System.out.println("Process\t\tArrival Time\tBurst Time\tPriority\tWaiting Time\tTurnaround Time");
        for (int i = 0; i < n; i++) {
            System.out.println("P" + A[i][0] + "\t\t" + A[i][1] + "\t\t" + A[i][2] + "\t\t" + A[i][3] + "\t\t" + A[i][4] + "\t\t" + (A[i][2] + A[i][4]));
        }
        System.out.print("Process Execution Order: ");
        for (int i = 0; i < n; i++) {
            System.out.print("P" + A[i][0]);
            if (i < n - 1) {
                System.out.print(" -> ");
            }
        }
        System.out.println();

        // Calculate averages
        avgWaitingTime = (float) totalWaitingTime / n;
        avgTurnaroundTime = avgWaitingTime;
        System.out.println("Average Waiting Time= " + avgWaitingTime);
        System.out.println("Average Turnaround Time= " + avgTurnaroundTime);
    }
}
