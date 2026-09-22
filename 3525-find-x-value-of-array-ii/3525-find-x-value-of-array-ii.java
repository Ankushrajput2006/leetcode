import java.util.*;

class Solution {

    class Node {
        int l, r;
        int prod;
        int[] cnt;

        Node(int l, int r, int k) {
            this.l = l;
            this.r = r;
            this.prod = 1;
            this.cnt = new int[k];
        }
    }

    class SegmentTree {
        int k;
        Node[] tree;

        SegmentTree(int[] nums, int k) {
            this.k = k;
            int n = nums.length;

            tree = new Node[4 * n];

            build(1, 0, n - 1, nums);
        }

        Node merge(Node a, Node b) {
            Node res = new Node(0, 0, k);

            // Product of entire combined segment
            res.prod = (a.prod * b.prod) % k;

            // Prefixes completely inside left part
            for (int r = 0; r < k; r++) {
                res.cnt[r] = a.cnt[r];
            }

            // Prefixes that use all of left + prefix of right
            for (int r = 0; r < k; r++) {
                int newRem = (a.prod * r) % k;
                res.cnt[newRem] += b.cnt[r];
            }

            return res;
        }

        void build(int node, int l, int r, int[] nums) {
            tree[node] = new Node(l, r, k);

            if (l == r) {
                int value = nums[l] % k;

                tree[node].prod = value;
                tree[node].cnt[value] = 1;

                return;
            }

            int mid = (l + r) / 2;

            build(node * 2, l, mid, nums);
            build(node * 2 + 1, mid + 1, r, nums);

            tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
            tree[node].l = l;
            tree[node].r = r;
        }

        void update(int node, int pos, int value) {
            if (tree[node].l == tree[node].r) {

                value %= k;

                tree[node].prod = value;

                Arrays.fill(tree[node].cnt, 0);
                tree[node].cnt[value] = 1;

                return;
            }

            int mid = (tree[node].l + tree[node].r) / 2;

            if (pos <= mid) {
                update(node * 2, pos, value);
            } else {
                update(node * 2 + 1, pos, value);
            }

            Node merged = merge(tree[node * 2], tree[node * 2 + 1]);

            tree[node].prod = merged.prod;
            tree[node].cnt = merged.cnt;
        }

        Node query(int node, int ql, int qr) {

            // Complete overlap
            if (ql <= tree[node].l && tree[node].r <= qr) {
                return tree[node];
            }

            int mid = (tree[node].l + tree[node].r) / 2;

            // Completely in left
            if (qr <= mid) {
                return query(node * 2, ql, qr);
            }

            // Completely in right
            if (ql > mid) {
                return query(node * 2 + 1, ql, qr);
            }

            // Split between left and right
            Node left = query(node * 2, ql, qr);
            Node right = query(node * 2 + 1, ql, qr);

            return merge(left, right);
        }
    }

    public int[] resultArray(int[] nums, int k, int[][] queries) {

        int n = nums.length;

        SegmentTree tree = new SegmentTree(nums, k);

        int[] answer = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {

            int index = queries[i][0];
            int value = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];

            // Permanent update
            tree.update(1, index, value);

            // Query [start, n-1]
            Node result = tree.query(1, start, n - 1);

            answer[i] = result.cnt[x];
        }

        return answer;
    }
}