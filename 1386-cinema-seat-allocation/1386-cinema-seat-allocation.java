class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        // Bitmask for each row:
        // seats 2-9 are relevant.
        // We use bits 0-7 for seats 2-9.
        int LEFT  = 0b11110000; // seats 2,3,4,5
        int MIDDLE = 0b00111100; // seats 4,5,6,7
        int RIGHT = 0b00001111; // seats 6,7,8,9

        Map<Integer, Integer> rows = new HashMap<>();

        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int s = seat[1];

            // Only seats 2 through 9 affect possible groups.
            if (s >= 2 && s <= 9) {
                int bit = 1 << (s - 2);
                rows.put(row, rows.getOrDefault(row, 0) | bit);
            }
        }

        // Every completely empty row can fit 2 families.
        int answer = (n - rows.size()) * 2;

        for (int mask : rows.values()) {
            boolean left = (mask & LEFT) == 0;
            boolean middle = (mask & MIDDLE) == 0;
            boolean right = (mask & RIGHT) == 0;

            if (left && right) {
                answer += 2;
            } else if (left || middle || right) {
                answer += 1;
            }
        }

        return answer;
    }
}