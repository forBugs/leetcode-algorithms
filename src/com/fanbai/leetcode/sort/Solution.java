package com.fanbai.leetcode.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Solution {
    public int[][] merge(int[][] intervals) {
        List<int[]> list = new ArrayList();
        Arrays.sort(intervals, (v1, v2) -> v1[0]-v2[0]);

        for(int[] interval : intervals) {
            int L = interval[0];
            int R = interval[1];
            if(list.size() == 0 || list.get(list.size()-1)[1] < L) {
                list.add(interval);
            } else {
                list.get(list.size()-1)[1] = Math.max(list.get(list.size()-1)[1], R);
            }
        }


        return list.toArray(new int[list.size ()][]);
    }
}