package by.iTechArt.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/*Here are the restrictions we want to apply:
A-Z characters are permitted
a-z characters are permitted
0-9 digits are permitted
Underscore(_), dash(-), and dot(.) are permitted
Other characters are not permitted*/

public class EmailValidator implements Validator {
    private static final String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private Pattern p = Pattern.compile(regex);
    public EmailValidator(){super();}
    @Override
    public boolean isValid(String email) {
        Matcher m = p.matcher(email);
        return m.find();
    }
}
