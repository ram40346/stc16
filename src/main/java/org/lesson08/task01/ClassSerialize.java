package org.lesson08.task01;

import java.io.Serializable;

/**
 * Класс реализует интерфейс Serializable
 */
public class ClassSerialize implements Serializable {

    private static final long serialVersionUID = 12052019L;

    private int simpleInt;
    private double simpleDouble;
    private String simpleString;
    private char simpleChar;
    private long simpleLong;
    private byte simpleByte;

    public ClassSerialize(int simpleInt, double simpleDouble, String simpleString, char simpleChar, long simpleLong, byte simpleByte) {
        this.simpleInt = simpleInt;
        this.simpleDouble = simpleDouble;
        this.simpleString = simpleString;
        this.simpleChar = simpleChar;
        this.simpleLong = simpleLong;
        this.simpleByte = simpleByte;
    }

    public int getSimpleInt() {
        return simpleInt;
    }

    public void setSimpleInt(int simpleInt) {
        this.simpleInt = simpleInt;
    }

    public double getSimbpleDouble() {
        return simpleDouble;
    }

    public void setSimbpleDouble(double simbpleDouble) {
        this.simpleDouble = simbpleDouble;
    }

    public String getSimpleString() {
        return simpleString;
    }

    public void setSimpleString(String simpleString) {
        this.simpleString = simpleString;
    }

    public char getSimpleChar() {
        return simpleChar;
    }

    public void setSimpleChar(char simpleChar) {
        this.simpleChar = simpleChar;
    }

    public long getSimpleLong() {
        return simpleLong;
    }

    public void setSimpleLong(long simpleLong) {
        this.simpleLong = simpleLong;
    }

    public byte getSimpleByte() {
        return simpleByte;
    }

    public void setSimpleByte(byte simpleByte) {
        this.simpleByte = simpleByte;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof ClassSerialize) {
            ClassSerialize classSerialize = (ClassSerialize) o;

            return simpleInt == classSerialize.simpleInt
                    && classSerialize.simpleDouble == simpleDouble
                    && simpleChar != classSerialize.simpleChar
                    && simpleLong != classSerialize.simpleLong
                    && simpleByte != classSerialize.simpleByte
                    && simpleString.equals(classSerialize.simpleString);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = simpleInt;
        temp = Double.doubleToLongBits(simpleDouble);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + simpleString.hashCode();
        result = 31 * result + (int) simpleChar;
        result = 31 * result + (int) (simpleLong ^ (simpleLong >>> 32));
        result = 31 * result + (int) simpleByte;
        return result;
    }

    @Override
    public String toString() {
        return "ClassSerialize{" +
                "simpleInt=" + simpleInt +
                ", simpleDouble=" + simpleDouble +
                ", simpleString='" + simpleString + '\'' +
                ", simpleChar=" + simpleChar +
                ", simpleLong=" + simpleLong +
                ", simpleByte=" + simpleByte +
                '}';
    }
}
