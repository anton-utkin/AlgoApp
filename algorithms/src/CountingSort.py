class CountingSort:
    """Complexity: O(N)"""
    @staticmethod
    def sort(array, maxNumber):
        tmp = [0] * (maxNumber + 1)
        res = [None] * len(array)
        for j in range(0, len(array)):
            tmp[array[j]] = tmp[array[j]] + 1
        for i in range(1, len(tmp)):
            tmp[i] = tmp[i] + tmp[i-1]
        j = len(array) - 1;
        while(j >= 0):
            res[tmp[array[j]] - 1] = array[j]
            tmp[array[j]] = tmp[array[j]] - 1
            j = j - 1
        for j in range(0, len(array)):
            array[j] = res[j]