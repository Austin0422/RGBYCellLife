
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stopwatch;

public class RunTimeTest {
    public static void main(String[] args) {
        int x = 2;// samplex.txt
        int sys = 0;// 运行第sys个系统
        for (int i = x; i <= x; i++) {
//            System.out.println("Please choose the mode (If choose terminal mode, please type 'terminal'): ");
//            String str = StdIn.readString();
//            if (str.equals("terminal")) {
//                CellSystem.mode = 1;
//            }

            In fin = new In("src\\sample\\sample" + i + ".txt");
            String[] data = fin.readAllStrings();
            fin.close();

            Stopwatch timer = new Stopwatch();

            double[] time = new double[10];
            double now = 0;
            double last;

            switch (sys) {
                case 0:
                    // CellSystem
                    if (args.length != 0 && args[0].equals("terminal")) {
                        CellSystem.mode = 1;
                    }

                    CellSystem system = new CellSystem(data);
                    if (CellSystem.mode == 1) { // Terminal mode
                        for (int j = 0; j < 10; j++) {
                            last = now;
                            now = timer.elapsedTime();
                            system.run2(system);
                            time[j] = now - last;
                        }
                    } else { // GUI mode
                        system.run1(system);
                    }
                    break;
                case 1:
                    // CellSystem1
                    if (args.length != 0 && args[0].equals("terminal")) {
                        CellSystem1.mode1 = 1;
                    }

                    CellSystem1 system1 = new CellSystem1(data);
                    if (CellSystem1.mode1 == 1) { // Terminal mode
                        for (int j = 0; j < 10; j++) {
                            last = now;
                            now = timer.elapsedTime();
                            system1.run2(system1);
                            time[j] = now - last;
                        }
                    } else { // GUI mode
                        system1.run1(system1);
                    }
            }

            for (double t : time) {
                System.out.printf("Time spend: %.10f seconds\n", t);
            }
        }
    }

}
