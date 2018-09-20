package collections;

import java.io.*;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by ravi on 1/9/18.
 */
public class CollectionOperations {
    private char lookFor;
    private Path file;

    CollectionOperations(char lookFor, Path file) {
        this.lookFor = lookFor;
        this.file = file;
    }

    public int count() throws IOException {
        int count = 0;
        try (InputStream in = Files.newInputStream(file);
             BufferedReader reader = new BufferedReader(new InputStreamReader(in)))
        {
            String line = null;
            while ((line = reader.readLine()) != null) {
                for (int i = 0; i < line.length(); i++) {
                    if (lookFor == line.charAt(i)) {
                        count++;
                    }
                }
            }
        } catch (IOException x) {
            System.err.println(x);
        }
        return count;
    }

    static void usage() {
        System.out.println("usage: java CountLetter <letter>");
        System.exit(-1);
    }

    public static void main(String[] args) throws IOException {

        if (args.length != 1 || args[0].length() != 1)
            usage();

        char lookFor = args[0].charAt(0);
        Path file = Paths.get("/tmp/test.txt");
        int count = new CollectionOperations(lookFor, file).count();
        System.out.format("File '%s' has %d instances of letter '%c'.%n",
                file, count, lookFor);
    }
}