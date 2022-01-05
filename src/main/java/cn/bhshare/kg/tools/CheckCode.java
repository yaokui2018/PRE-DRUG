package cn.bhshare.kg.tools;

import javax.servlet.http.HttpServletRequest;

public class CheckCode {

    public boolean checkVcode(String vcode, HttpServletRequest request){
        String code = request.getSession().getAttribute("vcode").toString().trim().toUpperCase();
        String mycode = vcode.trim().toUpperCase();
        System.out.println(code+"----------------"+vcode);
        if (mycode.equals(code)) {
            request.getSession().setAttribute("vcode","");
            return true;
        }
        else
            return false;
    }

    public boolean checkEcode(String ecode, HttpServletRequest request){
        String code = request.getSession().getAttribute("ecode").toString().trim();
        if (ecode.trim().equals(code)) {
//            request.getSession().setAttribute("ecode","");
            return true;
        }
        else
            return false;
    }

    public boolean checkEmail(String email, HttpServletRequest request){
        String mail = request.getSession().getAttribute("useremail").toString();
        if (mail.equals(email))
            return true;
        else
            return false;
    }

}
