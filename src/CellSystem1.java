import edu.princeton.cs.algs4.StdDraw;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class CellSystem1 {

    private int width; // Width of the window in GUI
    private int height;// Height of the window in GUI
    private double time;
    private int numOfQ; // The number of queries(for terminal mode)
    private double[][] acts;
    private Cell[] cells;
    private int size;
    private final double error = 0.0001;

    /*
        type = 0 -> GUI mode
        type = 1 -> Terminal mode
         */
    public static int mode1 = 0;

    // Construct a new cellSystem
    public CellSystem1(String[] args) {
        size = Integer.parseInt(args[2]);
        cells = new Cell[size];
        time = 0;

        if (mode1 == 0) { // GUI mode
            width = Integer.parseInt(args[0]);
            StdDraw.setXscale(0, width);
            height = Integer.parseInt(args[1]);
            StdDraw.setYscale(0, height);
            StdDraw.enableDoubleBuffering();
            for (int i = 0, j = 0; j < size; i += 5, j++) {
                cells[j] = new Cell(j, Double.parseDouble(args[i + 3]), Double.parseDouble(args[i + 4]), Double.parseDouble(args[i + 5]), Double.parseDouble(args[i + 6]), args[i + 7].charAt(0));
                StdDraw.setPenColor(cells[j].Color());
                StdDraw.filledCircle(cells[j].getxPosition(), cells[j].getyPosition(), cells[j].getRadius());
            }
            StdDraw.show();
            this.numOfQ = Integer.parseInt(args[3 + Integer.parseInt(args[2]) * 5]);
            this.acts = new double[numOfQ][2];
            for (int i = 0, j = 0; j < numOfQ; i += 2, j++) {
                acts[j][0] = Double.parseDouble(args[i + 4 + Integer.parseInt(args[2]) * 5]);
                acts[j][1] = Double.parseDouble(args[i + 5 + Integer.parseInt(args[2]) * 5]);
            }
        } else { // Terminal mode
            width = Integer.parseInt(args[0]);
            height = Integer.parseInt(args[1]);
            for (int i = 0, j = 0; j < Integer.parseInt(args[2]); i += 5, j++) {
                cells[j] = new Cell(j, Double.parseDouble(args[i + 3]), Double.parseDouble(args[i + 4]), Double.parseDouble(args[i + 5]), Double.parseDouble(args[i + 6]), args[i + 7].charAt(0));
            }
            this.numOfQ = Integer.parseInt(args[3 + Integer.parseInt(args[2]) * 5]);
            this.acts = new double[numOfQ][2];
            for (int i = 0, j = 0; j < numOfQ; i += 2, j++) {
                acts[j][0] = Double.parseDouble(args[i + 4 + Integer.parseInt(args[2]) * 5]);
                acts[j][1] = Double.parseDouble(args[i + 5 + Integer.parseInt(args[2]) * 5]);
            }
        }
    }

    public void run1(CellSystem1 cellSystem) { // GUI mode -> 一直不停
        for (double t1 = 0; true; t1++) {
            for (double t2 = 1; t2 <= 15; t2++) {

//                try {
//                    Thread.sleep(500);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }

                time = t1 + t2 / 15.0;

                // Move cell[i] to new position.

                for (Cell c : cells) {
                    cellSystem.move(c);
                }

                // Scan cell[i]'s perception range, calculate the number of cells
                for (Cell c : cells) {
                    c.setNumOfC(countAll(c));
                    if (c.getColor() == 'r') { // For red cells
                        c.setNumOfR(countR(c));
                        c.setNumOfY(countY(c));
                    } else if (c.getColor() == 'g') { // For green cells
                        c.setNumOfG(countG(c));
                        c.setNumOfR(countR(c));
                    } else if (c.getColor() == 'b') { // For blue cells
                        c.setNumOfB(countB(c));
                        c.setNumOfG(countG(c));
                    } else if (c.getColor() == 'y') { // For yellow cells
                        c.setNumOfY(countY(c));
                        c.setNumOfB(countB(c));
                    }
                }

                // Change cell[i]'s color with the rules if necessary
                StdDraw.clear();
                for (Cell c : cells) {
                    c.change();
                    StdDraw.setPenColor(c.Color());
                    StdDraw.filledCircle(c.getxPosition(), c.getyPosition(), c.getRadius());
                }
                StdDraw.show();

            }
        }
    }

    public void run2(CellSystem1 cellSystem) { // Terminal mode
        for (double t1 = 0; true; t1++) {
            for (double t2 = 1; t2 <= 15; t2++) {
                time = t1 + t2 / 15.0;

                if (time > acts[numOfQ - 1][0]) {
                    break;
                }

                // Move cell[i] to new position.
                for (Cell c : cells) {
                    cellSystem.move(c);
                }

                // Scan cell[i]'s perception range, calculate the number of cells
                for (Cell c : cells) {
                    c.setNumOfC(countAll(c));
                    if (c.getColor() == 'r') { // For red cells
                        c.setNumOfR(countR(c));
                        c.setNumOfY(countY(c));
                    } else if (c.getColor() == 'g') { // For green cells
                        c.setNumOfG(countG(c));
                        c.setNumOfR(countR(c));
                    } else if (c.getColor() == 'b') { // For blue cells
                        c.setNumOfB(countB(c));
                        c.setNumOfG(countG(c));
                    } else if (c.getColor() == 'y') { // For yellow cells
                        c.setNumOfY(countY(c));
                        c.setNumOfB(countB(c));
                    }
                }

                // Change cell[i]'s color with the rules if necessary
                for (Cell c : cells) {
                    c.change();
                }

                new File("src\\output").mkdir();
                try (PrintWriter fout = new PrintWriter("src\\output\\output.txt")) {

                    for (int i = 0; i < numOfQ; i++) {
                        if (Math.abs(time - acts[i][0]) < 0.0001) {
                            fout.println(cells[(int) acts[i][1]].toString());
                            System.out.println(cells[(int) acts[i][1]].toString());
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (time > acts[numOfQ - 1][0] || Math.abs(time - acts[numOfQ - 1][0]) < 0.0001) {
                break;
            }
        }
    }

    // Cell moves to next place
    public void move(Cell cell) {
        if (cell.getColor() == 'r') { // Upward
            if (!isStop(cell) && !cell.isToEdge(width, height)) {
                cell.setyPosition(cell.getyPosition() + (double) 1 / 15);
            } else {
                cell.setyPosition(cell.getyPosition() + increment(cell));
            }
        } else if (cell.getColor() == 'g') { // Downward
            if (!isStop(cell) && !cell.isToEdge(width, height)) {
                cell.setyPosition(cell.getyPosition() - (double) 1 / 15);
            } else {
                cell.setyPosition(cell.getyPosition() - increment(cell));
            }
        } else if (cell.getColor() == 'b') { // Left
            if (!isStop(cell) && !cell.isToEdge(width, height)) {
                cell.setxPosition(cell.getxPosition() - (double) 1 / 15);
            } else {
                cell.setxPosition(cell.getxPosition() - increment(cell));
            }
        } else if (cell.getColor() == 'y') { // Right
            if (!isStop(cell) && !cell.isToEdge(width, height)) {
                cell.setxPosition(cell.getxPosition() + (double) 1 / 15);
            } else {
                cell.setxPosition(cell.getxPosition() + increment(cell));
            }
        }
    }

    /*
    Decide if cell will meet other cells in its path
    cell -> 移动的细胞
     */
    public boolean isStop(Cell cell) {
        for (Cell c : cells) {
            if (c != cell) {
                if (c.isIn(cell)) {
                    return true;
                }
            }
        }
        return false;
    }

    /*
    (x, y), r: 入参细胞(移动中的细胞)
    (x_1, y_1), r_1: 被创的那个细胞
    d^2 + (2y - 2y_1)d + [(x_1 - x)^2 + (y_1 + y)^2 - (r_1 + r)^2] = 0

    delta < 0 -> d无解 -> 两圆相离(不会出现相切) -> d = 1/15
    delta = 0 -> d一解 ->
    delta > 0 -> d两解 -> 一正一负 -> 输出正确移动路径上对应的d
     */
    public double increment(Cell cell) {
        double dmin = (double) 1 / 15;
        double d = (double) 1 / 15;
        double x = cell.getxPosition();
        double y = cell.getyPosition();
        double r = cell.getRadius();

        for (Cell c : cells) {
            if (c != cell) {
                if (c.isIn(cell)) {
                    if (cell.getColor() == 'r') { // Upward
                        double delta = 4 * (Math.pow(c.getRadius() + r, 2) - Math.pow(c.getxPosition() - x, 2));
                        if (delta > 0) {
                            d = c.getyPosition() - y - Math.sqrt(delta) / 2;
                        } else if (Math.abs(delta) < error) {
                            d = c.getyPosition() - y;
                        }
                        if (d < dmin) {
                            dmin = d;
                        }
                    } else if (cell.getColor() == 'g') { // Downward
                        double delta = 4 * (Math.pow(c.getRadius() + r, 2) - Math.pow(c.getxPosition() - x, 2));
                        if (delta > 0) {
                            d = y - c.getyPosition() - Math.sqrt(delta) / 2;
                        } else if (Math.abs(delta) < error) {
                            d = y - c.getyPosition();
                        }
                        if (d < dmin) {
                            dmin = d;
                        }
                    } else if (cell.getColor() == 'b') { // Left
                        double delta = 4 * (Math.pow(c.getRadius() + r, 2) - Math.pow(c.getyPosition() - y, 2));
                        if (delta > 0) {
                            d = x - c.getxPosition() - Math.sqrt(delta) / 2;
                        } else if (Math.abs(delta) < error) {
                            d = x - c.getxPosition();
                        }
                        if (d < dmin) {
                            dmin = d;
                        }
                    } else if (cell.getColor() == 'y') { // Right
                        double delta = 4 * (Math.pow(c.getRadius() + r, 2) - Math.pow(c.getyPosition() - y, 2));
                        if (delta > 0) {
                            d = c.getxPosition() - x - Math.sqrt(delta) / 2;
                        } else if (Math.abs(delta) < error) {
                            d = c.getxPosition() - x;
                        }
                        if (d < dmin) {
                            dmin = d;
                        }
                    }
                } else if (cell.isToEdge(width, height)) {
                    if (cell.getColor() == 'r') { // Upward
                        d = height - cell.getRadius() - cell.getyPosition();
                        if (d < dmin) {
                            dmin = d;
                        }
                    } else if (cell.getColor() == 'g') { // Downward
                        d = cell.getyPosition() - cell.getRadius();
                        if (d < dmin) {
                            dmin = d;
                        }
                    } else if (cell.getColor() == 'b') { // Left
                        d = cell.getxPosition() - cell.getRadius();
                        if (d < dmin) {
                            dmin = d;
                        }
                    } else if (cell.getColor() == 'y') { // Right
                        d = width - cell.getRadius() - cell.getxPosition();
                        if (d < dmin) {
                            dmin = d;
                        }
                    }
                }
            }
        }
        return dmin;
    }

    // Count the number of all cells in the perception range
    public int countAll(Cell cell) {
        int count = 0;
        for (Cell c : cells) {
            if (c != cell) {
                if (c.isPerceived(cell)) {
                    count++;
                }
            }
        }
        return count;
    }

    // Count the number of red cells in the perception range
    public int countR(Cell cell) {
        int count = 0;
        for (Cell c : cells) {
            if (c != cell) {
                if (c.isPerceived(cell) && c.getColor() == 'r') {
                    count++;
                }
            }
        }
        return count;
    }

    // Count the number of green cells in the perception range
    public int countG(Cell cell) {
        int count = 0;
        for (Cell c : cells) {
            if (c != cell) {
                if (c.isPerceived(cell) && c.getColor() == 'g') {
                    count++;
                }
            }
        }
        return count;
    }

    // Count the number of blue cells in the perception range
    public int countB(Cell cell) {
        int count = 0;
        for (Cell c : cells) {
            if (c != cell) {
                if (c.isPerceived(cell) && c.getColor() == 'b') {
                    count++;
                }
            }
        }
        return count;
    }

    // Count the number of yellow cells in the perception range
    public int countY(Cell cell) {
        int count = 0;
        for (Cell c : cells) {
            if (c != cell) {
                if (c.isPerceived(cell) && c.getColor() == 'y') {
                    count++;
                }
            }
        }
        return count;
    }

    public Cell[] getCells() {
        return cells;
    }

}
