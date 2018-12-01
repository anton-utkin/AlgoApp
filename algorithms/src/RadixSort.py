class RadixSort:
    """Complexity: O(N*k)
    for N keys which are integers of word size k"""
    @staticmethod
    def _list_to_buckets(array, base, iteration):
        buckets = [[] for i in range(base)]
        for number in array:
            digit = (number // (base ** iteration)) % base
            buckets[digit].append(number)
        return buckets
        
    @staticmethod
    def _buckets_to_list(buckets):
        numbers = []
        for bucket in buckets:
            for number in bucket:
                numbers.append(number)
        return numbers
    
    @staticmethod
    def sort(array, base=10):
        maxval = max(array)
        it = 0
        result = []
        while base ** it <= maxval:
            result = RadixSort._buckets_to_list(
                RadixSort._list_to_buckets(array, base, it))
            it += 1
        for j in range(0, len(result)):
            array[j] = result[j]
