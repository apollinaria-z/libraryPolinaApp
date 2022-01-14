package by.iTechArt.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
/*Here are the restrictions we want to apply:
yyyy-MM-dd
Other characters are not permitted*/

public class BirthValidator implements Validator {
    public BirthValidator(){super();}
    @Override
    public boolean isValid(String birthday) {
        if (!("".equals(birthday))) {
            try {
                SimpleDateFormat fdate = new SimpleDateFormat("yyyy-MM-dd");
                fdate.setLenient(false);
                fdate.parse(birthday);
            } catch (ParseException e) {
                return false;
            }
            return true;
        } else  return false;
    }
}
