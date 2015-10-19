package pl.edu.agh.mtraton;

import pl.edu.agh.mtraton.Utils.*;
import pl.edu.agh.mtraton.Utils.Queue;
import pl.edu.agh.mtraton.Utils.Stack;

import java.util.*;
//import java.util.Queue;


/**
 * Created by Rael on 18.10.2015.
 */
public class FordFulkerson {
    AdjencyListGraph lgraph = new AdjencyListGraph("D:\\d.txt");
    MatrixGraph graph = new MatrixGraph("D:\\d.txt");
    int fordFulkerson(int startVertex, int EndVertex)
    {

        // create residual network
        AdjencyListGraph residual = new AdjencyListGraph("D:\\duzy_graf.txt");
        // f(u,v) \leftarrow 0 for all edges (u,v) (przep�yw)
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


    public void ffMatrix(int sVertex, int tVertex )
    {

        // variables
        Queue  q = new Queue(1000); // todo: dynamic queue
        int [][] capacities;
        int [][] flows;
        int [] previous; // previous nodes for BFS
        int [] cfp; // capacity of path
        int nVertices;
        int fmax, cp, x,i;
        boolean escape; // to break nested loops

        nVertices = graph.getNumberOfVertices();
        //System.out.println("Vertices: " + nVertices);

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
        System.out.println("Matrix fmax = " + fmax);
        /*

        for( x = 0; x < nVertices; x++)
        {
            for(int y =0; y < nVertices; y++)
            {
                if(capacities[x][y] != 0) System.out.print(x + " -> " + y + " " + flows[x][y]  + " : " + capacities[x][y]);
            }
        }
        */

    }
    public void ffList(int sVertex, int tVertex)
    {
        int nVertices = lgraph.getNumberOfVertices();
        int  fmax, i, cp, u, v;

        Queue q = new Queue(1000);
        int[] previous = new int[nVertices];
        int[] cfp = new int[nVertices];
        boolean test = false;
        //Vertex x,z;
        //todo : is graph structure valid -> flows?
        fmax = 0;

        for(u = 0; u < nVertices; u++)
        {
            // iterate through neighbours of u'th node
            Vertex[] neighbors = lgraph.getOutcomingVertices(u);
            for(int counter = 0; counter < neighbors.length; counter++)
            {
                Vertex x = neighbors[counter];
                test = false;
                Vertex [] zlist = lgraph.getOutcomingVertices(x.getVid());
                for(int z  = 0; z < zlist.length; z++)
                {
                    if(zlist[z].getVid() == u)
                    {
                        test = true;
                        break;
                    }
                }
                if(test) continue;

                // add new edge
                /*
                Vertex z = new Vertex();
                zlist[0].setVid(u);
                zlist[0].setValue(0);
                zlist[0].setF(0);
                lgraph.addEdge(u,neighbors[x.getVid()].getVid(), 0, 0);// todo
                neighbors[x.getVid()] = z;
                */



            }
        }
        while(true)
        {
            for(i = 0; i < nVertices; i++)
            {
                previous[i] = -1;
            }
            cfp[sVertex] = Integer.MAX_VALUE;

            while(!q.isEmpty()) q.remove();

            q.add(sVertex);

            while(!q.isEmpty())
            {
                test = false;
                u = q.remove();

                Vertex [] neighbours = lgraph.getOutcomingVertices(u);
                for(i = 0; i < neighbours.length; i++)
                {
                    Vertex x = neighbours[i];
                    cp = ((int)x.getValue()) - x.getF();

                    if(cp != 0 && (previous[x.getVid()] == -1) )
                    {
                        previous[x.getVid()] = u;
                        if(cp < cfp[u])
                        {
                            cfp[x.getVid()] = cp;
                        }
                        else
                        {
                            cfp[x.getVid()] = cfp[u];
                        }

                        if(x.getVid() == tVertex)
                        {
                            test = true;
                            break;
                        }
                        else
                        {
                            q.add(x.getVid());
                        }

                    }
                }
                if(test) break;

            }
            if(!test) break;

            fmax += cfp[tVertex];
            for( v = tVertex; v != sVertex; v = u)
            {
                u = previous[v];
                Vertex[] neighbors = lgraph.getOutcomingVertices(u);
                for(int z = 0; z < neighbors.length; z++)
                {

                    if(neighbors[z].getVid() == v)//todo
                    {
                        neighbors[z].setF(neighbors[z].getF() + cfp[tVertex]);
                        break;
                    }
                }

                neighbors = lgraph.getOutcomingVertices(v);
                for(int z = 0; z < neighbors.length; z++)
                {

                    if(neighbors[z].getVid() == u)//todo
                    {
                        neighbors[z].setF(neighbors[z].getF() - cfp[tVertex]);
                        break;
                    }
                }

            }
        }
        System.out.println("List fmax : " + fmax);

    }
}
