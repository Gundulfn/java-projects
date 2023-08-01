package dev.helper;

import javax.swing.*;

public class Helper {
    public static boolean isFieldEmpty(JTextField field){ // kullanıcı ekleme işleminde fld_name-username-pass gibi bölümler boş ise hata vermesi için yapılan method
        return field.getText().trim().isEmpty();
    }
    public static void showMsg(String str){
        String message;
        String title;

        switch (str){
            case "fill":
                title = "Error";
                message = "Please fill in all fields";
                break;
            case "done":
                title = "Result";
                message = "Succesful";
                break;
            case"Error":
                title = "Error";
                message = "Error";
                break;
            default:
                title = "Error";
                message = str;
        }

        JOptionPane.showMessageDialog(null,message,title,JOptionPane.INFORMATION_MESSAGE);
    }

    public static boolean confirm(String str){
        String message;

        if(str.equals("sure")){
            return JOptionPane.showConfirmDialog(null,
                    "Are you sure you want delete?","Confirm", JOptionPane.YES_NO_OPTION) == 0;
        }else{
            return JOptionPane.showConfirmDialog(null,
                    str,"Confirm", JOptionPane.YES_NO_OPTION) == 0;
        }
    }
}
