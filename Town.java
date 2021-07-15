public class Town
{
    private String name;
    private int postCode;
    public Town()
    {
        name = "Cape Town";
        postCode = 1345;
    }
    public Town(String name, int postCode)
    {
        this.name = name;
        this.postCode = postCode;
    }
    public String getName()
    {
        return name;
    }
    public int getPostCode()
    {
        return postCode;
    }
    public String toString()
    {
        return getName() + " (" + getPostCode() + ")";
    }
}