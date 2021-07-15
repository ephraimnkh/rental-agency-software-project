import java.util.ArrayList;
public class House
{
    private String street;
    private Town town;
    private final int PROPERTY_CODE;
    private Person owner;
    private ArrayList<Person> tenants = new ArrayList<Person>();
    private PaySchedule p;
    public PaySchedule copyP;
    private static int housesMade;
    public boolean leaseMade = false;
    public House()
    {
        street = "Newman";
        town = new Town("Durban",1987);
        owner = new Person("Ruben", "0789456123");
        p = new PaySchedule(0,0);
        owner.numberOfOwnedProperties++;
        housesMade++;
        PROPERTY_CODE = housesMade;
    }
    public House(String street, Town town, Person owner)
    {
        this.street = street;
        this.town = town;
        this.owner = owner;
        p = new PaySchedule(0,0);
        owner.numberOfOwnedProperties++;
        housesMade++;
        PROPERTY_CODE = housesMade;
    }
    public String toString()
    {
        return PROPERTY_CODE + ". House at " + getStreet() + " street " + town.getName();
    }
    public void establishLease(Person tenant, int rent, int weeksOfLease)
    {
        if (tenants.size() > 0)
        {
            int i = 0;
            while (i != tenants.size())
                tenants.remove(i);
        }
        tenants.add(tenant);
        p = new PaySchedule(rent, weeksOfLease);
        copyP = new PaySchedule(rent, weeksOfLease);
        leaseMade = true;
    }
    public boolean addTenant(Person tenant)
    {
        boolean success = false;
        if (tenants.size() > 0 && tenants.size() < 3)
        {
            tenants.add(tenant);
            success = true;
        }
        return success;
    }
    public boolean payRent()
    {
        if(leaseMade && p.getNumberOfWeeksForRent() > 0)
        {
            copyP.makePayment();
            return p.makePayment();
        }
        else
            return false;
    }
    public String getStreet()
    {
        return street;
    }
    public Town getTown()
    {
        Town copy = new Town(town.getName(), town.getPostCode());
        return copy;
    }
    public int getPropertyCode()
    {
        return PROPERTY_CODE;
    }
    public Person getOwner()
    {
        Person copy = new Person(owner.getName(), owner.getPhoneNumber());
        return copy;
    }
    public ArrayList<Person> getTenant()
    {
        ArrayList<Person> copy = (ArrayList) tenants.clone();
        return copy;
    }
}