/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bsh.operators.standard;

import bsh.operators.Extension;

/**
 * Trig functions like cos, sin, tan, etc. are in radians.
 * Trig functions like cosd, sind, tand, etc. are in degrees.
 *
 * @author Scott Stevenson
 */
public class MathFunctions {

    @Extension
    public static Double sin(Double value) {
        return Math.sin(value);
    }

    @Extension
    public static Double sind(Double value) {
        return Math.sin(Math.toRadians(value));
    }

    @Extension
    public static Double cos(Double value) {
        return Math.cos(value);
    }

    @Extension
    public static Double cosd(Double value) {
        return Math.cos(Math.toRadians(value));
    }

    @Extension
    public static Double tan(Double value) {
        return Math.tan(value);
    }

    @Extension
    public static Double tand(Double value) {
        return Math.tan(Math.toRadians(value));
    }

    @Extension
    public static Double toDegrees(Double value) {
        return Math.toDegrees(value);
    }

    @Extension
    public static Double toRadians(Double value) {
        return Math.toRadians(value);
    }

    @Extension
    public static double[] sin(double[] value) {
        double[] result = new double[value.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = Math.sin(value[i]);
        }
        return result;
    }
    
    @Extension
    public static double[] sind(double[] value) {
        double[] result = new double[value.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = Math.sin(Math.toRadians(value[i]));
        }
        return result;
    }
    @Extension
    public static double[] cos(double[] value) {
        double[] result = new double[value.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = Math.cos(value[i]);
        }
        return result;
    }
    @Extension
    public static double[] cosd(double[] value) {
        double[] result = new double[value.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = Math.cos(Math.toRadians(value[i]));
        }
        return result;
    }
    @Extension
    public static double[] tan(double[] value) {
        double[] result = new double[value.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = Math.tan(value[i]);
        }
        return result;
    }
    @Extension
    public static double[] tand(double[] value) {
        double[] result = new double[value.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = Math.tan(Math.toRadians(value[i]));
        }
        return result;
    }
    @Extension
    public static double[] toDegrees(double[] value) {
        double[] result = new double[value.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = Math.toDegrees(value[i]);
        }
        return result;
    }
    @Extension
    public static double[] toRadians(double[] value) {
        double[] result = new double[value.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = Math.toRadians(value[i]);
        }
        return result;
    }
}
