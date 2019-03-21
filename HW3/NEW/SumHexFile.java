import java.util.*;
import java.io.*;

public class SumHexFile {
    public static void main(String[] args) {
        if (args.length < 2)
        {
            System.out.println("ERROR: name of input or output file not found in arguments of program");
            return;
        }
        File outputFile = new File(args[1]);
        int ans = 0;
        try (Scanner in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(args[0]), "UTF-8")))) {
            while (in.hasNext()) {
                String s = in.next().toLowerCase();
                ans += toInt(s);
            }
        } catch (FileNotFoundException e) {
            System.err.println("ERROR: input file not found: " + e.getMessage());
        } catch (UnsupportedEncodingException e) {
            System.err.println("ERROR: unsupported encoding: " + e.getMessage());
        }
        try (FileWriter out = new FileWriter(outputFile)){
            out.write(ans + "\n");
        } catch (FileNotFoundException e) {
            System.err.println("ERROR: output file not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("ERROR: output error: " + e.getMessage());
        }
    }

    public static int toInt(String s) throws NumberFormatException {
        if(s.startsWith("0x")) {
            return Integer.parseUnsignedInt(s.substring(2, s.length()), 16);
        } else {
            return Integer.parseInt(s);
        }
    }
}