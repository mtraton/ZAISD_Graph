package pl.edu.agh.mtraton;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;
/**
 * Created by Rael on 2015-10-08.
 */
public class MatrixGraph implements Graph {
    // POLA
    int [][] graph;
    int graphSize;
    public MatrixGraph(String Path)
    {
        graphSize = loadNumberOfIndices(Path);
        graph = new int[graphSize][graphSize];
        loadGraphFromFile(Path);
    }

    // METODY
    public int loadNumberOfIndices(String Path)
    {
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

    public void loadGraphFromFile(String Path) // TODO: przerobiæ to wczytywananie by korzysta³o z hashmapy
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
                graph[v1][v2] = weight;
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
        // expand matrix?

    }
    public void removeVertex(int vertexID)
    {
        //
    }
    public void addEdge(int v1, int v2, int weight)
    {
        // check if out of bonds?
        graph[v1][v2] = weight;
    }

    public void removeEdge(int v1, int v2, int weight)
    {
        graph[v1][v2] = 0;
    }
    public int[] returnNeighbourVertices(int vertexID)
    {
        int[] result;
        for (int i = 0; i < graphSize; i++)
        {
            if(graph[vertexID][i] != 0);
        }
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

    public void printGraph()
    {
       for (int i = 0; i < graphSize; i++)
       {
           for(int j = 0; j < graphSize; j++)
           {
               System.out.println("[" + i + "]" + "[" + j + "] = " + graph[i][j]);
           }
       }
    }
    /* TODO: pozamieniaæ nazwy, sprawdziæ!
    public int[][] expandMatrix(int [][] oldArray, int newSize, int oldSize)
    {
        int [][] expandedMatrix = new int [indicesCount][newSize];
        for(int i = 0; i < indicesCount; i++)
        {
            System.arraycopy(oldArray[i], 0, expandedMatrix[i], 0, oldSize);
        }
        return expandedMatrix;
    }
    */


    public void printWholeGraph()
    {
        for (int i = 0; i < graphSize; i++)
        {
            System.out.println();
            for(int j = 0; j < graphSize; j++)
            {
                String str = Integer.toString(graph[i][j]);
                //String result = String.format("%03d", str);


               // result = result + ",";
                System.out.printf("%-3d", graph[i][j]);
                System.out.print(",");
            }
        }
    }

};
