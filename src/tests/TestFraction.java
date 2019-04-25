package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import numeric.Fraction;

class TestFraction {

	Fraction f1;
	Fraction f2;
	Fraction zero;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		f1 = new Fraction(3, 5);
		f2 = new Fraction(-3, 4);
		zero = Fraction.zero();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	final void testZero() {
		assertEquals(0, zero.getNumerator());
	}

	@Test
	final void testAdd() {
		Fraction sum = f1.add(f2);
		assertEquals(-3, sum.getNumerator());
		assertEquals(20, sum.getDenominator());
	}

	@Test
	final void testSubtract() {
		Fraction diff = f1.subtract(f2);
		assertEquals(27, diff.getNumerator());
		assertEquals(20, diff.getDenominator());
	}

	@Test
	final void testMultiply() {
		Fraction prod = f1.multiply(f2);
		assertEquals(-9, prod.getNumerator());
		assertEquals(20, prod.getDenominator());
	}

	@Test
	final void testDivide() {
		Fraction div = f1.divide(f2);
		assertEquals(-4, div.getNumerator());
		assertEquals(5, div.getDenominator());
	}

	@Test
	final void testNegate() {
		Fraction neg = f1.negate();
		assertEquals(-3, neg.getNumerator());
		assertEquals(5, neg.getDenominator());
	}

	@Test
	final void testReciprocal() {
		Fraction reciprocal = f1.reciprocal();
		assertEquals(5, reciprocal.getNumerator());
		assertEquals(3, reciprocal.getDenominator());
	}

	@Test
	final void testAbs() {
		Fraction abs = f2.abs();
		assertEquals(3, abs.getNumerator());
		assertEquals(4, abs.getDenominator());
	}

	@Test
	final void testCompareTo() {
		int n = f1.compareTo(f2);
		assertEquals(1, n);
	}

	@Test
	final void testGreaterThan() {
		boolean b = f1.greaterThan(f2);
		assertTrue(b);
	}

	@Test
	final void testSmallerThan() {
		boolean b = f2.smallerThan(f1);
		assertTrue(b);
	}

	@Test
	final void testIsZero() {
		Fraction z = f1.subtract(f1);
		boolean b = z.isZero();
		assertTrue(b);
	}

}
