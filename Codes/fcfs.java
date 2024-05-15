import java.util.*;

class P {
    String n;
    int a, b, c, t, w, priority;
    
    P(String n, int a, int b, int priority) {
        this.n = n;
        this.a = a;
        this.b = b;
        this.priority = priority;
    }
}

public class fcfs {

    private static void sortP(List<P> processes) {
        for (int i = 0; i < processes.size() - 1; i++) {
            for (int j = i + 1; j < processes.size(); j++) {
                P p1 = processes.get(i);
                P p2 = processes.get(j);
                if (p1.priority > p2.priority || (p1.priority == p2.priority && p1.a > p2.a)) {
                    processes.set(i, p2);
                    processes.set(j, p1);
                }
            }
        }
    }

    public static void main(String[] args) {
        List<P> ps = new ArrayList<>();
        ps.add(new P("P1", 1, 10, 3));
        ps.add(new P("P2", 0, 2, 0));
        ps.add(new P("P3", 0, 7, 1));
        ps.add(new P("P4", 1, 6, 1));
        ps.add(new P("P5", 6, 13, 3));
        
        sortP(ps);

        int ct = 0;
        int tw = 0;
        
        System.out.println("Process\t\tArrival Time\t\tBurst Time\t\tWaiting Time\t\tPriority\t\tTurnaround Time");
        for (P p : ps) {
            p.w = Math.max(0, ct - p.a);
            tw += p.w;
            p.c = Math.max(ct, p.a) + p.b;
            int priority = p.priority;
            int arrivalTime = p.a;
            p.t = p.w + p.b;
            System.out.print(p.n + "\t\t" + arrivalTime + "\t\t\t" + p.b + "\t\t\t" + p.w + "\t\t\t" + priority + "\t\t\t" + p.t);
            if (priority > 0) {
                System.out.println(" \t( I am priority, my process id is " + p.n+ " )");
            } else {
                System.out.println(" \t( I am arrival time, my process id is " + p.n+ " ) ");
            }
            ct = p.c;
        }
        
        double aw = (double) tw / ps.size();
        System.out.println("\nAverage Waiting Time: " + aw);
    }
}
