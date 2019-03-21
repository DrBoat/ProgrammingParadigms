import java.io.*;
import java.util.*;

public class ReverseTranspose {
    public static void main(String[] args){
        int n = 0;
        int m = 1;
        Scanner stdInScanner = new Scanner(System.in);
        ArrayList <ArrayList> st = new ArrayList(0);
        while (stdInScanner.hasNextLine()){
            ArrayList <Integer> li = new ArrayList<>(0);
            Scanner nowLine = new Scanner(stdInScanner.nextLine());
            while  (nowLine.hasNextInt())
            {
                li.add(nowLine.nextInt());
            }
            if (m < li.size()) m = li.size();
            st.add(li);
            n++;
        }

        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (st.size() > j)
                {
                    if (st.get(j).size() > i)
                    {
                        System.out.printf(st.get(j).get(i) + " ");
                    }
                }
            }
            System.out.printf("\n");
        }
    }
}
