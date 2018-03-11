// Making PhoneNumber comparable - Pages 65-66
package org.effectivejava.examples.chapter03.item14;

import java.util.Comparator;
import java.util.NavigableSet;
import java.util.Random;
import java.util.TreeSet;

import static java.util.Comparator.comparingInt;

public final class PhoneNumber implements Cloneable, Comparable<PhoneNumber> {
    private final short areaCode;
    private final short prefix;
    private final short lineNum;

    public PhoneNumber(int areaCode, int prefix, int lineNumber) {
        rangeCheck(areaCode, 999, "area code");
        rangeCheck(prefix, 999, "prefix");
        rangeCheck(lineNumber, 9999, "line number");
        this.areaCode = (short) areaCode;
        this.prefix = (short) prefix;
        this.lineNum = (short) lineNumber;
    }

    private static void rangeCheck(int arg, int max, String name) {
        if (arg < 0 || arg > max)
            throw new IllegalArgumentException(name + ": " + arg);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof PhoneNumber))
            return false;
        PhoneNumber pn = (PhoneNumber) o;
        return pn.lineNum == lineNum && pn.prefix == prefix
                && pn.areaCode == areaCode;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + areaCode;
        result = 31 * result + prefix;
        result = 31 * result + lineNum;
        return result;
    }

    /**
     * Returns the string representation of this phone number. The string
     * consists of fourteen characters whose format is "(XXX) YYY-ZZZZ", where
     * XXX is the area code, YYY is the prefix, and ZZZZ is the line number.
     * (Each of the capital letters represents a single decimal digit.)
     * <p>
     * If any of the three parts of this phone number is too small to fill up
     * its field, the field is padded with leading zeros. For example, if the
     * value of the line number is 123, the last four characters of the string
     * representation will be "0123".
     * <p>
     * Note that there is a single space separating the closing parenthesis
     * after the area code from the first digit of the prefix.
     */
    @Override
    public String toString() {
        return String.format("(%03d) %03d-%04d", areaCode, prefix, lineNum);
    }

    @Override
    public PhoneNumber clone() {
        try {
            return (PhoneNumber) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(); // Can't happen
        }
    }

    /*
    // Multiple-field Comparable with primitive fields
    @Override
    public int compareTo(PhoneNumber pn) {
        int result = Short.compare(areaCode, pn.areaCode);
        if (result == 0) {
            result = Short.compare(prefix, pn.prefix);
            if (result == 0)
                result = Short.compare(lineNum, pn.lineNum);
        }
        return result;
    }
    */

    // Comparable with comparator construction methods
    private static final Comparator<PhoneNumber> COMPARATOR =
            comparingInt((PhoneNumber pn) -> pn.areaCode)
                    .thenComparingInt(pn -> pn.prefix)
                    .thenComparingInt(pn -> pn.lineNum);

    @Override
    public int compareTo(PhoneNumber pn) {
        return COMPARATOR.compare(this, pn);
    }

    public static void main(String[] args) {
        NavigableSet<PhoneNumber> s = new TreeSet<PhoneNumber>();
        for (int i = 0; i < 10; i++)
            s.add(randomPhoneNumber());
        System.out.println(s);
    }

    private static final Random rnd = new Random();

    private static PhoneNumber randomPhoneNumber() {
        return new PhoneNumber((short) rnd.nextInt(1000),
                (short) rnd.nextInt(1000), (short) rnd.nextInt(10000));
    }
}
