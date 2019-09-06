package com.mksoft.mkjw_second_project.model.Token

data class OAuthToken(
    val access_token:String,
    val expires_in:Long,
    val token_type:String,
    val refresh_token:String,
    val scope:String
)