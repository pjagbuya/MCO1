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
    /**
     * (6n^2 + 7n - 5)/2
     */

    public void insertionSort(Record[] arr, int n) {
        // TODO: Implement this sorting algorithm here.


        Record rSelected;                       
        int i;                                  
        int j;                                  

 


        for(i=1; i<n;i++){                      //2n + 2

            rSelected = arr[i];                 //n-1

            for(j=i-1; j>=0; j--){              // (n^2 +n -2)/2 + (n^2 -n)/2 + 1


                // Checking if the selected number is greate than the one beside it

                // If else if block: 4(n(n-1)/2)
                if(rSelected.getIdNumber() < arr[j].getIdNumber()){             //(n^2 -n)/2
                    swapRec(arr, j, j+1);                                       //3(n^2 -n)/2
                }
                else if(rSelected.getIdNumber() > arr[j].getIdNumber()){        //1(n(n-1)/2)
                    arr[j+1] = rSelected;                                       //1(n(n-1)/2)
                    break;                                                      //1(n(n-1)/2)
                }

            }

        }


        




    }


    /**
     * 
     * T(n) = 2n^2 + 10n - 3
     */
    public void selectionSort(Record[] arr, int n) {
        // TODO: Implement this sorting algorithm here.
        int nMin;                                                           
        int i;                                                              
        int j;                                                              



        for(i = 0; i < n; i++)     // 2n + 2              
        {
            nMin = i;               // n

            for (j = i + 1; j < n; j++){   // (n^2 + 3n -4)/2 + (n^2 + n-2)/2  +  1

                if(arr[nMin].getIdNumber() > arr[j].getIdNumber()){  //(n^2 + n-2)/2
                    nMin = j;                                        //(n^2 + n-2)/2
                }

            }

            if (i != nMin)          //n
            {
                swapRec(arr, i, nMin);      //3n
            }
        }


    }

    private void merge(Record arr[], int p, int r, int mid) {
        int size1 = mid - p + 1;                //1
        int size2 = r - mid;                    //1
        Record temp1[] = new Record[size1];     //1
        Record temp2[] = new Record[size2];     //1
        int i;                                  
        


        for (i = 0; i < size1; i++) {           //(n+6)/2 + (n+4)/2 + 1
            temp1[i] = arr[p + i];              //(n+4)/2
        } 

        for (i = 0; i < size2; i++) {           //(n+4)/2 + (n+2)/2 + 1

            temp2[i] = arr[mid + 1 + i];        //(n+2)/2
        }
        
  
 
        i = 0;                                          //1
        int j = 0;                                      //1
        int k = p;                                      //1


        while (i < size1 && j < size2) {                //(n+2)/2

            if (temp1[i].getIdNumber() < temp2[j].getIdNumber()) {  //n/2
                arr[k] = temp1[i];                                  //n/2
                i++;                                                //n/2
            }
            else {
                arr[k] = temp2[j];
                j++;
            }

            k++;                                                    //n/2
        }
 
        while (i < size1) {                                 //1
           
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

    /**
     * 
     * T(n) = (11/2)log_2(n) + 30n -15
     */

    public void mergeSort(Record[] arr, int p, int r) {
        

        if (p < r) {                           // 1
            int mid = p + (r - p) / 2;              // 1
            mergeSort(arr, p, mid);            // T(n/2) )
            mergeSort(arr, mid + 1, r);        // T(n/2)
                                                // T
            merge(arr, p, r, mid);             
                                                //9n + 15
                                                
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