package expressions;
import java.util.Map;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.Exception;
public class Commands {
    
    public static String differentiate(Expression exp, String v){
        assert v.matches("[a-zA-Z]+");
        Expression var = Expression.parse(v);
        return exp.differentiate(var).toString();
    }
    
    public static String simplify(Expression exp, String vars){

        Map<Expression,Double> values = new HashMap<>();
        String pattern = "[a-zA-Z]+="
                + "([0-9]+(\\.[0-9]*)?|\\.[0-9]+)((e|E)(\\-|\\+)?[0-9]+)?";
        if(vars.length()>0){
            assert vars.matches(pattern+"(\\s"+pattern+")*");
               String[] varMap = vars.split("\\s");
                for(String varPair:varMap){
                    String[] pair = varPair.split("=");
                    values.put(Expression.parse(pair[0]),Double.valueOf(pair[1]));
                }
        }
        return exp.simplify(values).toString();
<<<<<<< HEAD
     //zaijiayige
=======
     //you yao jia zhushi
>>>>>>> C4
    }
    
}
