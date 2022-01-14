package by.iTechArt.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PassportIdValidator implements Validator {
    private Pattern p = Pattern.compile("MP\\d{7}$");

    public PassportIdValidator() {
        super();
    }

    @Override
    public boolean isValid(String name) {
        Matcher m = p.matcher(name);
        return m.find();
    }

}
