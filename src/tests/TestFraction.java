package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigInteger;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import numeric.Fraction;

class TestFraction {

	Fraction f1;
	Fraction f2;
	BigInteger i1;
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
		i1 = new BigInteger("-7");
		zero = Fraction.zero();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	final void testZero() {
		assertEquals(0, zero.numerator);
	}

	@Test
	final void testAdd() {
		Fraction sum = f1.add(f2);
		assertEquals(-3, sum.numerator);
		assertEquals(20, sum.denominator);
		Fraction sumInteger = f1.add(i1);
		assertEquals(-32, sumInteger.numerator);
		assertEquals(5, sumInteger.denominator);
	}

	@Test
	final void testSubtract() {
		Fraction diff = f1.subtract(f2);
		assertEquals(27, diff.numerator);
		assertEquals(20, diff.denominator);
		Fraction diffInteger = f1.subtract(i1);
		assertEquals(38, diffInteger.numerator);
		assertEquals(5, diffInteger.denominator);
	}

	@Test
	final void testMultiply() {
		Fraction prod = f1.multiply(f2);
		assertEquals(-9, prod.numerator);
		assertEquals(20, prod.denominator);
		Fraction prodInteger = f1.multiply(i1);
		assertEquals(-21, prodInteger.numerator);
		assertEquals(5, prodInteger.denominator);
	}

	@Test
	final void testDivide() {
		Fraction div = f1.divide(f2);
		assertEquals(-4, div.numerator);
		assertEquals(5, div.denominator);
		Fraction divInteger = f1.divide(i1);
		assertEquals(-3, divInteger.numerator);
		assertEquals(35, divInteger.denominator);
	}

	@Test
	final void testNegate() {
		Fraction neg = f1.negate();
		assertEquals(-3, neg.numerator);
		assertEquals(5, neg.denominator);
	}

	@Test
	final void testReciprocal() {
		Fraction reciprocal = f1.reciprocal();
		assertEquals(5, reciprocal.numerator);
		assertEquals(3, reciprocal.denominator);
	}

	@Test
	final void testAbs() {
		Fraction abs = f2.abs();
		assertEquals(3, abs.numerator);
		assertEquals(4, abs.denominator);
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
