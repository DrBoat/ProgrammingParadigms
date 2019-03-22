import java.math.*;
import java.security.*;
import java.util.*;
import java.io.*;
import java.nio.file.*;


public class CalcMD5 {
    public static void main (String[] args){
        if (args.length != 1) {
            System.err.println("Enter the name only of ONE file!");
            return;
        }

        try (Scanner inputFile = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(args[0]), "UTF-8")))){
            while (inputFile.hasNextLine()){
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.reset();
                md.update(Files.readAllBytes(Paths.get(inputFile.nextLine())));
                String hash = new BigInteger(1, md.digest()).toString(16);
                StringBuilder nulls = new StringBuilder();
                for (int i = 0; i < 32 - hash.length(); i++) nulls.append("0");
                System.out.println(nulls + hash.toUpperCase());
            }
        } catch (FileNotFoundException e) {
            System.err.println("ERROR! File not found: " + e.getMessage());
        } catch (UnsupportedEncodingException e) {
            System.err.println("ERROR! Unsupported Encoding: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Input ERROR: " + e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            System.err.println("ERROR of Hash calculating: " + e.getMessage());
        }
    }
}
