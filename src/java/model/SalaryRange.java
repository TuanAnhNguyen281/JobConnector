/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author tuananh
 */
public class SalaryRange {
    int salaryRangeID;
    String salaryDescription;

    public SalaryRange(int salaryRangeID, String salaryDescription) {
        this.salaryRangeID = salaryRangeID;
        this.salaryDescription = salaryDescription;
    }

    public int getSalaryRangeID() {
        return salaryRangeID;
    }

    public void setSalaryRangeID(int salaryRangeID) {
        this.salaryRangeID = salaryRangeID;
    }

    public String getSalaryDescription() {
        return salaryDescription;
    }

    public void setSalaryDescription(String salaryDescription) {
        this.salaryDescription = salaryDescription;
    }
    
    
}
