import java.util.Scanner;

public class SJF {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n;
        int[][] processes;

        System.out.println("Enter number of processes:");
        n = input.nextInt();
        processes = new int[n][4];

        System.out.println("Enter Arrival Time, Burst Time, and Priority for each process:");
        for (int i = 0; i < n; i++) {
            System.out.print("P" + (i + 1) + " Arrival Time: ");
            processes[i][0] = input.nextInt();
            System.out.print("P" + (i + 1) + " Burst Time: ");
            processes[i][1] = input.nextInt();
            System.out.print("P" + (i + 1) + " Priority: ");
            processes[i][2] = input.nextInt();
            processes[i][3] = i + 1;
        }

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (processes[j][0] > processes[j + 1][0]) {
                    int[] temp = processes[j];
                    processes[j] = processes[j + 1];
                    processes[j + 1] = temp;
                }
            }
        }

        int[] completionTime = new int[n];
        int[] waitingTime = new int[n];
        int[] turnaroundTime = new int[n];

        int currentTime = 0;
        for (int i = 0; i < n; i++) {
            int shortestJobIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (processes[j][1] < processes[shortestJobIndex][1] || (processes[j][1] == processes[shortestJobIndex][1] && processes[j][2] < processes[shortestJobIndex][2])) {
                    shortestJobIndex = j;
                }
            }

            int[] temp = processes[i];
            processes[i] = processes[shortestJobIndex];
            processes[shortestJobIndex] = temp;

            completionTime[i] = currentTime + processes[i][1];
            currentTime += processes[i][1];

            turnaroundTime[i] = completionTime[i] - processes[i][0];
            waitingTime[i] = turnaroundTime[i] - processes[i][1];
        }

        System.out.println("Process\t\tArrival Time\tBurst Time\tPriority\tCompletion Time\tWaiting Time\tTurnaround Time");
        for (int i = 0; i < n; i++) {
       
