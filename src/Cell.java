
import java.awt.*;

public class Cell {
    //  Does NOT change during the life cycle of the cell
    private final int identity;// An integer represent the uniqueness of cell
    private final double radius;// Unit is meter

    private Rect rect;

    private final double error = 0.0001;

    /*
    Position: Represented by a real number pair (x,y)
    x: Distance between the left border of the plain and the center of the cell (in meters)
    y: Distance between the bottom border of the plain and the center of the cell (in meters)
     */
    private double xPosition;
    private double yPosition;

    private char color;
    private final double perceptionRange;// 感知范围，正方形边长的一半

    private int numOfR;
    private int numOfG;
    private int numOfB;
    private int numOfY;
    private int numOfC;

    public Cell(int id, double x, double y, double r, double perceptionRange, char color) {
        identity = id;
        radius = r;
        xPosition = x;
        yPosition = y;
        this.color = color;
        this.perceptionRange = perceptionRange;
    }

    // Decide if two cells are overlapping
    public boolean isOverlap(Cell c1, Cell c2) {
        double x1 = c1.xPosition;
        double y1 = c1.yPosition;
        double r1 = c1.radius;
        double x2 = c2.xPosition;
        double y2 = c2.yPosition;
        double r2 = c2.radius;
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2)) < r1 + r2 || Math.abs(Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2)) - (r1 + r2)) < error;
    }

    // Decide if current cell(调用此方法的cell) is in cell c's(入参的cell) perception rage
    public boolean isPerceived1(Cell cell) {
        double xLength = Math.abs(cell.xPosition - xPosition);
        double yLength = Math.abs(cell.yPosition - yPosition);
        double p = cell.perceptionRange;
        if (xLength > yLength && (xLength < p + radius || Math.abs(p + radius - xLength) < error
        ))
            return true;
        else if (xLength < yLength && (yLength < p + radius || Math.abs(p + radius - yLength) < error
        ))
            return true;
        else if ((xLength > p || Math.abs(xLength - p) < error
        ) && (yLength > p || Math.abs(yLength - p) < error
        )) {
            return Math.sqrt(Math.pow(yLength - p, 2) + Math.pow(xLength - p, 2)) < radius || Math.abs(Math.sqrt(Math.pow(yLength - p, 2) + Math.pow(xLength - p, 2)) - radius) < error
                    ;
        }
        return false;
    }

    public boolean isPerceived(Cell cell) {
        double x = xPosition;
        double y = yPosition;
        double r = radius;
        double x1 = cell.xPosition;
        double y1 = cell.yPosition;
        double p1 = cell.perceptionRange;

        if ((x1 - p1 < x || Math.abs(x1 - p1 - x) < error
        ) && (x < x1 + p1 || Math.abs(x1 + p1 - x) < error
        )) {
            if (y >= y1 && (y < y1 + p1 + r) || Math.abs(y1 + p1 + r - y) < error
            ) {
                return true;
            } else return y <= y1 && (y > y1 - p1 - r || Math.abs(y1 - p1 - r - y) < error
            );
        } else if ((y1 - p1 < y || Math.abs(y1 - p1 - y) < error
        ) && (y < y1 + p1 || Math.abs(y1 + p1 - y) < error
        )) {
            if (x <= x1 && (x > x1 - p1 - r || Math.abs(x1 - p1 - r - x) < error
            )) {
                return true;
            } else return x >= x1 && (x < x1 + p1 + r || Math.abs(x1 + p1 + r - x) < error
            );
        } else
            return (((x > x1 + p1 || Math.abs(x1 + p1 - x) < error
            ) && (y > y1 + p1 || Math.abs(y1 + p1 - y) < error
            )) || ((x > x1 + p1 || Math.abs(x1 + p1 - x) < error
            ) && (y < y1 - p1 || Math.abs(y1 - p1 - y) < error
            )) || ((x < x1 - p1 || Math.abs(x1 - p1 - x) < error
            ) && (y < y1 - p1 || Math.abs(y1 - p1 - y) < error
            )) || ((x < x1 - p1 || Math.abs(x1 - p1 - x) < error
            ) && (y > y1 + p1 || Math.abs(y1 + p1 - y) < error
            ))) && (Math.pow(x - x1, 2) + Math.pow(y - y1, 2) <= Math.pow(r + Math.sqrt(2) * p1, 2) || Math.abs(Math.pow(x - x1, 2) + Math.pow(y - y1, 2) - Math.pow(r + Math.sqrt(2) * p1, 2)) < error
            );
    }

//    public boolean isPerceived(Cell c) {
//        double x1 = c.getxPosition();
//        double y1 = c.getyPosition();
//        double p1 = c.getPerceptionRange();
//        double x = xPosition;
//        double y = yPosition;
//        double r = radius;
//
//        if (x1 - p1 <= x && x <= x1 + p1) {
//            if (y > y1 && y <= y1 + p1 + r) {
//                return true;
//            } else return y < y1 && y >= y1 - p1 - r;
//        } else if (y1 - p1 <= y && y <= y1 + p1) {
//            if (x < x1 && x >= x1 - p1 - r) {
//                return true;
//            } else return x > x1 && x <= x1 + p1 + r;
//        } else if ((x == x1 - p1 - r && y == y1 + p1 + r) || (x == x1 + p1 + r && y == y1 + p1 + r) || (x == x1 + p1 + r && y == y1 - p1 - r) || (x == x1 - p1 - r && y == y1 - p1 - r)) {
//            return Math.pow(x - x1, 2) + Math.pow(y - y1, 2) <= Math.pow(r + Math.sqrt(2 * p1), 2);
//        } else {
//            return false;
//        }
//    }

//    // Decide if a point is within the cell
//    public boolean isInCircle(double x, double y) {
//        return Math.abs(x - xPosition) <= radius && Math.abs(y - yPosition) <= radius;
//    }

    // Cell changes its color
    public void change() {
        switch (color) {
            case 'r':
                if (numOfR >= 3 && (double) numOfR / numOfC > 0.7) {
                    color = 'g';
                } else if (numOfY >= 1 && (double) numOfY / numOfC < 0.1) {
                    color = 'y';
                }
                break;
            case 'g':
                if (numOfG >= 3 && (double) numOfG / numOfC > 0.7) {
                    color = 'b';
                } else if (numOfR >= 1 && (double) numOfR / numOfC < 0.1) {
                    color = 'r';
                }
                break;
            case 'b':
                if (numOfB >= 3 && (double) numOfB / numOfC > 0.7) {
                    color = 'y';
                } else if (numOfG >= 1 && (double) numOfG / numOfC < 0.1) {
                    color = 'g';
                }
                break;
            case 'y':
                if (numOfY >= 3 && (double) numOfY / numOfC > 0.7) {
                    color = 'r';
                } else if (numOfB >= 1 && (double) numOfB / numOfC < 0.1) {
                    color = 'b';
                }
        }
    }

    // Decide if current cell(调用此方法的cell) is in the motion path of the parameter cell(入参的cell)
    public boolean isIn(Cell cell) {
        double x1 = xPosition;
        double y1 = yPosition;
        double r1 = radius;
        double x = cell.xPosition;
        double y = cell.yPosition;
        double r = cell.radius;
        switch (cell.getColor()) {
            case 'r':
                if (y1 >= y && y1 <= y + (double) 1 / 15) return Math.abs(x1 - x) <= r + r1;
                else if (y1 > y + (double) 1 / 15)
                    return Math.pow(x - x1, 2) + Math.pow(y + (double) 1 / 15 - y1, 2) <= Math.pow(r + r1, 2);
                break;
            case 'g':
                if (y1 >= y - (double) 1 / 15 && y1 <= y) return Math.abs(x - x1) <= r + r1;
                else if (y1 < y - (double) 1 / 15)
                    return Math.pow(x - x1, 2) + Math.pow(y - (double) 1 / 15 - y1, 2) <= Math.pow(r + r1, 2);
                break;
            case 'b':
                if (x1 >= x - (double) 1 / 15 && x1 <= x) return Math.abs(y - y1) <= r + r1;
                else if (x1 < x - (double) 1 / 15)
                    return Math.pow(x - (double) 1 / 15 - x1, 2) + Math.pow(y - y1, 2) <= Math.pow(r + r1, 2);
                break;
            case 'y':
                if (x1 >= x && x1 <= x + (double) 1 / 15) return Math.abs(y1 - y) <= r + r1;
                else if (x1 > x + (double) 1 / 15)
                    return Math.pow(x + (double) 1 / 15 - x1, 2) + Math.pow(y - y1, 2) <= Math.pow(r + r1, 2);
                break;
        }
        return false;
    }

    public boolean isToEdge(int width, int height) {
        if (color == 'r') { // Upward
            return yPosition + (double) 1 / 15 + radius > height || Math.abs(yPosition + (double) 1 / 15 + radius - height) < error
                    ;
        } else if (color == 'g') { // Downward
            return yPosition - (double) 1 / 15 - radius < 0 || Math.abs(yPosition - (double) 1 / 15 - radius) < error
                    ;
        } else if (color == 'b') { // Left
            return xPosition - (double) 1 / 15 - radius < 0 || Math.abs(xPosition - (double) 1 / 15 - radius) < error
                    ;
        } else if (color == 'y') { // Right
            return xPosition + (double) 1 / 15 + radius > width || Math.abs(xPosition + (double) 1 / 15 + radius - width) < error
                    ;
        }
        return false;
    }

    public double getxPosition() {
        return xPosition;
    }

    public void setxPosition(double xPosition) {
        this.xPosition = xPosition;
    }

    public double getyPosition() {
        return yPosition;
    }

    public void setyPosition(double yPosition) {
        this.yPosition = yPosition;
    }

    public char getColor() {
        return color;
    }

    public Color Color() {
        if (color == 'r') {
            return Color.RED;
        } else if (color == 'g') {
            return Color.GREEN;
        } else if (color == 'b') {
            return Color.BLUE;
        } else {
            return Color.YELLOW;
        }
    }

    public void setColor(char color) {
        this.color = color;
    }

    public double getRadius() {
        return radius;
    }

    public boolean inSquare(Square q) {
        return q.contains(this.getRect());
    }

    public Rect getRect() {
        return rect;
    }

    public int getNumOfR() {
        return numOfR;
    }

    public void setNumOfR(int numOfR) {
        this.numOfR = numOfR;
    }

    public int getNumOfG() {
        return numOfG;
    }

    public void setNumOfG(int numOfG) {
        this.numOfG = numOfG;
    }

    public int getNumOfB() {
        return numOfB;
    }

    public void setNumOfB(int numOfB) {
        this.numOfB = numOfB;
    }

    public int getNumOfY() {
        return numOfY;
    }

    public void setNumOfY(int numOfY) {
        this.numOfY = numOfY;
    }

    public int getNumOfC() {
        return numOfC;
    }

    public void setNumOfC(int numOfC) {
        this.numOfC = numOfC;
    }

    public double getPerceptionRange() {
        return perceptionRange;
    }

    public int getIdentity() {
        return identity;
    }

    @Override
    public String toString() {
        return xPosition + " " + yPosition + " " + color;
    }
}
