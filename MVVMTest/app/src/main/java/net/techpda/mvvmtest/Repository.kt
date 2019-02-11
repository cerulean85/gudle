package net.techpda.mvvmtest

import io.reactivex.Single

interface Repository {

    fun getShortenUrl(url: String): Single<ShortenUrl>

}