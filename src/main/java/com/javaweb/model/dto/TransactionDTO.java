package com.javaweb.model.dto;

public class TransactionDTO extends  AbstractDTO {

    private String code ;
    private Long customerTransID;

    private  Long idTransaction ;

    private String note ;

    private String managementStaff;

    public Long getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(Long idTransaction) {
        this.idTransaction = idTransaction;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getCustomerTransID() {
        return customerTransID;
    }

    public void setCustomerTransID(Long customerTransID) {
        this.customerTransID = customerTransID;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getManagementStaff() {
        return managementStaff;
    }

    public void setManagementStaff(String managementStaff) {
        this.managementStaff = managementStaff;
    }
}
