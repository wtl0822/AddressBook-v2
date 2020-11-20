package com.wtl.hw4.models;

import com.wtl.hw4.Data.AddressBook;
import com.wtl.hw4.Data.ContactInfo;

import java.util.Vector;

public class BookAlters {
    // 检查 table 中信息是否合法
    public static boolean checkValidAdd(AddressBook table, ContactInfo info){
        boolean isvalid = true;
        Vector<ContactInfo> list = table.getBookinfo();
        // 检查名字是否添加重复
        for (int i = 0; i < list.size() && isvalid; i++) {
            if (list.elementAt(i).getContactname().equals(info.getContactname()))
                isvalid = false;
        }
        return isvalid;
    }
    // 更新指定元素
    public static boolean alterElem(AddressBook addressBook, ContactInfo info) {
        int index = -1;
        Vector<ContactInfo> list = addressBook.getBookinfo();
        for (int i = 0; i < list.size() && -1 == index; i++) {
            if (list.elementAt(i).getContactname().equals(info.getContactname()))
                index = i;
        }

        if (index != -1) { // 如果找到替换该元素
            list.set(index, info);
            return true;
        }
        else {
            return false;
        }
    }
    public static void deleteElem(AddressBook addressBook, int index) {
        addressBook.getBookinfo().remove(index);
    }

}
