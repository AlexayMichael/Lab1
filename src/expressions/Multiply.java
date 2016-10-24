package expressions;

import java.util.*;


class Multiply implements Expression {

	private final Expression left;
	private final Expression right;

	Multiply(final Expression left, final Expression right) {
		this.left = left;
		this.right = right;
	}

	public String toString() {
		return "(" + this.left.toString() + " * " + this.right.toString() + ")";
	}

	public List<Expression> terms() {
		List<Expression> terms = new ArrayList<>();
		terms.add(this);
		return terms;
	};

	public List<Expression> factors() {
		List<Expression> factors = new ArrayList<>();
		factors.addAll(this.left.factors());
		factors.addAll(this.right.factors());
		return factors;
	};

	// Uses d(u*v)/dt = u(dv/dt) + v(du/dt)
	public Expression differentiate(final Expression var) {
		return Expression.sum(Expression.times(left, right.differentiate(var)),
				Expression.times(left.differentiate(var), right));
	}

	public Expression simplify(final Map<Expression, Double> values) {
		Expression simpExp = Expression.times(left.simplify(values), right.simplify(values));
		Number numFactor = new Number(simpExp.numFactor());
		Expression varFactor = simpExp.varFactor();
		return Expression.times(numFactor, varFactor);
	}

	public double numTerm() {
		return 0;
	}

	public double numFactor() {
		return left.numFactor() * right.numFactor();
	}

	public Expression varTerm() {
		return this;
	}

	public Expression varFactor() {
		return Expression.times(left.varFactor(), right.varFactor());
	}

	public boolean equals(final Object other) {
		if (!(other instanceof Multiply)) {
			return false;
		} else {
			Multiply otherMult = (Multiply) other;
			// return (this.factors().equals(otherMult.factors()));
			return (this.left.equals(otherMult.left) && this.right.equals(otherMult.right));
		}
	}

	public int hashCode() {
		int result = 43;
		result = 37 * result + left.hashCode();
		result = 37 * result + right.hashCode();
		return result;
	}

	public static void main(final String[] args) {
		Number n = new Number(5);
		Variable v = new Variable("x");
		Map<Expression, Double> m = new HashMap<>();
		m.put(new Variable("x"), 3.0);
		System.out.println(Expression.times(n, Expression.sum(n, v)).simplify(m));
	}
}
