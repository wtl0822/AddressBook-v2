package com.wtl.hw4.Data;

import lombok.Data;

import java.io.Serializable;

@Data
public class ContactInfo implements Serializable {
    private String contactname;
    private String telephone;
    private String email;
    private String address;
    private String qqNum;
    private String message;

    public ContactInfo(String _contactname, String _telephone, String _email, String _address, String _qqNum, String _message) {
        this.contactname = _contactname;
        this.telephone = _telephone;
        this.email = _email;
        this.qqNum = _qqNum;
        this.address = _address;
        this.message = _message;
    }
}
