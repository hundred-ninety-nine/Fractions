/**
 * 
 */
package numeric;

import java.math.BigInteger;

/**
 * @author ic
 *
 */
public final class Fraction extends Number implements Comparable<Fraction> {

	public long numerator;
	public long denominator;

	public Fraction(long numerator) {
		this.numerator = numerator;
		this.denominator = 1;
	}

	public Fraction(long numerator, long denominator) throws ArithmeticException {
		this.numerator = numerator;
		this.denominator = denominator;
	}

	public static Fraction zero() {
		return new Fraction(0, 1);
	}

	public static Fraction one() {
		return new Fraction(1);
	}

	public Fraction add(Fraction anotherFraction) throws ArithmeticException, NullPointerException {
		if (anotherFraction == null)
			throw new NullPointerException("anotherFraction is null");

		checkDivByZero();
		anotherFraction.checkDivByZero();

		Fraction s1 = this.simplify();
		Fraction s2 = anotherFraction.simplify();

		return new Fraction(
				Math.addExact(Math.multiplyExact(s1.numerator, s2.denominator),
						Math.multiplyExact(s1.denominator, s2.numerator)),
				Math.multiplyExact(s1.denominator, s2.denominator)).simplify();
	}

	public Fraction add(BigInteger anotherInteger) throws ArithmeticException, NullPointerException {
		if (anotherInteger == null)
			throw new NullPointerException("anotherInteger is null");

		return add(new Fraction(anotherInteger.longValue()));
	}

	public Fraction subtract(Fraction anotherFraction) throws ArithmeticException, NullPointerException {
		if (anotherFraction == null)
			throw new NullPointerException("anotherFraction is null");

		return this.add(anotherFraction.negate());
	}

	public Fraction subtract(BigInteger anotherInteger) throws ArithmeticException, NullPointerException {
		if (anotherInteger == null)
			throw new NullPointerException("anotherInteger is null");

		return subtract(new Fraction(anotherInteger.longValue()));
	}

	public Fraction multiply(Fraction anotherFraction) throws ArithmeticException, NullPointerException {
		if (anotherFraction == null)
			throw new NullPointerException("anotherFractioncannot is null");

		checkDivByZero();
		anotherFraction.checkDivByZero();

		Fraction s1 = this.simplify();
		Fraction s2 = anotherFraction.simplify();

		return new Fraction(Math.multiplyExact(s1.numerator, s2.numerator),
				Math.multiplyExact(s1.denominator, s2.denominator)).simplify();
	}

	public Fraction multiply(BigInteger anotherInteger) throws ArithmeticException, NullPointerException {
		if (anotherInteger == null)
			throw new NullPointerException("anotherInteger is null");

		return multiply(new Fraction(anotherInteger.longValue()));
	}

	public Fraction divide(Fraction anotherFraction) throws ArithmeticException, NullPointerException {
		if (anotherFraction == null)
			throw new NullPointerException("anotherFractioncannot is null");

		return this.multiply(anotherFraction.reciprocal());
	}

	public Fraction divide(BigInteger anotherInteger) throws ArithmeticException, NullPointerException {
		if (anotherInteger == null)
			throw new NullPointerException("anotherInteger is null");

		return divide(new Fraction(anotherInteger.longValue()));
	}

	public Fraction negate() throws ArithmeticException {
		return new Fraction(Math.negateExact(numerator), denominator).simplify();
	}

	public Fraction reciprocal() throws ArithmeticException {
		return new Fraction(denominator, numerator).simplify();
	}

	public Fraction abs() throws ArithmeticException {
		return new Fraction(Math.abs(numerator), Math.abs(denominator)).simplify();
	}

	@Override
	public int compareTo(Fraction anotherFraction) throws ArithmeticException {
		checkDivByZero();
		anotherFraction.checkDivByZero();
		double diff = this.doubleValue() - anotherFraction.doubleValue();
		return diff < 0 ? -1 : diff > 0 ? 1 : 0;
	}

	public boolean greaterThan(Fraction anotherFraction) throws ArithmeticException, NullPointerException {
		if (anotherFraction == null)
			throw new NullPointerException("anotherFractioncannot is null");
		checkDivByZero();
		anotherFraction.checkDivByZero();
		return this.compareTo(anotherFraction) > 0;
	}

	public boolean smallerThan(Fraction anotherFraction) throws ArithmeticException, NullPointerException {
		if (anotherFraction == null)
			throw new NullPointerException("anotherFractioncannot is null");
		checkDivByZero();
		anotherFraction.checkDivByZero();
		return this.compareTo(anotherFraction) < 0;
	}

	public boolean isZero() throws ArithmeticException {
		checkDivByZero();
		return this.numerator == 0;
	}

	public int hashCode() throws ArithmeticException {
		Fraction s = this.simplify();
		if (s.denominator < 0)
			return (int) (-s.numerator * 3 - s.denominator * 7);
		else
			return (int) (s.numerator * 3 + s.denominator * 7);
	}

	@Override
	public double doubleValue() throws ArithmeticException {
		checkDivByZero();
		return ((double) numerator / (double) denominator);
	}

	@Override
	public float floatValue() throws ArithmeticException {
		checkDivByZero();
		return ((float) numerator / (float) denominator);
	}

	@Override
	public int intValue() throws ArithmeticException {
		checkDivByZero();
		return (int) (numerator / denominator);
	}

	@Override
	public long longValue() throws ArithmeticException {
		checkDivByZero();
		return (numerator / denominator);
	}

	public boolean equals(Object o) throws ArithmeticException {
		if (this == o)
			return true;
		if (o == null)
			return false;
		if (this.getClass() != o.getClass())
			return false;
		Fraction f = (Fraction) o;
		Fraction s1 = this.simplify();
		Fraction s2 = f.simplify();
		return (s1.numerator == s2.numerator && s1.denominator == s2.denominator)
				|| (s1.numerator == -s2.numerator && s1.denominator == -s2.denominator);

	}

	public String toString() throws ArithmeticException {
		return toString(true);
	}

	public String toString(boolean simplify) throws ArithmeticException {
		if (simplify)
			return this.simplify().numerator + "/" + this.simplify().denominator;
		else
			return this.numerator + "/" + this.denominator;
	}

	public Fraction simplify() throws ArithmeticException {
		checkDivByZero();

		if (numerator == 0)
			return new Fraction(0, 1);

		Fraction c = canonicalize();

		long gcd = BigInteger.valueOf(c.numerator).gcd(BigInteger.valueOf(c.denominator)).longValue();

		return new Fraction(c.numerator / gcd, c.denominator / gcd);
	}

	private Fraction canonicalize() {
		if (denominator < 0)
			return new Fraction(-numerator, -denominator);
		else
			return new Fraction(numerator, denominator);
	}

	private void checkDivByZero() throws ArithmeticException {
		if (denominator == 0) {
			throw new ArithmeticException("Division by zero");
		}
	}
}
