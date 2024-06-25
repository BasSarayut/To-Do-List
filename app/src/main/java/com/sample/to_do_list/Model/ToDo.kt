package com.sample.to_do_list.Model

data class ToDo(
    var id: Int,
    var task: String,
    var isCompleted: Boolean = false
)
