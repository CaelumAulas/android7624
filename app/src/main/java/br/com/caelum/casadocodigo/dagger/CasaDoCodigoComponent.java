package br.com.caelum.casadocodigo.dagger;

import javax.inject.Singleton;

import br.com.caelum.casadocodigo.activity.CarrinhoActivity;
import br.com.caelum.casadocodigo.fragment.DetalhesLivroFragment;
import dagger.Component;

@Singleton
@Component(modules = CasaDoCodigoModule.class)
public interface CasaDoCodigoComponent {

    void inject(CarrinhoActivity activity);
    void inject(DetalhesLivroFragment fragment);

}
