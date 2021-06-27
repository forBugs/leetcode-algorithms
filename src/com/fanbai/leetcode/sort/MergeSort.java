package com.fanbai.leetcode.sort;

import com.sun.org.apache.xpath.internal.operations.String;

class MergeSort {
    public int[] sortArray(int[] nums) {
        if(nums == null || nums.length == 0) {
            return nums;
        }
        // 归并排序
        mergeSort(nums, 0, nums.length - 1);

        return nums;
    }

    public void mergeSort(int[] nums, int left, int right) {
        if(left >= right) return;
        int middle = (left + right) >> 1;
        // 分为两个部分，分别去递归排序
        mergeSort(nums, left, middle);
        mergeSort(nums, middle+1, right);
        // 合并排序后结果(用于合并两个有序数组)
        merge(nums, left, right, middle);
    }

    public void merge(int[] nums, int left, int right, int middle) {
        int[] tempArray = new int[right - left + 1];
        int p1 = left, p2 = middle + 1, p = 0;
        while(p1 <= middle && p2 <= right) {
            if(nums[p1] <= nums[p2]) {
                tempArray[p++] = nums[p1++];
            } else {
                tempArray[p++] = nums[p2++];
            }
        }

        while(p1 <= middle) {
            tempArray[p++] = nums[p1++];
        }
        while(p2 <= right) {
            tempArray[p++] = nums[p2++];
        }
        System.arraycopy(tempArray, 0, nums, left, right - left +1);
    }


}