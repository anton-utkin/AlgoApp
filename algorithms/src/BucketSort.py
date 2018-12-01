from InsertionSort import InsertionSort

class BucketSort:
    """Complexity: O(N)"""
    @staticmethod
    def sort(array):
        n = len(array)
        B = [[]*n for i in range(n)]
        for i in range (0, n):
            B[int(n * array[i])].append(array[i])
        for i in range (0, n):
            if len(B[i]) > 0:
                InsertionSort.sort(B[i])
        del array[:]
        for i in range (0, n):
            if len(B[i]) > 0:
                array.extend(B[i])