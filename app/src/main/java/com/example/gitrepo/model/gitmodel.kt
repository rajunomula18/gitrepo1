package com.example.gitrepo.model


data class gitmodel(
    val incomplete_results: Boolean,
    val items: List<Item>,
    val total_count: Int
)