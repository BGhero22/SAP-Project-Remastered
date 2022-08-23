import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator {
    private final Pattern pattern;

    private Matcher matcher;



    private static final String Password_PATTERN = "^[A-Za-z0-9_@-]{6,64}$";

    public PasswordValidator(){

        pattern = Pattern.compile(Password_PATTERN);

    }

    public boolean validatePassword(final String password){

        matcher = pattern.matcher(password);

        return matcher.matches();

    }
}
