package pl.edu.agh.mtraton;

/**
 * Created by Rael on 2015-10-14.
 */

// TODO : szablon klasy inicjalizowanej dowoln¹ wartoœci¹
public class Vertex <X> {


    private static int next_id = 0; // TODO : does it make sense?
    private int vid;
    private X value;

    public Vertex()
    {
        vid = next_id++;
    }

    public Vertex(X val)
    {
        vid = next_id++;
        value = val;
    }
    public Vertex(int id, X val)
    {
        vid = id;
        value = val;
    }

    public int getVid() {
        return vid;
    }

    public void setVid(int vid) {
        this.vid = vid;
    }

    public X getValue() {
        return value;
    }

    public void setValue(X value) {
        this.value = value;
    }

    @Override
    public String toString() {
        String s = "";
        s = s + "vid: " + vid + ", val:" + value;
        return s;
    }
}
