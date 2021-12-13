package com.khalil.gestiondesetudiant;

public class Etudiant {
    Integer id ;
    String Fname, Sname , Cls ;

    public Etudiant() {
    }

    public Etudiant( String fname, String sname, String cls) {

        Fname = fname;
        Sname = sname;
        Cls = cls;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFname() {
        return Fname;
    }

    public void setFname(String fname) {
        Fname = fname;
    }

    public String getSname() {
        return Sname;
    }

    public void setSname(String sname) {
        Sname = sname;
    }

    public String getCls() {
        return Cls;
    }

    public void setCls(String cls) {
        Cls = cls;
    }
}
