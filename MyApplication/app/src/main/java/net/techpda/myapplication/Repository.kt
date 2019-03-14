package net.techpda.myapplication

import com.google.gson.annotations.SerializedName

class Repository(val id: Long, val name: String, val description: String, val owner: User, @field:SerializedName("stargazers_count")
val stars: Long, @field:SerializedName("forks_count")
                 val forks: Long)
