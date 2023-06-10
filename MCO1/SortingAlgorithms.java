/* This file contains implementations of sorting algorithms.
 * You are NOT allowed to modify any of the given method headers.
 */

public class SortingAlgorithms {

    /*
     * You may define additional helper functions here if you need to.
     * Make sure the helper functions have the private access modifier, as
     * they will only be used in this class.
     */


     // Added a swapRec
    private void swapRec(Record arr[], int i, int j)
    {

        Record temp;

        temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;


    }

    public void insertionSort(Record[] arr, int n) {
        // TODO: Implement this sorting algorithm here.


        Record rSelected;
        int i;
        int j;

 


        for(i=1; i<n;i++){

            rSelected = arr[i];

            for(j=i-1; j>=0; j--){


                // Checking if the selected number is greate than the one beside it
                if(rSelected.getIdNumber() < arr[j].getIdNumber()){
                    swapRec(arr, j, j+1);
                }
                else if(rSelected.getIdNumber() > arr[j].getIdNumber()){
                    arr[j+1] = rSelected;
                    j = -1;
                }

            }

        }


        




    }

    public void selectionSort(Record[] arr, int n) {
        // TODO: Implement this sorting algorithm here.
        int nMin;
        int i;
        int j;



        for(i = 0; i < n; i++)
        {
            nMin = i;

            for (j = i + 1; j < n; j++){

                if(arr[nMin].getIdNumber() > arr[j].getIdNumber()){
                    nMin = j;
                }

            }

            if (i != nMin)
            {
                swapRec(arr, i, nMin);
            }
        }


    }

    public void mergeSort(Record[] arr, int p, int r) {
        // TODO: Implement this sorting algorithm here.

    }

    /*
     * Define AT LEAST ONE more sorting algorithm here, apart from the
     * ones given above. Make sure that the method accepts an array of
     * records
     */

    public void radixSort(Record[] arr, int p, int r) {

        // TODO: Implement this sorting algorithm here.

    }

}