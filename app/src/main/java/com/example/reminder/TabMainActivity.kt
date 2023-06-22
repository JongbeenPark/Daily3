package com.example.reminder

import android.app.TabActivity
import android.content.Intent
import android.os.Bundle
import android.widget.TabHost
import com.lilcode.aop.p4c03.googlemap.SearchActivity


class TabMainActivity : TabActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tab_main)

        val tabHost = tabHost
        var spec: TabHost.TabSpec
        var intent: Intent

        intent = Intent().setClass(this, MainActivity::class.java)
        spec = tabHost.newTabSpec("Day")
        spec.setIndicator("일정")
        spec.setContent(intent)
        tabHost.addTab(spec)

        intent = Intent().setClass(this, SearchActivity::class.java)
        spec = tabHost.newTabSpec("Search")
        spec.setIndicator("지도검색")
        spec.setContent(intent)
        tabHost.addTab(spec)

        intent = Intent().setClass(this, HomeDeviceActivity::class.java)
        spec = tabHost.newTabSpec("Device")
        spec.setIndicator("홈 기기")
        spec.setContent(intent)
        tabHost.addTab(spec)

        intent = Intent().setClass(this, SettingsActivity::class.java)
        spec = tabHost.newTabSpec("Settings")
        spec.setIndicator("설정")
        spec.setContent(intent)
        tabHost.addTab(spec)
    }
}
