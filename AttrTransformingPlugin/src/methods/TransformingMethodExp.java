package methods;

/**
 *
 * @author misiek (mw219725@gmail.com)
 */
public class TransformingMethodExp implements DoubleTransformingMethod {

    public Double transform(Double x) {
        return Math.exp(x);
    }
}
