package com.example.headsupgame

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible
import retrofit2.Call
import retrofit2.Callback
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
             var timer=60000L
    lateinit var tvname :TextView
    lateinit var tvtaboo1 :TextView
    lateinit var tvtaboo2 :TextView
    lateinit var tvtaboo3 :TextView
    lateinit var showText :TextView
    lateinit var tvstate: TextView


    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        title="Timer:--"


        val rotation= resources.configuration.orientation

        if(rotation== Configuration.ORIENTATION_LANDSCAPE){
             tvname=findViewById(R.id.tvname)
             tvtaboo1=findViewById(R.id.tvtaboo1)
             tvtaboo2=findViewById(R.id.tvtaboo2)
             tvtaboo3=findViewById(R.id.tvtaboo3)
             tvstate=findViewById(R.id.state)
            var dbhr=DBHlpr(applicationContext)
            val data = dbhr.readData()
            randomfun(data)


        }
        else{
            showText=findViewById(R.id.textView)
            var startbtn=findViewById<Button>(R.id.startbtn)
            startbtn.isVisible=true
            showText.isVisible=true

            startbtn.setOnClickListener {
                showText.text="Rotate The Device ! "
                countTimer()

            }
            var addbtn=findViewById<Button>(R.id.btn)
            addbtn.setOnClickListener {
                val intent = Intent(this, ADDCelebrity::class.java)
                startActivity(intent)
            }


        }



    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong("Timer", timer)

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        timer= savedInstanceState.getLong("Timer", 60000L)
         title= "Timer:${timer / 1000}"
        countTimer()
    }

fun countTimer(){
    val rotation= resources.configuration.orientation
       if(rotation== Configuration.ORIENTATION_PORTRAIT){
           if (timer!=0L && timer !=60000L){
               showText.text="Rotate The Device ! "

           }

       }
 //   else{
    //            if (timer==0L || timer==60000L)
    //                       {
    //                        tvstate.isVisible=true
    //                       }
    //
    //       }
    object: CountDownTimer(timer, 1000) {

        override fun onTick(millisUntilFinished: Long) {
            timer=millisUntilFinished
            title="Timer ${timer / 1000}"
        }

        override fun onFinish() {
            title="Finish!"
             //timer=60000L

        }
    }.start()


}
   fun randomfun(list: List<Heads>){

       var random = Random.nextInt(0,list.size)

       var randomobject = list[random]
       tvname.text= randomobject.name
        tvtaboo1.text=randomobject.taboo1
        tvtaboo2.text=randomobject.taboo2
       tvtaboo3.text=randomobject.taboo3

    }
   // fun restart(){
    //
    //        this.recreate()
    //    }


}