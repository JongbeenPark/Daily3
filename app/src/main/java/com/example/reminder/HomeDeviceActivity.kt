package com.example.reminder

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Switch
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeDeviceActivity : AppCompatActivity() {
    private val BASE_URL = "https://dotc7yrm3eomll2gu3drgeo4rjvs6xbq.ui.nabu.casa/api/"
    private val TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiI5ZTY2NTBmODhhMmI0MjcxOWFhMjgzY2IzMzBiYTIwYyIsImlhdCI6MTY4NzM1MjU2NCwiZXhwIjoyMDAyNzEyNTY0fQ.ujtzbRfNAW7LRpzGsxYcd_7AQdFkQN_KZsUWw3zif2Y"
    private val ENTITY = "switch.esp12e_12e_led"
    private val ENTITY2 = "climate.esp12e_lg_ac"

    private lateinit var haApi: HA_API
    private lateinit var sw1: Switch
    private lateinit var sw2: Switch

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_device)

        sw1 = findViewById(R.id.swc1)
        sw2 = findViewById(R.id.swc2)


        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        haApi = retrofit.create(HA_API::class.java)

        sw1.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                toggleLight(true)
            }else{
                toggleLight(false)
            }
        }
        sw2.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                toggleClimate(true)
            }else{
                toggleClimate(false)
            }
        }
    }

    private fun toggleLight(turnOn: Boolean) {
        val action = if (turnOn) "on" else "off"
        val request = LightRequest(ENTITY)

        val call = haApi.toggleLight("Bearer $TOKEN", action, request)
        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@HomeDeviceActivity, "전등" + if (turnOn) "켜짐" else "꺼짐", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this@HomeDeviceActivity, "요청실패", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(this@HomeDeviceActivity, "통신오류", Toast.LENGTH_LONG).show()
            }
        })
    }
    private fun toggleClimate(turnOn: Boolean) {
        val action = if (turnOn) "on" else "off"
        val request = LightRequest(ENTITY2)

        val call = haApi.toggleClimate("Bearer $TOKEN", action, request)
        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@HomeDeviceActivity, "에어컨" + if (turnOn) "켜짐" else "꺼짐", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this@HomeDeviceActivity, "요청실패", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(this@HomeDeviceActivity, "통신오류", Toast.LENGTH_LONG).show()
            }
        })
    }
}