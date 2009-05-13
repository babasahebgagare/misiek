package affinitymain;

import java.util.Map;
import java.util.TreeMap;
public class CommandLineParser {

    public static Map<String, String> parse(String line) {
        Map<String, String> ret = new TreeMap<String, String>();

        String[] tokens = line.split("\\s+");

        for (String token : tokens) {
            String[] attr = token.split("=");
            if (attr.length == 2) {
                ret.put(attr[0], attr[1]);
            }
        }

        return ret;
    }
}
