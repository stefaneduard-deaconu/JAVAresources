
import java.util.*;
import java.util.stream.Stream;

// ?? surprise ??
//interface BacktrackingAlgorithm {
//    public static
//}
//interface BacktrackingEngine {
//
//    public static isValid();
//}

class ClassicBacktracking {
    public static void cartesian(Integer setLength, Integer power) {
        Stack<Integer> stack = new Stack<>();
        // backtracking
        // we'll use the set {0, 1, 2, 3, ..., setLength - 1}
        stack.push(-1);
        while(!stack.empty()) {
            // next possible index:
            if (stack.peek() < setLength - 1)
                stack.push(stack.pop() + 1);
            else {
                stack.pop();
                continue;
            }
            //
            if (true) { // every item is valid
                // if we have obtained a complete solution:
                if (stack.size() == power) {
                    stack.forEach(
                            (element) -> System.out.print(element.toString() + " ")
                    );
                    System.out.println();
                }
                else {
                    stack.push(-1);
                }
            }
        }
    }
    public static void arrangements(Integer setLength, Integer subsetLength) {
        Stack<Integer> stack = new Stack<>();
        // backtracking
        stack.push(-1);
        while(!stack.empty()) {
            // next possible index:
            if (stack.peek() < setLength - 1)
                stack.push(stack.pop() + 1);
            else {
                stack.pop();
                continue;
            }
            // we check if the current element is valid (it's the one and single apparition of this value inside the stack):
            if (stack.indexOf(stack.peek()) == stack.size() - 1) {
                // if we have obtained a complete solution:
                if (stack.size() == subsetLength) {
                    stack.forEach(
                            (element) -> System.out.print(element.toString() + " ")
                    );
                    System.out.println();
                }
                else {
                    stack.push(-1);
                }
            }
        }
    }
    /*
        sum
     */
    /*
        number with  neighbour having1 parity different
     */
    public static void permutations(Integer setLength) {
        arrangements(setLength, setLength);
    }
    public static void combinations(Integer setLength, Integer subsetLength) { // without the second continue (#1)
        Stack<Integer> stack = new Stack<Integer>();
        // backtracking
        stack.push(-1);
        while(!stack.empty()) {
            // next possible index:
            if (stack.peek() < setLength - 1)
                stack.push(stack.pop() + 1);
            else {
                stack.pop();
                continue;
            }
            // we check if the current element is valid;
            //   more exactly, whether the last element si the maximum (step-by-step ----> we only compare the last 2)
            if (stack.size() == 1 || stack.peek() > stack.elementAt(stack.size() - 2)) {
                // and if we have obtained a complete solution:
                if (stack.size() == subsetLength) {
                    // we just print it ----> this is a combination :)
                    stack.forEach(
                            (element) -> System.out.print(element.toString() + " ")
                    );
                    System.out.println();
                }
                else {
                    stack.push(-1);
                }
            }
        }
    }
    public static void allCombinations(Integer setLength) { // without the second continue (#1)
        Stack<Integer> stack = new Stack<Integer>();
        // backtracking
        stack.push(-1);
        while(!stack.empty()) {
            // next possible index:
            if (stack.peek() < setLength - 1)
                stack.push(stack.pop() + 1);
            else {
                stack.pop();
                continue;
            }
            // we check if the current element is valid;
            //   more exactly, whether the last element si the maximum (step-by-step ----> we only compare the last 2)
            if (stack.size() == 1 || stack.peek() > stack.elementAt(stack.size() - 2)) {
                // and if we have obtained a complete solution:
                if (stack.size() > 0) { // so practically every time, so we could replace this with if (true), or just not write it at all
                    // we just print it ----> this is a combination :)
                    stack.forEach(
                            (element) -> System.out.print(element.toString() + " ")
                    );
                    System.out.println();
                    /* As you can see, we may have tried to to make an algorithm as general as
                     *   to only change bits (like modules) of it,
                     *   only that we didn't yet do a jot good enough out of it :)).
                     *
                     */
                    if (stack.size() < setLength - 1) {
                        stack.push(-1);
                    }
                }
            }
        }
    }
}
class PlaneBacktracking {
//    TODO
//    implement a Lee's algorithm example
    static private class Coord {
        public Coord(Integer x, Integer y, Integer time) {
            this.row = x;
            this.column = y;
            this.time = time;
        }
        private Integer row, column, time;

        public Integer getRow() {
            return row;
        }
        public Integer getColumn() {
            return column;
        }
        public Integer getTime() {
            return time;
        }
    }
    // no obstacles in our way, just declaring a matrix (each element is -1), and we see what is the fastest way to get
    //   from the starting points to any other point on the matrix
    private static Boolean insideMatrix(Integer matrixHeight, Integer matrixWidth, Integer x, Integer y) {
        return !(x < 0 || x >= matrixHeight || y < 0 || y >= matrixWidth);
    }
    public static Integer[][] leePattern(Integer numRows, Integer numColumns, Integer[][] startingPoints) {
        Integer [][] matrix = new Integer[numRows][numColumns];
        for (Integer i = 0; i < matrix.length; i++)
            for (Integer j = 0; j < matrix[i].length; j++)
                matrix[i][j] = -1;
        Queue<Coord> queue = new LinkedList<>();
        ///
        Stream.of(startingPoints).forEach(
                (array) -> queue.offer(new Coord(array[0], array[1], 0))
        );
        //
        Integer[][] nextPosition = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        Coord currentItem;
        //
        //
        while (!queue.isEmpty()) {
            currentItem = queue.poll();
            matrix[currentItem.getRow()][currentItem.getColumn()] = currentItem.getTime();
            // introducing the next elements:
            for (Integer[] position : nextPosition) {
                Integer nextRow = currentItem.getRow() + position[0];
                Integer nextCol = currentItem.getColumn() + position[1];
//                if (insideMatrix(numRows, numColumns, nextRow, nextCol))
//                    System.out.print(nextRow + " " + nextCol + ", ");
                if (insideMatrix(numRows, numColumns, nextRow, nextCol) &&
                        (matrix[nextRow][nextCol] == -1 || matrix[nextRow][nextCol] > currentItem.getTime() + 1)) // (*)
                    queue.offer(new Coord(nextRow, nextCol,  currentItem.getTime() + 1));
            }
        }
        return matrix;
    }
}

public class Main {

    public static void main(String args[]) {
//        Scanner sc = new Scanner(System.in);
//        System.out.print("Cardinal of the set: ");
//        setLength = sc.nextInt();
//        System.out.print("Cardinal of the arrangements: ");
//        subsetLength = sc.nextInt();
        System.out.println("Arrangements:");
        ClassicBacktracking.arrangements(5, 3);
        System.out.println("Permutations:");
        ClassicBacktracking.permutations(3);
        System.out.println("Combinations:");
        ClassicBacktracking.combinations(5, 3);
        System.out.println("All combinations:");
        ClassicBacktracking.allCombinations(5);
//        // leePattern:
        Integer[][] pattern = {{0, 0}, {0, 4}, {4, 0}, {4, 4}};
        System.out.println("Input coordonates for Lee: ");
        Stream.of(pattern).forEach(
                (array) -> System.out.print(array[0] + " " + array[1] + "; ")
        );
        Integer [][] leePattern = PlaneBacktracking.leePattern(5, 5, pattern);
        System.out.println("Output Matrix:");
        Stream.of(leePattern).forEach(
                (patternRow) -> {
                    Stream.of(patternRow).forEach(
                            (item) -> System.out.print(item + " ")
                    );
                    System.out.println();
                }
        );
//         now we create a second pattern:
        Integer [][] pattern2 = {{2, 2}, {17, 17}, {9, 9}, {10, 10}, {2, 13}, {5, 16}, {17, 6}, {14, 3}};
        System.out.println("Input coordonates for Lee: ");
        Stream.of(pattern2).forEach(
                (array) -> System.out.print(array[0] + " " + array[1] + "; ")
        );
        System.out.println();
        leePattern = PlaneBacktracking.leePattern(20, 20, pattern2);
        System.out.println("Output Matrix:");
        Stream.of(leePattern).forEach(
                (patternRow) -> {
                    Stream.of(patternRow).forEach(
                            (item) -> System.out.printf("%3d", item)
                            // comments the lambda below and uncomment the one above to get
                            //   a non-fancy ( :) ) representation of the pattern
//                            (item) -> {
//                                if (item == 0) System.out.print("  ");
//                                else if (item % 4 < 2)
//                                    System.out.print("▒"); // ░
//                                else
//                                    System.out.print("▓");
//                            }
                    );
                    System.out.println();
                }
        );
    }
}
