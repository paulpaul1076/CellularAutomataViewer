package castrings;

import java.util.HashSet;

public class CAStrings {

    static byte[] rules = new byte[27];

    static void getNextRules() {
        for (int i = 0; i < 27; ++i) {
            if (i == 2 + 3 * 1 || i == 1 + 3 * 1 || i == 0 + 3 * 1) {
                continue;
            }
            switch (rules[i]) {
                case 0:
                    rules[i]++;
                    return;
                case 1:
                    rules[i]++;
                    return;
                case 2:
                    rules[i] = 0;
                    break;
            }
        }
    }

    static byte[][] triples = {
        {0, 0, 2},
        {0, 1, 2},
        {0, 2, 0},
        {0, 2, 1},
        {0, 2, 2},
        {1, 0, 2},
        {1, 1, 2},
        {1, 2, 0},
        {1, 2, 1},
        {1, 2, 2},
        {2, 0, 0},
        {2, 0, 1},
        {2, 0, 2},
        {2, 1, 0},
        {2, 1, 1},
        {2, 1, 2},
        {2, 2, 0},
        {2, 2, 1},
        {2, 2, 2}
    };

    static String trim(String s) {
        int start = 0;
        while (start < s.length() && s.charAt(start) == '0') {
            start++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < s.length(); i++) {
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }

    static void dfs(String s, HashSet<String> set) {
        s = trim(s);
        if (set.contains(s)) {
            return;
        }
        set.add(s);
        String next = "";
        for (int i = 0; i < s.length() - 1; ++i) {
            int left, middle, right;
            if (i == 0) {
                left = 0;
            } else {
                left = s.charAt(i - 1);
            }
            middle = s.charAt(i) - '0';
            right = s.charAt(i + 1) - '0';
            int rulePtr = left * 3 * 3 + middle * 3 + right;
            next += Integer.toString(rules[rulePtr]);
        }
        dfs(next + "0", set);
        dfs(next + "1", set);
        dfs(next + "2", set);
        System.out.println(set.size());
        maxSize = Math.max(set.size(), maxSize);
        set.remove(s);
    }

    static String getString(byte[] a) {
        StringBuilder sb = new StringBuilder();
        for (int i = 26; i >= 0; i--) {
            sb.append(Byte.toString(a[i]));
        }
        return sb.toString();
    }
    static int maxSize = 0;

    public static void main(String[] args) {
        long bound = (long) Math.pow(3, 24);
        for (int x = 0; x < 19; ++x) {
            rules[3 * 1 + 0] = triples[18][0];
            rules[3 * 1 + 1] = triples[18][1];
            rules[3 * 1 + 2] = triples[18][2];
            for (long i = 0; i < bound; ++i) {
                HashSet<String> set = new HashSet<>();
                maxSize = 0;
                dfs("010", set);
                dfs("011", set);
                dfs("012", set);
                getNextRules();
                System.out.println(getString(rules));
            }
        }
    }

}
