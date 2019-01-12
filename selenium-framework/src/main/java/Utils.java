import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utils {

    public static double parseStringToDouble(String value){
        return Double.valueOf(value);
    }

    public static String getDate(int noOfDays){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());

        if(noOfDays != 0){
            c.add(Calendar.DATE, noOfDays);
        }
        String output = sdf.format(c.getTime());
        return output;
    }
}
