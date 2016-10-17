package expressions;
import java.util.*;
//the definition of the number
class Number implements Expression{
	
	private final double num;
	
	
	private void checkRep(){
		assert num >= 0;
	}
	
	public Number(double n){
		num = n;
		checkRep();
	};
	
    public List<Expression> terms(){
        List <Expression> terms = new ArrayList<>();
        terms.add(this);
        return terms;
    };
    

    public List<Expression> factors(){
        List <Expression> factors = new ArrayList<>();
        factors.add(this);
        return factors;
    };
    //jia zhushi 
	
	

	public String toString(){
		return num+"";
		}
	
	
	public boolean equals(Object other){
		if(!(other instanceof Number)){return false;}
		Number otherNum = (Number)other;
		return num == otherNum.num;
	}
	
	//Uses d(constant)/dt = 0
	public Expression differentiate(Expression var){
	    return new Number(0);
	}

	public Expression simplify(Map<Expression,Double> values){
	    return this;
	}
	
    public double numTerm(){
        return num;
    }
    
    public double numFactor(){
        return num;
    }
    
    public Expression varTerm(){
        return new Number(0);
    }
    
    public Expression varFactor(){
        return new Number(1);
    }
	
	public int hashCode(){
	    int result = 43;
	    long c = Double.doubleToLongBits(num);
	    return 37*result + (int)(c&(c>>>32));
	}

}


