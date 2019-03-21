import java.io.*;
import java.util.*;

public class WordStatWords {
    public static void main(String[] args) {
        if (args.length < 2)
        {
            System.out.println("ERROR: name of input or output file not found in arguments of program");
            return;
        }
        Map<String, Integer> words = new HashMap<>();
        try (Scanner input = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(args[0]), "UTF-8")))) {
            StringBuilder sb = new StringBuilder();
            input.useDelimiter("");
            while (input.hasNext()) {
                String s = input.next();
                for (int i = 0; i < s.length(); i++) {
                    if (Character.isLetter(s.charAt(i)) || Character.getType(s.charAt(i)) == Character.DASH_PUNCTUATION || s.charAt(i) == '\'') {
                        sb.append(s.charAt(i));
                    } else if (sb.length() != 0) {
                        String tmp = sb.toString().toLowerCase();
                        if (words.containsKey(tmp)) {
                            words.put(tmp, words.get(tmp) + 1);
                        } else {
                            words.put(tmp, 1);
                        }
                        sb.setLength(0);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("ERROR: input file not found: " + e.getMessage());
        } catch (UnsupportedEncodingException e) {
            System.err.println("ERROR: unsupported encoding: " + e.getMessage());
        }

        File outputFile = new File(args[1]);
        try (FileWriter out = new FileWriter(outputFile)){
            Map<String, Integer> treeMap = new TreeMap<String, Integer>(words);
            for (Map.Entry i : treeMap.entrySet()) {
                out.write(i.getKey() + " " + i.getValue() + "\n");
            }
        } catch (FileNotFoundException e) {
            System.err.println("ERROR: output file not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("ERROR: output error: " + e.getMessage());
        }
    }
}
