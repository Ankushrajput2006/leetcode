class Solution {
    public boolean stoneGame(int[] piles) {
        return true;/*from observation Alice always wins because
        there are an even number of piles, Alice can always choose either all even-indexed piles or all odd-indexed piles, whichever has the larger total. She can force this by choosing the appropriate end on her first move.*/
    }
}