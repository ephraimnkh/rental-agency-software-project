public class PaySchedule
{
    private int weeklyRentDue;
    private int numberOfWeeksForRent;
    public PaySchedule()
    {
        weeklyRentDue = 4800;
        numberOfWeeksForRent = 5;
    }
    public PaySchedule(int weeklyRentDue, int numberOfWeeksForRent)
    {
        this.weeklyRentDue = weeklyRentDue;
        this.numberOfWeeksForRent = numberOfWeeksForRent;
    }
    public int getWeeklyRent()
    {
        return weeklyRentDue;
    }
    public int getNumberOfWeeksForRent()
    {
        return numberOfWeeksForRent;
    }
    public boolean makePayment()
    {
        boolean success = false;
        if(getNumberOfWeeksForRent() > 0)
        {
            numberOfWeeksForRent--;
            success = true;
        }
        else
            success = false;
        return success;
    }
}