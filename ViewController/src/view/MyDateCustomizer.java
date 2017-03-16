package view;

import java.util.HashMap;
import java.util.Locale;
import java.util.TimeZone;

import oracle.adf.view.rich.util.DateCustomizer;

import oracle.jbo.domain.Date;


public class MyDateCustomizer extends DateCustomizer{
public String format(Date date, String key, Locale locale, TimeZone tz)
{
// For illustrative purpose
// Hashmap holding the holiday list
HashMap holidays = new HashMap();
holidays.put(new Date("25-Dec-2015"), "Christmas");
holidays.put(new Date("01-Jan-2015"), "New Year");

if ("af|calendar::month-grid-cell-header-misc".equals(key))
{
return holidays.get(date)!=null?holidays.get(date).toString():null;

} 

return null;

}

    @Override
    public String format(java.util.Date date, String string, Locale locale, TimeZone timeZone) {
        return null;
    }
}
