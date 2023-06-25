/* This file contains implementations of sorting algorithms.
 * You are NOT allowed to modify any of the given method headers.
 */

public class SortingAlgorithms {
    /*
     * You may define additional helper functions here if you need to.
     * Make sure the helper functions have the private access modifier, as
     * they will only be used in this class.
     */

    /**
     * T(n) = (3/2)n^2 + (9/2)n - 4
     */
    public void insertionSort(Record[] arr, int n) {
        Record rSelected;

        int i;                                  
        int j;                                  

        for (i = 1; i < n; i++) {           // 1 + (n - 1 + 1) + (n - 1) = 2n
            rSelected = arr[i];             // n - 1
            j = i;                          // n - 1

            // In the worst case, the numbers in the array to sort are sorted
            // in the reversed order. This means arr[1] would be moved 1 time,
            // arr[2] moved 2 times, and so on until arr[n - 1] would be moved
            // n - 1 times. This is the sum of the integers from 1 to n - 1.
            //
            // Hence, the while loop and its associated statements must execute
            // at least ((n - 1)n) / 2 times. There are n - 1 main iterations,
            // meaning n - 1 final condition checks for the while loop as well.

            // (n(n - 1) / 2) + (n - 1)
            while (j > 0 && 
                   arr[j-1].getIdNumber() > rSelected.getIdNumber()
            ) {  
                arr[j] = arr[j - 1];        // ((n - 1)n) / 2
                j -= 1;                     // ((n - 1)n) / 2
            }

            arr[j] = rSelected;             // n - 1
        }
    }

    /**
     * T(n) = 2n^2 + 6n + 3
     */
    public void selectionSort(Record[] arr, int n) {
        int nMin;                                                           
        int i;                                                              
        int j;                                                              

        for (i = 0; i < n; i++) {          // 1 + (n - 0 + 1) + (n - 0) = 2n + 2              
            nMin = i;                      // n

            // 1 + sum(n - i, i = 0 to n - 1) + sum(n - i - 1, i = 0 to n - 1)
            // = n^2 + 1
            for (j = i + 1; j < n; j++) {
                // sum(n - i - 1, i = 0 to n - 1)
                // = (1/2)(n^2 - n)
                if (arr[nMin].getIdNumber() > arr[j].getIdNumber()) {
                    nMin = j;              // (1/2)(n^2 - n)
                }
            }

            if (i != nMin) {               // n
                swapRec(arr, i, nMin);     // 3n
            }
        }
    }

    /**
     * T(n) = 3
     */
    private void swapRec(Record arr[], int i, int j) {
        Record temp;

        temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * T(n) = 1                  if n == 1
     *        2T(n/2) + 8n + 15  if n >= 2
     * 
     * Closed form:
     * T(n) = 8nlog_2(n) + 16n - 15
     */
    public void mergeSort(Record[] arr, int p, int r) {
        if (p < r) {                           // 1
            int mid = p + (r - p) / 2;         // 1
            mergeSort(arr, p, mid);            // T(n/2)
            mergeSort(arr, mid + 1, r);        // T(n/2)

            merge(arr, p, r, mid);             // 8n + 13              
        }
    }

    /**
     * T(n) = 8n + 13
     */
    private void merge(Record arr[], int p, int r, int mid) {
        int size1 = mid - p + 1;                // 1
        int size2 = r - mid;                    // 1
        Record temp1[] = new Record[size1];     // 1
        Record temp2[] = new Record[size2];     // 1
        int i;                                  
        
        // Goes through the first element until the midpoint.
        // First half will be iterated through; i = 0 to (n + 1)/2 (exclusive).
        for (i = 0; i < size1; i++) {           // 1 + ((n + 1)/2 - 0 + 1) + ((n + 1)/2) = n + 3
            temp1[i] = arr[p + i];              // (n + 1)/2
        } 

        // Goes through the element after the midpoint until the last element.
        // Last half will be iterated through; i = 0 to (n - 1)/2 (exclusive).
        for (i = 0; i < size2; i++) {           // 1 + ((n - 1)/2 - 0 + 1) + ((n - 1)/2) = n + 1
            temp2[i] = arr[mid + 1 + i];        // (n - 1)/2
        }
        
        i = 0;                                  // 1
        int j = 0;                              // 1
        int k = p;                              // 1

        // In the worst case, the arrays would have alternating numbers such
        // that temp1[0] < temp2[0], temp2[0] < temp1[1], temp1[1] < temp2[2],
        // and so on. An example would be 1 4 5 8 | 2 3 6 7.
        // 
        // Hence, in such a case, this loop must run n - 1 times excluding the
        // final condition check, leaving the greatest number in one of the 
        // arrays.
        while (i < size1 && j < size2) {        // (n - 1) + 1 = n
            // In the said worst case, one of the arrays would be completely
            // emptied, while the other would have one element remaining. 
            //
            // Each comparison must remove an element from one of the arrays,
            // hence there should be n/2 + (n/2 - 1) = n - 1 comparisons made
            // in the worst case. Each comparison is accompanied by an increment
            // line (i++ or j++), so the line count for the comparisons would be 
            // 2(n - 1) = 2n - 2.
            // 
            // The initial if statement condition should be run n - 1 times,
            // along with the k++ increment. Hence, the total frequency count
            // for the lines in the loop should be:
            //
            // (2n - 2) + (n - 1) + (n - 1) = 4n - 4

            if (temp1[i].getIdNumber() < temp2[j].getIdNumber()) {
                arr[k] = temp1[i]; 
                i++;
            } else {
                arr[k] = temp2[j];
                j++;
            }
          
            k++;
        }

        // Assuming one of the arrays has already been exhausted, the while
        // condition will only be checked once.
        while (i < size1) {                     // 1
            arr[k] = temp1[i];                  // 0
            i++;                                // 0
            k++;                                // 0
        }
 
        // To exhaust the second array, this loop must be run twice: once
        // to place the last element in the result array, and another for
        // the final condition check.
        while (j < size2) {                     // 2
            arr[k] = temp2[j];                  // 1
            j++;                                // 1
            k++;                                // 1
        }
    }

    /*
     * Define AT LEAST ONE more sorting algorithm here, apart from the
     * ones given above. Make sure that the method accepts an array of
     * records
     */

    /**
     * T(n) = k(11n + 39) + 4n + 6, where k = max no. of digits in 
     * <code>arr</code>.
     */
    public void radixSort(Record[] arr, int n) {
        int arrMax = max(arr, n);                                                   // 4n + 4

        for (int k = 1; arrMax / k > 0; k *= 10) {                                  // 1 + (k + 1) + k = 2k + 2, where k = max no. of digits in arr
            countingSort(arr, n, k);                                                // (k)(11n + 37)
        } 
    }

    /**
     * Implemented for <code>radixSort()</code>.
     * T(n) = 11n + 37
     */
    private void countingSort(Record[] arr, int n, int k) {       
        int i;
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
        for (i = n - 1; i >= 0; i--) {                                              // 1 + (n - 1 - 0 + 2) + n = 2n + 2
            int d = kthDigit(arr[i].getIdNumber(), k);                              // n

            // Subtract 1 to get the index since each count starts from 1.
            output[counts[d] - 1] = arr[i];                                         // n
            counts[d]--;                                                            // n
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