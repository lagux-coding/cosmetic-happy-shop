package sample.google;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public class GoogleAccount {
    private String id;
    private String email;
    private String first_name;
    private String given_name;
    private String family_name;
    private String picture;
    private boolean verified_email;

    public GoogleAccount() {
    }

    public GoogleAccount(String id, String email, String first_name, String given_name, String family_name, String picture, boolean verified_email) {
        this.id = id;
        this.email = email;
        this.first_name = first_name;
        this.given_name = given_name;
        this.family_name = family_name;
        this.picture = picture;
        this.verified_email = verified_email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getGiven_name() {
        return given_name;
    }

    public void setGiven_name(String given_name) {
        this.given_name = given_name;
    }

    public String getFamily_name() {
        return family_name;
    }

    public void setFamily_name(String family_name) {
        this.family_name = family_name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public boolean isVerified_email() {
        return verified_email;
    }

    public void setVerified_email(boolean verified_email) {
        this.verified_email = verified_email;
    }

    @Override
    public String toString() {
        return "GoogleAccount{" + "id=" + id + ", email=" + email + ", first_name=" + first_name + ", given_name=" + given_name + ", family_name=" + family_name + ", picture=" + picture + ", verified_email=" + verified_email + '}';
    }
}
