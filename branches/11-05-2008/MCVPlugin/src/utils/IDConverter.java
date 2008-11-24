package utils;

public class IDConverter {

    public static String splitCytoID(String CytoID) {
        String[] split = CytoID.split(":");
        return split[0];
    }
}
