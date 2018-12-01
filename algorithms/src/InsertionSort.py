class InsertionSort:
    """Complexity: O(N^2)"""
    @staticmethod
    def sort(array):
        j = 1;
        while(j < len(array)):
            key = array[j]
            i = j - 1;
            while(i >= 0 and array[i] > key):
                array[i+1] = array[i]
                i = i - 1;
            array[i+1] = key
            j = j + 1
            