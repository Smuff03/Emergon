package com.example.emergon;

import android.renderscript.Matrix4f;
import android.widget.EditText;

public class dataholder {
    String ad,dob,email,un,ps,ph,age,hg,wg,mc,n,bg,gno,add;

    public dataholder(String Un, String Ps, String N, String Ad, String Dob, String Email, String Ph, String Age, String Hg, String Wg, String Mc, String Bg,String Gno, String Add) {
        n = N;
        ad = Ad;
        dob = Dob;
        email = Email;
        un = Un;
        ps= Ps;
        ph = Ph;
        age = Age;
        hg = Hg ;
        wg = Wg;
        mc = Mc;
        bg = Bg;
        add = Add;
        gno = Gno;
    }
    public String getGno() {
        return gno;
    }

    public void setGno(String Gno) {
        gno = Gno;
    }
    public String getAd() {
        return ad;
    }

    public void setAd(String Ad) {
        ad = Ad;
    }
    public String getDod() {
        return dob;
    }

    public void setDob(String Dob) {
        dob = Dob;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String Email) {
        email = Email;
    }
    public String getUn() {
        return un;
    }

    public void setUn(String Un) {
        un = Un;
    }
    public String getPs() {
        return ps;
    }

    public void setPs(String Ps) {
        ps = Ps;
    }
    public String getPh() {
        return ph;
    }

    public void setPh(String Ph) {
        ph = Ph;
    }
    public String getAge() {
        return age;
    }

    public void setAge(String Age) {
        age = Age;
    }
    public String getHg() {
        return hg;
    }

    public void setHg(String Hg) {
        hg = Hg;
    }
    public String getWg() {
        return wg;
    }

    public void setWg(String Wg) {
        wg = Wg;
    }
    public String getMc() {
        return mc;
    }

    public void setMc(String Mc) {
        mc = Mc;
    }
    public String getN() {
        return n;
    }

    public void setN(String N) {
        n = N;
    }
    public String getBg() {
        return bg;
    }

    public void setBg(String Bg) {
        bg = Bg;
    }
    public String getAdd() {
        return add;
    }

    public void setAdd(String Add) {
        add = Add;
    }
}
