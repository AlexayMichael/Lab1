package expressions;
import java.util.*;
public interface Expression {
	//Expression = Number(n: int) 
	
	//+ Plus(left: Expression, right: Expression) 
	//+ Multiply (left: Expression, right: Expression)
    
    
  
    public static Expression parse(String input){
        return Parser.parse(input);
  
    }
    
    public static Expression make(double num){
        return new Number(num);
    }
    
    public static Expression make(String var){
        return new Variable(var);
    }
    
    public static Expression sum(Expression left, Expression right){
        Number zero = new Number(0);
        if(left.equals(zero)){
            return right;
        }
        if(right.equals(zero)){
            return left;
        }
        return new Plus(left,right);
    }
    

   public static Expression times(Expression left, Expression right){
       Number zero = new Number(0);
       Number one = new Number(1);
       if(left.equals(zero)||right.equals(zero)){
           return new Number(0);
       }
       if(left.equals(one)){
           return right;
       }
       if(right.equals(one)){
           return left;
       }
       return new Multiply(left,right);
   }
    
   public Expression simplify(Map<Expression,Double> values);
    
    public double numTerm();
    
    public double numFactor();
    
    public Expression varTerm();

    public Expression varFactor();
    
    
    public Expression differentiate(Expression var);
    

    
    
    public List<Expression> terms();
    
    public List<Expression> factors();

	@Override public String toString();
	

	@Override public boolean equals(Object other);
	
	@Override public int hashCode();
}
