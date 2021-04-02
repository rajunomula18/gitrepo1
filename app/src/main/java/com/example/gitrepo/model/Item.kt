package com.example.gitrepo.model

data class Item(
    val git_url: String,
    val html_url: String,
    val name: String,
    val path: String,
    val repository: Repository,
    val score: Int,
    val sha: String,
    val url: String
)