import math

class MergeSort:
    """Complexity: O(N*log(N))"""
    @staticmethod
    def sort(array):
        MergeSort._sort(array, 0, len(array) - 1)

    @staticmethod
    def _sort(array, begin, end):
        if(begin == end):
            return
        middle = math.ceil((begin + end) / 2)
        MergeSort._sort(array, begin, middle - 1)
        MergeSort._sort(array, middle, end)
        MergeSort._merge(array, begin, middle, end)
        
    @staticmethod
    def _merge(array, begin, middle, end):
        result = []
        p1 = begin
        p2 = middle
        while(p1 < middle and p2 < end  + 1):
            if(array[p1] < array[p2]):
                result.append(array[p1])
                p1+=1
            else:
                result.append(array[p2])
                p2+=1
        while(p1 < middle):
            result.append(array[p1])
            p1+=1
        while(p2 < end  + 1):
            result.append(array[p2])
            p2+=1
        for x in range(begin, end + 1):
            array[x] = result[x - begin]