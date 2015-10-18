package pl.edu.agh.mtraton.Utils;

/**
 * Created by Rael on 18.10.2015.
 */
public class Stack {
    int [] stack;
    int counter;


    // todo: expanding stack
    public Stack(int max)
    {
        stack = new int[max];
        counter = 0;
    }

    public void push(int val)
    {
        stack[counter] = val;
        counter++;
    }


    public int pop() {
        if(!isEmpty())
        {
            counter--;
            return stack[counter];
        }
        else return 0;

    }
    public boolean isEmpty()
    {
       return(counter== 0);
    }
    public void printStack()
    {

    }
}
