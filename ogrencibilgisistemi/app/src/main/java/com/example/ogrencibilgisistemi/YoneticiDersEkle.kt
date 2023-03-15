package com.example.ogrencibilgisistemi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ogrencibilgisistemi.databinding.ActivityYoneticiDersEkleBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class YoneticiDersEkle : AppCompatActivity() {
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_yonetici_ders_ekle)
        val binding= ActivityYoneticiDersEkleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database= FirebaseDatabase.getInstance().getReference("dersler")
        binding.dersEkleButtonid.setOnClickListener(){

            var dersid=binding.dersEkleid.text.toString()
            var derskodu=binding.dersEkleid.text.toString()
            var dersAdi=binding.dersAdiid.text.toString()
            var dersOgretimGorevlisi=binding.dersOgretimGorevlisiid.text.toString()

            database.child(dersid).setValue(dersler(derskodu,dersAdi,dersOgretimGorevlisi))

            binding.dersEkleid.text.clear()
            binding.dersAdiid.text.clear()
            binding.dersOgretimGorevlisiid.text.clear()


        }


    }
}