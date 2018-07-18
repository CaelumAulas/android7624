package br.com.caelum.casadocodigo.application;

import android.app.Application;

import br.com.caelum.casadocodigo.dagger.DaggerCasaDoCodigoComponent;

public class CasaDoCodigoApplication extends Application {

    private DaggerCasaDoCodigoComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component =
                (DaggerCasaDoCodigoComponent) DaggerCasaDoCodigoComponent.builder().build();
    }

    public DaggerCasaDoCodigoComponent getComponent() {
        return component;
    }
}
