

package pl.edu.agh.mtraton;

/*
Stworzy� interfejs Graf o nast�puj�cych metodach (poni�sza lista jest propozycj� przydatnych funkcji)
Dodaj w�ze�
Usu� w�ze�
Dodaj kraw�d�
Usu� kraw�d�
Podaj w�z�y s�siednie do w�z�a
Podaj kraw�dzie incydentne do w�z�a
Podaj ilo�� w�z��w
Podaj ilo�� kraw�dzi
Czy w�z�y s� s�siednie
Zaimplementowa� powy�szy interfejs za pomoc� reprezentacji macierzowej i listy s�siedztw
[Uwaga!!] Prosz� nie u�ywa� gotowych struktur danych typu ArrayList itp., a jedynie  Typ [] tablica

 */


public class Main {

    public static void main(String[] args) {
	// write your code here
       // MatrixGraph SampleGraph = new MatrixGraph("C:\\graf.txt");
       // SampleGraph.printWholeGraph();

        AdjencyListGraph AdjGraph = new AdjencyListGraph("C:\\graf.txt");
        /*
        int [] arr = {1,2,3};
        int [] arr2 = AdjGraph.expandArray(arr,10,3);
        for(int i = 0; i < arr2.length; i++)
        {
            System.out.println("x = " + arr2[i]);
        }
        */
        /*
        int [][] matrix = {
                {1, 2, 3},
                {3, 4, 5},
                {6, 7, 9}
        };
        int [][] expandedMatrix = AdjGraph.expandMatrix(matrix, 4, 3);
        for( int[] line : expandedMatrix)
        {
            System.out.println("");
            for (int i : line)
            {
                System.out.print(i + ", ");
            }
        }
        */

    }




}
