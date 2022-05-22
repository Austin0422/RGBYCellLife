import java.util.LinkedList;

public class QuadTree {

    private LinkedList<Cell> cell; // body or aggregate body stored in this node
    private Rect rect;
    private Square square; // square region that the tree represents
    private QuadTree father;
    private QuadTree NW; // tree representing northwest quadrant
    private QuadTree NE; // tree representing northeast quadrant
    private QuadTree SW; // tree representing southwest quadrant
    private QuadTree SE; // tree representing southeast quadrant


    public QuadTree(Square square, QuadTree fatherNode) { // initialize with square and fatherNode
        this.square = square;
        this.cell = null;
        this.father = fatherNode;
        this.rect = null;
        this.NE = null;
        this.NW = null;
        this.SE = null;
        this.SW = null;
    }

    public void insert(Cell cell, double dt) {

        //while the particle could still be inserted into the square
        while (square.contains(cell.getRect())) {
            if (!isExternal()) { // internal node
                // recursively insert cells into the appropriate quadrant
                putBody(cell, dt);
            } else { // external node
                // subdivide the region further by creating four children
                NW = new QuadTree(square.NW(), this);
                NE = new QuadTree(square.NE(), this);
                SE = new QuadTree(square.SE(), this);
                SW = new QuadTree(square.SW(), this);
                // recursively insert both this body and Body b into the appropriate quadrant
                putBody(cell, dt);

            }
        }
        // insert the particle to the father node of the current node

    }

    private void putBody(Cell cell, double dt) {
        if (cell.inSquare(square.NW()))
            NW.insert(cell, dt);
        else if (cell.inSquare(square.NE()))
            NE.insert(cell, dt);
        else if (cell.inSquare(square.SE()))
            SE.insert(cell, dt);
        else if (cell.inSquare(square.SW()))
            SW.insert(cell, dt);
    }

    // method clear is wrong
    public void clear() {
        QuadTree root = null;
        while (this.father != null) {

            root = this.father;
        }
        root.NW = null;
        root.NE = null;
        root.SE = null;
        root.SW = null;
    }

    private boolean isExternal() {
        // a node is external if all four children are null
        return (NW == null && NE == null && SW == null && SE == null);
    }

//    public Particle[] retrieve(Quadtree){
//        Particles[] allParticles = new Particle[];
//        return
//    }


//    public String toString() {
//        if (isExternal())
//            return " " + particle + "\n";
//        else
//            return "*" + particle + "\n" + NW + NE + SW + SE;
//    }
}