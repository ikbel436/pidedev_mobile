/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;
import com.codename1.io.Preferences;
/**
 *
 * @author malak_6
 */
public class SessionManager {
    
    public static Preferences pref ; // 3ibara memoire sghira nsajlo fiha data 
    
    
    
    // hethom données ta3 user lyt7b tsajlhom fi session  ba3d login 
    private static int id ; 
    private static String nom ; 
    private static String prenom; 
    private static String mail;
    private static String adresse; 
    private static String tel;
    private static String nomequipe;
    private static String mdp ;
    private static String image;

    public static Preferences getPref() {
        return pref;
    }

    public static void setPref(Preferences pref) {
        SessionManager.pref = pref;
    }

    public static int getId() {
        return pref.get("id",id);// kif nheb njib id user connecté apres njibha men pref 
    }

    public static void setId(int id) {
        pref.set("id",id);//nsajl id user connecté  w na3tiha identifiant "id";
    }

    public static String getNom() {
        return nom;
    }

    public static void setNom(String nom) {
        SessionManager.nom = nom;
    }

    public static String getPrenom() {
        return prenom;
    }

    public static void setPrenom(String prenom) {
        SessionManager.prenom = prenom;
    }

    public static String getMail() {
        return mail;
    }

    public static void setMail(String mail) {
        SessionManager.mail = mail;
    }

    public static String getAdresse() {
        return adresse;
    }

    public static void setAdresse(String adresse) {
        SessionManager.adresse = adresse;
    }

    public static String getTel() {
        return tel;
    }

    public static void setTel(String tel) {
        SessionManager.tel = tel;
    }

    public static String getNomequipe() {
        return nomequipe;
    }

    public static void setNomequipe(String nomequipe) {
        SessionManager.nomequipe = nomequipe;
    }

    public static String getMdp() {
        return mdp;
    }

    public static void setMdp(String mdp) {
        SessionManager.mdp = mdp;
    }

    public static String getImage() {
        return image;
    }

    public static void setImage(String image) {
        SessionManager.image = image;
    }

   
    
    
    
}
