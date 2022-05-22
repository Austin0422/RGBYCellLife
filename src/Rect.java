
// 细胞移动路径的范围
public class Rect {
    private double x;
    private double y;
    private double width;
    private double height;

    public Rect(Cell cell) {
        switch (cell.getColor()) {
            case 'r': // Move upward
                this.x = cell.getxPosition();
                this.y = cell.getyPosition() + (double) 1 / 15;
                this.width = 2 * cell.getRadius();
                this.height = 2 * cell.getRadius() + (double) 1 / 15;
                break;
            case 'g': // Move downward
                this.x = cell.getxPosition();
                this.y = cell.getyPosition();
                this.width = 2 * cell.getRadius();
                this.height = 2 * cell.getRadius() + (double) 1 / 15;
                break;
            case 'b': // Move left
                this.x = cell.getxPosition() - (double) 1 / 15;
                this.y = cell.getyPosition();
                this.width = 2 * cell.getRadius() + (double) 1 / 15;
                this.height = 2 * cell.getRadius();
                break;
            case 'y': // Move right
                this.x = cell.getxPosition();
                this.y = cell.getyPosition();
                this.width = 2 * cell.getRadius() + (double) 1 / 15;
                this.height = 2 * cell.getRadius();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + cell.getColor());
        }
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}