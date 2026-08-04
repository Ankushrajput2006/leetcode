class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangle = new ArrayList<>();
        for(int i=1;i<=numRows;i++){
            triangle.add(printrow(i));
        }
        return triangle;
    }
    public static ArrayList<Integer> printrow(int row){
        ArrayList<Integer> rows = new ArrayList<>();
        int ans = 1;
        rows.add(1);
        for(int i=1;i<row;i++){
            ans = ans * (row - i) /i;
            rows.add(ans);
        }
        return rows;
    }
}