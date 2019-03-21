import java.io.*;
import java.util.*;

public class Reverse {
    public static void main(String[] args) throws IOException {
        Scanner stdInScanner = new Scanner(System.in);
        Vector st = new Vector(0);
        while (stdInScanner.hasNextLine()){
            String now = stdInScanner.nextLine();
            st.addElement(now);
        }
        for (int i = st.size() - 1; i >= 0; i--)
        {
            Vector nowOut = new Vector(0);
            Scanner toVector = new Scanner ((String) st.elementAt(i));
            while (toVector.hasNextInt())
            {
                nowOut.addElement(toVector.nextInt());
            }
            for (int j = nowOut.size() - 1; j >= 0; j--)
            {
                System.out.printf(nowOut.get(j) + " ");
            }
            System.out.printf("\n");
        }
    }
}
