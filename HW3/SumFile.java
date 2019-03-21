import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class SumFile {
    public static void main(String[] args) {
        int ans = 0;
        File inputFile = new File(args[0]);
        try {
            Scanner nums = new Scanner(inputFile);
            while (nums.hasNextInt()) {
                ans += nums.nextInt();
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
