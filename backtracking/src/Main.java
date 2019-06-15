
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String args[]) {
        Stack<Integer> stack = new Stack<Integer>();

        stack.push(-1);
        Scanner sc = new Scanner(System.in);
        Integer setLength, subsetLength;
        System.out.print("Cardinal of the set: ");
        setLength = sc.nextInt();
        System.out.print("Cardinal of the arrangements: ");
        subsetLength = sc.nextInt();
        //
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
                continue;
            }
            else {
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
}
