import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MailValidator {
    private final Pattern pattern;

    private Matcher matcher;

    private static final String EMAIL_PATTERN =

            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"

                    + "company"+"*(\\.[A-Za-z]{2,})$";

    public MailValidator() {

        pattern = Pattern.compile(EMAIL_PATTERN);

    }

    public boolean validateMail(final String checkedMail) {

        matcher = pattern.matcher(checkedMail);

        return matcher.matches();

    }
}
