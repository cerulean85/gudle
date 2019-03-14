package net.techpda.myapplication

import io.reactivex.Single
import javax.inject.Inject

class RepositoryProvider  @Inject
constructor(private val repoService: RepositoryService) {

    val repositories: Single<List<Repository>>
        get() = repoService.repositories

    fun getRepo(owner: String, name: String): Single<Repository> {
        return repoService.getRepo(owner, name)
    }
}