package view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.sql.DataSource;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.faces.bi.event.graph.SelectionEvent;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputText;

import oracle.jbo.Row;
import oracle.jbo.uicli.binding.JUCtrlHierBinding;
import oracle.jbo.uicli.binding.JUCtrlHierNodeBinding;

import org.apache.myfaces.trinidad.model.CollectionModel;
import org.apache.myfaces.trinidad.model.DateListProvider;


public class DatePageBean  implements DateListProvider {
    
    
    private String h;
    private RichInputText message;
      private String msg;
    public DatePageBean() {
        super();
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

    public void setH(String h) {
        this.h = h;
    }

    public String getH() {
        return h;
    }
    
    
    
    public static Connection getConnectionDS(String dsName) throws NamingException, SQLException {
        Connection con = null;
        DataSource datasource = null;

        Context initialContext = new InitialContext();
        if (initialContext == null) {
        }
        datasource = (DataSource) initialContext.lookup(dsName);
        if (datasource != null) {
            con = datasource.getConnection();
        } else {
            System.out.println("Failed to Find JDBC DataSource.");
        }
        return con;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    public void ref(ActionEvent actionEvent) {
        DCIteratorBinding iter = (DCIteratorBinding) BindingContext.getCurrent().getCurrentBindingsEntry().get("DemandeDeCongeView1_1Iterator"); // from pageDef.  
        Row r = iter.getCurrentRow();  
        Object value1 = r.getAttribute("IdDemande");  
        Connection con = null;
             try {
                 con = getConnectionDS("java:comp/env/jdbc/connectpfeDS");
                
             } catch (SQLException e) {
             } catch (NamingException e) {
             }
        String req="UPDATE DEMANDE_DE_CONGE SET ETAT='Refusée Sup' WHERE ID_DEMANDE='"+value1+"'";
             
             
        try{
                                   PreparedStatement stmt = con.prepareStatement(req);
                                                   int rs = stmt.executeUpdate();
              
            } 
                                      
                                      catch (SQLException e) {   }
             
             
             
    }
       
  
     
     
     
     
     
     
        public void ref1(ActionEvent actionEvent) {
            DCIteratorBinding iter = (DCIteratorBinding) BindingContext.getCurrent().getCurrentBindingsEntry().get("DemandeDeCongeView1_1Iterator"); // from pageDef.  
            Row r = iter.getCurrentRow();  
            Object value1 = r.getAttribute("IdDemande");  
            Connection con = null;
                 try {
                     con = getConnectionDS("java:comp/env/jdbc/connectpfeDS");
                    
                 } catch (SQLException e) {
                 } catch (NamingException e) {
                 }
            String req="UPDATE DEMANDE_DE_CONGE SET ETAT='Refusée Sup' WHERE ID_DEMANDE='"+value1+"'";
                 
                 
            try{
                                       PreparedStatement stmt = con.prepareStatement(req);
                                                       int rs = stmt.executeUpdate();
                  
                } 
                                          
                                          catch (SQLException e) {   }
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
        
        
        
    }

    
    public void processSelectionSL(SelectionEvent selectionEvent) {

    RichTable richTable = (RichTable)selectionEvent.getSource();
    CollectionModel tableModel = (CollectionModel)richTable.getValue();
    JUCtrlHierBinding adfTableBinding = (JUCtrlHierBinding)tableModel.getWrappedData();
    Object selectedRowData = richTable.getSelectedRowData();
    JUCtrlHierNodeBinding nodeBinding = (JUCtrlHierNodeBinding)selectedRowData;
    for (Object o : nodeBinding.getAttributeValues()) {
    System.out.println("Selected values " + o);
    }

    }


    public void setMessage(RichInputText message) {
        this.message = message;
    }

    public RichInputText getMessage() {
        return message;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}


