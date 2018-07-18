package br.com.caelum.casadocodigo.application;

import android.app.Application;

import br.com.caelum.casadocodigo.dagger.CasaDoCodigoModule;
import br.com.caelum.casadocodigo.dagger.DaggerCasaDoCodigoComponent;
import br.com.caelum.casadocodigo.dagger.WebServiceModule;

public class CasaDoCodigoApplication extends Application {

    private DaggerCasaDoCodigoComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component =
                (DaggerCasaDoCodigoComponent)
                        DaggerCasaDoCodigoComponent.builder()
                                .webServiceModule(new WebServiceModule("http://cdcmob.herokuapp.com/"))
                                .build();
    }

    public DaggerCasaDoCodigoComponent getComponent() {
        return component;
    }
}
