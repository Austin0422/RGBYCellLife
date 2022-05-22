import edu.princeton.cs.algs4.In;

public class CoordinateAndColorTest {
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

            errorRateTest(x);
            countError(x);
        }
    }

    // Coordinate error rate test
    public static void errorRateTest(int n) {
        In fin = new In("src\\output\\output.txt");
        String[] out = fin.readAllStrings();
        fin.close();

        In f = new In("src\\sample\\sample" + n + "_out.txt");
        String[] sample = f.readAllStrings();
        f.close();

        double errorXMax = 0;
        double errorYMax = 0;
        double errorX;
        double errorY;

        for (int i = 0, j = 1; i < out.length / 3 && i < sample.length / 3; i += 3, j += 3) {
            errorX = Math.abs((Double.parseDouble(out[i]) - Double.parseDouble(sample[i])) / Double.parseDouble(sample[i]));
            if (errorX > errorXMax) {
                errorXMax = errorX;
            }
            errorY = Math.abs((Double.parseDouble(out[j]) - Double.parseDouble(sample[j])) / Double.parseDouble(sample[j]));
            if (errorY > errorYMax) {
                errorYMax = errorY;
            }
        }

        System.out.printf("Maximum error rate of xPosition: %.5f\n", errorXMax);
        System.out.printf("Maximum error rate of yPosition: %.5f\n", errorYMax);
    }

    // Color error rate test
    public static void countError(int n) {
        In fin = new In("src\\output\\output.txt");
        String[] out = fin.readAllStrings();
        fin.close();

        In f = new In("src\\sample\\sample" + n + "_out.txt");
        String[] sample = f.readAllStrings();
        f.close();

        int count = 0;
        char colorOut;
        char colorSam;

        for (int i = 0, j = 2; i < out.length / 3 && i < sample.length / 3; i += 3, j += 3) {
            colorOut = out[j].charAt(0);
            colorSam = sample[j].charAt(0);

            if (colorOut != colorSam) {
                count++;
            }
        }
        System.out.printf("Number of error color: %d\n", count);
        System.out.printf("Color error rate: %.10f\n", (double) count / (sample.length / 3));
    }

}
