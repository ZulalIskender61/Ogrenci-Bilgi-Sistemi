package com.example.ogrencibilgisistemi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ogrencibilgisistemi.databinding.ActivityKayitOlBinding
import com.example.ogrencibilgisistemi.databinding.ActivityYoneticiYemekEkleBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class YoneticiYemekEkle : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    //lateinit var binding : ActivityYoneticiYemekEkleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_yonetici_yemek_ekle)
        val binding= ActivityYoneticiYemekEkleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database= FirebaseDatabase.getInstance().getReference("yemek")

        binding.yemekEkle.setOnClickListener(){


            var gun=binding.yemekTarihid.text.toString()
            var arayemek=binding.araYemekTextid.text.toString()
            var anayemek=binding.anaYemekTextid.text.toString()
            var corba=binding.corbaTextid.text.toString()
            var tatli=binding.tatliTextid.text.toString()

            database.child(gun).setValue(yemek(gun,arayemek,anayemek,corba,tatli))

            binding.yemekTarihid.text.clear()
            binding.araYemekTextid.text.clear()
            binding.anaYemekTextid.text.clear()
            binding.corbaTextid.text.clear()
            binding.tatliTextid.text.clear()


        }
    }


}