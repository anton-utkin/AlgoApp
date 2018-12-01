class BinarySearch:
    #Complexity: O(log(N))
    @staticmethod
    def search(array, item):
        first = 0
        last = len(array)-1

        while first <= last:
            midpoint = (first + last)//2
            if array[midpoint] == item:
                return midpoint
            else:
                if item < array[midpoint]:
                    last = midpoint-1
                else:
                    first = midpoint+1
        return -1