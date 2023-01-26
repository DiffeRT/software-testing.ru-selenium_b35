package app;

public class Customer {
    //Required
    public String firstName;
    public String lastName;
    public String address1;
    public String postCode;
    public String city;
    public String phone;
    public String email;
    public String password;
    public String country;
    //Optional relative
    public String state;
    //Optional
    public String taxID;
    public String company;
    public String address2;

    public void setRandomNameAndEmail(String firstName, String lastName) {
        this.firstName = firstName + (int)(Math.random() * 1000);
        this.lastName = lastName + (int)(Math.random() * 1000);
        this.email = this.firstName.toLowerCase() + "." + this.lastName.toLowerCase() + "@mail.com";
    }

    public Customer setRandomCustomer() {
        setRandomNameAndEmail("James", "Bond");
        address1 = "101 Oak Creek Blvd";
        postCode = "95066";
        city = "Scotts Valley";
        phone = "+18314327654";
        password = "user123";
        country = "United States";
        state = "California";
        return this;
    }

    public String getUserFullName() {
        return firstName + " " + lastName;
    }
}
