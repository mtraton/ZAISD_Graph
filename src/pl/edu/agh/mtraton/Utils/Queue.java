package pl.edu.agh.mtraton.Utils;

/**
 * Created by Rael on 18.10.2015.
 */
public class Queue {
    //todo: repair!!!
    int [] queue;
    int counter;

    public  Queue(int size)
    {
        queue = new int[size];
        counter = 0;
    }
    public void add(int val)
    {
        queue[counter] = val;
        counter++;
    }

    public int remove()
    {
        if(isEmpty())
        {
            System.out.println("Queue empty");
            return 0;
        }
        int res = queue[0];
        for (int i=0; i < counter; i++)
            queue[i] = queue[i+1];

        queue[counter] = 0;

        return res;
    }
    public boolean isEmpty()
    {
        return (counter == 0);
    }

}
