package 算法.线性;

import java.util.HashSet;
import java.util.Set;

/**
 * 查找无重复字符最长子串：滑动窗口
 *
 * @author: fanruikang
 * @date: 2020-07-16 
 */
public class LongestSubString {
    public static void main(String[] args) {
        StringBuffer s = new StringBuffer();
        for (int i = 0; i < 2; i++) {
            s.append("abcdefghigklmnopqrst");
        }
        long old = System.currentTimeMillis();
        System.out.println(lengthofLongestSubstring(s.toString()));
        System.out.println(System.currentTimeMillis() - old);
    }

    static int lengthofLongestSubstring(String s) {
        Set<Character> occ = new HashSet<>();
        int n = s.length();
        int rk = -1, ans = 0;
//        for (int i = 0; i < n; i++) {
        for (int i = 0; i < n && rk + 1 < n; i++) {//改进最后无效左边右滑
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (i != 0) {
                occ.remove(s.charAt(i - 1));
            }
            while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                occ.add(s.charAt(rk + 1));
                ++rk;
            }
            ans = Math.max(ans, rk - i + 1);
        }
        return ans;
    }
}
