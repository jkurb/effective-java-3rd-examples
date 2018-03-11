// Broken - violates symmetry! - Pages 39-40
package org.effectivejava.examples.chapter03.item10.equals.symmetry;

public final class CaseInsensitiveString {
    private final String s;

    public CaseInsensitiveString(String s) {
        if (s == null)
            throw new NullPointerException();
        this.s = s;
    }

    // Broken - violates symmetry!
    @Override
    public boolean equals(Object o) {
        if (o instanceof CaseInsensitiveString)
            return s.equalsIgnoreCase(((CaseInsensitiveString) o).s);
        if (o instanceof String) // One-way interoperability!
            return s.equalsIgnoreCase((String) o);
        return false;
    }

    // This version is correct.
    /*
     @Override
     public boolean equals(Object o) {
     return o instanceof CaseInsensitiveString &&
        ((CaseInsensitiveString) o).s.equalsIgnoreCase(s);
     }
     */

    public static void main(String[] args) {
        int w = (int) 888.8;
        byte x = (byte) 1000L;
        long y = (byte) 100;
        byte z = (byte) 100L;

        CaseInsensitiveString cis = new CaseInsensitiveString("Polish");
        String s = "polish";
        System.out.println(cis.equals(s) + "  " + s.equals(cis));
    }
}
