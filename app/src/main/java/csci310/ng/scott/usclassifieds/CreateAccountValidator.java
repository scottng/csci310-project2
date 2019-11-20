package csci310.ng.scott.usclassifieds;

public class CreateAccountValidator {
    String errorMessage;

    public CreateAccountValidator() {
        errorMessage = "";
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public boolean emptyName(String s) {
        if(s.trim().length() == 0) {
            errorMessage = "Please enter full name.";
            return true;
        }
        else return false;
    }

    public boolean emptyEmail(String s) {
        if(s.trim().length() == 0) {
            errorMessage = "Please enter an email.";
            return true;
        }
        else return false;
    }

    public boolean nonvalidEmail(String s) {
        if(!s.matches(".*@usc\\.edu")) {
            errorMessage = "Please enter a USC email.";
            return true;
        }
        else return false;
    }

    public boolean emptyPassword(String s) {
        if (s.trim().length() == 0) {
            errorMessage = "Please enter a password.";
            return true;
        }
        else return false;
    }

    public boolean nonvalidPassword(String s) {
        if (s.length() < 6) {
            errorMessage = "Password must be longer than 6 characters.";
            return true;
        }
        else return false;
    }

    public boolean emptyConfirmPassword(String s) {
        if (s.trim().length() == 0) {
            errorMessage = "Please confirm password.";
            return true;
        }
        else return false;
    }

    public boolean nonmatchingPasswords(String a, String b) {
        if(!a.equals(b)) {
            errorMessage = "Passwords do not match.";
            return true;
        }
        else return false;
    }

    public boolean invalidInput(String fullName, String password, String email, String confirmPassword) {
        if (emptyName(fullName)) return true;
        if (emptyEmail(email)) return true;
        if (nonvalidEmail(email)) return true;
        if (emptyPassword(password)) return true;
        if (nonvalidPassword(password)) return true;
        if (emptyConfirmPassword(password)) return true;
        if (nonmatchingPasswords(password, confirmPassword)) return true;
        return false;
    }

}
