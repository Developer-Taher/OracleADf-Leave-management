package view;


public class Calendar {
    public Calendar() {
        super();
    }
    
      
        private MyDateCustomizer holidays = new MyDateCustomizer(); ;



        public void setHolidays(MyDateCustomizer holidays) {
        this.holidays = holidays;
        }

        public MyDateCustomizer getHolidays() {
        return holidays;
        }
        }



   

