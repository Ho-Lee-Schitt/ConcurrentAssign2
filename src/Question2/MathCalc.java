package Question2;

/**
 * Created by cgf13hun on 27/03/2017.
 */

public class MathCalc implements MathService
{


    @Override
    public double add(double firstValue, double secondValue) {
        return (firstValue + secondValue);
    }

    @Override
    public double sub(double firstValue, double secondValue) {
        return (firstValue - secondValue);
    }

    @Override
    public double div(double firstValue, double secondValue) {
        return (firstValue/secondValue);
    }

    @Override
    public double mul(double firstValue, double secondValue) {
        return (firstValue*secondValue);
    }
} // end MathCalc
