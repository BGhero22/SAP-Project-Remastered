public  abstract class Users {
    private String email;
    private UserSpec userSpec;

    public Users(String email, UserSpec userSpec) {
        this.email = email;
        this.userSpec = userSpec;
    }

    public UserSpec getUserSpec() {
        return userSpec;
    }

    public void setUserSpec(UserSpec userSpec) {
        this.userSpec = userSpec;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}



