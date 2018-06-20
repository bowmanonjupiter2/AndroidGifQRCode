package com.force.rain.com.helloworld

import android.graphics.BitmapFactory
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.github.sumimakito.awesomeqr.AwesomeQRCode
import kotlinx.android.synthetic.main.activity_main.*
import pl.droidsonroids.gif.GifDrawable
import java.io.*

class MainActivity : AppCompatActivity() {

    companion object {
        val TAG = MainActivity::class.java.simpleName
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView(){
        initQRCode()
    }

    private fun initQRCode(){

        val backgroundBmp = BitmapFactory.decodeResource(resources,R.mipmap.nba)
        val qrcodeBmp = AwesomeQRCode.create("The National Basketball Association (NBA) is a men's professional basketball league in North America; composed of 30 teams (29 in the United States and 1 in Canada). It is widely considered to be the premier men's professional basketball league in the world. ",288,10, 0.5f,Color.DKGRAY,Color.WHITE,backgroundBmp,false, true)


        val gifDrawable = GifDrawable(assets, "matchman.gif")

        val encoder = AnimatedGifEncoder()

        var bos = ByteArrayOutputStream()

        encoder.setDelay(50)
        encoder.setRepeat(0)
        encoder.start(bos)

        var index = 0

        while (index < gifDrawable.numberOfFrames){

            gifDrawable.seekToFrame(index)

            val output = BitMapUtil.overdraw(qrcodeBmp,gifDrawable.currentFrame)

            encoder.addFrame(output)

            output.recycle()

            index ++
        }

        encoder.finish()

        val outputGif = GifDrawable(bos.toByteArray())

        qrcodeImg2.setImageDrawable(outputGif)

        try{
            val outputStream = FileOutputStream(File("/sdcard/Download", "gif-qr-code.gif"))
            outputStream.write(bos.toByteArray())
        }catch (e:FileNotFoundException){
            e.printStackTrace()

        }catch (e:IOException){
            e.printStackTrace()
        }
    }
}
