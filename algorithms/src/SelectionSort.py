class SelectionSort:
    #Complexity: O(N^2)
    @staticmethod
    def sort(array):
        for min in range(0, len(array)):
            least = min
            for j in range(min + 1, len(array)):
                if array[j] < array[least]:
                    least = j
            tmp = array[min]
            array[min] = array[least]
            array[least] = tmp