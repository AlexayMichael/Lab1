package expressions;

import java.util.*;

import expressions.Number;

class Plus implements Expression {

	private final Expression left;
	private final Expression right;

	Plus(final Expression left, final Expression right) {
		this.left = left;
		this.right = right;
	};

	public List<Expression> terms() {
		List<Expression> terms = new ArrayList<>();
		terms.addAll(this.left.terms());
		terms.addAll(this.right.terms());
		return terms;
	};

	public List<Expression> factors() {
		List<Expression> factors = new ArrayList<>();
		factors.add(this);
		return factors;
	};

	// this is the main formula
	// Uses d(u+v)/dt = du/dt + dv/dt
	@Override
	public Expression differentiate(final Expression var) {
		return Expression.sum(left.differentiate(var), right.differentiate(var));
	}

	@Override
	public Expression simplify(final Map<Expression, Double> values) {
		Expression simpExp = Expression.sum(left.simplify(values), right.simplify(values));
		Number numTerm = new Number(simpExp.numTerm());
		Expression varTerm = simpExp.varTerm();
		return Expression.sum(numTerm, varTerm);
	}

	@Override
	public double numTerm() {
		return left.numTerm() + right.numTerm();
	}

	@Override
	public double numFactor() {
		return 1;
	}

	@Override
	public Expression varTerm() {
		return Expression.sum(left.varTerm(), right.varTerm());
	}

	@Override
	public Expression varFactor() {
		return this;
	}

	@Override
	public String toString() {
		// List <Expression> terms = this.terms();
		// String plusString = "";
		// for(Expression term:terms){
		// plusString+= " + " + term.toString();
		// }
		// plusString = "(" + plusString.substring(3) + ")";
		// return plusString;
		return "(" + this.left.toString() + " + " + this.right.toString() + ")";
	}

	public boolean equals(final Object other) {
		if (!(other instanceof Plus)) {
			return false;
		}
		Plus otherPlus = (Plus) other;
		return (this.left.equals(otherPlus.left) && this.right.equals(otherPlus.right));
	}

	public int hashCode() {
		int result = 43;
		result = 37 * result + left.hashCode();
		result = 37 * result + right.hashCode();
		return result;
	}

}
