package com.example.ogrencibilgisistemi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ogrencibilgisistemi.databinding.ActivityOgrenciSayfasiBinding
import com.example.ogrencibilgisistemi.databinding.ActivityYemekListesiBinding
import com.google.firebase.database.*

class YemekListesi : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    lateinit var binding : ActivityYemekListesiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_yemek_listesi)
        binding= ActivityYemekListesiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        database= FirebaseDatabase.getInstance().getReference("yemek")

        var getdata= object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var sb=StringBuilder() //Metin birleştirme sınıfı
                for(i in snapshot.children){

                    var Tarih=i.child("yemekid").getValue().toString()
                    var AraYemek=i.child("arayemek").getValue().toString()
                    var AnaYemek=i.child("anayemek").getValue().toString()
                    var Corba=i.child("corba").getValue().toString()
                    var Tatli=i.child("tatli").getValue().toString()

                    sb.append("${i.key}  \n $AraYemek \n $AnaYemek \n $Corba \n $Tatli\n ")

                }
              //  binding.Ye.setText(sb)
                binding.yemekListesiTextid.setText(sb)


            }

            override fun onCancelled(error: DatabaseError) {
            }
        }

        database.addValueEventListener(getdata)
        database.addListenerForSingleValueEvent(getdata)
    }
}