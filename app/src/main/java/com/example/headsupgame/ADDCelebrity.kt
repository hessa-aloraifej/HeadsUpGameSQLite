package com.example.headsupgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_addcelebrity.*

class ADDCelebrity : AppCompatActivity() {
    lateinit var name:EditText
    lateinit var taboo1:EditText
    lateinit var taboo2:EditText
    lateinit var taboo3:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addcelebrity)
        name=findViewById(R.id.name)
        taboo1=findViewById(R.id.tabo1)
        taboo2=findViewById(R.id.taboo2)
        taboo3=findViewById(R.id.taboo3)

        var addbtn=findViewById<Button>(R.id.button2)

            var dbhr=DBHlpr(applicationContext)
            var delbtn=findViewById<Button>(R.id.delbtn)
            var editbtn=findViewById<Button>(R.id.editbtn)
            var deletedname=findViewById<EditText>(R.id.deletedname)
            var prevName=findViewById<EditText>(R.id.prevName)
            var newName=findViewById<EditText>(R.id.newName)
        addbtn.setOnClickListener {
            var name=name.text.toString()
            var taboo1=taboo1.text.toString()
            var taboo2=taboo2.text.toString()
            var taboo3=taboo3.text.toString()
           dbhr.savedat(name,taboo1,taboo2,taboo3)
            Toast.makeText(applicationContext, "Celebrity Has Been Saved Successfully", Toast.LENGTH_SHORT).show()


        }


        editbtn.setOnClickListener {



            var u= dbhr.update(prevName.text.toString(),newName.text.toString())
            if (u>0) {
                Toast.makeText(applicationContext, "update success!", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(applicationContext, "update failed!", Toast.LENGTH_SHORT).show();
            }



        }

        delbtn.setOnClickListener {

           var n= dbhr.del(deletedname.text.toString())
            if (n>0) {
                Toast.makeText(applicationContext, "delete success!", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(applicationContext, "delete failed!", Toast.LENGTH_SHORT).show();
            }

        }
    }
}