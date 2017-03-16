package model;


import oracle.jbo.server.ViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Sat Apr 04 12:35:00 CEST 2015
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class DemandeDeCongeViewImpl extends ViewObjectImpl {
    /**
     * This is the default constructor (do not remove).
     */
    public DemandeDeCongeViewImpl() {
    }


    /** Delete the department row with the department id aDepId
     * Search the department via its id and if found delete the row without committing
     * @param aDepId Id of the department to delete
     */


    /**
     * Returns the bind variable value for log.
     * @return bind variable value for log
     */
    public String getlog() {
        return (String)getNamedWhereClauseParam("log");
    }

    /**
     * Sets <code>value</code> for bind variable log.
     * @param value value to bind as log
     */
    public void setlog(String value) {
        setNamedWhereClauseParam("log", value);
    }
}