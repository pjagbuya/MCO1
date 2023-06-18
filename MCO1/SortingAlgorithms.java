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

    private void merge(Record arr[], int p, int r, int mid) {
        int size1 = mid - p + 1;                //1
        int size2 = r - mid;                    //1
        Record temp1[] = new Record[size1];     //1
        Record temp2[] = new Record[size2];     //1
        int i;                                  //1
        


        // for (i = 0; i < size1; i++) { 
        //     temp1[i] = arr[p + i]; 
        // } 

        // for (i = 0; i < size2; i++) { 

        //     temp2[i] = arr[mid + 1 + i]; 
        // }
        
        for (i = 0; i < size1 || i < size2; i++) {      //(n + 1)(3)

            if (i < size1)                              //2(n)
                temp1[i] = arr[p + i];                  //
            
            if (i < size2)                              //2n
                temp2[i] = arr[mid + i + 1];
        }
 
        i = 0;                                          //1
        int j = 0;                                      //1
        int k = p;                                      //1


        while (i < size1 && j < size2) {                //(n+3) -2 (minus 2 due to the second condition will not be checked when it is still less and will not check when it reaches cap)

            if (temp1[i].getIdNumber() < temp2[j].getIdNumber()) {  //3(n)
                arr[k] = temp1[i];
                i++;
            }
            else {
                arr[k] = temp2[j];
                j++;
            }

            k++;                                                    //1(n)
        }
 
        while (i < size1) {                                 //0
           
            arr[k] = temp1[i];                              //0
            i++;                                            //0
            k++;                                            //0
        }
 
        while (j < size2) {                                 //2
            
            arr[k] = temp2[j];                              //1
            j++;                                            //1
            k++;                                            //1
        }
    }

    public void mergeSort(Record[] arr, int p, int r) {
        

        if (p < r) {                           // 1
            int mid = p + (r - p) / 2;              // 1
            mergeSort(arr, p, mid);            // T(n/2) )
            mergeSort(arr, mid + 1, r);        // T(n/2)
                                                // T
            merge(arr, p, r, mid);             
                                                //34n + 48
                                                
        }
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