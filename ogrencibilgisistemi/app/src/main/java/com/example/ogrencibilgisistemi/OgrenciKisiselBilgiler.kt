package com.example.ogrencibilgisistemi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.ogrencibilgisistemi.databinding.ActivityMainBinding
import com.example.ogrencibilgisistemi.databinding.ActivityOgrenciKisiselBilgilerBinding
import com.example.ogrencibilgisistemi.databinding.ActivityYemekListesiBinding
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_ogrenci_kisisel_bilgiler.*

class OgrenciKisiselBilgiler : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    lateinit var binding : ActivityMainBinding
    lateinit var binding1 : ActivityOgrenciKisiselBilgilerBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ogrenci_kisisel_bilgiler)
        val binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val binding1= ActivityOgrenciKisiselBilgilerBinding.inflate(layoutInflater)
        setContentView(binding1.root)

        val tc=binding.girisKullaniciAdi.text.toString()
        println(tc)

        database= FirebaseDatabase.getInstance().getReference("kullanicilar")
        //veri okuma işlemi
        var getdata= object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var sb=StringBuilder()
                for(i in snapshot.children){

                    //database deki verileri okuyor.
                    var kullaniciTc=i.child("uutc").getValue().toString()
                    var kullaniciAdSoyad=i.child("uadSoyad").getValue()
                    var kullaniciMail=i.child("umail").getValue()
                    var kullaniciParola=i.child("uparola").getValue().toString()
                    var kullaniciTelefon=i.child("utelefon").getValue()
                    var kullaniciUnvan=i.child("uunvan").getValue()

                    if (kullaniciTc==tc){
                        sb.append("${i.key} \n $kullaniciTc \n $kullaniciAdSoyad \n $kullaniciMail \n $kullaniciParola \n $kullaniciTelefon \n $kullaniciUnvan \n")

                    }

                }
                binding1.ogrenciKisiselBilgilerTextid.setText(sb)




            }

            override fun onCancelled(error: DatabaseError) {

            }

        }
        database.addValueEventListener(getdata)
        database.addListenerForSingleValueEvent(getdata)



    }
}


