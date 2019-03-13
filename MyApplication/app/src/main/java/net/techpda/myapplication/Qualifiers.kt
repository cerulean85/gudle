package net.techpda.myapplication

import javax.inject.Qualifier

@Qualifier
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class Named(val value: String = "")