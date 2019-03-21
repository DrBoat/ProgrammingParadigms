import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class ReverseMin {
    public static void main (String [] args) {
        List <Integer> minRow = new ArrayList<>(), minCol = new ArrayList<>(), numbsRow = new ArrayList<>();
        try {
            MyScanner in = new MyScanner (System.in);
            try {
                int prev = 1, minimum = Integer.MAX_VALUE, numbers = 0;
                while (in.hasNext()) {
                    while (prev != in.getLineNumber()) {
                        numbsRow.add(numbers);
                        minRow.add(minimum);
                        minimum = Integer.MAX_VALUE;
                        numbers = 0;
                        prev++;
                    }
                    int curNumber = in.nextInt();
                    minimum = Math.min(curNumber, minimum);
                    if (minCol.size() <= numbers) {
                        minCol.add(curNumber);
                    } else {
                        minCol.set(numbers, Math.min(minCol.get(numbers), curNumber));
                    }
                    numbers++;
                }
                while (prev != in.getLineNumber()) {
                    numbsRow.add(numbers);
                    minRow.add(minimum);
                    numbers = 0;
                    prev++;
                }
            } finally {
                in.close();
            }
        } catch (NumberFormatException e) {
            System.out.println("ERROR: Not A Number");
            return;
        } catch (UnsupportedEncodingException e) {
            System.out.print("ERROR: Unsupported Encoding");
            return;
        } catch (IOException e) {
            System.out.print("ERROR: Reading Error");
            return;
        }
        
        for (int i = 0; i < minRow.size(); i++) {
            if (minRow.size() > 0) {
                for (int j = 0; j < numbsRow.get(i); j++) {
                    System.out.print(Math.min(minRow.get(i), minCol.get(j)) + " ");
                }
            } else {
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
