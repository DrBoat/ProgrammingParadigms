import java.io.*;
import java.util.*;

public class Reverse {
    public static void main(String[] args) throws IOException {
        Scanner stdInScanner = new Scanner(System.in);
        ArrayDeque <String> st = new ArrayDeque <> (0);
        while (stdInScanner.hasNextLine()){
            String now = stdInScanner.nextLine();
            st.addFirst(now);
        }
        for (int i = st.size() - 1; i >= 0; i--)
        {
            ArrayDeque <Integer> nowOut = new ArrayDeque <> (0);
            Scanner toDeque = new Scanner (st.pollFirst());
            while (toDeque.hasNextInt())
            {
                nowOut.addFirst(toDeque.nextInt());
            }
            for (int j = nowOut.size() - 1; j >= 0; j--)
            {
                System.out.printf(nowOut.pollFirst() + " ");
            }
            System.out.printf("\n");
        }
    }
}
