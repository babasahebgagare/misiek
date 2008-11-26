package IO;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class MCVBufferedReader {

    private BufferedReader br = null;
    private FileInputStream fis = null;
    private BufferedInputStream bis = null;
    private DataInputStream dis = null;

    public MCVBufferedReader(String filepath) throws FileNotFoundException {
        File file = new File(filepath);

        fis = new FileInputStream(file);
        bis = new BufferedInputStream(fis);
        dis = new DataInputStream(bis);
        br = new BufferedReader(new InputStreamReader(dis));
    }

    public BufferedReader getBufferedReader() {
        return br;
    }

    public void close() throws IOException {

        br.close();
        dis.close();
        bis.close();
        fis.close();

    }
}
