package model.common;

import org.eclipse.persistence.sdo.SDODataObject;

public class DemandeDeCongeViewSDOImpl extends SDODataObject implements DemandeDeCongeViewSDO {

   public static final int START_PROPERTY_INDEX = 0;

   public static final int END_PROPERTY_INDEX = START_PROPERTY_INDEX + 8;

   public DemandeDeCongeViewSDOImpl() {}

   public java.lang.Integer getIdDemande() {
      return new Integer(getInt(START_PROPERTY_INDEX + 0));
   }

   public void setIdDemande(java.lang.Integer value) {
      set(START_PROPERTY_INDEX + 0 , value);
   }

   public java.sql.Date getDateDemande() {
      return (java.sql.Date)get(START_PROPERTY_INDEX + 1);
   }

   public void setDateDemande(java.sql.Date value) {
      set(START_PROPERTY_INDEX + 1 , value);
   }

   public java.sql.Date getDateConge() {
      return (java.sql.Date)get(START_PROPERTY_INDEX + 2);
   }

   public void setDateConge(java.sql.Date value) {
      set(START_PROPERTY_INDEX + 2 , value);
   }

   public java.sql.Date getDateFin() {
      return (java.sql.Date)get(START_PROPERTY_INDEX + 3);
   }

   public void setDateFin(java.sql.Date value) {
      set(START_PROPERTY_INDEX + 3 , value);
   }

   public java.lang.String getMotif() {
      return getString(START_PROPERTY_INDEX + 4);
   }

   public void setMotif(java.lang.String value) {
      set(START_PROPERTY_INDEX + 4 , value);
   }

   public java.lang.Integer getIdDemandeur() {
      return new Integer(getInt(START_PROPERTY_INDEX + 5));
   }

   public void setIdDemandeur(java.lang.Integer value) {
      set(START_PROPERTY_INDEX + 5 , value);
   }

   public java.lang.String getTypec() {
      return getString(START_PROPERTY_INDEX + 6);
   }

   public void setTypec(java.lang.String value) {
      set(START_PROPERTY_INDEX + 6 , value);
   }

   public java.lang.String getEtat() {
      return getString(START_PROPERTY_INDEX + 7);
   }

   public void setEtat(java.lang.String value) {
      set(START_PROPERTY_INDEX + 7 , value);
   }

   public java.lang.String getIsRemoveAttachmentEnabled() {
      return getString(START_PROPERTY_INDEX + 8);
   }

   public void setIsRemoveAttachmentEnabled(java.lang.String value) {
      set(START_PROPERTY_INDEX + 8 , value);
   }


}

