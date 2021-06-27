package com.fanbai.leetcode.slidingwindow;

import java.util.*;

class Solution438 {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList();
        Map<Character, Integer> window = new HashMap();
        Map<Character, Integer> need = new HashMap();
        for(int i = 0; i< p.length(); i++) {
            char c = p.charAt(i);
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        int left = 0;
        int right = 0;
        int valid = 0;
        while(right < s.length()) {
            char c = s.charAt(right);
            right++;
            if(need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if(Objects.equals(need.get(c), window.get(c))) {
                    valid++;
                }
            }

            while(right - left >= p.length()) {
                // 更新结果集
                if(valid == need.size()) {
                    res.add(left);
                }
                char d = s.charAt(left);
                left++;
                if(need.containsKey(d)) {
                    if(Objects.equals(need.get(d), window.get(d))) {
                        valid--;
                    }
                    window.put(d, window.getOrDefault(d, 0) - 1);
                }
            }
        }

        return res;
    }
}