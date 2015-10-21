package pl.edu.agh.mtraton;

import pl.edu.agh.mtraton.Utils.GraphUtils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Rael on 2015-10-08.
 */
public class MatrixGraph implements Graph {
    // POLA
    private int [][] graph;
    int graphSize;
    public MatrixGraph(String Path)
    {
        graphSize = loadNumberOfIndices(Path) + 1;// todo: achieve array index = graph index
        setGraph(new int[graphSize][graphSize]);
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
                int v1 = Integer.parseInt(trimmedArray[0]) ; // array index starts with 0, graph index starts with 1!
                int v2 = Integer.parseInt(trimmedArray[1]) ;
                int weight = Integer.parseInt(trimmedArray[2]);
                getGraph()[v1][v2] = weight;
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
        // check if vertex already exists
        if(vertexID >= getGraph().length)
        {
            // expand matrix
            setGraph(expandMatrix(getGraph(), vertexID, getGraph().length));
        }
        else{
            System.out.println("Either incorrect or already existing vertex index given!");
        }
    }
    public void removeVertex(int vertexID)
    {
        // todo: change implementation so index would be independent of vertex id
    }

    public void clearVertex(int vertexID){
        for(int i = 0; i < getGraph().length; i++)
        {
            getGraph()[i][vertexID] = 0;
            getGraph()[vertexID][i] = 0;
        }
        // todo : change indexing to 1?
    }

    public void addEdge(int v1, int v2, int weight)
    {
        // check if out of bonds?
        getGraph()[v1][v2] = weight;
    }

    public void removeEdge(int v1, int v2)
    {
        getGraph()[v1][v2] = 0;
    }

    public Vertex [] expandArray (Vertex [] oldArray, int newSize, int oldSize)
    {
        Vertex [] expandedArray = new Vertex [newSize];
        System.arraycopy(oldArray, 0, expandedArray, 0, oldSize);
        return expandedArray;
    }
    public Vertex[] returnNeighbourVertices(int vertexID)
    {
        Vertex[] result = new Vertex[0];
        for (int i = 0; i < graphSize; i++)
        {
            if(getGraph()[vertexID][i] != 0)
            {
                result = expandArray(result, result.length+1, result.length);
                result[result.length-1] = new Vertex(i, -1);
            }
            if(getGraph()[i][vertexID] != 0)
            {
                result = expandArray(result, result.length+1, result.length);
                result[result.length-1] = new Vertex(i, -1);
            }
            //TODO remove duplicates
            result = removeDuplicates(result);
        }
        return result;
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



    public Edge[] getEdges()
    {
        Edge[] edges = new Edge[getNumberOfEdges()];

        return edges;
    }


    public Edge[] returnIncidentEdges(int vertexID)
    {
        // Outcoming edges
        Edge[] incident = new Edge[0];
        for(int i = 0; i < getGraph().length; i++)
        {
                if(getGraph()[vertexID][i] !=0)
                {
                    // System.out.print("i = " + i);

                    incident =  GraphUtils.expandArray(incident, incident.length + 1, incident.length);
                    Edge tmp = new Edge(vertexID, new Vertex(i, getGraph()[vertexID][i]), getGraph()[vertexID][i]);
                    incident[incident.length-1] = tmp;
                }
                if(getGraph()[i][vertexID] != 0)
                {
                    incident =  GraphUtils.expandArray(incident, incident.length + 1, incident.length);
                    Edge tmp = new Edge(i, new Vertex(vertexID, getGraph()[i][vertexID]), getGraph()[i][vertexID]);
                    incident[incident.length-1] = tmp;
                 }
        }
        return incident;
    }
    public int getNumberOfVertices(){
        return getGraph().length;
    }
    public int getNumberOfEdges()
    {
        int sum = 0;
        for(int[] i : getGraph())
        {
            for(int j: i)
            {
                if(j != 0) sum++;
            }
        }
        return sum;
    }
    public boolean areNeighbours(int v1, int v2)
    {
        if(getGraph()[v1][v2] != 0) return true;
        else return false;
    }

    public void printGraph()
    {
       for (int i = 0; i < getGraph().length; i++)
       {
           for(int j = 0; j < getGraph().length; j++)
           {
               System.out.println("[" + i + "]" + "[" + j + "] = " + getGraph()[i][j]);
           }
       }
    }
    // TODO: pozamieniaæ nazwy, sprawdziæ!
    public int[][] expandMatrix(int [][] oldArray, int newSize, int oldSize)
    {
        int indicesCount = getGraph().length;
        int [][] expandedMatrix = new int [newSize][newSize];
        for(int i = 0; i < indicesCount; i++)
        {
            System.arraycopy(oldArray[i], 0, expandedMatrix[i], 0, oldSize);
        }
        for(int i = 0; i < newSize; i++)
        {
            if(i<oldSize)
            {
                for(int j = oldSize; j < newSize; j++)
                {
                    expandedMatrix[i][j] = 0;
                }
            }
            else
            {
                for(int j = 0; j < newSize; j++)
                {
                    expandedMatrix[i][j] = 0;
                }
            }


        }
        return expandedMatrix;
    }



    public void printWholeGraph()
    {
        for (int i = 0; i < getGraph().length; i++)
        {
            System.out.println();
            for(int j = 0; j < getGraph().length; j++)
            {
                String str = Integer.toString(getGraph()[i][j]);
                //String result = String.format("%03d", str);


               // result = result + ",";
                System.out.printf("%-3d", getGraph()[i][j]);
                System.out.print(",");
            }
        }
    }

    public int[][] getGraph() {
        return graph;
    }

    public void setGraph(int[][] graph) {
        this.graph = graph;
    }
};
