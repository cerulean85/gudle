package net.techpda.rxmvvmjavajavatest2.base.di;

import android.app.Application;

import net.techpda.rxmvvmjavajavatest2.viewmodel.base.BaseApplication;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.DaggerActivity;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {ContextModule.class, ApplicationModule.class, AndroidSupportInjectionModule.class, ActivityBindingModule.class})
public interface ApplicationComponent extends AndroidInjector<DaggerActivity> {

    void inject(BaseApplication application);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);
        ApplicationComponent build();

    }


}
