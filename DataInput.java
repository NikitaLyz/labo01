import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public final class DataInput{
public static Long getLong() throws IOException {
    String s = getString();
    return Long.valueOf(s);
}

public static char getChar() throws IOException {
    String s = getString();
    return s.charAt(0);
}

public static Integer getInt() {
    String s = "";
    try {
        s = getString();
    } catch (IOException e) {
        e.printStackTrace();
    }
    return Integer.valueOf(s);
}

public static String getString() throws IOException {
    InputStreamReader isr = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(isr);
    return br.readLine();
}
}
