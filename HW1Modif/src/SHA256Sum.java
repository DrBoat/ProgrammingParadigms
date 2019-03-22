import java.io.*;
import java.math.*;
import java.nio.file.*;
import java.security.*;
import java.util.*;

public class SHA256Sum {
    public static void main (String[] args) {
        try {
            for (String filename : args) {
                byte[] fileBytes = Files.readAllBytes(Paths.get(filename));
                System.out.println(calcSHA256(fileBytes).toUpperCase() + " *" + filename);
            }
            if (args.length == 0) {
                byte[] stdinBytes = System.in.readAllBytes();
                System.out.println(calcSHA256(stdinBytes).toUpperCase() + " *-");
            }
        } catch (FileNotFoundException e) {
            System.err.println("ERROR! File not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Input ERROR: " + e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            System.err.println("ERROR of Hash calculating: " + e.getMessage());
        }
    }

    private static String calcSHA256 (byte[] allBytes) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.reset();
        md.update(allBytes);
        return String.format("%064x", new BigInteger(1, md.digest()));
    }
}
