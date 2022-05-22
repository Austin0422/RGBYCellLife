public class Square {
    // Center of the Square
    private final double xmid;
    private final double ymid;

    private final double length; // Width of the Square
    private final double height; // Height of the Square

    public Square(double xmid, double ymid, double length, double height) {  // Initialize
        this.xmid = xmid;
        this.ymid = ymid;
        this.length = length;
        this.height = height;
    }

    // NW表示一个大格子的左上小格，NE右上，SW左下，SE右下
    public Square NW() {
        double x = this.xmid - this.length / 4.0;
        double y = this.ymid + this.length / 4.0;
        double len = this.length / 2.0;
        double h = this.height / 2.0;
        return new Square(x, y, len, h);
    }

    public Square NE() {
        double x = this.xmid + this.length / 4.0;
        double y = this.ymid + this.length / 4.0;
        double len = this.length / 2.0;
        double h = this.height / 2.0;
        return new Square(x, y, len, h);
    }

    public Square SW() {
        double x = this.xmid - this.length / 4.0;
        double y = this.ymid - this.length / 4.0;
        double len = this.length / 2.0;
        double h = this.height / 2.0;
        return new Square(x, y, len, h);
    }

    public Square SE() {
        double x = this.xmid + this.length / 4.0;
        double y = this.ymid - this.length / 4.0;
        double len = this.length / 2.0;
        double h = this.height / 2.0;
        return new Square(x, y, len, h);
    }

    public boolean contains(Rect rect) {  // check if the cell is in the square totally
        double halfLen = this.length / 2.0;
        double halfH = this.height / 2.0;
        return (rect.getX() + rect.getWidth() <= this.xmid + halfLen && rect.getX() >= this.xmid - halfLen && rect.getY() <= this.ymid + halfH && rect.getY() - rect.getHeight() >= this.ymid - halfH);
    }

    public double getLength() {
        return length;
    }

    public double getXmid() {
        return xmid;
    }

    public double getYmid() {
        return ymid;
    }

}