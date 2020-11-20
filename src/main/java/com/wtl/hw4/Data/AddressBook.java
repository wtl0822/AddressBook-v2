package com.wtl.hw4.Data;

import lombok.Data;

import java.awt.print.Book;
import java.io.Serializable;
import java.util.Vector;

@Data
public class AddressBook implements Serializable {
    private Vector<ContactInfo> bookinfo;

    public AddressBook() {
        bookinfo = new Vector<ContactInfo>();
        bookinfo.add(new ContactInfo("王天乐", "18991876691", "2280847145@qq.com"
                , "北京市北京邮电大学西土城校区", "2280847145", ""));
        bookinfo.add(new ContactInfo("路人甲", "18859824491", "1881310314@126.com"
                , "北京市北京邮电大学西土城校区", "471874147", ""));
        bookinfo.add(new ContactInfo("路人乙", "13992810493", "7123891178@163.com"
                , "北京市北京邮电大学沙河校区", "58798952", ""));
        bookinfo.add(new ContactInfo("路人丙", "13259109481", "813231597@qq.com"
                , "北京市北京邮电大学沙河校区", "174582175", ""));
    }
}
