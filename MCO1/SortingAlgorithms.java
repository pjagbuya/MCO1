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

    /**
     * T(n) = k(11n + 35) + 4n + 4, where k = max no. of digits in 
     * <code>arr</code>.
     */
    public void radixSort(Record[] arr, int n) {
        int arrMax = max(arr, n);                                                   // 4n + 4

        for (int k = 1; arrMax / k > 0; k *= 10) {                                  // k + 1, where k = max no. of digits in arr
            countingSort(arr, n, k);                                                // (k)(11n + 34)
        } 
    }

    /**
     * Implemented for <code>radixSort()</code>.
     * T(n) = 11n + 34
     */
    private void countingSort(Record[] arr, int n, int k) {       
        int i;                                                                      // 1
        int[] counts = new int[10];                                                 // 1
        Record[] output = new Record[n];                                            // 1

        // Count the no. of occurrences of the kth digit in each element of arr.
        for (i = 0; i < n; i++) {                                                   // 1 + (n - 0 + 1) + n = 2n + 2
            counts[kthDigit(arr[i].getIdNumber(), k)]++;                            // n
        }

        // Get the position of the last occurrence of a digit in the output 
        // array by computing the cummulative sum of the values in the count 
        // array.
        for (i = 1; i < counts.length; i++) {                                       // 1 + (10 - 1 + 1) + 9 = 20
            counts[i] += counts[i - 1];                                             // 9
        }

        // Build the output array, now knowing the where the last occurrence of
        // each digit should be in the output array. 
        // 
        // As only the ending position of each digit in the output array is 
        // known, each digit in the output array will be written backwards, 
        // starting from the ending position until no more occurrences of a 
        // digit are left.
        //
        // Hence, to keep this algorithm stable, the input array should also be
        // traversed backwards so that the last occurrence in the input will end
        // up at its last position in the output, and so on.
        for (i = n - 1; i >= 0; i--) {                                              // 1 + (n - 1 - 0 + 2) + (n - 1) = 2n + 1
            int d = kthDigit(arr[i].getIdNumber(), k);                              // n - 1

            // Subtract 1 to get the index since each count starts from 1.
            output[counts[d] - 1] = arr[i];                                         // n - 1
            counts[d]--;                                                            // n - 1
        }

        // Copy the sorted output into the given array. The given array cannot
        // be directly worked on as the necessary values to work on will be 
        // overwritten in the process.
        for (i = 0; i < n; i++) {                                                   // 1 + (n - 0 + 1) + n = 2n + 2
            arr[i] = output[i];                                                     // n
        }
    }

    /**
     * Implemented for <code>radixSort()</code>.
     * T(n) = 1
     */
    private int kthDigit(int num, int k) {
        return (num / k) % 10;                                                      // 1
    }

    /**
     * Implemented for <code>radixSort()</code>.
     * T(n) = 4n + 4
     */
    private int max(Record[] arr, int n) {
        int maxVal = arr[0].getIdNumber();                                          // 1

        for (int i = 0; i < n; i++) {                                               // 1 + (n - 0 + 1) + n = 2n + 2
            if (arr[i].getIdNumber() > maxVal) {                                    // n
                maxVal = arr[i].getIdNumber();                                      // n
            }
        }

        return maxVal;                                                              // 1
    }
}