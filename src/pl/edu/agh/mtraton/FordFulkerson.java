package pl.edu.agh.mtraton;

import pl.edu.agh.mtraton.Utils.*;
//import pl.edu.agh.mtraton.Utils.Queue;
import pl.edu.agh.mtraton.Utils.Stack;

import java.util.*;
import java.util.Queue;


/**
 * Created by Rael on 18.10.2015.
 */
public class FordFulkerson {
   // AdjencyListGraph graph = new AdjencyListGraph("C:\\graf.txt");
    MatrixGraph graph = new MatrixGraph("C:\\graf.txt");
    int fordFulkerson(int startVertex, int EndVertex)
    {

        // create residual network
        AdjencyListGraph residual = new AdjencyListGraph("D:\\duzy_graf.txt");
        // f(u,v) \leftarrow 0 for all edges (u,v) (przep³yw)
        for(int i = 0; i < residual.graph.length; i++)
        {
            for(int j = 0; j < residual.graph[i].length; j++)
            {
                residual.graph[i][j].setValue(0);
            }
        }

        return 0;
    }

    public int[] findDFSPath(int startVertex, int endVertex)
    {
        boolean [] visited = new boolean[graph.getNumberOfVertices()+1];
        boolean found = false;
        for(int i = 0; i < visited.length; i++)
        {
            visited[i] = false;
        }
        int [] path = new int[graph.getNumberOfVertices()+1];

        java.util.Stack<Integer>  s= new java.util.Stack<>();
        //Stack s = new Stack(10000);
        int v, u;
        path[startVertex] = -1;
        s.push(startVertex);
        visited[startVertex] = true;
        v = -1;
        while(!s.isEmpty())
        {

            v = s.pop();
            System.out.println("v = " + v);
            //System.out.print("V = " + v);
            if(v == endVertex)
            {
                found = true;
                break;

            }

            Vertex [] neigh = graph.returnNeighbourVertices(v);
            for(int i = 0; i < neigh.length; i++ )
            {
                u = neigh[i].getVid();
                if(!visited[u])
                {
                    path[u] = v;
                    s.push(u);
                    visited[u] = true;
                }

            }
        }
        if(!found) System.out.println("Path not found!");
        else
        {
            while (v > -1)
            {
                System.out.print(v + " -> ");
                v = path[v];
            }
        }

        return null;

    }


    public void foobar(int sVertex, int tVertex )
    {

        // variables
        java.util.Queue <Integer> q = new LinkedList<Integer>(); // todo: dynamic queue
        int [][] capacities;
        int [][] flows;
        int [] previous; // previous nodes for BFS
        int [] cfp; // capacity of path
        int nVertices;
        int fmax, cp, x,i;
        boolean escape; // to break nested loops

        nVertices = graph.getNumberOfVertices();


        //capacities = new int[nVertices][nVertices];
        flows = new int[nVertices][nVertices];

        previous = new int [nVertices];
        cfp = new int [nVertices];

        capacities = graph.getGraph();

        fmax = 0;

        while(true)
        {
            for(i = 0; i < nVertices; i++) previous[i] = -1; // initialize previouses :D
            previous[sVertex] = -2; // starting vertex does not have previous vertex
            cfp[sVertex] = Integer.MAX_VALUE;  // todo: is this assignment valid?


            while(!q.isEmpty())
            {
                q.remove(); // todo: is it valid ?
            }

           // q = new Queue(1000);


            q.add(sVertex);
            escape = false;

            while (!q.isEmpty()) //BFS path search
            {
                x = q.remove(); // todo
                for (int y = 0; y <nVertices; y++)
                {
                    cp = capacities[x][y] - flows[x][y]; // calculate residual capacity for edge x->y

                    if( (cp != 0) && (previous[y] == -1)) // edge does not exist and vertex y was already visited
                    {
                        previous[y] = x;

                        // calculate residual path capacity for y - smaller between cfp to previous or current cfp from x -> y

                        if(cfp[x] > cp)
                        {
                            cfp[y] = cp;
                        }
                        else
                        {
                            cfp[y] = cfp[x];
                        }

                        if(y == tVertex) // reached t
                        {
                            fmax += cfp[tVertex];

                            // backtracking and changing values of flows
                            i = y;
                            while(i != sVertex) //
                            {
                                x = previous[i];
                                flows[x][i] += cfp[tVertex];
                                flows[i][x] -= cfp[tVertex];
                                i = x;
                            }

                            // jump out of loops
                            escape = true;
                            break;
                        }

                        q.add(y); // q is not t vertex -> new loop iteration
                    }
                }

                // break from while if t was found
                if(escape) break;
            }
            if(!escape) break;
        }
        System.out.println("fmax = " + fmax);
        for( x = 0; x < nVertices; x++)
        {
            for(int y =0; y < nVertices; y++)
            {
                if(capacities[x][y] != 0) System.out.print(x + " -> " + y + " " + flows[x][y]  + " : " + capacities[x][y]);
            }
        }

    }
}
