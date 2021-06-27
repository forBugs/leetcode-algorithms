package com.fanbai.leetcode.slidingwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * 最小覆盖子串
 * https://leetcode-cn.com/problems/minimum-window-substring/
 */
class Solution76 {
    Map<Character, Integer> need = new HashMap<>();
    Map<Character, Integer> window = new HashMap();
    public String minWindow(String s, String t) {

        int sLen = s.length();
        int tLen = t.length();
        int left = 0;
        int right = 0;

        int valid = 0;
        int start = 0;
        int len = Integer.MAX_VALUE;

        for(int i = 0; i < tLen; i++) {
            char c = t.charAt(i);
            need.put(c, need.getOrDefault(c, 0) + 1);
        }
        while (right < sLen) {
            // 移动右指针
            char c = s.charAt(right);
            right++;
            // 进行窗口内数据的一系列更新
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.get(c).equals(need.get(c))) {
                    valid++;
                }
            }
            // 开始移动左指针
            while (valid == need.size()) {
                // 在这里更新最小覆盖子串
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }
                char d = s.charAt(left);
                // 左移窗口
                left++;
                // 进行窗口数据的一系列更新
                if (need.containsKey(d)) {
                    if (window.get(d).equals(need.get(d))) {
                        valid--;
                    }
                    window.put(d, window.getOrDefault(d, 0) - 1);
                }
            }

        }


        return len != Integer.MAX_VALUE ? s.substring(start, start + len) : "";
    }
}