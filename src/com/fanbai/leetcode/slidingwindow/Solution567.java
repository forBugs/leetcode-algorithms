package com.fanbai.leetcode.slidingwindow;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

class Solution567 {
    public boolean checkInclusion(String s1, String s2) {
        Map<Character, Integer> window = new HashMap();
        Map<Character, Integer> need = new HashMap();
        int left = 0;
        int right = 0;
        int valid = 0;
        // 初始化need窗口
        for(int i = 0; i< s1.length(); i++) {
            need.put(s1.charAt(i), need.getOrDefault(s1.charAt(i), 0) + 1);
        }
        // 移动右窗口
        while(right < s2.length()) {
            char c = s2.charAt(right);
            right++;
            // 进行窗口内数据的一系列更新
            if(need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if(Objects.equals(window.get(c), need.get(c))) {
                    valid++;
                }
            }

            // 移动右窗口
            while(right - left >= s1.length()) {
                // 更新最优解
                if(valid == need.size()) {
                    System.out.println(window.keySet());
                    return true;
                }
                char d = s2.charAt(left);
                left++;
                if(window.containsKey(d)) {
                    if(Objects.equals(window.get(d), need.get(d))) {
                        valid--;
                    }
                    window.put(d, window.getOrDefault(d, 0) - 1);
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        String s1 = "ab";
        String s2 = "eidbaooo";

        System.out.println(new Solution567().checkInclusion(s1, s2));
    }
}