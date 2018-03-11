//Use nonstatic member classes to implement their iterators

package org.effectivejava.examples.chapter04.item24;

import java.util.AbstractSet;
import java.util.Iterator;

// Typical use of a nonstatic member class
public class MySet<E> extends AbstractSet<E> {
    @Override
    public Iterator<E> iterator() {
        return new MyIterator();
    }

    @Override
    public int size() {
        return 0;
    }

    private class MyIterator implements Iterator<E> {

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public E next() {
            return null;
        }
    }
}