package net.techpda.mvvmtest

import org.koin.dsl.module.Module
import org.koin.dsl.module.module

val shortenUrlModules: Module = module {

    factory {
        NetworkRepositoryImpl(get()) as Repository
    }

    factory {
        ShortenUrlViewModelFactory(get())
    }

}