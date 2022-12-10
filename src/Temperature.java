import java.text.DecimalFormat;
public class Temperature
{
    private static double highTemp;
    private static double lowTemp;
    private static String tempScale;
    private boolean didSetFirstTemps = false;
    private static double highestTrackedTempF = 0;
    private static double lowestTrackedTempF = 0;
    private static boolean sawFreezing=false;

    // Precondition: scale must be: "F" or "C"; anything else will default to "F"
    private static DecimalFormat d = new DecimalFormat("#.#");
    public static final int FREEZING_TEMP_F = 32;
    public Temperature(double high, double low, String scale)
    {
        highTemp = high;
        lowTemp = low;

        if (scale.equals("F") || scale.equals("C"))
        {
            tempScale = scale;
        }
        else
        {
            tempScale = "F";
        }
        if (!didSetFirstTemps){
            didSetFirstTemps=true;
            highestTrackedTempF=high;
            lowestTrackedTempF=low;
        }
    }

    public double getHighTemp() {
        return highTemp;
    }
    public double getLowTemp() {
        return lowTemp;
    }
    public double getLowestTrackedTempF() {
        return lowestTrackedTempF;
    }
    public double getHighestTrackedTempF() {
        return highestTrackedTempF;
    }

    public static boolean isSawFreezing() {
        return sawFreezing;
    }

    public boolean isBelowFreezing(){
        minimumOrMaximum();
        if (lowTemp<FREEZING_TEMP_F||highTemp<FREEZING_TEMP_F){
            sawFreezing();
            return true;
        }
        return false;
    }
    public static void sawFreezing(){
        if (lowTemp<FREEZING_TEMP_F||highTemp<FREEZING_TEMP_F){
            sawFreezing=true;
        }
    }
    public String getTempScale() {
        return tempScale;
    }

    public static void minimumOrMaximum(){
        if (tempScale.equals("F")){
            if (highTemp>highestTrackedTempF){
                highestTrackedTempF=highTemp;
            }
            if (lowTemp<lowestTrackedTempF){
                lowestTrackedTempF = lowTemp;
            }
        }
        if(tempScale.equals("C")){
            highTemp=convertCtoF(highTemp);
            lowTemp=convertCtoF(lowTemp);
            if (highTemp>highestTrackedTempF){
                highestTrackedTempF=highTemp;
            }
            if (lowTemp<lowestTrackedTempF){
                lowestTrackedTempF = lowTemp;
            }
        }
    }

    // 1. Add your two static methods here:
    public static double convertCtoF(double temp){
        return (temp*1.8)+32;
    }

    public static double convertFtoC(double temp){
        return (temp-32)/1.8;
    }


    // 2. Add your two instance methods here:
    public void changeToC(){
        if(tempScale.equals("F")){
            highTemp=convertFtoC(highTemp);
            lowTemp=convertFtoC(lowTemp);
            tempScale="C";
        }
    }
    public void changeToF(){
        if(tempScale.equals("C")){
            highTemp=convertCtoF(highTemp);
            lowTemp=convertCtoF(lowTemp);
            tempScale="F";
        }
    }


    // 3. Add your private static helper rounding "utility" method here:
    private static String roundToNearestTenth(double num){
        return d.format(num);
    }

    // 4. Complete the toString method below (using your static helper method)
    //    so it returns a String that prints like:

    //    High Temperature: 67.4 F (or C)
    //    Low Temperature: 58.3 F (or C)

    public String toString()
    {
        String returnStuff = "";
        returnStuff = "High Temperature: "+roundToNearestTenth(highTemp)+tempScale+"\nLow Temperature: "+roundToNearestTenth(lowTemp)+tempScale;
        return returnStuff;
    }
}
