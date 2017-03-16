package view.backing;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Calendar;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.sql.DataSource;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.output.RichPanelCollection;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.event.PopupCanceledEvent;
import oracle.adf.view.rich.event.PopupFetchEvent;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.jbo.Row;

import org.joda.time.DateTime;
import org.joda.time.Days;


public class DemCongeBean {
    
    private String d;
    private Login p1;
    private Integer iduser;
    private String motif1;
    private Number s;
    private Integer idrh;
    private String msg;
    
    static final long MILI_SECONDS_PER_DAY = 86400000;
    private Number days;
    private RichInputText motif;
    private RichTable t1;
    private String message1;
    private RichPanelCollection pan;
    private RichTable t2;


    public DemCongeBean() {
        super();
        
        

    }

    public void setD(String d) {
        this.d = d;
    }

    public String getD() {
        return d;
    }

    public void setP1(Login p1) {
        this.p1 = p1;
    }

    public Login getP1() {
        return p1;
    }
    
    
    public void a(){
        
        d=p1.getUname();
        
        
        
        }
    
    public void setMotif1(String motif1) {
        this.motif1 = motif1;
    }

    public String getMotif1() {
        return motif1;
    }
    public void setIduser(Integer iduser) {
        this.iduser = iduser;
    }

    public Integer getIduser() {
        return iduser;
    }

    public void setS(Integer s) {
        this.s = s;
    }

    public Number getS() {
        return s;
    }
    public void setIdrh(Integer idrh) {
        this.idrh = idrh;
    }

    public Integer getIdrh() {
        return idrh;
    }
   
    public String action() {
       
        FacesMessage mesg = new FacesMessage("Bad Login....Please try again"+p1.getUname());
        FacesContext.getCurrentInstance().addMessage(null,mesg);
        // Add event code here...
        return null;
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



  

            public void editPopupFetchListener(PopupFetchEvent popupFetchEvent) {
                if (popupFetchEvent.getLaunchSourceClientId().contains("cbInsert")) {
                    BindingContainer bindings = getBindings();
                    OperationBinding operationBinding = bindings.getOperationBinding("CreateInsert");
                    operationBinding.execute();
                }
            }
            
            public void editDialogListener(DialogEvent dialogEvent) {
                if (dialogEvent.getOutcome().name().equals("ok")) {
                    BindingContainer bindings = getBindings();
                    OperationBinding operationBinding = bindings.getOperationBinding("Commit");
                    operationBinding.execute();   
                } else if (dialogEvent.getOutcome().name().equals("cancel")) {
                    BindingContainer bindings = getBindings();
                    OperationBinding operationBinding = bindings.getOperationBinding("Rollback");
                    operationBinding.execute();
                }
            }
            


            public void editPopupCancelListener(PopupCanceledEvent popupCanceledEvent) {
                BindingContainer bindings = getBindings();
                OperationBinding operationBinding = bindings.getOperationBinding("Rollback");
                operationBinding.execute();
            }



            public BindingContainer getBindings() {
                return BindingContext.getCurrent().getCurrentBindingsEntry();
            }
   
   
        public void sampleDialogListener(DialogEvent dialogEvent) {
              if ("yes".equals(dialogEvent.getOutcome())) {
                  ADFUtil.invokeEL("#{bindings.Commit.execute}");
                  FacesContext context = FacesContext.getCurrentInstance();
                  context.getApplication().getNavigationHandler().handleNavigation(context,
                                                                                   null,
                                                                                   "submit");
                  
             
              } else if ("no".equals(dialogEvent.getOutcome())) {
                  ADFUtil.invokeEL("#{bindings.Rollback.execute}");
                 FacesContext context = FacesContext.getCurrentInstance();
                 context.getApplication().getNavigationHandler().handleNavigation(context,
                                                                                  null,
                                                                                  "noAction");
             } else if ("cancel".equals(dialogEvent.getOutcome())) {
                 FacesContext context = FacesContext.getCurrentInstance();
                 context.getApplication().getNavigationHandler().handleNavigation(context,
                                                                                  null,
                                                                                  "cancel");
             }
    FacesMessage mesg = new FacesMessage("Demande Modifiée");
    FacesContext.getCurrentInstance().addMessage(null,mesg); 
}
    public void onDeleteItem(ActionEvent actionEvent) {
           ADFUtil.invokeEL("#{bindings.Delete.execute}");
           ADFUtil.invokeEL("#{bindings.Commit.execute}");
       }
    public void sampleDialogListen(DialogEvent dialogEvent) {
          if ("yes".equals(dialogEvent.getOutcome())) {
              ADFUtil.invokeEL("#{bindings.Delete.execute}");
             ADFUtil.invokeEL("#{bindings.Commit.execute}");
              FacesContext context = FacesContext.getCurrentInstance();
              context.getApplication().getNavigationHandler().handleNavigation(context,
                                                                               null,
                                                                               "submit");
         
         } else if ("cancel".equals(dialogEvent.getOutcome())) {
             FacesContext context = FacesContext.getCurrentInstance();
             context.getApplication().getNavigationHandler().handleNavigation(context,
                                                                              null,
                                                                              "cancel");
         }
    
    }


    public String cb3_action() {
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("Delete");
        Object result = operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            return null;
        }
        
        FacesMessage mesg = new FacesMessage("Demande annulée");
        FacesContext.getCurrentInstance().addMessage(null,mesg); 
        return null;
    }



    public String cb5_action3() {
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("Delete1");
        Object result = operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            return null;
        }
        return null;
    }
    public String cb3_action2() {
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("Delete");
        Object result = operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            return null;
        }
        FacesMessage mesg = new FacesMessage("Demande annulée");
        FacesContext.getCurrentInstance().addMessage(null,mesg); 
        return null;
    }

    public String cb5_action() {
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("Delete1");
        Object result = operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            return null;
        }
        return null;
    }


    
    
    public void AcceptSupHir(ActionEvent actionEvent){
                Integer id=(Integer)actionEvent.getComponent().getAttributes().get("new");
                DateFormat formatterr = new SimpleDateFormat("dd/MM/yyyy");
                 
                         java.util.Date someDate3=new java.util.Date();
                java.util.Date someDate4=new java.util.Date();
                        java.util.Date today = Calendar.getInstance().getTime();
                        
                     
                         try   {
                            someDate3 = formatterr.parse((String)actionEvent.getComponent().getAttributes().get("date1"));
                         }
                         catch(ParseException pe)    {
                             System.out.println("Parser Exception");
                         }
                 try   {
                    someDate4 = formatterr.parse((String)actionEvent.getComponent().getAttributes().get("date2"));
                 }
                 catch(ParseException pe)    {
                     System.out.println("Parser Exception");
                 }
                 Number days = Days.daysBetween(new DateTime(someDate3),new DateTime(someDate4)).getDays(); 
                
                      String a =(String)actionEvent.getComponent().getAttributes().get("typecon");
              
                
            
            Connection con = null;
                 try {
                     con = getConnectionDS("java:comp/env/jdbc/connectpfeDS");
                    
                 } catch (SQLException e) {
                 } catch (NamingException e) {
                 }
            
            String req1="SELECT * FROM EMPLOYE WHERE ID_EMPLOYE=(SELECT ID_DEMANDEUR FROM DEMANDE_DE_CONGE WHERE ID_DEMANDE='"+id+"') AND FONCTION='RH'";
            String req="UPDATE DEMANDE_DE_CONGE SET ETAT='Acceptée Sup' WHERE ID_DEMANDE='"+id+"'";
            String req2="UPDATE DEMANDE_DE_CONGE SET ETAT='Acceptée' WHERE ID_DEMANDE='"+id+"'";
            String req3="UPDATE SOLDE_EMPLOYE SET SOLDE_RESTANT=(SELECT SOLDE_RESTANT FROM SOLDE_EMPLOYE WHERE ID_EMPLOYE=(SELECT ID_DEMANDEUR FROM DEMANDE_DE_CONGE WHERE ID_DEMANDE='"+id+"') AND TYPEC='"+a+"') - ('"+days+"')  WHERE TYPEC='"+a+"' AND (ID_EMPLOYE=(SELECT ID_DEMANDEUR FROM DEMANDE_DE_CONGE WHERE ID_DEMANDE='"+id+"'))"; 
            try{
            PreparedStatement stmtt = con.prepareStatement(req1);
                               ResultSet rs4 = stmtt.executeQuery();
                
                if(rs4.next()){
                
                
                try{
                    
                        PreparedStatement stmttt = con.prepareStatement(req3);
                                        int rs1 = stmttt.executeUpdate();
                    
                    
                                           PreparedStatement stmt = con.prepareStatement(req2);
                                                           int rs = stmt.executeUpdate(); 
                    
                    
                        AdfFacesContext.getCurrentInstance().addPartialTarget(t1);
                    showMessage1(actionEvent);
                    
                    } 
                                              
                                              catch (SQLException e) {   }
               
                }else{
                    
                    
                    try{
                                               PreparedStatement stmt = con.prepareStatement(req);
                                                               int rs = stmt.executeUpdate();
                            AdfFacesContext.getCurrentInstance().addPartialTarget(t1);
                        } 
                                                  
                                                  catch (SQLException e) {   }
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
            
           
                }}
            catch (SQLException e) {}
        
        
        
        
        
                comm();
                executebutt();
                DCBindingContainer bindings = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
                 DCIteratorBinding iter2 = bindings.findIteratorBinding("DemandeDeCongeView1_1Iterator");
                 iter2.executeQuery();
                
                AdfFacesContext.getCurrentInstance().addPartialTarget(getT1());
               
        
        showMessage(actionEvent);
        
            }
        
    

    public void RefuSupH(ActionEvent actionEvent) {
        // Add event code here...
       Integer h=(Integer)actionEvent.getComponent().getAttributes().get("new1");
        setS(h);
       
    }



    public void RefuSupDefinitive(ActionEvent actionEvent) {  
    
        FacesMessage mesg = new FacesMessage("Bad Login....Please try again"+getS());
        FacesContext.getCurrentInstance().addMessage(null,mesg);
       
    }


    public String cb5_action2() {
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("Delete1");
        Object result = operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            return null;
        }
        return null;
    }




    /*public void Refusdrh(ActionEvent actionEvent) {
        Integer id=(Integer)actionEvent.getComponent().getAttributes().get("id2");
        setIdrh(id);
        
    }


    public void RefusDefinitivedrh(ActionEvent actionEvent) {
        // Add event code here...
        
        FacesMessage mesg1 = new FacesMessage("Votre solde restant est insuffisant en  "+getMotif());
        FacesContext.getCurrentInstance().addMessage(null,mesg1);
    }*/




































    
    
    
    
    
    
    
    
    
    
    
    
    
        
        

    public void Acceptdemdrh(ActionEvent actionEvent) {
        Integer id=(Integer)actionEvent.getComponent().getAttributes().get("id1");
             
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
         
                 java.util.Date someDate1=new java.util.Date();
         java.util.Date someDate2=new java.util.Date();
                java.util.Date today = Calendar.getInstance().getTime();
                
             
                 try   {
                    someDate1 = formatter.parse((String)actionEvent.getComponent().getAttributes().get("date1"));
                 }
                 catch(ParseException pe)    {
                     System.out.println("Parser Exception");
                 }
         try   {
            someDate2 = formatter.parse((String)actionEvent.getComponent().getAttributes().get("date2"));
         }
         catch(ParseException pe)    {
             System.out.println("Parser Exception");
         }
         Number days = Days.daysBetween(new DateTime(someDate1),new DateTime(someDate2)).getDays(); 
        
              String a =(String)actionEvent.getComponent().getAttributes().get("typecon");
        
        Connection con = null;
             try {
                 con = getConnectionDS("java:comp/env/jdbc/connectpfeDS");
                
             } catch (SQLException e) {
             } catch (NamingException e) {
             }
        
        
        String req="UPDATE DEMANDE_DE_CONGE SET ETAT='Acceptée' WHERE ID_DEMANDE='"+id+"'";
        String req1="UPDATE SOLDE_EMPLOYE SET SOLDE_RESTANT=(SELECT SOLDE_RESTANT FROM SOLDE_EMPLOYE WHERE ID_EMPLOYE=(SELECT ID_DEMANDEUR FROM DEMANDE_DE_CONGE WHERE ID_DEMANDE='"+id+"') AND TYPEC='"+a+"') - ('"+days+"')  WHERE TYPEC='"+a+"' AND (ID_EMPLOYE=(SELECT ID_DEMANDEUR FROM DEMANDE_DE_CONGE WHERE ID_DEMANDE='"+id+"'))";
      
               try{
        PreparedStatement stmt = con.prepareStatement(req);
                        int rs = stmt.executeUpdate();
            PreparedStatement stmt1 = con.prepareStatement(req1);
                            int rs1 = stmt1.executeUpdate();
        
        // Add event code here...
        
        } catch (SQLException e) {
        }

        com1();
        ext();
        DCBindingContainer bindings = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
         DCIteratorBinding iter2 = bindings.findIteratorBinding("DemandeDeConge1View1Iterator");
         iter2.executeQuery();
        
        AdfFacesContext.getCurrentInstance().addPartialTarget(getT2());

      showMessage2(actionEvent);
 

    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    private static java.sql.Date getCurrentDate() {
        java.util.Date today = new java.util.Date();
        return new java.sql.Date(today.getTime());
    }
    
    
    
    
    
    
    
    
    
   
    
    
    
    
    
    
    
    
    
    
    public void oke(ActionEvent actionEvent) {
        
                                   
                                  DateFormat formatterr = new SimpleDateFormat("dd/MM/yyyy");
                                   java.util.Date someDate3=new java.util.Date();
                                   java.util.Date someDate4=new java.util.Date();
                                   java.util.Date today = Calendar.getInstance().getTime();
                                   
                                   
                                   try   {
                                      someDate3 = formatterr.parse((String)actionEvent.getComponent().getAttributes().get("date1"));
                                   }
                                   catch(ParseException pe)    {
                                       System.out.println("Parser Exception");
                                   }
                                   try   {
                                   someDate4 = formatterr.parse((String)actionEvent.getComponent().getAttributes().get("date2"));
                                   }
                                   catch(ParseException pe)    {
                                   System.out.println("Parser Exception");
                                   }
                                   Number days = Days.daysBetween(new DateTime(someDate3),new DateTime(someDate4)).getDays();
                 FacesMessage mesg1 = new FacesMessage("Votre solde restant est insuffisant en  "+days);
                 FacesContext.getCurrentInstance().addMessage(null,mesg1);
            
                              
                               }
                          
                             
                             
       
        
        
        
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

        public void Envoyerdemande(ActionEvent actionEvent) {
            
        int days = Days.daysBetween(new DateTime(actionEvent.getComponent().getAttributes().get("date1")),new DateTime(actionEvent.getComponent().getAttributes().get("date2"))).getDays(); 
        
             String a =(String)actionEvent.getComponent().getAttributes().get("typecon");
        

        
                               
             Connection con = null;
                  try {
                      con = getConnectionDS("java:comp/env/jdbc/connectpfeDS");
                     
                  } catch (SQLException e) {
                  } catch (NamingException e) {
                  }
        /*DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();*/
            java.util.Date currdate=getCurrentDate();


        String req="INSERT INTO DEMANDE_DE_CONGE(ID_DEMANDEUR,DATE_DEMANDE,DATE_CONGE,DATE_FIN,TYPEC,ETAT) VALUES ((SELECT ID_EMPLOYE FROM EMPLOYE WHERE ID_EMPLOYE IN(SELECT ID_EMP FROM PROFIL WHERE LOGIN='"+p1.getUname()+"')),TO_DATE('"+currdate+"','YYYY-MM-DD'),TO_DATE('"+actionEvent.getComponent().getAttributes().get("date1")+"','YYYY-MM-DD'),TO_DATE('"+actionEvent.getComponent().getAttributes().get("date2")+"','YYYY-MM-DD'),'"+a+"','En attente') ";
                 
                  String req3="Select SOLDE_RESTANT FROM SOLDE_EMPLOYE WHERE TYPEC='"+a+"' AND (ID_EMPLOYE=(SELECT ID_EMP FROM PROFIL WHERE LOGIN='"+p1.getUname()+"'))";
                      try{
             PreparedStatement stmt1 = con.prepareStatement(req3);
                                ResultSet rs2 = stmt1.executeQuery();
            
        while(rs2.next()){
                              
                              
                               int id = rs2.getInt(1);
                              
                              
                              
        if (id>days){
                              
                                  try{
                               PreparedStatement stmt = con.prepareStatement(req);
                                               int rs = stmt.executeUpdate();
                                   
                                   FacesMessage mesg1 = new FacesMessage("Demande envoyée: nombre de jours demandés=  "+days);
                                   FacesContext.getCurrentInstance().addMessage(null,mesg1);
                               
                               } catch (SQLException e) {
             
                          } }
        
        
        
        else {
                                  
             FacesMessage mesg1 = new FacesMessage("Votre solde restant est insuffisant en  "+a);
             FacesContext.getCurrentInstance().addMessage(null,mesg1);
        }
                          
                           }
                      
                         
                         
                            
        } catch (SQLException e) {
        }
        
            
            
            
     /*  DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        
                java.util.Date someDate1=new java.util.Date();
        java.util.Date someDate2=new java.util.Date();
               java.util.Date today = Calendar.getInstance().getTime();
               
            
                try   {
                   someDate1 = formatter.parse((String)actionEvent.getComponent().getAttributes().get("date1"));
                }
                catch(ParseException pe)    {
                    System.out.println("Parser Exception");
                }
        try   {
           someDate2 = formatter.parse((String)actionEvent.getComponent().getAttributes().get("date2"));
        }
        catch(ParseException pe)    {
            System.out.println("Parser Exception");
        }
        int days = Days.daysBetween(new DateTime(someDate1),new DateTime(someDate2)).getDays(); 
      
             String a =(String)actionEvent.getComponent().getAttributes().get("typecon");
      

        
                               
             Connection con = null;
                  try {
                      con = getConnectionDS("java:comp/env/jdbc/connectpfeDS");
                     
                  } catch (SQLException e) {
                  } catch (NamingException e) {
                  }
       DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();
            java.util.Date currdate=getCurrentDate();


        String req="INSERT INTO DEMANDE_DE_CONGE(ID_DEMANDEUR,DATE_DEMANDE,DATE_CONGE,DATE_FIN,TYPEC,ETAT) VALUES ((SELECT ID_EMPLOYE FROM EMPLOYE WHERE ID_EMPLOYE IN(SELECT ID_EMP FROM PROFIL WHERE LOGIN='"+p1.getUname()+"')),TO_DATE('"+currdate+"','YYYY-MM-DD'),'"+actionEvent.getComponent().getAttributes().get("date1")+"','"+actionEvent.getComponent().getAttributes().get("date2")+"','"+a+"','En attente') ";
                 
                  String req3="Select SOLDE_RESTANT FROM SOLDE_EMPLOYE WHERE TYPEC='"+a+"' AND (ID_EMPLOYE=(SELECT ID_EMP FROM PROFIL WHERE LOGIN='"+p1.getUname()+"'))";
                      try{
             PreparedStatement stmt1 = con.prepareStatement(req3);
                                ResultSet rs2 = stmt1.executeQuery();
            
      while(rs2.next()){
                              
                              
                               int id = rs2.getInt(1);
                              
                              
                              
        if (id>days){
                              
                                  try{
                               PreparedStatement stmt = con.prepareStatement(req);
                                               int rs = stmt.executeUpdate();
                                   
                               
                               
                               } catch (SQLException e) {
             
                          } }
     
     
     
     else {
                                  
             FacesMessage mesg1 = new FacesMessage("Votre solde restant est insuffisant en  "+a);
             FacesContext.getCurrentInstance().addMessage(null,mesg1);
        }
                          
                           }
                      
                         
                         
                            
        } catch (SQLException e) {
        }
       */      
    }


    public void Essai(ActionEvent actionEvent) {
        // Add event code here...
        Connection con = null;
             try {
                 con = getConnectionDS("java:comp/env/jdbc/connectpfeDS");
                
             } catch (SQLException e) {
             } catch (NamingException e) {
             }
      
        
        try{
            Statement statement=null;
            statement=con.createStatement();
            ResultSet res=statement.executeQuery("SELECT FONCTION FROM EMPLOYE WHERE ID_EMPLOYE=1");
     
            String fn=res.getString(1); 
            FacesMessage mesg = new FacesMessage("Bad Login....Please try again"+fn);
            FacesContext.getCurrentInstance().addMessage(null,mesg); }
            
            catch (SQLException e) {}
    }

    public void setMotif(RichInputText motif) {
        this.motif = motif;
    }

    public RichInputText getMotif() {
        return motif;
    }

    public String cb5_action4() {
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("Delete1");
        Object result = operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            return null;
            
        }
        FacesMessage mesg = new FacesMessage("Demande Annulée");
        FacesContext.getCurrentInstance().addMessage(null,mesg); 
        return null;
        
    }


    public void setT1(RichTable t1) {
        this.t1 = t1;
    }

    public RichTable getT1() {
        return t1;
    }
    
    
    
    
    
    
    
    public void insertmotif(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome().name().equals("ok")) {
            
            
            DCIteratorBinding iter = (DCIteratorBinding) BindingContext.getCurrent().getCurrentBindingsEntry().get("DemandeDeCongeView1_1Iterator"); // from pageDef.  
            Row r = iter.getCurrentRow();  
            Object value1 = r.getAttribute("IdDemande");  
            Connection con = null;
                 try {
                     con = getConnectionDS("java:comp/env/jdbc/connectpfeDS");
                    
                 } catch (SQLException e) {
                 } catch (NamingException e) {
                 }
            String req1="UPDATE DEMANDE_DE_CONGE SET ETAT='Refusée Sup' WHERE ID_DEMANDE='"+value1+"'";
            String req="UPDATE DEMANDE_DE_CONGE SET MOTIF='"+message1+"' WHERE ID_DEMANDE='"+value1+"'";
            
                 
                 
            try{
                                    
                    PreparedStatement stmt1 = con.prepareStatement(req1);
                                    int rs1= stmt1.executeUpdate();
                
                
                   
                } 
                                          
                                          catch (SQLException e) {   }
                 
            try{
                                       PreparedStatement stmt = con.prepareStatement(req);
                                                       int rs = stmt.executeUpdate();
                  
                } 
                                          
                                          catch (SQLException e) {   }
            
            comm();
            executebutt();
            DCBindingContainer bindings = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
             DCIteratorBinding iter2 = bindings.findIteratorBinding("DemandeDeCongeView1_1Iterator");
             iter2.executeQuery();
            
            AdfFacesContext.getCurrentInstance().addPartialTarget(getT1());
              
        } else if (dialogEvent.getOutcome().name().equals("cancel")) {
            BindingContainer bindings = getBindings();
            OperationBinding operationBinding = bindings.getOperationBinding("Rollback");
            operationBinding.execute();
            
            AdfFacesContext.getCurrentInstance().addPartialTarget(pan);  
        }
        comm();
        executebutt();
        DCBindingContainer bindings = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
         DCIteratorBinding iter2 = bindings.findIteratorBinding("DemandeDeCongeView1_1Iterator");
         iter2.executeQuery();
        
        AdfFacesContext.getCurrentInstance().addPartialTarget(getT1()); 
        
        
        StringBuilder message = new StringBuilder("<html><body>");
        message.append("<p style='color:red'><i>Demande refusée avec le motif  suivant:</i></p>"+message1);
        message.append("</body></html>");
        FacesMessage fm = new FacesMessage(message.toString());
        fm.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext fctx = FacesContext.getCurrentInstance();
        fctx.addMessage(null, fm);
                             
      
    }


    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMessage1(String message1) {
        this.message1 = message1;
    }

    public String getMessage1() {
        return message1;
    }
    
    
    
    
    
    public void refusdemdrh(DialogEvent dialogEvent) {
        DCIteratorBinding iter7 = (DCIteratorBinding) BindingContext.getCurrent().getCurrentBindingsEntry().get("DemandeDeConge1View1Iterator"); // from pageDef.  
        Row r1 = iter7.getCurrentRow();  
        Object value4 = r1.getAttribute("IdDemandeur");  
        if (dialogEvent.getOutcome().name().equals("ok")) {
            
            
            DCIteratorBinding iter = (DCIteratorBinding) BindingContext.getCurrent().getCurrentBindingsEntry().get("DemandeDeConge1View1Iterator"); // from pageDef.  
            Row r = iter.getCurrentRow();  
            Object value2 = r.getAttribute("IdDemande");  
            Connection con = null;
                 try {
                     con = getConnectionDS("java:comp/env/jdbc/connectpfeDS");
                    
                 } catch (SQLException e) {
                 } catch (NamingException e) {
                 }
            String req1="UPDATE DEMANDE_DE_CONGE SET ETAT='Refusée drh' WHERE ID_DEMANDE='"+value2+"'";
            String req2="SELECT NOM_PRENOM from EMPLOYE WHERE ID_EMPLOYE='"+value4+"'";
           
            try{
                                    
                    PreparedStatement stmt1 = con.prepareStatement(req1);
                                    int rs1= stmt1.executeUpdate();
                    PreparedStatement stmt = con.prepareStatement(req2);
                                       ResultSet rs2 = stmt.executeQuery();
                  
                
                   /* String fn=rs2.getString("NOM_PRENOM"); 
                    FacesMessage mesg = new FacesMessage("Bad Login....Please try again"+fn);
                    FacesContext.getCurrentInstance().addMessage(null,mesg);*/
                } 
                                          
                                          catch (SQLException e) {   }
                        
           
            com1();
            ext();
            DCBindingContainer bindings = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
             DCIteratorBinding iter2 = bindings.findIteratorBinding("DemandeDeConge1View1Iterator");
             iter2.executeQuery();
            
            AdfFacesContext.getCurrentInstance().addPartialTarget(getT2());
           
            
           
           
       
              
        } else if (dialogEvent.getOutcome().name().equals("cancel")) {
            BindingContainer bindings = getBindings();
            OperationBinding operationBinding = bindings.getOperationBinding("Rollback");
            operationBinding.execute();
        }
        
        
        com1();
        ext();
        DCBindingContainer bindings = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
         DCIteratorBinding iter2 = bindings.findIteratorBinding("DemandeDeConge1View1Iterator");
         iter2.executeQuery();
        
        AdfFacesContext.getCurrentInstance().addPartialTarget(getT2());
             
    
    }


    public void setPan(RichPanelCollection pan) {
        this.pan = pan;
    }

    public RichPanelCollection getPan() {
        return pan;
    }


    public String executebutt() {
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("Execute");
        Object result = operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            return null;
        }
        return null;
    }

   
    
    public void abc(PopupFetchEvent popupFetchEvent) {
        if (popupFetchEvent.getLaunchSourceClientId().contains("cbInsert")) {
            BindingContainer bindings = getBindings();
            OperationBinding operationBinding = bindings.getOperationBinding("CreateInsert");
            operationBinding.execute();
           
        }
    }

    public String comm() {
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("Commit");
        Object result = operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            return null;
        }
        return null;
    }

    public void setT2(RichTable t2) {
        this.t2 = t2;
    }

    public RichTable getT2() {
        return t2;
    }

    public String ext() {
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("Execute");
        Object result = operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            return null;
        }
        return null;
    }

    public String com1() {
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("Commit");
        Object result = operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            return null;
        }
        return null;
    }


    public void showMessage(ActionEvent actionEvent) {
           StringBuilder message = new StringBuilder("<html><body>");
           message.append("<p style='color:green'><i>Demande Acceptée</i></p>");
           message.append("</body></html>");
           FacesMessage fm = new FacesMessage(message.toString());
           fm.setSeverity(FacesMessage.SEVERITY_INFO);
           FacesContext fctx = FacesContext.getCurrentInstance();
           fctx.addMessage(null, fm);
       }
    public void showMessage1(ActionEvent actionEvent) {
           StringBuilder message = new StringBuilder("<html><body>");
           message.append("<p style='color:green'><i>Demande du Responsable RH Acceptée</i></p>");
           message.append("</body></html>");
           FacesMessage fm = new FacesMessage(message.toString());
           fm.setSeverity(FacesMessage.SEVERITY_INFO);
           FacesContext fctx = FacesContext.getCurrentInstance();
           fctx.addMessage(null, fm);
       }
    public void showMessage2(ActionEvent actionEvent) {
           StringBuilder message = new StringBuilder("<html><body>");
           message.append("<p style='color:green'><i>Demande acceptée de l'employé</i></p>");
           message.append("</body></html>");
           FacesMessage fm = new FacesMessage(message.toString());
           fm.setSeverity(FacesMessage.SEVERITY_INFO);
           FacesContext fctx = FacesContext.getCurrentInstance();
           fctx.addMessage(null, fm);
       }
   
}

