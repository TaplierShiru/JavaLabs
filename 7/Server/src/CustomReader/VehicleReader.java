package CustomReader;
import java.io.*;
import java.util.*;


public class VehicleReader {

    private BufferedReader in;
    private StringTokenizer tok = new StringTokenizer(" ");

    public VehicleReader(String filename){
        try {
            in = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException e) {
            in = new BufferedReader(new InputStreamReader(System.in));
        }
    }

    /**
     * readLong, readDouble and other are similar
     *
     * @return int type from input
     */
    public int readInt() {
        return Integer.parseInt(readString());
    }

    public float readFloat() {
        return Float.parseFloat(readString());
    }

    /**
     * Tokens are separated by space or endExclusive of line
     *
     * @return non-empty string token from input (or null if there is no any token)
     */
    public String readString() {
        while (!tok.hasMoreTokens()) {
            String nextLine = readLine();
            if (null == nextLine) {
                return null;
            }
            tok = new StringTokenizer(nextLine);
        }

        return tok.nextToken();
    }

    /**
     * @return whole line from input
     */
    public String readLine() {
        try {
            return in.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void close() throws IOException {
        in.close();
    }
}
