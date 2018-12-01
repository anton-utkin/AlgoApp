class HeapSort:
    #Complexity: O(N*log(N))
    @staticmethod
    def sort(array):
        length = len(array) - 1
        leastParent = length // 2
        for i in range (leastParent, -1, -1):
            HeapSort.moveDown(array, i, length)
        for i in range (length, 0, -1):
            if array[0] > array[i]:
                HeapSort.swap(array, 0, i)
                HeapSort.moveDown(array, 0, i - 1)
    
    @staticmethod
    def moveDown(array, first, last):
        largest = 2 * first + 1
        while largest <= last:
            if (largest < last) and (array[largest] < array[largest + 1]):
                largest += 1
         
            if array[largest] > array[first]:
                HeapSort.swap(array, largest, first)
                first = largest;
                largest = 2 * first + 1
            else:
                return 
     
    @staticmethod
    def swap(A, x, y):
        tmp = A[x]
        A[x] = A[y]
        A[y] = tmp