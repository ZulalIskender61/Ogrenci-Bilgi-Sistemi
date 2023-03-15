package com.example.ogrencibilgisistemi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

class Yonetici : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_yonetici)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater=menuInflater
        menuInflater.inflate(R.menu.yonetici_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId==R.id.kayitOnayi){
            intent=Intent(applicationContext,YoneticiKayitOnayi::class.java)
            startActivity(intent)
        }
        if(item.itemId==R.id.duyuruEkle){
            intent=Intent(applicationContext,YoneticiDuyuruEkle::class.java)
            startActivity(intent)
        }
        if(item.itemId==R.id.dersEkle){
            intent=Intent(applicationContext,YoneticiDersEkle::class.java)
            startActivity(intent)
        }
        if(item.itemId==R.id.yemekEkle){
            intent=Intent(applicationContext,YoneticiYemekEkle::class.java)
            startActivity(intent)
        }
        if(item.itemId==R.id.yoneticiCikis) {
            intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }

        return super.onOptionsItemSelected(item)
    }
}