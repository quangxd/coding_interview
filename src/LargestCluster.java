import java.util.Stack;

public class LargestCluster {

    public static  int largestClusterSize(int[][] matrix) {
        //define direction up, down, right, left
        int[] direction = {-1, 0, 1, 0, -1, 0};
        int largestSize = 0;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if(matrix[i][j] == 1) {//if count to visited cell
                    int clusterSize = dfs(i, j, matrix, direction);//find the size of this cluster
                    largestSize = Math.max(largestSize, clusterSize);
                }
            }
        }

        return largestSize;
    }

    private static  int dfs(int x, int y, int[][] matrix, int[] direction) {
        int size = 0;
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{x, y});

        while (!stack.isEmpty()) {
            int[] cell = stack.pop();

            int cx = cell[0], cy = cell[1];

            if(cx < 0 || cx >= matrix.length || cy < 0 || cy >= matrix[0].length || matrix[cx][cy] == 0) {
                //if out of bound or visited --> ignore
                continue;
            }
            //mark the cell as visited
            matrix[cx][cy] = 0;
            size ++;

            //move to 4 directions (up, down, left, right)
            for (int i = 0; i < 4; i++) {
                int nx = cx + direction[i];
                int ny = cy + direction[i+1];

                stack.push(new int[]{nx, ny});
            }
        }
        return size;
    }

    //test the function
    //Explain Additional Requirement please find in readme file.
    public static void main(String[] args) {
        //given 2D array
        int[][] matrix = {
                {1, 0, 0, 0, 1},
                {1, 1, 1, 0, 1},
                {1, 0, 1, 0, 1},
                {1, 0, 0, 0, 1},
        };
        //expected result is 7
        System.out.println("Largest cluster:"+ largestClusterSize(matrix));
        System.out.println("log 1");
    }
}
