package com.example.ogrencibilgisistemi
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.ParcelUuid
import android.view.View.inflate
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.resources.Compatibility.Api21Impl.inflate
import androidx.core.content.res.ColorStateListInflaterCompat.inflate
import androidx.core.content.res.ComplexColorCompat.inflate
import androidx.core.graphics.drawable.DrawableCompat.inflate
import com.example.ogrencibilgisistemi.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
class MainActivity : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var database=FirebaseDatabase.getInstance().reference
        //veri ekleme işlemi
        binding.btnGirisYap.setOnClickListener {

            database=FirebaseDatabase.getInstance().getReference("kullanicilar")
            //veri okuma işlemi
            var getdata= object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    var girisKullanici=binding.girisKullaniciAdi.text.toString()
                    var girisParola=binding.girisParola.text.toString()
                    for(i in snapshot.children){

                        //database deki verileri okuyor.
                        var kullaniciTc=i.child("uutc").getValue().toString()
                        var kullaniciAdSoyad=i.child("uadSoyad").getValue()
                        var kullaniciMail=i.child("umail").getValue()
                        var kullaniciParola=i.child("uparola").getValue().toString()
                        var kullaniciTelefon=i.child("utelefon").getValue()
                        var kullaniciUnvan=i.child("uunvan").getValue()
                        // sb.append("${i.key} $kullaniciTc $kullaniciAdSoyad $kullaniciMail $kullaniciParola $kullaniciTelefon $kullaniciUnvan")
                        if ((girisKullanici==kullaniciTc)&&(girisParola==kullaniciParola)){
                            if (girisKullanici==girisParola){
                                intent= Intent(applicationContext,sifreDegistir::class.java)
                                startActivity(intent)
                            }else {
                                if (kullaniciUnvan.toString()=="ogrenci"){
                                    intent= Intent(applicationContext,OgrenciSayfasi::class.java)
                                    startActivity(intent)
                                    class ogrenci{
                                        var tc: String=""
                                        constructor(tc: String){
                                            this.tc=tc
                                        }
                                    }
                                }else if (kullaniciUnvan.toString()=="ogretmen"){

                                    intent= Intent(applicationContext,OgretimGorevlisi::class.java)
                                    startActivity(intent)
                                }
                                else{
                                    intent= Intent(applicationContext,Yonetici::class.java)
                                    startActivity(intent)
                                }
                            }
                        }
                        /*if(girisParola!=kullaniciParola){
                            Toast.makeText(applicationContext,"Giriş Bilgileri Hatalı",Toast.LENGTH_LONG).show()
                        }*/
                    }
                }
                override fun onCancelled(error: DatabaseError) {

                }
            }
            database.addValueEventListener(getdata)
            database.addListenerForSingleValueEvent(getdata)
        }
        binding.btnKayitOl.setOnClickListener {
            intent=Intent(applicationContext,KayitOl::class.java)
            startActivity(intent)
        }
    }
}