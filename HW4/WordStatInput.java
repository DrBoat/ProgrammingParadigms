import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class WordStatInput {
    public static void main(String[] args) {
        Map<String, Integer> words = new LinkedHashMap<>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    new FileInputStream(args[0]), "UTF-8"));
            Scanner input = new Scanner(br);
            input.useDelimiter("");
            StringBuilder sb = new StringBuilder();
            while (input.hasNext()) {
                char c = input.next().charAt(0);
                if (Character.isLetter(c) || Character.getType(c) == Character.DASH_PUNCTUATION || c == '\'') {
                    sb.append(c);
                } else if (sb.length() != 0) {
                    String tmp = sb.toString().toLowerCase();
                    if (words.containsKey(tmp)) {
                        words.put(tmp, words.get(tmp) + 1);
                    } else {
                        words.put(tmp, 1);
                    }
                    sb.delete(0, sb.length());
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        File outputFile = new File(args[1]);
        try {
            PrintWriter out = new PrintWriter(outputFile);
            for (Map.Entry i : words.entrySet()) {
                out.println(i.getKey() + " " + i.getValue());
            }
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
