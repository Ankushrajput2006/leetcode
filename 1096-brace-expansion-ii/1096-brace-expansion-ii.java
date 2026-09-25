import java.util.*;

class Solution {
    public List<String> braceExpansionII(String expression) {
        Set<String> result = dfs(expression, 0, expression.length() - 1);
        List<String> ans = new ArrayList<>(result);
        Collections.sort(ans);
        return ans;
    }

    private Set<String> dfs(String s, int l, int r) {
        Set<String> result = new HashSet<>();
        List<Set<String>> parts = new ArrayList<>();

        int i = l;

        while (i <= r) {
            if (s.charAt(i) == '{') {
                int count = 1;
                int j = i + 1;

                while (count > 0) {
                    if (s.charAt(j) == '{') count++;
                    else if (s.charAt(j) == '}') count--;
                    j++;
                }

                parts.add(dfs(s, i + 1, j - 2));
                i = j;
            } 
            else if (s.charAt(i) == ',') {
                addProduct(parts, result);
                parts.clear();
                i++;
            } 
            else {
                Set<String> single = new HashSet<>();
                single.add(String.valueOf(s.charAt(i)));
                parts.add(single);
                i++;
            }
        }

        addProduct(parts, result);

        return result;
    }

    private void addProduct(List<Set<String>> parts, Set<String> result) {
        Set<String> current = new HashSet<>();
        current.add("");

        for (Set<String> part : parts) {
            Set<String> next = new HashSet<>();

            for (String a : current) {
                for (String b : part) {
                    next.add(a + b);
                }
            }

            current = next;
        }

        result.addAll(current);
    }
}