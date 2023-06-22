package com.example.reminder

import com.google.gson.annotations.SerializedName

data class LightRequest(
    @SerializedName("entity_id")
    val entityId: String
)