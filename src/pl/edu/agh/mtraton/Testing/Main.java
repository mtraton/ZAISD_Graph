

package pl.edu.agh.mtraton.Testing;

/*
Stworzy� interfejs Graf o nast�puj�cych metodach (poni�sza lista jest propozycj� przydatnych funkcji)
Dodaj w�ze�
Usu� w�ze�
Dodaj kraw�d�
Usu� kraw�d�
Podaj w�z�y s�siednie do w�z�a
Podaj kraw�dzie incydentne do w�z�a
Podaj ilo�� w�z��w
Podaj ilo�� kraw�dzi
Czy w�z�y s� s�siednie
Zaimplementowa� powy�szy interfejs za pomoc� reprezentacji macierzowej i listy s�siedztw
[Uwaga!!] Prosz� nie u�ywa� gotowych struktur danych typu ArrayList itp., a jedynie  Typ [] tablica

 */


import pl.edu.agh.mtraton.*;

public class Main {

    public static void main(String[] args) {
	// write your code here

        //AdjencyListGraph graph = new AdjencyListGraph("D:\\d.txt");
        //graph.printGraph();
        //System.out.println(graph.getNumberOfVertices());
        FordFulkerson ford = new FordFulkerson();
        //ford
       // System.out.print(ford.findDFSPath(1,4));
        ford.foobar(1,2);


       // MatrixGraph MatGraph = new MatrixGraph("C:\\graf.txt");

        //MatGraph.addVertex(25);
        //MatGraph.clearVertex(5);
/*
        MatGraph.printWholeGraph();
        Vertex [] arr = MatGraph.returnNeighbourVertices(1);
        AdjencyListGraph adj = new AdjencyListGraph("C:\\graf.txt");
        adj.printArray(arr);
        System.out.println(adj.areNeighbours(1, 2));
        System.out.println(adj.areNeighbours(1, 4));
        Edge[] incident = MatGraph.returnIncidentEdges(1);
        System.out.println("Edges incident to 1: ");
        adj.printArray(incident);
        System.out.println("Vertices: " + MatGraph.getNumberOfVertices());
        System.out.println("Vertices: " + MatGraph.getNumberOfEdges());
*/
        //AdjencyListGraph AdjGraph = new AdjencyListGraph("C:\\graf.txt");
        //AdjGraph.addVertex(21);
        //AdjGraph.printGraph();
        //AdjGraph.addEdge(1,3,100);
        //System.out.println("Before removing edges");
        //AdjGraph.printGraph();

        //AdjGraph.removeEdge(1, 3);
        //AdjGraph.removeEdge(1,16);
        //System.out.println("After removing edges");
        //AdjGraph.printGraph();
        //Vertex [] neigh = AdjGraph.returnNeighbourVertices(1);
        //AdjGraph.printArray(neigh);
       // AdjGraph.printGraph();
        //Edge[] incident = AdjGraph.returnOutComingEdges(1);
        //System.out.println("Edges incident to 1: ");
        //AdjGraph.printArray(incident);
        //System.out.println("Vertices in graph : " + AdjGraph.getNumberOfVertices());
        //System.out.println("Edges in graph : " + AdjGraph.getNumberOfEdges());
       // AdjGraph.clearVertex(10);
        //AdjGraph.printGraph();

        /*


        int [] arr = {1,2,3};
        int [] arr2 = AdjGraph.expandArray(arr,10,3);
        for(int i = 0; i < arr2.length; i++)
        {
            System.out.println("x = " + arr2[i]);
        }
        */
        /*
        int [][] matrix = {
                {1, 2, 3},
                {3, 4, 5},
                {6, 7, 9}
        };
        int [][] expandedMatrix = AdjGraph.expandMatrix(matrix, 4, 3);
        for( int[] line : expandedMatrix)
        {
            System.out.println("");
            for (int i : line)
            {
                System.out.print(i + ", ");
            }
        }
        */

    }




}
