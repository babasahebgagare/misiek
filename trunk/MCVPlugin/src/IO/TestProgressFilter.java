/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IO;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author misiek
 */
public class TestProgressFilter extends FilterInputStream {

    /**
     * tracks how many bytes we have read so far
     */
    private long progress;

    // -------------------------- PUBLIC INSTANCE  METHODS --------------------------
    /**
     * Reads the next byte of data from this input stream.
     */
    @Override
    public int read() throws IOException {
        int ret = super.read();
        progress++;
        System.out.println("ccc");
        reportProgress();
        return ret;
    }

    /**
     * Reads up to byte.length bytes of data from this input stream into an array of bytes.
     */
    @Override
    public int read(byte[] b) throws IOException {
        int ret = super.read(b);
        progress += b.length;
        System.out.println("ccc");
        reportProgress();
        return ret;
    }

    /**
     * Reads up to len bytes of data from this input stream into an array of bytes.
     */
    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        int ret = super.read(b, off, len);
        progress += len;
        System.out.println("ccc");
        reportProgress();
        return ret;
    }

    /**
     * Skips over and discards n bytes of data from this input stream.   *
     */
    @Override
    public long skip(long n) throws IOException {
        long ret = super.skip(n);
        progress += n;
        System.out.println("ccc");
        reportProgress();
        return ret;
    }

    @Override
    public synchronized void mark(int readlimit) {
        super.mark(readlimit);
        System.out.println("uiuii");
    }

    // --------------------------- CONSTRUCTORS ---------------------------
    /**
     * Constructor
     *
     * @param in input stream to progress monitor
     */
    public TestProgressFilter(InputStream in) {
        super(in);
    }

    // -------------------------- OTHER METHODS --------------------------
    /**
     * report the progress to the user
     */
    @SuppressWarnings({"WeakerAccess"})
    protected void reportProgress() {
        // this is a dummy routine. A real version would send an event
        // to a progress meter.
        System.out.println(progress);
    }
}
