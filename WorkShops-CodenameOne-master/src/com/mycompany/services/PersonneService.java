/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.util.Resources;
import com.mycompany.gui.NewsfeedForm;
import com.mycompany.gui.SessionManager;
import com.mycompany.utils.Statics;
import java.util.Map;
import java.util.Vector;

/**
 *
 * @author malak_6
 */
public class PersonneService {
    
    
    //singleton 
    public static PersonneService instance = null ;
    
    public static boolean resultOk = true;
    String json;

    //initilisation connection request 
    private ConnectionRequest req;
    
    
    public static PersonneService getInstance() {
        if(instance == null )
            instance = new PersonneService();
        return instance ;
    }
    
    
    
    public PersonneService() {
        req = new ConnectionRequest();
        
    }
    //Signup
    public void signup(TextField nom,TextField prenom,TextField mail,TextField adresse,TextField image,TextField nomequipe,TextField datenaissance,TextField tel,TextField mdp,ComboBox<String> etat, ComboBox<String> roles , Resources res ) {
        
     
        
        String url = Statics.BASE_URL+"/personne/personne/mobileajout?nom="+nom.getText().toString()+"&prenom="+prenom.getText().toString()+"&datenaissance="+datenaissance.getText().toString()+"&adresse="+adresse.getText().toString()+"&mail="+mail.getText().toString()+"&tel="+tel.getText().toString()+"&role="+roles.getSelectedItem().toString()+"&mdp="+mdp.getText().toString()+"&nomequipe="+nomequipe.getText().toString()+"&etat="+etat.getSelectedItem().toString()+"&image="+image.getText().toString();
        
       
        
        req.setUrl(url);
       
        //Control saisi
        if(nom.getText().equals("") && prenom.getText().equals(" ") && mail.getText().equals(" ")) {
            
            Dialog.show("Erreur","Veuillez remplir les champs","OK",null);
            
        }
        
        //hethi wa9t tsir execution ta3 url 
        req.addResponseListener((e)-> {
         
            //njib data ly7atithom fi form 
            byte[]data = (byte[]) e.getMetaData();//lazm awl 7aja n7athrhom ke meta data ya3ni na5o id ta3 kol textField 
            String responseData = new String(data);//ba3dika na5o content 
            
            System.out.println("data ===>"+responseData);
        }
        );
        
        
        //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
        
            
    }
    
    
    
     //SignIn
    
    public void signin(TextField mail,TextField mdp, Resources rs ) {
        
        
        String url = Statics.BASE_URL+"/personne/personne/loginMobile?mail="+mail.getText().toString()+"&mdp="+mdp.getText().toString();
        
      
        req = new ConnectionRequest(url, false); //false ya3ni url mazlt matba3thtich lel server
        req.setUrl(url);
        
        req.addResponseListener((e) ->{
            
            JSONParser j = new JSONParser();
            
            String json = new String(req.getResponseData()) + "";
            
            
            try {
            
            if(json.equals("failed")) {
                Dialog.show("Echec d'authentification","mail ou mot de passe éronné","OK",null);
            }
            else {
                System.out.println("data =="+json);
                
                Map<String,Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
                
                
             
                //Session 
                float id = Float.parseFloat(user.get("id").toString());
                SessionManager.setId((int)id);//jibt id ta3 user ly3ml login w sajltha fi session ta3i
                
                SessionManager.setMdp(user.get("mdp").toString());
                SessionManager.setNom(user.get("nom").toString());
                SessionManager.setMail(user.get("mail").toString());
                
                //photo 
                
                if(user.get("image") != null)
                    SessionManager.setImage(user.get("image").toString());
                
                
                if(user.size() >0 ) // l9a user
                   // new ListReclamationForm(rs).show();//yemchi lel list reclamation
                    new NewsfeedForm(rs).show();
                    
                    }
            
            }catch(Exception ex) {
                ex.printStackTrace();
            }
            
            
            
        });
    
         //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
    // mot de passe oublie
    public String getPasswordByEmail(String email, Resources rs ) {
        
        
        String url = Statics.BASE_URL+"/user/getPasswordByEmail?email="+email;
        req = new ConnectionRequest(url, false); //false ya3ni url mazlt matba3thtich lel server
        req.setUrl(url);
        
        req.addResponseListener((e) ->{
            
            JSONParser j = new JSONParser();
            
             json = new String(req.getResponseData()) + "";
            
            
            try {
            
          
                System.out.println("data =="+json);
                
                Map<String,Object> password = j.parseJSON(new CharArrayReader(json.toCharArray()));
                
                
            
            
            }catch(Exception ex) {
                ex.printStackTrace();
            }
            
            
            
        });
    
         //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
    return json;
    }

// Profil 

public static void EditUser(String nom,String prenom,String mail,String adresse,String nomequipe,String tel,String mdp)
{
    String url=Statics.BASE_URL+"/personne/personne/mobilemodifier?nom="+nom+"&prenom="+prenom+"&adresse="+adresse+"&mail="+mail+"&tel="+tel+"&mdp="+mdp+"&nomequipe="+nomequipe;
    
    MultipartRequest req= new MultipartRequest();
    req.setUrl(url);
    req.setPost(true);
    req.addArgument("id",String.valueOf(SessionManager.getId()));
    req.addArgument("nom",nom);
     req.addArgument("prenom",prenom);
      req.addArgument("adresse",adresse);
       req.addArgument("tel",tel);
        req.addArgument("nomequipe",nomequipe);
     req.addArgument("mdp",mdp);
      req.addArgument("mail",mail);
      System.out.println(mail);
      req.addResponseListener((response)-> {
          byte[] data= (byte[]) response.getMetaData();
          String s=new String(data);
          System.out.println(s);
         
      });
    
NetworkManager.getInstance().addToQueueAndWait(req);


}
}
