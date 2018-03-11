// Test program that uses CounterPoint as Point
package org.effectivejava.examples.chapter03.item10.equals.transitivity;

import org.effectivejava.examples.chapter03.item10.equals.transitivity.CounterPoint;
import org.effectivejava.examples.chapter03.item10.equals.transitivity.Point;

import java.util.HashSet;
import java.util.Set;

public class CounterPointTest {



	// Initialize UnitCircle to contain all Points on the unit circle
	private static final Set<Point> unitCircle;

	static {
		unitCircle = new HashSet<Point>();
		unitCircle.add(new Point(1, 0));
		unitCircle.add(new Point(0, 1));
		unitCircle.add(new Point(-1, 0));
		unitCircle.add(new Point(0, -1));
	}

	public static boolean onUnitCircle(Point p) {
		return unitCircle.contains(p);
	}

	public static void main(String[] args) {
		Point p1 = new Point(1, 0);
		Point p2 = new CounterPoint(1, 0);

		p1.equals(p2);
		// Prints true
		System.out.println(onUnitCircle(p1));

		System.out.println(onUnitCircle(p2));
		// Should print true, but doesn't if Point uses getClass-based equals
	}
}
