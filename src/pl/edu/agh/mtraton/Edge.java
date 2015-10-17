package pl.edu.agh.mtraton;

/**
 * Created by Rael on 2015-10-14.
 */
// TODO : szablon klasy inicjalizowanej dowoln¹ wartoœci¹

public class Edge {
    private int start;
    private Vertex end;
    private int val;// todo: paramatrize

    public Edge(int start, Vertex end, int val)
    {
        this.start = start;
        this.end = end;
        this.val = val;
    }
    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public Vertex getEnd() {
        return end;
    }

    public void setEnd(Vertex end) {
        this.end = end;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        String s = "";
        s = s + "Edge from : " + start + " to: " + end.getVid() + " with weight: " + end.getValue();
        return s;
    }
}
