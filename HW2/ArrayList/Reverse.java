import java.io.*;
import java.util.*;

public class Reverse {
    public static void main(String[] args) throws IOException {
        Scanner stdInScanner = new Scanner(System.in);
        ArrayList st = new ArrayList(0);
        while (stdInScanner.hasNextLine()){
            String now = stdInScanner.nextLine();
            st.add(now);
        }
        for (int i = st.size() - 1; i >= 0; i--)
        {
            ArrayList nowOut = new ArrayList(0);
            Scanner toArrayList = new Scanner ((String) st.get(i));
            while (toArrayList.hasNextInt())
            {
                nowOut.add(toArrayList.nextInt());
            }
            for (int j = nowOut.size() - 1; j >= 0; j--)
            {
                System.out.printf(nowOut.get(j) + " ");
            }
            System.out.printf("\n");
        }
    }
}
