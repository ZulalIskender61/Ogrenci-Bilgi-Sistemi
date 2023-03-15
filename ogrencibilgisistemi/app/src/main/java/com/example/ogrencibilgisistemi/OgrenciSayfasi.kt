package com.example.ogrencibilgisistemi

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.ogrencibilgisistemi.databinding.ActivityOgrenciSayfasiBinding
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.ogrenci_toolbar.view.*

class OgrenciSayfasi : AppCompatActivity() {
    private lateinit var database: DatabaseReference

    lateinit var binding : ActivityOgrenciSayfasiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ogrenci_sayfasi)
        binding=ActivityOgrenciSayfasiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        database= FirebaseDatabase.getInstance().getReference("Duyurular")

        var getdata= object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var sb=StringBuilder() //Metin birleştirme sınıfı

                for(i in snapshot.children){

                    var duyurular=i.child("sayfaDuyurusu").getValue().toString()
                    sb.append("${i.key} $duyurular ")
                }
                binding.ogrenciSayfasiDuyuruEkrani.setText(sb)
            }
            override fun onCancelled(error: DatabaseError) {
            }
        }
        database.addValueEventListener(getdata)
        database.addListenerForSingleValueEvent(getdata)
        //veri okuma işlemi
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean { //menu bağlama

        val menuInflater=menuInflater
        menuInflater.inflate(R.menu.ogrenci_menu,menu)

        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId==R.id.ogrenciAnaSayfa){
            intent=Intent(applicationContext,OgrenciSayfasi::class.java)
            startActivity(intent)
        }
        if(item.itemId==R.id.ogrenciKisiselBilgiler){

            intent=Intent(applicationContext,OgrenciKisiselBilgiler::class.java)
            startActivity(intent)

        }
        if(item.itemId==R.id.ogrenciSınavlar){
            intent=Intent(applicationContext,OgrenciSinavlar::class.java)
            startActivity(intent)
        }
        if(item.itemId==R.id.listYem){
            intent=Intent(applicationContext,YemekListesi::class.java)
            startActivity(intent)
        }
        if(item.itemId==R.id.ogrenciCikis){
            intent=Intent(applicationContext,MainActivity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }

}