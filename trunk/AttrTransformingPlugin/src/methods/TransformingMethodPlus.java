package methods;

/**
 *
 * @author misiek (mw219725@gmail.com)
 */
public class TransformingMethodPlus implements DoubleTransformingMethod {

    private Double offset;

    public TransformingMethodPlus(Double offset) {
        this.offset = offset;
    }

    public Double transform(Double x) {
        return x + offset;
    }
}
