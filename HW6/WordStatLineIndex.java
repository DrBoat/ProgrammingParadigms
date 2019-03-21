import java.util.*;
import java.io.*;
import java.nio.file.NoSuchFileException;

public class WordStatLineIndex{
    public static void main(String[] args) {
        Map<String, List<Integer>> m = new TreeMap<String, List<Integer>>();
        if (args.length != 2) {
            System.err.println("There are no names for input and output files!");
            return;
        }
        try {
            MyScanner reader = new MyScanner(new File(args[0]), "UTF-8");
            try {
                int counterC = 1;
                int prevLine = 0;
                while (reader.hasNext()) {
                    if (prevLine != reader.getLineNumber()) {
                        prevLine = reader.getLineNumber();
                        counterC = 1;
                    } else {
                        counterC++;
                    }
                    String key = reader.next().toLowerCase();
                    List<Integer> curLine;
                    if (m.get(key) == null) {
                        curLine = new ArrayList<Integer>();
                        curLine.add(1);
                    } else {
                        curLine = m.get(key);
                        curLine.set(0, curLine.get(0) + 1);
                    }
                    curLine.add(prevLine);
                    curLine.add(counterC);
                    m.put(key, curLine);
                } 
            } catch (NullPointerException e) {
                System.err.println("Invalid input! " + e.getMessage());
            } finally {
                reader.close();
            }
        } catch (IOException e) {
            System.err.println("Could not read file! " + e.getMessage());
        }
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]), "UTF-8"));
            try {
                for (Map.Entry<String, List<Integer>> entry: m.entrySet()) {
                    List<Integer> ans = entry.getValue();
                    writer.write(entry.getKey() + " " + ans.get(0));
                    for (int i = 1; i < ans.size(); i += 2) {
                        writer.write(" " + ans.get(i) + ":" + ans.get(i + 1));
                    }
                    writer.newLine();
                }  
            } catch (IOException e) {
                System.err.println("Failed to display answer! ");
            } finally {
                writer.close();
            }
        } catch (IOException e) {
            System.err.println("Could not write answer! ");
        }
        
    }
}
