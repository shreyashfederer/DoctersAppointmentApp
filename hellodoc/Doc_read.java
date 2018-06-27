package com.example.ameya.hellodoc;

/**
 * Created by ameya on 10/6/17.
 */

public class Doc_read {


    private  String name;
    private String age;
    private String qualification;
    private String address;
    private String mci_no;
    private  String speciallization;
    private String sex;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Doc_read(String name, String age, String qualification, String address, String mci_no, String doc_pics, String specialization,String sex) {
        this.name = name;
        this.age = age;
        this.qualification = qualification;
        this.address = address;
        this.mci_no = mci_no;
        this.doc_pics = doc_pics;
        this.specialization = specialization;
        this.sex=sex;
    }
    public Doc_read()
    {

    }

    public String getQualification() {
        return qualification;

    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMci_no() {
        return mci_no;
    }

    public void setMci_no(String mci_no) {
        this.mci_no = mci_no;
    }

    public String getDoc_pics() {
        return doc_pics;
    }

    public void setDoc_pics(String doc_pics) {
        this.doc_pics = doc_pics;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    private String doc_pics;
    private String specialization;
}
