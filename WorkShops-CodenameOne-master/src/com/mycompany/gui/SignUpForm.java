/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.mycompany.gui;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.services.PersonneService;
import java.util.Vector;

/**
 * Signup UI
 *
 * @author Shai Almog
 */
public class SignUpForm extends BaseForm {

    public SignUpForm(Resources res) {
        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("SignIn");
                
        TextField nom = new TextField("", "Nom", 20, TextField.ANY);
        TextField prenom = new TextField("", "pernom", 20, TextField.ANY);
        TextField adresse = new TextField("", "adresse", 20, TextField.ANY);
        TextField tel = new TextField("", "tel", 20, TextField.ANY);
        TextField datenaissance = new TextField("", "date", 20, TextField.ANY);
        TextField nomequipe = new TextField("", "Nom equipe", 20, TextField.ANY);
        //TextField etat = new TextField("", "Etat", 20, TextField.ANY);
        TextField image = new TextField("", "image", 20, TextField.ANY);
        TextField mail = new TextField("", "E-Mail", 20, TextField.EMAILADDR);
        TextField mdp = new TextField("", "Password", 20, TextField.PASSWORD);
       
           //Role 
        //Vector 3ibara ala array 7atit fiha roles ta3na ba3d nzidouhom lel comboBox
        Vector<String> vectorRole;
        vectorRole = new Vector();
        
        vectorRole.add("fan");
        vectorRole.add("respo");
        
        ComboBox<String>roles = new ComboBox<>(vectorRole);
        
           //Etat
        //Vector 3ibara ala array 7atit fiha roles ta3na ba3d nzidouhom lel comboBox
        Vector<String> vectorEtat;
        vectorEtat = new Vector();
        
        vectorEtat.add("desactive");
        vectorEtat.add("active");
        
        ComboBox<String>etat = new ComboBox<>(vectorEtat);
        
        
        
        
        nom.setSingleLineTextArea(false);
         prenom.setSingleLineTextArea(false);
          adresse.setSingleLineTextArea(false);
           tel.setSingleLineTextArea(false);
            nomequipe.setSingleLineTextArea(false);
             //etat.setSingleLineTextArea(false);
              datenaissance.setSingleLineTextArea(false);
              image.setSingleLineTextArea(false);
        mail.setSingleLineTextArea(false);
        mdp.setSingleLineTextArea(false);
        
        Button next = new Button("SignUp");
        Button signIn = new Button("Sign In");
        signIn.addActionListener(e -> new SignInForm(res).show());
        signIn.setUIID("Link");
        Label alreadHaveAnAccount = new Label("Already have an account?");
        
        Container content = BoxLayout.encloseY(
                new Label("Sign Up", "LogoLabel"),
                new FloatingHint(nom),
                createLineSeparator(),
                new FloatingHint(prenom),
                createLineSeparator(),
                new FloatingHint(datenaissance),
                createLineSeparator(),
                new FloatingHint(adresse),
                createLineSeparator(),
                new FloatingHint(tel),
                createLineSeparator(),
                
                new FloatingHint(mail),
                 new FloatingHint(mdp),
                createLineSeparator(),
                createLineSeparator(),
                new FloatingHint(image),
                createLineSeparator(),
               
               // new FloatingHint(etat),
                createLineSeparator(),
                etat,
                createLineSeparator(),
                roles,//sinon y7otich role fi form ta3 signup
                new FloatingHint(nomequipe),
                createLineSeparator()
                
        );
        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);
        add(BorderLayout.SOUTH, BoxLayout.encloseY(
                next,
                FlowLayout.encloseCenter(alreadHaveAnAccount, signIn)
        ));
        next.requestFocus();
        next.addActionListener((e) -> {
            
            PersonneService.getInstance().signup(nom, prenom, mail,adresse, image, nomequipe, datenaissance,tel,mdp, etat, roles ,res );
            Dialog.show("Success","account is saved","OK",null);
            new SignInForm(res).show();
        });
    }
    
}
