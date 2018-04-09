package com.onezao.onezao.onezao;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;
import android.view.View.OnClickListener;

import com.onezao.onezao.onezao.Http0329.HttpTestActivity;
import com.onezao.onezao.onezao.okhttp0409.OkHttpActivity0409;


public class AdminActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,OnClickListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_admin);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       Button btn_xutils_btn = (Button) findViewById(R.id.btn_xutils);
        btn_xutils_btn.setOnClickListener(this);

        Button btn_toOkHttp_0409 = (Button) findViewById(R.id.btn_toOkHttp_0409);
        btn_toOkHttp_0409.setOnClickListener(this);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action , I hope we can be better.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void onClick(View view){
       int id = view.getId();
       switch (id){
           case R.id.btn_xutils :
               Toast.makeText(AdminActivity.this,"HttpTestActivity",Toast.LENGTH_SHORT).show();
               Intent intent =  new Intent(AdminActivity.this,HttpTestActivity.class);
               startActivity(intent);
               return;
           case R.id.btn_toOkHttp_0409 :
               Toast.makeText(AdminActivity.this,"btn_https0329",Toast.LENGTH_SHORT).show();
               Intent intent2 =  new Intent(AdminActivity.this,OkHttpActivity0409.class);
               startActivity(intent2);
               return;
       }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //测试xutils


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.admin, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        AdminUtils.AdminMenu(AdminActivity.this, item);
        return super.onOptionsItemSelected(item);
    }

/*    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
           //  gallery
        } else if (id == R.id.nav_slideshow) {
            //  slideshow
        } else if (id == R.id.nav_manage) {
            Toast.makeText(this,"功能设置",Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


/*    //加载顶部菜单，添加菜单的点击事件。
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //设置左上角的图标的点击事件  ActionBar
        ActionBar actionBar = this.getActionBar();
        actionBar.setHomeButtonEnabled(true);
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.admin, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        AdminUtils.AdminMenu(AdminActivity.this, item);
        return super.onOptionsItemSelected(item);
    }*/
}
