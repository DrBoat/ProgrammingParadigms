import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Scanner;

public class SumHexFile {
    public static void main(String[] args) {
        BigInteger ans = new BigInteger("0", 10);
        File inputFile = new File(args[0]);
        try {
            Scanner nums = new Scanner(inputFile);
            while (nums.hasNext()) {
                String s = nums.next();
                if (s.length() > 2) {
                    if (s.charAt(1) == 'x' || s.charAt(1) == 'X') {
                        StringBuilder sb = new StringBuilder(s);
                        sb.delete(0, 2);
                        s = sb.toString();
                    }
                }
                BigInteger tmp = new BigInteger(s, 16);
                ans = ans.add(tmp);
            }
            nums.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        File outputFile = new File(args[1]);
        try {
            PrintWriter out = new PrintWriter(outputFile);
            out.println(ans);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
