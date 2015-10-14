

package pl.edu.agh.mtraton;

/*
Stworzyæ interfejs Graf o nastêpuj¹cych metodach (poni¿sza lista jest propozycj¹ przydatnych funkcji)
Dodaj wêze³
Usuñ wêze³
Dodaj krawêdŸ
Usuñ krawêdŸ
Podaj wêz³y s¹siednie do wêz³a
Podaj krawêdzie incydentne do wêz³a
Podaj iloœæ wêz³ów
Podaj iloœæ krawêdzi
Czy wêz³y s¹ s¹siednie
Zaimplementowaæ powy¿szy interfejs za pomoc¹ reprezentacji macierzowej i listy s¹siedztw
[Uwaga!!] Proszê nie u¿ywaæ gotowych struktur danych typu ArrayList itp., a jedynie  Typ [] tablica

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
