package pl.edu.agh.mtraton;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Rael on 2015-10-08.
 */
//=
// TODO type float  - zamieniæ int - wagi s¹ zmiennoprzecinkowe
// TODO prealokacja i potem póŸniejsze rozszerzanie

public class AdjencyListGraph implements  Graph {
    // POLA

    int [][] graph; //  list of outcoming edges from gives vertex
    int indicesCount = 0;
    public AdjencyListGraph (String path)
    {
        indicesCount = loadNumberOfIndices(path);
        graph = new int[indicesCount][0];
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
        // TODO :
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

    public int [] expandArray (int [] array, int newSize, int oldSize)
    {
        int [] expandedArray = new int [newSize];
        System.arraycopy(array, 0, expandedArray, 0, oldSize);
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
                int v1 = Integer.parseInt(trimmedArray[0]) -1; // array index starts with 0, graph index starts with 1!
                int v2 = Integer.parseInt(trimmedArray[1]) -1;
                int weight = Integer.parseInt(trimmedArray[2]);
                // ehxpand array
                {
                    graph[v1] = expandArray(graph[v1], graph[v1].length, graph[v1].length + 1);
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
    public void addVertex(int vertexID)
    {

    }
    public void removeVertex(int vertexID)
    {

    }
    public void addEdge(int v1ID, int v2ID, int weight)
    {

    }
    public void removeEdge(int v1ID, int v2ID, int weight)
    {

    }
    public int[] returnNeighbourVertices(int vertexID)
    {
        return null;
    }
    public int[] returnIncidentEdges(int vertexID)
    {
        return null;
    }
    public int getNumberOfVertices(){
        return 0;
    }
    public int getNumberOfEdges()
    {
        return 0;
    }
    public boolean areNeighbours(int v1, int v2)
    {
        return false;
    }
}
