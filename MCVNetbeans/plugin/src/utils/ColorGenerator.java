package utils;

import java.awt.Color;
import java.util.Random;

public class ColorGenerator {

    private static Random rand = new Random(1243242);

    public static Color generateColor(String FamilyName) {
        return new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
    }

    public static int random(int n) {
        return rand.nextInt(n);
    }
}
