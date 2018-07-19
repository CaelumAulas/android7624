package br.com.caelum.casadocodigo.fcm;

import com.google.firebase.iid.FirebaseInstanceId;

public class GeradorDeTokenFCM {

    public String geraToken(){
        FirebaseInstanceId firebaseInstanceId = FirebaseInstanceId.getInstance();

        String token = firebaseInstanceId.getToken();

        return token;
    }
}
