package pl.edu.agh.mtraton.Utils;

import pl.edu.agh.mtraton.Edge;
import pl.edu.agh.mtraton.Vertex;

/**
 * Created by Rael on 17.10.2015.
 */
public class GraphUtils{

        public static Edge[] expandArray (Edge [] oldArray, int newSize, int oldSize)
        {
            Edge [] expandedArray = new Edge [newSize];
            System.arraycopy(oldArray, 0, expandedArray, 0, oldSize);
            return expandedArray;
        }
};
