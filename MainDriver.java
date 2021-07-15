import java.util.Scanner;
import java.util.ArrayList;
public class MainDriver
{    
    //ArrayList declarations at class level so it can be referenced by all methods
    private static ArrayList<House> houses;
    private static ArrayList<Person> landlords;

    //Town declarations at class level so it can be referenced by all methods
    private static Town t1;
    private static Town t2;
    private static Town t3;

    //Class level scanner declaration and intialization made so all methods can use this one scanner instead declaring many scanners
    private static Scanner scan = new Scanner(System.in);
    public static void main(String[] args)
    {
        t1 = new Town("Krugersdorp", 1739);
        t2 = new Town("Sandton", 1653);
        t3 = new Town("Germiston", 1283);

        Person p1 = new Person("Ephraim", "0987654321");
        Person p2 = new Person("Zoe", "0749540584");
        Person p3 = new Person("Quinton", "0123456788");
        Person p4 = new Person("Faith", "0258147369");
        Person p5 = new Person("Thamo", "0123789456");

        House h1 = new House("Wolfberg", t1, p1);
        House h2 = new House("Turner", t2, p2);
        House h3 = new House("Burger", t3, p3);
        House h4 = new House("Vorster", t1, p5);
        House h5 = new House("Hupton", t2, p4);

        houses = new ArrayList<House>();     
        houses.add(h1);
        houses.add(h2);
        houses.add(h3);
        houses.add(h4);
        houses.add(h5);

        landlords = new ArrayList<Person>();
        landlords.add(p1);
        landlords.add(p2);
        landlords.add(p3);
        landlords.add(p4);
        landlords.add(p5);

        boolean process = true;
        while (process)
        {
            System.out.println("--------------------------------------------------");
            System.out.println("Select an option below by typing the option number: ");
            System.out.println("1. Create House");
            System.out.println("2. Create Lease");
            System.out.println("3. Process Rent");
            System.out.println("4. Report on Property");
            System.out.println("5. Add additional tenant to Existing Lease");
            System.out.println("6. Exit");
            String input = scan.nextLine();
            switch (input)
            {
                case "1":       createHouse();
                                break;
                case "2":       createLease();
                                break;
                case "3":       if (processRent())//If rent processed successfully processRent() returns true displaying the success message below else if false method displays failure
                                    System.out.println("Payment was successful");
                                break;
                case "4":       reportOnProperty();
                                break;
                case "5":       addToLease();
                                break;
                case "6":       process = false;
                                System.out.println("\n" + "exiting...");
                                System.exit(0);
                                break;
                default:        System.out.println("Pick an option from above!");
            }
        }
    }

    public static void createHouse()
    {
        //Values used to make house
        Person owner = null;
        Town town = null;
        String street = "";
        boolean process = true;
        int stop = 0;
        while (process)
        {
            System.out.println("--------------------------------------------------");
            System.out.println("Pick a landlord: ");
            for (int i = 0; i < landlords.size(); i++)
            {
                int n = i; n++; //Variable made to display landlords values starting at 1
                System.out.println(n + ". " + landlords.get(i));
            }
            String input = removeWhiteSpace(scan.nextLine());
            if (input.length() != 0 && (Integer.parseInt(input) - 1) < landlords.size() && (Integer.parseInt(input) - 1) >= 0)//Check if something was entered and if it is a element within the ArrayList
            {
                int i = Integer.parseInt(input) - 1; //insure that value provided is subtracted by 1 so as to reference correct ArrayList element
                if (landlords.get(i).numberOfOwnedProperties < 5)
                {
                    owner = landlords.get(i);
                    break;
                }
                else
                {
                    System.out.println("Owner already has 5 houses, pick another");
                    return;
                }
            }
            else
                System.out.println("Pick an option from above!");
        }
        while (town == null)
        {
            System.out.println("--------------------------------------------------");
            System.out.println("Pick a Town: ");
            System.out.println("1. " + t1.getName());
            System.out.println("2. " + t2.getName());
            System.out.println("3. " + t3.getName());
            String input = scan.nextLine();
            switch (input)
            {
                case "1":   town = t1;
                break;
                case "2":   town = t2;
                break;
                case "3":   town = t3;
                break;
                default:    System.out.println("Pick an option from above!");
            }
        }
        while (process)
        {
            System.out.println("--------------------------------------------------");
            System.out.println("Enter a street name for your house: ");
            String input = removeWhiteSpace(scan.nextLine());
            if (input.length() > 0)
            {
                street = input;
                process = false;
            }
            else 
                System.out.println("Please enter a street name for your house");
        }
        System.out.println("--------------------------------------------------");
        House house = new House(street, town, owner);
        System.out.println("House successfully created");
        System.out.println("--------------------------------------------------");
        System.out.println("Created house details: ");               
        String output = "House property code: " + house.getPropertyCode() + "\n";
        output += "House address: " + house.getStreet() + " Street " + house.getTown() + "\n";
        output += "The owner of this house is: " + house.getOwner();
        System.out.println(output);
        houses.add(house); 
    }

    public static void createLease()
    {
        int i = 0;
        Person tenant = null;
        String name = "";
        String phoneNumber = "";
        String rent = "";
        String weeks = "";
        boolean process = true;
        while (process)
        {
            System.out.println("--------------------------------------------------");
            System.out.println("Select the house below that you would like to lease: ");
            for(int j = 0; j < houses.size(); j++)
            {
                System.out.println(houses.get(j).getPropertyCode() + ". " + "House at " + houses.get(j).getStreet() + " street " + houses.get(j).getTown());
            }
            String input = removeWhiteSpace(scan.nextLine());   
            if (input.length() > 0 && (Integer.parseInt(input) - 1) < houses.size() && (Integer.parseInt(input) - 1) >= 0) //Check if something was entered and if it is a element within the ArrayList
            {
                i = Integer.parseInt(input) - 1; //insure that value provided is subtracted by 1 so as to reference correct ArrayList element
                process = false;
            }
            else
                System.out.println("Pick an option from above!");
        } 
        System.out.println("--------------------------------------------------");
        System.out.println("Please enter tenant details below: ");
        do
        {
            System.out.println("Tenant name?");
            String input = scan.nextLine();
            input = removeWhiteSpace(input);
            if (input.length() > 0)
                name = input;
            else
                System.out.println("Please enter a name");
        } while (name.length() == 0);
        do
        {
            System.out.println("Tenant phone number?");
            String input = removeWhiteSpace(scan.nextLine());
            if (isADigitString(input))
            {
                phoneNumber = input;
                tenant = new Person(name, phoneNumber);
            }
        } while (phoneNumber.length() != 10);
        do
        {
            System.out.print("How much rent will the tenant pay per week in rands?\nR");
            String input = removeWhiteSpace(scan.nextLine());
            if (input.length() > 0 && isADigitString(input) && (Integer.parseInt(input)) > 0)
                rent = input;
            else
                System.out.println("Please enter an amount above 0");
        } while (rent.length() == 0);
        do
        {
            System.out.println("How many weeks is the lease for?");
            String input = removeWhiteSpace(scan.nextLine());
            if (input.length() > 0 && isADigitString(input) && (Integer.parseInt(input)) > 0)
                weeks = input;
            else
                System.out.println("Please enter a number of weeks above 0");
        } while (weeks.length() == 0);
        houses.get(i).establishLease(tenant, Integer.parseInt(rent), Integer.parseInt(weeks));
        System.out.println("\nLease establishment successful");
        System.out.println("--------------------------------------------------");
        System.out.println("Tentant details: \n  Name: " + tenant + "   Phone Number: " + tenant.getPhoneNumber() + "\n   Weekly rent: R" + rent + "  Duration of lease: " + weeks + " week(s)");
    } 

    public static boolean processRent()
    {
        boolean process = true;
        boolean success = true;
        String chosenProperty = "";
        House house = null;
        while (process)
        {
            System.out.println("--------------------------------------------------");
            System.out.println("Which house would you like to pay rent for?");
            for(int i = 0; i < houses.size(); i++) 
            {
                System.out.println(houses.get(i).getPropertyCode() + ". " + "House at " + houses.get(i).getStreet() + " street " + houses.get(i).getTown());
            }  
            chosenProperty = removeWhiteSpace(scan.nextLine());
            if (chosenProperty.length() > 0 && (Integer.parseInt(chosenProperty) - 1) < houses.size() && (Integer.parseInt(chosenProperty) - 1) >= 0) //Check if something was entered and if it is a element within the ArrayList
            {
                int i = Integer.parseInt(chosenProperty) - 1; //insure that value provided is subtracted by 1 so as to reference correct ArrayList element
                if (houses.get(i).payRent())
                    process = false;
                else
                {
                    success = false;
                    System.out.println("Payment was unsuccessful as No Lease established");
                    houses.get(i).leaseMade = false;
                    break;
                }
            }

            else
                System.out.println("Please pick an option from above");
        }
        return success;
    }

    public static void reportOnProperty()
    {
        boolean process = true;
        do
        {
            System.out.println("--------------------------------------------------");
            System.out.println("Which house would you like a report on?");
            for(int i = 0; i < houses.size(); i++)
            {
                System.out.println(houses.get(i).getPropertyCode() + ". " + "House at " + houses.get(i).getStreet() + " street " + houses.get(i).getTown());
            }
            String chosen = removeWhiteSpace(scan.nextLine());
            if (chosen.length() > 0 && (Integer.parseInt(chosen) - 1) < houses.size() && (Integer.parseInt(chosen) - 1) >= 0) //Check if something was entered and if it is a element within the ArrayList
            { 
                int i = Integer.parseInt(chosen) - 1; //insure that value provided is subtracted by 1 so as to reference correct ArrayList element
                String output = "--------------------------------------------------\n";
                output += "House property code: " + houses.get(i).getPropertyCode() + "\n";
                output += "House address: " + houses.get(i).getStreet() + " street " + houses.get(i).getTown() + "\n";
                output += "The owner of this house is: " + houses.get(i).getOwner();
                if (houses.get(i).leaseMade && houses.get(i).copyP.getNumberOfWeeksForRent() > 0)
                {
                    for (int j = 0; j < houses.get(i).getTenant().size(); j++)
                    {
                        int k = j; k++;
                        output += "\nHouse tenant no." + k + ": " + houses.get(i).getTenant().get(j);
                    }
                    output += "\nThe rent per week for the tenant(s) is: R" + houses.get(i).copyP.getWeeklyRent() + "\n";
                    output += "Remaining weeks until lease expires is: " + houses.get(i).copyP.getNumberOfWeeksForRent() + " week(s)";
                }
                System.out.println(output);
                process = false;
            }
            else
                System.out.println("Please pick one of the above!");
        }while (process);
    }

    public static void addToLease()
    {
        boolean process = true;
        int noLease = 0;
        String name = "";
        String phoneNumber = "";
        Person tenant = null;
        House propertyOfChoice = null;
        String input = "";
        int i = 0;
        main:
        do
        {    
            for (House h: houses)
            {
                if (h.leaseMade != true || h.copyP.getNumberOfWeeksForRent() == 0) //checks if current house referenced by for loop has a lease or if lease is finished being paid
                {
                    noLease++;
                    if (noLease == houses.size()) //if noLease equals the number of houses in the houses ArrayList it means all houses do not have a lease
                    {
                        System.out.println("No house has a lease, first create a lease");
                        return;
                    }   
                }
            }
            System.out.println("--------------------------------------------------");
            System.out.println("Which house would you like to add an additional tenant to?");
            for(int j = 0; j < houses.size(); j++)
            {
                if(houses.get(j).leaseMade && houses.get(j).copyP.getNumberOfWeeksForRent() > 0)
                    System.out.println(houses.get(j).getPropertyCode() + ". " + "House at " + houses.get(j).getStreet() + " street " + houses.get(j).getTown());
                else
                    noLease = houses.size();
            }  
            input = removeWhiteSpace(scan.nextLine());
            if (input.length() != 0 && (Integer.parseInt(input) - 1) < houses.size() && (Integer.parseInt(input) - 1) >= 0) //Check if something was entered and if it is a element within the ArrayList
            {
                i = Integer.parseInt(input) - 1; //insure that value provided is subtracted by 1 so as to reference correct ArrayList element
                if (houses.get(i).leaseMade)
                {
                    if (houses.get(i).getTenant().size() >= 3) //checks if selected property already has the maximum of 3 tenants allowed
                    {
                        System.out.println("Property already has a maximum of 3 Tenants");
                        return;
                    }
                    process = false;
                }
                else
                    System.out.println("Pick an option from above!");
            }
            else
                System.out.println("Pick an option from above!");
        } while (process);
        System.out.println("--------------------------------------------------");
        System.out.println("Please enter tenant details below: ");
        do
        {
            System.out.println("Tenant name?");
            input = removeWhiteSpace(scan.nextLine());
            if (input.length() > 0)
                name = input;
            else
                System.out.println("Please enter a name");
        } while (name.length() == 0);
        do
        {
            System.out.println("Tenant phone number?");
            input = removeWhiteSpace(scan.nextLine());
            if (isADigitString(input))
            {
                phoneNumber = input;
                tenant = new Person(name, phoneNumber);
            }
        } while (phoneNumber.length() != 10);
        System.out.println("\nTenant addition successful");
        System.out.println("--------------------------------------------------");
        System.out.println("Tentant details: \n  Name: " + tenant + "   Phone Number: " + tenant.getPhoneNumber() + "\n   Weekly rent: R" + houses.get(i).copyP.getWeeklyRent() + "  Duration of lease: " + houses.get(i).copyP.getNumberOfWeeksForRent() + " week(s)");
        houses.get(i).addTenant(tenant);
    }
    //Checker methods
    public static String removeWhiteSpace(String input) //Removes whitespace from full blank String or String with Whitespace in front and behind text
    {
        return input.trim();
    }

    public static boolean isADigitString(String text) //Checks if string entered is fully comprised of digits
    {
        int counter = 0;
        for(int i = 0; i < text.length(); i++)
            if (text.codePointAt(i) >=48 && text.codePointAt(i) <= 57)
                counter++;
        if (counter  == (int) text.length())
            return true;            
        else  
            return false;         
    }
}