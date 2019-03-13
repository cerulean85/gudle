package net.techpda.myapplication

import dagger.Module
import dagger.Provides

@Module
class AppModule {
    @Named("hello")
    @Provides
    fun provideHelloWorld() = "Hello World!!"
}