
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;

// ?? surprise ??
//interface BacktrackingAlgorithm {
//    public static
//}
//interface BacktrackingEngine {
//
//    public static isValid()
//}

class ClassicBacktracking {
    public static void arrangements(Integer setLength, Integer subsetLength) {
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
            // we check if the current element already is in the stack:
            if (stack.indexOf(stack.peek()) != stack.size() - 1) {
                // and if it is, we continue as to set the next element
                continue; // (#1)
            }
            else {
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
        //
//        System.out.println("Input Matrix for Lee:");
//        PlaneBacktracking.fill(5, 3);
//        System.out.println("Output Matrix:");
//        PlaneBacktracking.fill(5, 3);
    }
}
