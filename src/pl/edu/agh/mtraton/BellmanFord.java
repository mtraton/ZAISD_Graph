package pl.edu.agh.mtraton;

import pl.edu.agh.mtraton.Utils.Stack;

/**
 * Created by Rael on 21.10.2015.
 */
public class BellmanFord {

    Graph g ;
    int [] previous;

    int [] weights;


    public int [] bellmanFord(int sVertex, Graph g)
    {

        //1. Initialize data structures
        this.g = g;
        int vCounter = g.getNumberOfVertices();
        this.previous = new int [vCounter]; // todo : set on -1 or null

        this.weights =  new int [vCounter];
        for(int i = 0; i < vCounter; i++) // todo: which parameter should be loop exist
        {
            weights[i] = Integer.MAX_VALUE -1000;// TODO:!!! error with overflows!
            previous[i] = -1;
        }
        weights[sVertex]= 0;

        boolean test = true;
        for (int i = 0; i < vCounter - 1; i++)
        {
           // test = true;
            Edge[] edges = g.returnIncidentEdges(i); // todo: will it work out?
            for(int j = 0; j < edges.length; j++)
            {
                int u = i;
                int v = edges[j].getEnd().getVid(); // id of the
                int w = edges[j].getVal();
                int wu = weights[u];
                int wv = weights[v];
                if( (wu + w) < wv )
                {
                    //test  = false;
                    weights[v]= wu + w;
                    previous[v] = u;
                }

            }
            //if(test) return weights;
        }

        for(int i = 0; i < vCounter; i++)
        {
            Edge[] edges = g.returnIncidentEdges(i); // todo: will it work out?
            for(int j = 0; j < edges.length; j++)
            {
                int u = i;
                int v = edges[j].getEnd().getVid(); // id of the
                int w = edges[j].getVal();
                int wu = weights[u];
                int wv = weights[v];
                if( (wu + w) < wv ) {

                    System.out.print("Graph contains a negative-weight cycle");
                    test = true;
                    break;
                }

            }
            if(test) break; //null;

        }

        return weights;

        //return null;
    }
    public void printPath(int v)
    {
        System.out.println("Shortest path to " + v + ":") ;
        Stack s = new Stack(1000);
        for(int i = v; i != -1; i  = previous[i])
        {
            s.push(i);
        }
        while(!s.isEmpty())
        {
            System.out.print(s.pop() + " ");
        }
    }
}
