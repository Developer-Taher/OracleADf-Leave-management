package view.backing;


import java.io.Serializable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javax.sql.DataSource;

import model.AppModuleImpl;
import model.UserInfo;

import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;

import oracle.jbo.ApplicationModule;
import oracle.jbo.Session;
import oracle.jbo.client.Configuration;

import oracle.security.jps.internal.api.servlet.ServletAuthentication;

import org.apache.myfaces.trinidad.event.AttributeChangeEvent;


@SessionScoped
@ManagedBean(name = "loginBean")
public class Login implements Serializable {

    @SuppressWarnings("compatibility:-7317782854358391237")
    private static final long serialVersionUID = 1L;
        private String password;
        private String message, uname;
        private int nbd;
        private String first;
        private Integer s;
        private Integer h;
    private RichPanelFormLayout pfl;

    public String getMessage() {
            return message;
        }
     
        public void setMessage(String message) {
            this.message = message;
        }
        
     
        public String getPassword() {
            return password;
        }
     
        public void setPassword(String password) {
            this.password = password;
        }
     
        public String getUname() {
            return uname;
        }
     
        public void setuname(String uname) {
            this.uname = uname;
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
                 
                 
/*

    public String verif() {
        Connection con = null;
             try {
                 con = getConnectionDS("java:comp/env/jdbc/connectpfeDS");
             } catch (SQLException e) {
             } catch (NamingException e) {
             }
             try {
                   PreparedStatement stmt = con.prepareStatement("SELECT Login,Pwd FROM PROFIL WHERE LOGIN='"+uname+"' and PWD='"+password+"'");
                 ResultSet rs = stmt.executeQuery();
                 while (rs.next()) {
                     HttpSession session = Util.getSession();
                                session.setAttribute("username", uname);
                
                     
                     return "interfaceEmp";
                     
                 }

                 rs.close();


             } catch (SQLException e) {
                 throw new JboException(e);
             }
        FacesContext.getCurrentInstance().addMessage(
                           null,
                           new FacesMessage(FacesMessage.SEVERITY_WARN,
                           "Invalid Login!",
                           "Please Try Again!"));
        return "login1";
    }
    public String logout() {
         HttpSession session = Util.getSession();
         session.invalidate();
         return "login1";
      }
   */
    
              
              
              
              
              
              
protected void prepareSession(Session session){
        Map sessionScope = ADFContext.getCurrent().getSessionScope();
        String serverName = (String)sessionScope.get("serverName");
        System.out.println("In Model Layer serverName"+serverName);
        prepareSession(session);
    }
              
        
        
        
        
        
        
        
     public String verif() {
        FacesContext context = FacesContext.getCurrentInstance();  
               String amDef = "model.AppModule";
               String config = "AppModuleLocal";
               ApplicationModule ami =  Configuration.createRootApplicationModule(amDef,config);
               AppModuleImpl am = (AppModuleImpl)ami;
     
               UserInfo user= am.testUser(uname,password);
        
               if (user == null){
                 FacesMessage mesg = new FacesMessage("Bad Login....Please try again");
                 FacesContext.getCurrentInstance().addMessage(null,mesg);
                 return "login1";
               }
               
               else
                {
                   
            FacesContext ectx = FacesContext.getCurrentInstance();
                HttpServletRequest request = (HttpServletRequest)ectx.getExternalContext().getRequest();
                HttpSession httpSession = request.getSession();
                httpSession.setAttribute("serverName", request.getServerName());
                httpSession.setAttribute("serverPort", request.getServerPort());
                   
            
            ADFContext.getCurrent().getSessionScope().put("test",uname);
                   Connection con = null;
                        try {
                            con = getConnectionDS("java:comp/env/jdbc/connectpfeDS");
                           
                        } catch (SQLException e) {
                        } catch (NamingException e) {
                        }
                   
                   String req2="SELECT * FROM EMPLOYE WHERE ID_EMPLOYE IN(SELECT ID_EMP FROM PROFIL WHERE LOGIN='"+uname+"') AND FONCTION='RH'";
            String req="SELECT CHEF FROM EMPLOYE WHERE CHEF IN(SELECT ID_EMP FROM PROFIL WHERE LOGIN='"+uname+"')";
                   try{
         PreparedStatement stmt = con.prepareStatement(req);
                            ResultSet rs = stmt.executeQuery();
         PreparedStatement stmt2 = con.prepareStatement(req2);
                            ResultSet rs2 = stmt2.executeQuery();
      
                       
                           if (rs.next()) {
                                  
                               
                                return "interfaceSupHir";}
        
                 
                 // Session Put Attrib
            
                   /*context.getExternalContext().getSessionMap().put("userInf", uname);
                   HttpSession session = Util.getSession();
                              session.setAttribute("username", uname);*/
                                
                           else if(rs2.next()){
                                   
                               return "interfaceDRH"; }
                               
                          
                           
                           else {
                                      
                 return "interfaceEmp";
                               
                                   
                               }
        
                   
            
               
     } catch (SQLException e) {
            }
            
            return null;
        }
                 
     }
    


    public String getLogout() {
           HttpSession session = Util.getSession();
           session.invalidate();
           return "login1";
       }
    
    
    
    
    public String logout(){
            FacesContext fctx = FacesContext.getCurrentInstance();
                    ExternalContext ectx = fctx.getExternalContext();
                    HttpSession session = (HttpSession) ectx.getSession(false);
                    session.invalidate();
                    HttpServletRequest request = (HttpServletRequest)ectx.getRequest();
                    HttpServletResponse response = (HttpServletResponse) ectx.getResponse();
                    ServletAuthentication.logout(request,response,false);
                    return null;}
    
    
    
    
    
    
    public String nomemp(AttributeChangeEvent attributeChangeEvent) {
     
        Connection con = null;
             try {
                 con = getConnectionDS("java:comp/env/jdbc/connectpfeDS");
             } catch (SQLException e) {
             } catch (NamingException e) {
             }
             try {
                   PreparedStatement stmt = con.prepareStatement("select NOM_PRENOM from Employe WHERE ID_EMPLOYE=(SELECT ID_EMP FROM PROFIL WHERE LOGIN='"+uname+"'))");
                 ResultSet rs = stmt.executeQuery();
                
                     first = rs.getString("NOM_PRENOM");
                 
        
        
        // Add event code here...
    } catch (SQLException e) {
        }
             return first;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public void nbrdem(AttributeChangeEvent attributeChangeEvent) {
         
        Connection con = null;
             try {
                 con = getConnectionDS("java:comp/env/jdbc/connectpfeDS");
             } catch (SQLException e) {
             } catch (NamingException e) {
             }
             try {
                   PreparedStatement stmt = con.prepareStatement("select count(*) as nbre from DemandeDeConge WHERE ID_DEMANDEUR=(SELECT ID_EMPLOYE FROM EMPLOYE WHERE CHEF=(SELECT ID_EMP FROM PROFIL WHERE LOGIN='"+uname+"'))");
                 ResultSet rs = stmt.executeQuery();
                  rs.next();
             nbd=rs.getInt("nbre");
        
        
        
        // Add event code here...
    } catch (SQLException e) {
        }
    }


    public void setNbd(int nbd) {
        this.nbd = nbd;
    }

    public int getNbd() {
        return nbd;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getFirst() {
        return first;
    }

    public void ref(ActionEvent actionEvent) {
       
        FacesMessage mesg = new FacesMessage("Bad Login....Please try again"+h);
        FacesContext.getCurrentInstance().addMessage(null,mesg);
        
        
        
    }

    public void refuss(ActionEvent actionEvent) {
          
          
            
           
        }


    public void setS(Integer s) {
        this.s = s;
    }

    public Integer getS() {
        return s;
    }

    public void setH(Integer h) {
        this.h = h;
    }

    public Integer getH() {
        return h;
    }


    public void setPfl(RichPanelFormLayout pfl) {
        this.pfl = pfl;
    }

    public RichPanelFormLayout getPfl() {
        return pfl;
    }
}


