package expressions;

import java.util.*;

public interface Expression {
	// Expression = Number(n: int)
	// + Variable(s: String)
	// + Plus(left: Expression, right: Expression)
	// + Multiply (left: Expression, right: Expression)

	static Expression parse(String input) {
		return Parser.parse(input);

	}

	static Expression make(double num) {
		return new Number(num);
	}

	static Expression make(String var) {
		return new Variable(var);
	}

	static Expression sum(final Expression left, final Expression right) {
		Number zero = new Number(0);
		if (left.equals(zero)) {
			return right;
		}
		if (right.equals(zero)) {
			return left;
		}
		return new Plus(left, right);
	}

	static Expression times(final Expression left, final Expression right) {
		Number zero = new Number(0);
		Number one = new Number(1);
		if (left.equals(zero) || right.equals(zero)) {
			return new Number(0);
		}
		if (left.equals(one)) {
			return right;
		}
		if (right.equals(one)) {
			return left;
		}
		return new Multiply(left, right);
	}

	Expression simplify(Map<Expression, Double> values);

	double numTerm();

	double numFactor();

	Expression varTerm();

	Expression varFactor();

	Expression differentiate(Expression var);

	List<Expression> terms();

	List<Expression> factors();

	@Override String toString();

	@Override boolean equals(Object other);

	@Override int hashCode();
}
