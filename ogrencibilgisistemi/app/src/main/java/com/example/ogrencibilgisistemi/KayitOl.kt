package com.example.ogrencibilgisistemi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.ogrencibilgisistemi.databinding.ActivityKayitOlBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class KayitOl : AppCompatActivity() {
    private lateinit var database:DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kayit_ol)
        val binding=ActivityKayitOlBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //veri ekleme işlemi
        binding.btnKaydet.setOnClickListener {
            //oluşturulan değiskenlere girilen verileri atama
            var kullaniciTc= binding.kayitTcKimlik.text.toString().toInt()
            var kullaniciTc1= binding.kayitTcKimlik.text.toString()
            var kullaniciAdSoyad=binding.kayitAdSoyad.text.toString()
            var kullaniciTelefon=binding.kayitTelefonNumarasi.text.toString()
            var kullaniciMaili=binding.kayitMailAdresi.text.toString()
            var kullaniciParola=binding.kayitTcKimlik.text.toString()//Kayıt olurken kullanıcıya tc kimlik numarasını sifre olarak atadım.
            var kullaniciUnvan=binding.kayitUnvan.text.toString()
            //üzerine kaydeder.
            //database.setValue(universite(kullaniciTc,kullaniciAdSoyad,kullaniciTelefon,kullaniciMaili,kullaniciParola,kullaniciUnvan))

            database=FirebaseDatabase.getInstance().getReference("kullanicilar")
            //sürekli yeni kayıt eklemesi için
            database.child(kullaniciTc.toString()).setValue(universite(kullaniciTc1,kullaniciAdSoyad,kullaniciTelefon ,kullaniciMaili,kullaniciParola,kullaniciUnvan))
            Toast.makeText(applicationContext,"Kayıt Başarılı",Toast.LENGTH_LONG).show()
            //kayıt gerçekleştikten sonra verileri PlainText'lerden temizleme
            binding.kayitTcKimlik.text.clear()
            binding.kayitAdSoyad.text.clear()
            binding.kayitTelefonNumarasi.text.clear()
            binding.kayitMailAdresi.text.clear()
            binding.kayitUnvan.text.clear()
        }

    }

    fun giriseDon(view : View){

        var intent= Intent(applicationContext,MainActivity::class.java)
        startActivity(intent)

    }
}