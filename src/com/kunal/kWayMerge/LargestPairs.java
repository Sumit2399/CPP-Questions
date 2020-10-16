package com.kunal.kWayMerge;

import java.util.*;

/*
  Given two sorted arrays in descending order, find ‘K’ pairs with the largest sum where each pair consists of numbers from both the arrays.
  Time: O(K * K * LogK)
  Space: O(K)
 */
class LargestPairs {

    public static List<int[]> findKLargestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(p -> (p[0] + p[1])));
        for (int i = 0; i < nums1.length && i < k; i++) {
            for (int j = 0; j < nums2.length && j < k; j++) {
                if (minHeap.size() < k) {
                    minHeap.add(new int[]{nums1[i], nums2[j]});
                } else {
                    // if the sum of the two numbers from the two arrays is smaller than the smallest (top) element of
                    // the heap, we can 'break' here. Since the arrays are sorted in the descending order, we'll not be
                    // able to find a pair with a higher sum moving forward.
                    if (nums1[i] + nums2[j] < minHeap.peek()[0] + minHeap.peek()[1]) {
                        break;
                    } else { // else: we have a pair with a larger sum, remove top and insert this pair in the heap
                        minHeap.poll();
                        minHeap.add(new int[]{nums1[i], nums2[j]});
                    }
                }
            }
        }
        return new ArrayList<>(minHeap);
    }

    public static void main(String[] args) {
        int[] l1 = new int[]{9, 8, 2};
        int[] l2 = new int[]{6, 3, 1};
        List<int[]> result = LargestPairs.findKLargestPairs(l1, l2, 3);
        System.out.print("Pairs with largest sum are: ");
        for (int[] pair : result)
            System.out.print("[" + pair[0] + ", " + pair[1] + "] ");
    }
}
