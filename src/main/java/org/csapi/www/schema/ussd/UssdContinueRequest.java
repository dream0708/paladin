/**
 * UssdContinueRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.csapi.www.schema.ussd;

@SuppressWarnings({"serial","unused","rawtypes"})public class UssdContinueRequest  implements java.io.Serializable {
    private java.lang.String ussdIdentifier;

    private org.csapi.www.schema.ussd.UssdArray ussdMessage;

    public UssdContinueRequest() {
    }

    public UssdContinueRequest(
           java.lang.String ussdIdentifier,
           org.csapi.www.schema.ussd.UssdArray ussdMessage) {
           this.ussdIdentifier = ussdIdentifier;
           this.ussdMessage = ussdMessage;
    }


    /**
     * Gets the ussdIdentifier value for this UssdContinueRequest.
     * 
     * @return ussdIdentifier
     */
    public java.lang.String getUssdIdentifier() {
        return ussdIdentifier;
    }


    /**
     * Sets the ussdIdentifier value for this UssdContinueRequest.
     * 
     * @param ussdIdentifier
     */
    public void setUssdIdentifier(java.lang.String ussdIdentifier) {
        this.ussdIdentifier = ussdIdentifier;
    }


    /**
     * Gets the ussdMessage value for this UssdContinueRequest.
     * 
     * @return ussdMessage
     */
    public org.csapi.www.schema.ussd.UssdArray getUssdMessage() {
        return ussdMessage;
    }


    /**
     * Sets the ussdMessage value for this UssdContinueRequest.
     * 
     * @param ussdMessage
     */
    public void setUssdMessage(org.csapi.www.schema.ussd.UssdArray ussdMessage) {
        this.ussdMessage = ussdMessage;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UssdContinueRequest)) return false;
        UssdContinueRequest other = (UssdContinueRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.ussdIdentifier==null && other.getUssdIdentifier()==null) || 
             (this.ussdIdentifier!=null &&
              this.ussdIdentifier.equals(other.getUssdIdentifier()))) &&
            ((this.ussdMessage==null && other.getUssdMessage()==null) || 
             (this.ussdMessage!=null &&
              this.ussdMessage.equals(other.getUssdMessage())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getUssdIdentifier() != null) {
            _hashCode += getUssdIdentifier().hashCode();
        }
        if (getUssdMessage() != null) {
            _hashCode += getUssdMessage().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UssdContinueRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.csapi.org/schema/ussd", ">ussdContinueRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ussdIdentifier");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ussdIdentifier"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ussdMessage");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ussdMessage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.csapi.org/schema/ussd", "UssdArray"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}