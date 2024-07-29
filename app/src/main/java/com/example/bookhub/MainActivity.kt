package com.example.bookhub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.bookhub.fragment.AboutAppFragment
import com.example.bookhub.fragment.DashBoardFragment
import com.example.bookhub.fragment.FavoriteFragment
import com.example.bookhub.fragment.ProfileFragment
//import androidx.lifecycle.ReportFragment.Companion.reportFragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var coordinatorLayout: CoordinatorLayout
    private lateinit var toolbar: Toolbar
    private lateinit var frameLayout: FrameLayout
    private lateinit var navigationView: NavigationView
    var previousMenuItem: MenuItem? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawerLayout = findViewById(R.id.drawer_layout)
        coordinatorLayout = findViewById(R.id.coordinatorLayout)
        toolbar = findViewById(R.id.toolBar)
        frameLayout = findViewById(R.id.frameLaout)
        navigationView = findViewById(R.id.nav_view)
        setUpToolbar()

        val actionBarDrawerToggle = ActionBarDrawerToggle(
            this@MainActivity,
            drawerLayout,
            R.string.open_drawer,
            R.string.close_drawer


        )
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        navigationView.setNavigationItemSelectedListener {
            if (previousMenuItem != null) {

            }
            it.isChecked = true
            it.isChecked = true
            previousMenuItem = it

            when (it.itemId) {
                R.id.dashboard -> {

                    openDashBoard()



                    drawerLayout.closeDrawers()

                }
                R.id.favourites -> {

                    openFavourites()


                    drawerLayout.closeDrawers()
                }
                R.id.profile -> {

                    openProfile()
                    drawerLayout.closeDrawers()
                }
                R.id.about_app -> {

                    openAboutApp()

                    drawerLayout.closeDrawers()
                }
                else -> {
                    Toast.makeText(this, "Clicked on About App", Toast.LENGTH_SHORT).show()

                }

            }
            return@setNavigationItemSelectedListener true
        }
    }

    private fun setUpToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Toolbar Title"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun openDashBoard() {
        val fragment = DashBoardFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLaout, fragment)
        transaction.commit()
        supportActionBar?.title = "Dashboard"
        navigationView.setCheckedItem(R.id.dashboard)
    }

    private fun openAboutApp() {
        val fragment = AboutAppFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLaout, fragment)
        transaction.commit()
        supportActionBar?.title = "About App"
        navigationView.setCheckedItem(R.id.about_app)
    }

    private fun openFavourites() {
        val fragment = FavoriteFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLaout, fragment)
        transaction.commit()
        supportActionBar?.title = "Favorites"
        navigationView.setCheckedItem(R.id.favourites)
    }

    private fun openProfile() {
        val fragment = ProfileFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLaout, fragment)
        transaction.commit()
        supportActionBar?.title = "Profile"
        navigationView.setCheckedItem(R.id.profile)
    }

    override fun onBackPressed() {
        val frag = supportFragmentManager.findFragmentById(R.id.frameLaout)
        when (frag) {
            !is DashBoardFragment -> openDashBoard()
            else -> super.onBackPressed()

        }
    }
}