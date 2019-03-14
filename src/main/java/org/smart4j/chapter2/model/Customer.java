package org.smart4j.chapter2.model;

public class Customer {
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    //id
    private long id;
    //customer name
    private String name;
    //contact
    private  String contact;
    //telphone
    private String telephone;
    //email
    private String email;
    //remark
    private String remark;
    //省略getter/setter方法
}//INSERT INTO `customer` VALUES('1','customer1','Jack','13512345678','jack@gmain.com',NULL);
   // INSERT INTO `customer` VALUES('2','customer2','Rose','13623456789','rose@gmain.com',NULL);
