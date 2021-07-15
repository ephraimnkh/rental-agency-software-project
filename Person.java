public class Person
{
    private String name;
    private String phoneNumber;
    public int numberOfOwnedProperties;
    public Person()
    {
        name = "Danny";
        setPhoneNumber("0123789456");
    }
    public Person(String name, String phoneNumber)
    {
        this.name = name;
        setPhoneNumber(phoneNumber);
    }
    public void setPhoneNumber(String phoneNumber)
    {
        if(phoneNumber.length() == 10)
            this.phoneNumber = phoneNumber;
        else
            System.out.println("You must enter a 10 digit phone number");
    }
    public String getName()
    {
        return name;
    }
    public String getPhoneNumber()
    {
        return phoneNumber;
    }
    public String toString()
    {
        return name;
    }
}