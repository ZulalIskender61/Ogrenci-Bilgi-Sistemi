package com.example.ogrencibilgisistemi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ogrencibilgisistemi.databinding.ActivityKayitOlBinding
import com.example.ogrencibilgisistemi.databinding.ActivityYoneticiDuyuruEkleBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class YoneticiDuyuruEkle : AppCompatActivity() {
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_yonetici_duyuru_ekle)
        val binding= ActivityYoneticiDuyuruEkleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database= FirebaseDatabase.getInstance().getReference("Duyurular")

        binding.duyuruEklemeButtonid.setOnClickListener(){

            var duyuru=binding.duyuruEklemeid.text.toString()
            var yoneticiDuyurusu=1
            database.child(yoneticiDuyurusu.toString()).setValue(Duyurular(duyuru))
            binding.duyuruEklemeid.text.clear()
        }
    }
}