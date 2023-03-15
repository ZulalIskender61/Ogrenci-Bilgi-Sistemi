package com.example.ogrencibilgisistemi

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.ogrencibilgisistemi.databinding.ActivitySifreDegistirBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class sifreDegistir : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    lateinit var binding : ActivitySifreDegistirBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sifre_degistir)
        binding = ActivitySifreDegistirBinding.inflate(layoutInflater)
        setContentView(binding.root)
        database=FirebaseDatabase.getInstance().getReference("kullanicilar")
        val database1=FirebaseDatabase.getInstance().reference
        binding.sifreDegistir.setOnClickListener {
            var getdata= object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    var eskiSifre=binding.eskiSifre.text.toString()
                    var yeniSifre=binding.sifre.text.toString()
                    var yeniSifreTekrar=binding.sifreTekrar.text.toString()

                    for(i in snapshot.children){

                        var kullaniciTc=i.child("uutc").getValue().toString()
                        var kullaniciTc1=i.child("uutc").getValue().toString()
                        var kullaniciAdSoyad=i.child("uadSoyad").getValue().toString()
                        var kullaniciMail=i.child("umail").getValue().toString()
                        var kullaniciParola=i.child("uparola").getValue()
                        var kullaniciTelefon=i.child("utelefon").getValue().toString()
                        var kullaniciUnvan=i.child("uunvan").getValue().toString()

                        if (eskiSifre==kullaniciTc){
                            if(yeniSifre==yeniSifreTekrar){
                                kullaniciParola=yeniSifre
                                database.child(kullaniciTc).setValue(universite(kullaniciTc1,kullaniciAdSoyad,kullaniciTelefon ,kullaniciMail,kullaniciParola,kullaniciUnvan))
                                binding.eskiSifre.text.clear()
                                binding.sifre.text.clear()
                                binding.sifreTekrar.text.clear()
                                if (kullaniciUnvan=="ogrenci"){
                                    intent= Intent(applicationContext,OgrenciSayfasi::class.java)
                                    startActivity(intent)
                                }else if(kullaniciUnvan=="ogretmen"){
                                    intent= Intent(applicationContext,OgretimGorevlisi::class.java)
                                    startActivity(intent)
                                }else{
                                    intent= Intent(applicationContext,Yonetici::class.java)
                                    startActivity(intent)
                                }
                            }else{
                                Toast.makeText(applicationContext, "Sifreler uyusmuyor", Toast.LENGTH_SHORT).show()
                            }
                        }else{
                            Toast.makeText(applicationContext,"Lutfen eski sifrenizi dogru giriniz.",Toast.LENGTH_LONG).show()
                        }
                    }
                }
                override fun onCancelled(error: DatabaseError) {

                }
            }
            database.addValueEventListener(getdata)
            database.addListenerForSingleValueEvent(getdata)

        }
    }
}





