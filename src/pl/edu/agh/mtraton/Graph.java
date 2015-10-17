package pl.edu.agh.mtraton;

/**
 * Created by Rael on 2015-10-08.
 */

// number of vertex indice in file should be independent of it's id in graph
public interface Graph {

    // POLA

    // int[] GraphMatrix = 0;

    // METODY
    public void loadGraphFromFile(String Path);
    public void addVertex(int vertexID); // domyœlny argument?
    public void removeVertex(int vertexID);
    public void addEdge(int v1ID, int v2ID, int weight);
    public void removeEdge(int v1, int v2);
    public Vertex[] returnNeighbourVertices(int vertexID);
    public Edge[] returnIncidentEdges(int vertexID);
    public int getNumberOfVertices();
    public int getNumberOfEdges();
    public boolean areNeighbours(int v1, int v2);


}
