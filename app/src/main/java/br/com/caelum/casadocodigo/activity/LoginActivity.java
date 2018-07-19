package br.com.caelum.casadocodigo.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import javax.inject.Inject;

import br.com.caelum.casadocodigo.R;
import br.com.caelum.casadocodigo.application.CasaDoCodigoApplication;
import br.com.caelum.casadocodigo.fcm.GeradorDeTokenFCM;
import br.com.caelum.casadocodigo.webservices.WebClient;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.login_email)
    TextView campoEmail;

    @BindView(R.id.login_senha)
    TextView campoSenha;

    @BindView(R.id.login_logar)
    Button logar;

    private FirebaseAuth auth;

    @Inject
    WebClient client;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        CasaDoCodigoApplication application = (CasaDoCodigoApplication) getApplication();
        application.getComponent().inject(this);

        auth = FirebaseAuth.getInstance();

        auth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser currentUser = firebaseAuth.getCurrentUser();
                if (currentUser != null) {
                    Intent vaiParaMain = new Intent(getBaseContext(), MainActivity.class);
                    startActivity(vaiParaMain);
                    finish();
                }
            }
        });
    }

    @OnClick(R.id.login_logar)
    public void logar() {
        final String email = campoEmail.getText().toString();
        String senha = campoSenha.getText().toString();

        if (!email.isEmpty() && !senha.isEmpty()) {

            logar.setText("Entrando...");
            logar.setClickable(false);
            logar.setBackgroundColor(Color.GRAY);

            auth.signInWithEmailAndPassword(email, senha)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(getBaseContext(), "Não foi possível entrar", Toast.LENGTH_SHORT).show();

                                logar.setText("Entrar");
                                logar.setClickable(true);
                                logar.setBackgroundColor(getColor(R.color.laranja));
                            } else {
                                String id = new GeradorDeTokenFCM().geraToken();

                                client.cadastraDevice(id, email);
                            }
                        }
                    });
        } else {
            Toast.makeText(getBaseContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.login_novo)
    public void cadastrar() {
        String email = campoEmail.getText().toString();
        String senha = campoSenha.getText().toString();

        if (!email.isEmpty() && !senha.isEmpty()) {
            auth.createUserWithEmailAndPassword(email, senha)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(getBaseContext(), "Cadastro não foi possível", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        } else {
            Toast.makeText(getBaseContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show();
        }
    }

}
