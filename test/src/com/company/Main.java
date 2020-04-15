package com.company;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.regex.Pattern;

public class Main {
    public static String ReadFile(File file) throws IOException{
        byte[] encoded = Files.readAllBytes(Paths.get(String.valueOf(file)));
        return new String(encoded);
    }

    public static void main(String[] args){
        // write your code here

        File file = new File(new File("input.txt").getAbsolutePath());
        //System.out.println(file);

        String text = null;
        try {
            text = ReadFile(file);
            String[] words = text.toLowerCase().replaceAll("[-.?!)(,:]", "").split("\\s+");
            HashMap<String, Integer> wordCount = new HashMap<>();
            for (String word : words)
            {
                if (!wordCount.containsKey(word))
                {
                    wordCount.put(word, 0);
                }
                wordCount.put(word, wordCount.get(word) + 1);
            }
            for (String word : wordCount.keySet())
            {
                System.out.println(word + " " + wordCount.get(word));
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
