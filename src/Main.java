import edu.princeton.cs.algs4.In;

public class Main {
    public static void main(String[] args) {
        int x = 2;// samplex.txt
        int sys = 0;// 运行第sys个系统
        for (int i = x; i <= x; i++) {
            In fin = new In("src\\sample\\sample" + i + ".txt");
            String[] data = fin.readAllStrings();
            fin.close();

            switch (sys) {
                case 0:
                    // CellSystem
                    if (args.length != 0 && args[0].equals("terminal")) {
                        CellSystem.mode = 1;
                    }

                    CellSystem system = new CellSystem(data);
                    if (CellSystem.mode == 1) { // Terminal mode
                        system.run2(system);
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
                        system1.run2(system1);
                    } else { // GUI mode
                        system1.run1(system1);
                    }
            }
        }
    }

}
