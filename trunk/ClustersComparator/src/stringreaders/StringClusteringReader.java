package stringreaders;

import java.util.Map;

/**
 *
 * @author misiek (mw219725@gmail.com)
 */
public abstract class StringClusteringReader {

    protected String filename = null;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public abstract Map<String, String> readClustering();
}
