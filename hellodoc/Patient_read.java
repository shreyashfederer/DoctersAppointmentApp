package com.example.ameya.hellodoc;

/**
 * Created by ameya on 10/4/17.
 */

public class Patient_read {
  private   String name,phoneNo,address,image_URL,user_id,weight,blood_Group,sex,sugar,physically;
    private String age;
    public Patient_read()
    {

    }

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

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage_URL() {
        return image_URL;
    }

    public void setImage_URL(String image_URL) {
        this.image_URL = image_URL;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getBlood_Group() {
        return blood_Group;
    }

    public void setBlood_Group(String blood_Group) {
        this.blood_Group = blood_Group;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSugar() {
        return sugar;
    }

    public void setSugar(String sugar) {
        this.sugar = sugar;
    }

    public String getPhysically() {
        return physically;
    }

    public void setPhysically(String physically) {
        this.physically = physically;
    }

    public Patient_read(String name, String age, String phoneNo, String address, String image_URL, String user_id, String weight, String blood_Group, String sex, String sugar, String physically) {
        this.name = name;
        this.age = age;
        this.phoneNo = phoneNo;
        this.address = address;
        this.image_URL = image_URL;
        this.user_id = user_id;
        this.weight = weight;
        this.blood_Group = blood_Group;
        this.sex = sex;
        this.sugar = sugar;
        this.physically = physically;
    }
}
