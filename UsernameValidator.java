import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UsernameValidator{

    private final Pattern pattern;

    private Matcher matcher;

//declare a string pattern for username.

    private static final String USERNAME_PATTERN = "^[a-z0-9_-]{3,15}$";

    public UsernameValidator(){

        pattern = Pattern.compile(USERNAME_PATTERN);

    }

    public boolean validateUserName(final String username){

        matcher = pattern.matcher(username);

        return matcher.matches();

    }

}