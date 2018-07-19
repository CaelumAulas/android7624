package br.com.caelum.casadocodigo.dagger;

import javax.inject.Singleton;

import br.com.caelum.casadocodigo.activity.CarrinhoActivity;
import br.com.caelum.casadocodigo.activity.LoginActivity;
import br.com.caelum.casadocodigo.activity.MainActivity;
import br.com.caelum.casadocodigo.fragment.DetalhesLivroFragment;
import br.com.caelum.casadocodigo.fragment.ListaLivrosFragment;
import dagger.Component;

@Singleton
@Component(modules = {CasaDoCodigoModule.class, WebServiceModule.class})
public interface CasaDoCodigoComponent {

    void inject(CarrinhoActivity activity);
    void inject(DetalhesLivroFragment fragment);
    void inject(MainActivity activity);
    void inject(ListaLivrosFragment fragment);
    void inject(LoginActivity loginActivity);
}
