import random
 
class QuickSort:
    """"Complexity: O(N*Log(N)) on average"""
    @staticmethod
    def sort(array):
        QuickSort._sort(array, 0, len(array) - 1)
   
    @staticmethod
    def _sort(array, begin, end):
        if(begin < end):
            q = QuickSort._randomized_partition(array, begin, end)
            QuickSort._sort(array, begin, q - 1)
            QuickSort._sort(array, q + 1, end)
           
    @staticmethod
    def _randomized_partition(array, begin, end):
        i = random.randint(begin, end)
        QuickSort._swap(array, i, end)
        return QuickSort._partition(array, begin, end)
           
    @staticmethod
    def _partition(array, begin, end):
        x = array[end]
        i = begin - 1
        j = begin
        while(j < end):
            if(array[j] <= x):
                i = i + 1;
                QuickSort._swap(array, i, j)
            j = j + 1
        QuickSort._swap(array, i + 1, end)
        return i + 1
       
    @staticmethod            
    def _swap(array, i, j):
        tmp = array[i]
        array[i] = array[j]
        array[j] = tmp
