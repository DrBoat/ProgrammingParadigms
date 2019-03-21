import java.io.*;
import java.util.*;

public class Reverse {
    public static void main(String[] args) throws IOException {
        Scanner stdInScanner = new Scanner(System.in);
        Stack st = new Stack();
        while (stdInScanner.hasNextLine()){
            String now = stdInScanner.nextLine();
            st.push(now);
        }
        for (int i = st.size() - 1; i >= 0; i--)
        {
            Stack nowOut = new Stack();
            Scanner toStack = new Scanner ((String) st.pop());
            while (toStack.hasNextInt())
            {
                nowOut.push(toStack.nextInt());
            }
            for (int j = nowOut.size() - 1; j >= 0; j--)
            {
                System.out.printf(nowOut.pop() + " ");
            }
            System.out.printf("\n");
        }
    }
}
