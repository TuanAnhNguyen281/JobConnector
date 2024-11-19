package model;

public class userAccount {

    private int userid;
    private String username;
    private int roleid;
    private String email;
    private String password;
    private String fullname;
    private int packageID;

    public userAccount() {
    }

    public userAccount(String username, int roleid, String email, String password, String fullname) {
        this.username = username;
        this.roleid = roleid;
        this.email = email;
        this.password = password;
        this.fullname = fullname;
    }

    public userAccount(String username, int roleid, String email, String password, String fullname, int packageID) {
        this.username = username;
        this.roleid = roleid;
        this.email = email;
        this.password = password;
        this.fullname = fullname;
        this.packageID = packageID;
    }
    
    

    public userAccount(int userid, String username, int roleid, String email, String password, String fullname) {
        this.userid = userid;
        this.username = username;
        this.roleid = roleid;
        this.email = email;
        this.password = password;
        this.fullname = fullname;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getRoleid() {
        return roleid;
    }

    public void setRoleid(int roleid) {
        this.roleid = roleid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public int getPackageID() {
        return packageID;
    }

    public void setPackageID(int packageID) {
        this.packageID = packageID;
    }
    
    

    @Override
    public String toString() {
        return "userAccount{" + "userid=" + userid + ", username=" + username + ", roleid=" + roleid + ", email=" + email + ", password=" + password + ", fullname=" + fullname + '}';
    }

}
