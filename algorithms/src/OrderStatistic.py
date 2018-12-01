from QuickSort import QuickSort

class OrderStatistic:
    #Complexity: O(N)
    @staticmethod
    def select(array, i):
        if i > len(array) or i < 1:
            raise Exception("Invalid input")
        return OrderStatistic._select(array, 0, len(array) - 1, i)
    
    @staticmethod
    def _select(array, p, r, i):
        if p == r:
            return array[p]
        q = QuickSort._randomized_partition(array, p, r)
        k = q - p + 1
        if i == k:
            return array[q]
        elif i < k:
            return OrderStatistic._select(array, p, q - 1, i)
        else:
            return OrderStatistic._select(array, q + 1, r, i - k)