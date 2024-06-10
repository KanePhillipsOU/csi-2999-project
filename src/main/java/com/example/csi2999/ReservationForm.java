package com.example.csi2999;

public class ReservationForm {
    
    private String firstName;
    private String lastName;
    private String email;
    private long phoneNumber;
    private boolean agreedToTerms;
    private int selectedSiteId;
    private String startDate;
    private String endDate;
    

    // Getter and setter for firstName
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // Getter and setter for lastName
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // Getter and setter for email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Getter and setter for phoneNumber
    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // Getter and setter for agreedToTerms
    public boolean isAgreedToTerms() {
        return agreedToTerms;
    }

    public void setAgreedToTerms(boolean agreedToTerms) {
        this.agreedToTerms = agreedToTerms;
    }

    // Getter and setter for selectedSiteId
    public int getSelectedSiteId() {
        return selectedSiteId;
    }

    public void setSelectedSiteId(int selectedSiteId) {
        this.selectedSiteId = selectedSiteId;
    }

    // Getter and setter for startDate
    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    // Getter and setter for endDate
    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}