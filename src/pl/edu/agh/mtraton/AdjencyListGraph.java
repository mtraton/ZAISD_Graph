package pl.edu.agh.mtraton;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import pl.edu.agh.mtraton.Utils.GraphUtils;

/**
 * Created by Rael on 2015-10-08.
 */
//
// TODO change data type to float?
// TODO prealokacja i potem póŸniejsze rozszerzanie

public class AdjencyListGraph implements  Graph {
    // POLA

    Vertex<Integer> [][] graph; //  list of outcoming edges from gives vertex
    int indicesCount = 0;

    public AdjencyListGraph (String path)
    {
        indicesCount = loadNumberOfIndices(path);
        graph = new Vertex [indicesCount +1][0] ; // assumption - we can have indice with id that equals 0
        loadGraphFromFile(path);
    }

    public int getIndicesCount()
    {
        return indicesCount;
    }
    public void setIndicesCount(int val)
    {
        indicesCount = val;
    }

    public int loadNumberOfIndices(String Path)
    {
        // TODO : m¹drzejsze wczytywanie grafu
        BufferedReader br = null;
        String line =  "";
        String separator = ";";
        int max = 0;
        try {
            br = new BufferedReader(new FileReader(Path));
            while ((line = br.readLine()) != null) {


                String[] values = line.split(separator);
                String[] trimmedArray = new String[values.length];
                for (int i = 0; i <  values.length; i++) {
                    trimmedArray[i] = values[i].trim();
                }
                // TODO : waga jest zmiennoprzecinkowa - > zmieniæ , sprawdziæ usuwanie bia³ych znaków
                int v1 = Integer.parseInt(trimmedArray[0]);
                int v2 = Integer.parseInt(trimmedArray[1]);
                if(v1 > max ) max = v1;
                if(v2 > max ) max = v2;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
               e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return max;
    }



    // METODY
    /*
    public int [] expandArray (int [] array, int newSize, int oldSize)
    {
        int [] expandedArray = new int [newSize];
        System.arraycopy(array, 0, expandedArray, 0, oldSize);
        return expandedArray;
    }
    */


    public Vertex [] expandArray (Vertex [] oldArray, int newSize, int oldSize)
    {
        Vertex [] expandedArray = new Vertex [newSize];
        System.arraycopy(oldArray, 0, expandedArray, 0, oldSize);
        return expandedArray;
    }
    public void loadGraphFromFile(String Path)
    {
        BufferedReader br = null;
        String line =  "";
        String separator = ";";

        try {
            br = new BufferedReader(new FileReader(Path));
            while ((line = br.readLine()) != null) {


                String[] values = line.split(separator);
                String[] trimmedArray = new String[values.length];
                for (int i = 0; i <  values.length; i++) {
                    trimmedArray[i] = values[i].trim(); // removing whitespace from string
                }
                int v1 = Integer.parseInt(trimmedArray[0]); // array index starts with 0, graph index starts with 1!
                int v2 = Integer.parseInt(trimmedArray[1]) ; // TODO: check out if works ok
                int weight = Integer.parseInt(trimmedArray[2]);
                //printArray(graph[v1]);
                // expand array
                {
                    graph[v1] = expandArray(graph[v1], graph[v1].length + 1, graph[v1].length);
                   //    printArray(graph[v1]);
                    graph[v1][graph[v1].length-1] = new Vertex (v2, weight);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Loading complete!");
    }

    // TODO:  vertex id is not the same as it's place in array - how to deal with this?
    // Treat Matrix as a list?
    public void addVertex(int vertexID) // TODO : parametrize val?
    {
        for(int i = 0; i < graph.length; i++)
        {
            if(i == (vertexID-1)) {
                System.out.println("Vertex with given id exists!");
                return;
            }

        }
        graph = expandMatrix(graph, vertexID);

    }

    private Vertex[][] expandMatrix(Vertex[][] previous, int newSize) {

        int prevRowCount = previous.length;

        Vertex[][] withExtraRow = new Vertex[newSize][];
        System.arraycopy(previous, 0, withExtraRow, 0, previous.length);
        withExtraRow[prevRowCount] = new Vertex[1];

        return withExtraRow;
    }

    public void removeVertex(int vertexID) // todo: will this destroy the order in graph -> indices will not corespond with nodes?
    {
        // check if exists
        if(vertexID >= 0 && vertexID < graph.length)
        {
            Vertex[][] contractedArr = new Vertex[graph.length-1][];
            for(int i = 0, j = 0; (i < graph.length) && (j < contractedArr.length); i++, j++)
            {
                if(i == vertexID)
                {
                    j--; // skip removed vertex
                    continue;
                }
                contractedArr[j] = graph[i];
            }
            graph = contractedArr;
        }
        else{
            System.out.println("Given vertex does not exist");
        }
    }

    public void clearVertex(int vertexID){
        if(vertexID >= 0 && vertexID <= graph.length)
        {
            graph[vertexID] = new Vertex[0];
        }
        else{
            System.out.println("Given vertex does not exist");
        }
    }

    public void addEdge(int v1ID, int v2ID, int weight)
    {
        System.out.print("!!!" + graph[v1ID].length);

        for(int i = 1; i < graph[v1ID].length; i++) // TODO : deal with empty [0] elements
        {
            Vertex tmp = graph[v1ID][i];
            if(tmp.getVid() == v2ID) //  check out if given edge already exists
            {
                return;
            }
        }
        graph[v1ID] = expandArray(graph[v1ID], graph[v1ID].length + 1, graph[v1ID].length);
        graph[v1ID][graph[v1ID].length-1] = new Vertex(v2ID, weight);
    }

    public void removeEdge(int v1ID, int v2ID)
    {
        // check out if given edge exists
        for(int i = 0; i < graph[v1ID].length; i++)
        {
            if(graph[v1ID][i].getVid() == v2ID)
            {
                // delete vertex with v2ID
                graph[v1ID] = removeElementFromArray(graph[v1ID], v2ID);
                return;
            }

        }
        System.out.println("Given edge does not exist!"); // TODO: throw exception?
    }

    public Vertex[] removeElementFromArray(Vertex[] arr, int id)
    {
        Vertex [] newArr = new Vertex[arr.length-1];
        //printArray(arr);

        for(int i=0, j =0; (i < arr.length) && (j < newArr.length); i++, j++)
        {
            if(arr[i].getVid() == id)
            {
                j--;
                continue; // skip removed element
            }
            newArr[j] = arr[i];
        }
        return newArr;
    }

    public Vertex[] returnNeighbourVertices(int vertexID)
    {
        Vertex [] Neighbours = graph[vertexID];
        for(int i = 0; i < graph.length; i++)
        {
            // find neighbours whose edges are coming to vertex
            if(i == vertexID) continue;
            for(int j = 0; j < graph[i].length; j++)
            {
                if(graph[i][j].getVid() == vertexID)
                {
                    // expand and add new neighbour
                    // TODO: deal with duplicates
                    Neighbours = expandArray(Neighbours, Neighbours.length+1,Neighbours.length);
                    Neighbours[Neighbours.length-1] = new Vertex(i,-graph[i][j].getValue());
                }
            }
        }
        Neighbours = removeDuplicates(Neighbours);
        return Neighbours; //TODO:
    }

    public Vertex[] removeDuplicates(Vertex[] arr) {

        int end = arr.length;

        for (int i = 0; i < end; i++) {
            for (int j = i + 1; j < end; j++) {
                if (arr[i].getVid() == arr[j].getVid()) {
                    int shiftLeft = j;
                    for (int k = j+1; k < end; k++, shiftLeft++) {
                        arr[shiftLeft] = arr[k];
                    }
                    end--;
                    j--;
                }
            }
        }

        Vertex[] whitelist = new Vertex[end];
        for(int i = 0; i < end; i++){
            whitelist[i] = arr[i];
        }
        return whitelist;
    }


    public Edge[] returnIncidentEdges(int vertexID)
    {
        // outcoming edges
        Edge [] incident = new Edge[0];
        for(int i = 0; i < graph[vertexID].length; i++)
        {
            incident =  GraphUtils.expandArray(incident, incident.length+1, incident.length);
            Edge tmp = new Edge(vertexID, graph[vertexID][i], graph[vertexID][i].getValue());
            incident[incident.length-1] = tmp;
        }
        //incoming edges
        for(int i = 0; i < graph.length; i++)
        {
            for(int j = 0; j < graph[i].length; j++)
            {
                if(i == vertexID) continue;
                if(graph[i][j].getVid() == vertexID)
                {
                    incident =  GraphUtils.expandArray(incident, incident.length+1, incident.length);
                    Edge tmp = new Edge(j+1, graph[i][j], graph[i][j].getValue()); // todo: error?
                    incident[incident.length-1] = tmp;
                }
            }
        }
        return incident;

        //todo: merge with subfunctions

    }

    public Edge[] returnInComingEdges(int vertexID) {
        // Incoming edges
        Edge[] incident = new Edge[0];
        for(int i = 0; i < graph.length; i++)
        {
            for(int j = 0; j < graph[i].length; j++)
            {
                if(i == vertexID) continue;
                if(graph[i][j].getVid() == vertexID)
                {
                   // System.out.print("i = " + i);
                    printArray(graph[i]);
                    incident =  GraphUtils.expandArray(incident, incident.length+1, incident.length);
                    Edge tmp = new Edge(i, graph[i][j], graph[i][j].getValue());
                    incident[incident.length-1] = tmp;
                }
            }
        }
        return incident;

    }

    public Edge[] returnOutComingEdges(int vertexID) {
        // outcoming edges
        Edge[] incident = new Edge[0];
        for (int i = 0; i < graph[vertexID].length; i++) {
            incident = GraphUtils.expandArray(incident, incident.length + 1, incident.length);
            Edge tmp = new Edge(vertexID, graph[vertexID][i], graph[vertexID][i].getValue());
            incident[incident.length - 1] = tmp;
        }
        return incident;
    }

    public int getNumberOfVertices(){
        return graph.length - 1; // element with 0 index is empty!
    }
    public int getNumberOfEdges()
    {
        int edges = 0;
        for(int i = 0; i < graph.length; i++)
        {
                edges += graph[i].length;
        }
        return edges;
    }
    public boolean areNeighbours(int v1, int v2)
    {
        for(int i = 0; i < graph[v1].length; i++)
        {
            if(graph[v1][i].getVid() == v2) return true;
        }
        for(int i = 0; i < graph[v2].length; i++)
        {
            if(graph[v2][i].getVid() == v2) return true;
        }
        return false;
    }
    public void printArray (Vertex[] arr)
    {
        System.out.println("Array length: " + arr.length);
        for(int i = 0; i < arr.length; i++)
        {
            System.out.println("Array[" + i + "]: " + arr[i]);
        }
    }
    public void printArray (Edge[] arr)
    {
        System.out.println("Array length: " + arr.length);
        for(int i = 0; i < arr.length; i++)
        {
            System.out.println("Array[" + i + "]: " + arr[i]);
        }
    }
    public void printGraph() // TODO: what to do with 0 element?
    {
        for (int i = 0; i < graph.length; i++)
        {
            System.out.println("Vertex " + (i) + " neighbours:");
            for(int j = 0; j < graph[i].length; j++)
            {
                System.out.println(graph[i][j]);
            }
        }
    }
}
