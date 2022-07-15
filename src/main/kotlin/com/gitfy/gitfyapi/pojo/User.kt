package com.gitfy.gitfyapi.pojo

data class User(
    var uid: String,
    var userName: String,
    var nickName: String,
    var password: String,
    var userType: String,
    var telegramId: String,
)
