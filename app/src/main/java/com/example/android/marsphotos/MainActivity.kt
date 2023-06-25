/*
 * Copyright (C) 2021 The Android Open Source Project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.marsphotos

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.widget.Toolbar
import com.example.android.marsphotos.overview.LoginFragment

/**
 * MainActivity sets the content view activity_main, a fragment container that contains
 * overviewFragment.
 */
class MainActivity : AppCompatActivity() {
    private lateinit var imvLogin: ImageView
    private lateinit var toolbar: Toolbar
    private lateinit var tvTitle: TextView
    private lateinit var imvLeftButton: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO);
        toolbar = findViewById(R.id.toolbar)
        imvLogin = findViewById(R.id.imv_right_icon)
        imvLeftButton = findViewById(R.id.imv_left_icon)
        tvTitle = findViewById(R.id.tv_title)
        setSupportActionBar(toolbar)
        val fragment = LoginFragment()
        imvLogin?.setOnClickListener(View.OnClickListener {
            supportFragmentManager!!.beginTransaction()
                .replace(R.id.overviewFragment, fragment)
                .addToBackStack(null)
                .commit()
        })
    }

    public fun changeHeader(leftIcon: Boolean, title: String?, rightIcon: Boolean) {
        if (!leftIcon) {
            imvLeftButton.visibility = View.GONE
        } else {
            imvLeftButton.visibility = View.VISIBLE
        }
        tvTitle.text = title
        if (!rightIcon) {
            imvLogin.visibility = View.GONE
        } else {
            imvLogin.visibility = View.VISIBLE
        }
    }
    fun setLeftIcon(icon: Drawable?) {
        if (imvLeftButton.visibility == View.VISIBLE) {
            imvLeftButton.setImageDrawable(icon)
        }
    }

}