package view.backing;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;

import org.apache.myfaces.trinidad.model.DateListProvider;


public class datebean {
    private RichCommandButton a;
    private Date minDate = new Date();
    private RichOutputText aaaa;

    public datebean() {
    }
    
    
    
    
    

  

      public Date getMaxaDateVal() {
          Calendar cal = Calendar.getInstance();
          cal.setTime(new Date());
          cal.add(Calendar.DATE, 1); 
          return cal.getTime();

      }

    
    
    
    
    
    
    
    
    
    
    public String getCurrentSystemDate() {
      /*try {
      Calendar now = Calendar.getInstance();
      java.util.Date date = now.getTime();
      DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
      String currentDate = formatter.format(date);
      Date a=formatter.parse(currentDate);
      } catch (Exception e) {
      return null;
      }*/
      DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
      Date todaysDate = new Date();
          try
                 {
          String testDateString = df.format(todaysDate);
              return testDateString;
                 }
                 catch (Exception ex ){
                    System.out.println(ex);
                 }
          
          
          
          
  return null;        
          }
        
        
        
        
        
      
    
    public Date getMinDatee() {
        try {
                    Calendar cal = Calendar.getInstance();
        java.util.Date date = cal.getTime();
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    String currentDate = formatter.format(date);
        minDate = formatter.parse(currentDate);
       
        return formatter.parse(currentDate);
           
                } catch (Exception e) {
        return null;
                }
        
       
            }
        
    
        
        
    

    public Date getMaxDateVal() {
    Calendar cal = Calendar.getInstance();
    cal.setTime(new Date());
    cal.add(Calendar.DATE, 1); 
    return cal.getTime();

    }
    
    public oracle.jbo.domain.Date getCurrentDate()  {
        oracle.jbo.domain.Date cdate=new oracle.jbo.domain.Date(new java.sql.Date(new java.util.Date().getTime()));
        return cdate;  
    }

    public void setAaaa(RichOutputText aaaa) {
        this.aaaa = aaaa;
    }

    public RichOutputText getAaaa() {
        return aaaa;
    }

    public void affiche(ActionEvent actionEvent) {
        FacesMessage mesg = new FacesMessage("Bad Login....Please try again"+getAaaa());
        FacesContext.getCurrentInstance().addMessage(null,mesg);
    }


    public class HolidayBean implements DateListProvider {
        public HolidayBean() {
        }

        @Override
        public List<Date> getDateList(FacesContext facesContext, Calendar calendar, Date date, Date date2) {
            List<java.util.Date> holiDay = new ArrayList();
            holiDay.add(new Date("27-July-2014"));
            holiDay.add(new Date("29-July-2014"));
            holiDay.add(new Date("10-Aug-2014"));
            holiDay.add(new Date("25-Dec-2014"));
            holiDay.add(new Date("01-Jan-2015"));
            holiDay.add(new Date("22-Oct-2014"));
            holiDay.add(new Date("23-Oct-2014"));
            holiDay.add(new Date("24-Oct-2014"));

            return holiDay;
            //return Collections.emptyList();
        }
    }

    
        private HashMap fieldVal = new HashMap();

           public void setFieldVal(HashMap fieldVal) {
               this.fieldVal = fieldVal;
           }

           public HashMap getFieldVal() {
               return fieldVal;
           }


    
    public void disable(ActionEvent actionEvent) {
    

       
       
       
            
        /*RichCommandButton b=(RichCommandButton)actionEvent.getComponent().getAttributes().get("butt");*/ 
        if ((String)actionEvent.getComponent().getAttributes().get("etat")=="En attente"){
             getA().setDisabled(false);
            AdfFacesContext.getCurrentInstance().addPartialTarget(getA());
        }
        else{
           getA().setDisabled(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(getA());
            }
    
       getA().setDisabled(true);
       
    }

    public void setA(RichCommandButton a) {
        this.a = a;
    }

    public RichCommandButton getA() {
        return a;
    }
    
    
    
    
    
    
    
    public List<Date> getDateList(FacesContext facesContext, Calendar calendar,
                                     Date date, Date date1) {
           List holidays = new ArrayList<Date>();
           try{
               SimpleDateFormat simpleDate = new SimpleDateFormat("dd/MM/yyyy");
               holidays.add(simpleDate.parse("01/01/2015"));
               holidays.add(simpleDate.parse("01/02/2015"));
               holidays.add(simpleDate.parse("01/03/2015"));
               holidays.add(simpleDate.parse("01/04/2015"));
               holidays.add(simpleDate.parse("01/05/2015"));
               holidays.add(simpleDate.parse("01/06/2015"));
               holidays.add(simpleDate.parse("01/07/2015"));
               holidays.add(simpleDate.parse("19/07/2015"));
               holidays.add(simpleDate.parse("02/03/2015"));
               holidays.add(simpleDate.parse("06/03/2015"));
               holidays.add(simpleDate.parse("05/03/2015"));
           }
           catch(Exception e){
               e.printStackTrace();
               return Collections.emptyList();
           }
           return holidays;
       }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
        public Date getDateMin() {
               
                 return getCurrentDate(0, 0, 0);
             }

             public java.util.Date getCurrentDatee() {
                 return getCurrentDate(23, 59, 59);
             }

             public java.util.Date getCurrentDate(int hours, int minutes, int seconds) {
                 Calendar cal = Calendar.getInstance();
                 cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), hours, minutes, seconds);
                 return cal.getTime();

             }
    
    
        /*<af:validateDateTimeRange minimum="#{datebean.dateMin}"
                                                           hintMinimum="Date can not be less than current date"/>*/
    
    
    
    
    
            
    
    
    
    
    
    
    
    
    
    
      
    
    
    
    
    
    
    
    
    
             
    
    
    
    
        public void enableRemoveAttachment(ActionEvent actionEvent) {

            try{

                      BindingContext bc = BindingContext.getCurrent();

                      DCBindingContainer bindings = (DCBindingContainer)bc.getCurrentBindingsEntry();

                    // Get the Attachments iteraor

                    DCIteratorBinding glIter = bindings.findIteratorBinding("DemandeDeCongeView1");

                      if (glIter != null && glIter.getRowSetIterator().getRowCount() > 0) {

                          RowSetIterator rsi = glIter.getViewObject().createRowSetIterator(null);

                          Row glRow;

                          while (rsi.next() != null) {

                              glRow = rsi.getCurrentRow();

                              // Set “rendered” indicators

                              if (actionEvent.getComponent().getAttributes().get("etat")=="En attente"){

                                          glRow.setAttribute("IsRemoveAttachmentEnabled", "true");

                              }else{

                                glRow.setAttribute("IsRemoveAttachmentEnabled", "false");

                              }

                          }

                          rsi.closeRowSetIterator();

                      }

            }catch(Exception e){

              e.printStackTrace();

              throw new RuntimeException(e);

            }

        }
    
    

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    
    
    
    
    
    
    
    

}

