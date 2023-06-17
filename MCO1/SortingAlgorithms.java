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


        Record rSelected;                       //1
        int i;                                  //1
        int j;                                  //1

 


        for(i=1; i<n;i++){                      //n

            rSelected = arr[i];                 //1

            for(j=i-1; j>=0; j--){              // i-1 to 0


                // Checking if the selected number is greate than the one beside it

                // If else if block: 3
                if(rSelected.getIdNumber() < arr[j].getIdNumber()){             //1
                    swapRec(arr, j, j+1);                                       //1
                }
                else if(rSelected.getIdNumber() > arr[j].getIdNumber()){        //1
                    arr[j+1] = rSelected;                                       //1
                    break;                                                      //1
                }

            }

        }


        




    }

    public void selectionSort(Record[] arr, int n) {
        // TODO: Implement this sorting algorithm here.
        int nMin;                                                           //1
        int i;                                                              //1
        int j;                                                              //1



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

    public void radixSort(Record[] arr, int n) {

        // TODO: Implement this sorting algorithm here.

    }

}