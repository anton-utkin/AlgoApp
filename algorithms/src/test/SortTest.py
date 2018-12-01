import unittest
import copy
from MergeSort import MergeSort
from QuickSort import QuickSort
from InsertionSort import InsertionSort
from CountingSort import CountingSort
from BucketSort import BucketSort
from RadixSort import RadixSort
from SelectionSort import SelectionSort
from HeapSort import HeapSort

class TestSort(unittest.TestCase):
    def check(self, sorter, array, expectedArray, maxNumber = None):
        newArray = copy.copy(array)
        if maxNumber == None:
            sorter.sort(newArray)
        else:
            sorter.sort(newArray, maxNumber)
        self.assertEqual(newArray, expectedArray)
    
    def test_a(self):
        array = [0]
        expectedArray = [0]
        self.check(MergeSort(), array, expectedArray)
        self.check(QuickSort(), array, expectedArray)
        self.check(InsertionSort(), array, expectedArray)
        self.check(CountingSort(), array, expectedArray, 10)
        self.check(BucketSort(), [0], [0])
        self.check(RadixSort(), array, expectedArray)
        self.check(SelectionSort(), array, expectedArray)
        self.check(HeapSort(), array, expectedArray)
        
    def test_b(self):
        array = [1, 0]
        expectedArray = [0, 1]
        self.check(MergeSort(), array, expectedArray)
        self.check(QuickSort(), array, expectedArray)
        self.check(InsertionSort(), array, expectedArray)
        self.check(CountingSort(), array, expectedArray, 10)
        self.check(BucketSort(), [0.1, 0.2], [0.1, 0.2])
        self.check(RadixSort(), array, expectedArray)
        self.check(SelectionSort(), array, expectedArray)
        self.check(HeapSort(), array, expectedArray)
        
    def test_c(self):
        array = [0, 1]
        expectedArray = [0, 1]
        self.check(MergeSort(), array, expectedArray)
        self.check(QuickSort(), array, expectedArray)
        self.check(InsertionSort(), array, expectedArray)
        self.check(CountingSort(), array, expectedArray, 10)
        self.check(BucketSort(), [0.2, 0.1], [0.1, 0.2])
        self.check(RadixSort(), array, expectedArray)
        self.check(SelectionSort(), array, expectedArray)
        self.check(HeapSort(), array, expectedArray)
    
    def test_d(self):
        array = [0, 1, 2]
        expectedArray = [0, 1, 2]
        self.check(MergeSort(), array, expectedArray)
        self.check(QuickSort(), array, expectedArray)
        self.check(InsertionSort(), array, expectedArray)
        self.check(CountingSort(), array, expectedArray, 10)
        self.check(BucketSort(), [0.1, 0.2, 0.3], [0.1, 0.2, 0.3])
        self.check(RadixSort(), array, expectedArray)
        self.check(SelectionSort(), array, expectedArray)
        self.check(HeapSort(), array, expectedArray)
        
    def test_e(self):
        array = [0, 2, 1]
        expectedArray = [0, 1, 2]
        self.check(MergeSort(), array, expectedArray)
        self.check(QuickSort(), array, expectedArray)
        self.check(InsertionSort(), array, expectedArray)
        self.check(CountingSort(), array, expectedArray, 10)
        self.check(BucketSort(), [0.1, 0.3, 0.2], [0.1, 0.2, 0.3])
        self.check(RadixSort(), array, expectedArray)
        self.check(SelectionSort(), array, expectedArray)
        self.check(HeapSort(), array, expectedArray)
        
    def test_f(self):
        array = [1, 0, 2]
        expectedArray = [0, 1, 2]
        self.check(MergeSort(), array, expectedArray)
        self.check(QuickSort(), array, expectedArray)
        self.check(InsertionSort(), array, expectedArray)
        self.check(CountingSort(), array, expectedArray, 10)
        self.check(BucketSort(), [0.2, 0.3, 0.1], [0.1, 0.2, 0.3])
        self.check(RadixSort(), array, expectedArray)
        self.check(SelectionSort(), array, expectedArray)
        self.check(HeapSort(), array, expectedArray)
        
    def test_g(self):
        array = [1, 2, 0]
        expectedArray = [0, 1, 2]
        self.check(MergeSort(), array, expectedArray)
        self.check(QuickSort(), array, expectedArray)
        self.check(InsertionSort(), array, expectedArray)
        self.check(CountingSort(), array, expectedArray, 10)
        self.check(BucketSort(), [0.2, 0.1, 0.3], [0.1, 0.2, 0.3])
        self.check(RadixSort(), array, expectedArray)
        self.check(SelectionSort(), array, expectedArray)
        self.check(HeapSort(), array, expectedArray)
        
    def test_h(self):
        array = [2, 1, 0]
        expectedArray = [0, 1, 2]
        self.check(MergeSort(), array, expectedArray)
        self.check(QuickSort(), array, expectedArray)
        self.check(InsertionSort(), array, expectedArray)
        self.check(CountingSort(), array, expectedArray, 10)
        self.check(BucketSort(), [0.3, 0.1, 0.2], [0.1, 0.2, 0.3])
        self.check(RadixSort(), array, expectedArray)
        self.check(SelectionSort(), array, expectedArray)
        self.check(HeapSort(), array, expectedArray)
        
    def test_i(self):
        array = [2, 0, 1]
        expectedArray = [0, 1, 2]
        self.check(MergeSort(), array, expectedArray)
        self.check(QuickSort(), array, expectedArray)
        self.check(InsertionSort(), array, expectedArray)
        self.check(CountingSort(), array, expectedArray, 10)
        self.check(BucketSort(), [0.3, 0.2, 0.1], [0.1, 0.2, 0.3])
        self.check(RadixSort(), array, expectedArray)
        self.check(SelectionSort(), array, expectedArray)
        self.check(HeapSort(), array, expectedArray)
        
    def test_j(self):
        array = [2, 0, 1, 3, 5, 4, 7, 9, 8, 6]
        expectedArray = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
        self.check(MergeSort(), array, expectedArray)
        self.check(QuickSort(), array, expectedArray)
        self.check(InsertionSort(), array, expectedArray)
        self.check(CountingSort(), array, expectedArray, 9)
        self.check(BucketSort(), [0.3, 0.2, 0.1, 0, 0.8, 0.6, 0.9, 0.7, 0.5, 0.4], [0, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9])
        self.check(RadixSort(), array, expectedArray)
        self.check(SelectionSort(), array, expectedArray)
        self.check(HeapSort(), array, expectedArray)

if __name__ == '__main__':
    unittest.main()