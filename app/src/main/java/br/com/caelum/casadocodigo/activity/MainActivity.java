package br.com.caelum.casadocodigo.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import br.com.caelum.casadocodigo.R;
import br.com.caelum.casadocodigo.delegate.LivroDelegate;
import br.com.caelum.casadocodigo.fragment.DetalhesLivroFragment;
import br.com.caelum.casadocodigo.fragment.ErroFragment;
import br.com.caelum.casadocodigo.fragment.ListaLivrosFragment;
import br.com.caelum.casadocodigo.fragment.LoadingFragment;
import br.com.caelum.casadocodigo.modelo.Livro;
import br.com.caelum.casadocodigo.webservices.WebClient;

public class MainActivity extends AppCompatActivity implements LivroDelegate {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EventBus.getDefault().register(this);

        if (primeiraVezQueCriouATela(savedInstanceState)) {
            new WebClient().pegaLivros(0);

            exibe(new LoadingFragment(), false);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

    }


    @Override
    public void lidaCom(Livro livro) {

        exibe(DetalhesLivroFragment.com(livro), true);
    }

    @Subscribe
    @Override
    public void lidaCom(ArrayList<Livro> livros) {

        Fragment fragmentDaTela = pegaFragmentDaTela();

        if (fragmentDaTela instanceof ListaLivrosFragment ) {

            ListaLivrosFragment listaFragment = (ListaLivrosFragment) fragmentDaTela;


            listaFragment.atualizaListaCom(livros);

        } else {

            exibe(ListaLivrosFragment.com(livros), false);
        }
    }

    @Subscribe
    @Override
    public void lidaCom(Throwable erro) {

        exibe(ErroFragment.com(erro), false);
    }


    private void exibe(Fragment fragment, boolean precisaEntrarNaPilha) {

        FragmentManager manager = getSupportFragmentManager();

        FragmentTransaction transaction = manager.beginTransaction();

        transaction.replace(R.id.frame_principal, fragment);

        if (precisaEntrarNaPilha) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

    private Fragment pegaFragmentDaTela() {

        FragmentManager manager = getSupportFragmentManager();

        return manager.findFragmentById(R.id.frame_principal);
    }


    private boolean primeiraVezQueCriouATela(Bundle savedInstanceState) {
        return savedInstanceState == null;
    }

}

