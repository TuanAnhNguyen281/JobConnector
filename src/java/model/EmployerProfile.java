package model;


public class EmployerProfile {
  
    int employerID;
    int userID;
    int taxNumber;
    String companyName;
    String address;
    String employerEmail;
    String employerPhone;
    int industryID;
    int sizeID;
    String companyDescription;
    String logo;
    int checkID;
    String comment;
    String companySize;
    String companyIndustry;
    String companystatus;

    public EmployerProfile() {
    }

    public EmployerProfile(int userID, int taxNumber, String companyName, String address, String employerEmail,String employerPhone, int industryID, int sizeID, String companyDescription, String logo, int checkID, String comment) {
        this.userID = userID;
        this.taxNumber = taxNumber;
        this.companyName = companyName;
        this.address = address;
        this.employerEmail = employerEmail;
        this.employerPhone = employerPhone;
        this.industryID = industryID;
        this.sizeID = sizeID;
        this.companyDescription = companyDescription;
        this.logo = logo;
        this.checkID = checkID;
        this.comment = comment;
    }
    
    

    public int getEmployerID() {
        return employerID;
    }

    public void setEmployerID(int employerID) {
        this.employerID = employerID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(int taxNumber) {
        this.taxNumber = taxNumber;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getIndustryID() {
        return industryID;
    }

    public void setIndustryID(int industryID) {
        this.industryID = industryID;
    }

    public int getSizeID() {
        return sizeID;
    }

    public void setSizeID(int sizeID) {
        this.sizeID = sizeID;
    }

    public String getCompanyDescription() {
        return companyDescription;
    }

    public void setCompanyDescription(String companyDescription) {
        this.companyDescription = companyDescription;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public int getCheckID() {
        return checkID;
    }

    public void setCheckID(int checkID) {
        this.checkID = checkID;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getEmployerEmail() {
        return employerEmail;
    }

    public void setEmployerEmail(String employerEmail) {
        this.employerEmail = employerEmail;
    }

    public String getEmployerPhone() {
        return employerPhone;
    }

    public void setEmployerPhone(String employerPhone) {
        this.employerPhone = employerPhone;
    }


    public String getCompanySize() {
        return companySize;
    }

    public void setCompanySize(String companySize) {
        this.companySize = companySize;
    }

    public String getCompanyIndustry() {
        return companyIndustry;
    }

    public void setCompanyIndustry(String companyIndustry) {
        this.companyIndustry = companyIndustry;
    }

    public String getCompanystatus() {
        return companystatus;
    }

    public void setCompanystatus(String companystatus) {
        this.companystatus = companystatus;
    }

    

    
    
}
