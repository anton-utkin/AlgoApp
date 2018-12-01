import unittest
import copy
from BinarySearch import BinarySearch
from OrderStatistic import OrderStatistic

class TestAlgo(unittest.TestCase):
    
    def test_binarysearch(self):
        self.assertEqual(BinarySearch.search([], 1), -1)
        
        self.assertEqual(BinarySearch.search([1], 1), 0)
        self.assertEqual(BinarySearch.search([1], 0), -1)
        
        self.assertEqual(BinarySearch.search([0, 1], 0), 0)
        self.assertEqual(BinarySearch.search([0, 1], 1), 1)
        self.assertEqual(BinarySearch.search([0, 1], 2), -1)
        
        
        self.assertEqual(BinarySearch.search([0, 1, 2], 0), 0)
        self.assertEqual(BinarySearch.search([0, 1, 2], 1), 1)
        self.assertEqual(BinarySearch.search([0, 1, 2], 2), 2)
        self.assertEqual(BinarySearch.search([0, 1, 2], 3), -1)
        
        self.assertEqual(BinarySearch.search([0, 1, 2, 3, 4, 5, 6, 7, 8, 9], 7), 7)
        
    def test_order_statistic(self):
        with self.assertRaises(Exception): 
            OrderStatistic.select([], 1)
            
        with self.assertRaises(Exception): 
            OrderStatistic.select([], 0)
            
            
        self.assertEqual(OrderStatistic.select([100], 1), 100)
        with self.assertRaises(Exception): 
            OrderStatistic.select([100], 0)
        with self.assertRaises(Exception): 
            OrderStatistic.select([100], 2)
            
        self.assertEqual(OrderStatistic.select([101, 100], 1), 100)
        self.assertEqual(OrderStatistic.select([101, 100], 2), 101)
        
        self.assertEqual(OrderStatistic.select([100, 101], 1), 100)
        self.assertEqual(OrderStatistic.select([100, 101], 2), 101)
        
        self.assertEqual(OrderStatistic.select([100, 101, 102], 1), 100)
        self.assertEqual(OrderStatistic.select([100, 101, 102], 2), 101)
        self.assertEqual(OrderStatistic.select([100, 101, 102], 3), 102)
                    
        self.assertEqual(OrderStatistic.select([100, 102, 101], 1), 100)
        self.assertEqual(OrderStatistic.select([100, 102, 101], 2), 101)
        self.assertEqual(OrderStatistic.select([100, 102, 101], 3), 102)
        
        self.assertEqual(OrderStatistic.select([101, 100, 102], 1), 100)
        self.assertEqual(OrderStatistic.select([101, 100, 102], 2), 101)
        self.assertEqual(OrderStatistic.select([101, 100, 102], 3), 102)
        
        self.assertEqual(OrderStatistic.select([101, 102, 100], 1), 100)
        self.assertEqual(OrderStatistic.select([101, 102, 100], 2), 101)
        self.assertEqual(OrderStatistic.select([101, 102, 100], 3), 102)
        
        self.assertEqual(OrderStatistic.select([102, 101, 100], 1), 100)
        self.assertEqual(OrderStatistic.select([102, 101, 100], 2), 101)
        self.assertEqual(OrderStatistic.select([102, 101, 100], 3), 102)
        
        self.assertEqual(OrderStatistic.select([102, 100, 101], 1), 100)
        self.assertEqual(OrderStatistic.select([102, 100, 101], 2), 101)
        self.assertEqual(OrderStatistic.select([102, 100, 101], 3), 102)
        
        self.assertEqual(OrderStatistic.select([102, 101, 109, 107, 106, 108, 105, 103, 104], 5), 105)

        
        
            
if __name__ == '__main__':
    unittest.main()
        