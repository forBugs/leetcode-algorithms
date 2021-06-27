package com.fanbai.leetcode.sort;

class QuickSort2 {
    public int[] sortArray(int[] nums) {
        if(nums == null || nums.length == 0) {
            return nums;
        }
        // 快速排序
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    public void quickSort(int[] nums, int left, int right) {
        if(left > right) {
            return;
        }
        int i = left;
        int j = right;
        int tmp = nums[left];
        while(i < j) {
            while(i < j && nums[j] >= tmp) {
                j--;
            }
            if(i < j) {
                nums[i] = nums[j];
            }
            
            while(i < j && nums[i] <= tmp) {
                i++;
            }
            if(i < j) {
                nums[j] = nums[i];
            }
          
        }
        
        nums[i] = tmp;
        quickSort(nums, left, i-1);
        quickSort(nums, i+1, right);
    }

}