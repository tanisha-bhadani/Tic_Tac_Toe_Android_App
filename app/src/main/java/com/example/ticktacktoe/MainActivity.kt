package com.example.ticktacktoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    var winPositions = arrayOf(intArrayOf(0,1,2) , intArrayOf(3,4,5) , intArrayOf(6,7,8) , intArrayOf(0,3,6) , intArrayOf(1,4,7) , intArrayOf(2,5,8) , intArrayOf(0,4,8) , intArrayOf(2,4,6))
    var gameState = arrayOf(2,2,2,2,2,2,2,2,2)
    var counter : Int =0

    var gameActive : Boolean = true
    var activePlayer : Int =0

    fun playerTap(view: View) {

        var img : ImageView = view as ImageView
        var tappedImage : Int = Integer.parseInt(img.tag.toString())

        if(!gameActive) {
            gameReset(view)
            var status : TextView = findViewById(R.id.status)
            status.text = "Tap to play"
            return
        }

        if(gameState[tappedImage] == 2)
        {
            counter++

            if(counter == 9)
                gameActive = false

            gameState[tappedImage] = activePlayer

            img.translationY = -1000f

            if(activePlayer == 0)
            {
                img.setImageResource(R.drawable.x)
                activePlayer=1
                var status : TextView = findViewById(R.id.status)
                status.text = "0's Turn - Tap to play"
            }

            else
            {
                img.setImageResource(R.drawable.o)
                activePlayer=0
                var status : TextView =findViewById(R.id.status)
                status.text = "X's turn - Tap to Play"
            }
            img.animate().translationYBy(1000f).duration = 300
        }

        var flag: Int = 0

        for(winPosition in winPositions) {
            if ((gameState[winPosition[0]] == gameState[winPosition[1]]) && (gameState[winPosition[1]] == gameState[winPosition[2]]) && (gameState[winPosition[0]] != 2)) {
                flag = 1

                gameActive = false

                lateinit var winner: String

                if ((gameState[winPosition[0]]) == 0)
                    winner = "X has won"
                else
                    winner = "O has won"

                var status : TextView = findViewById(R.id.status)
                status.text = winner
            }

        }

        if(counter == 9 && flag==0) {
            var status : TextView = findViewById(R.id.status)
            status.text = "Match Draw"
        }
    }

    private fun gameReset(view: View) {
        gameActive = true
        activePlayer=0
        counter=0
        var i:Int =0
        while (i<gameState.size)
        {
            gameState[i++] = 2
        }


        findViewById<ImageView>(R.id.image0).setImageResource(0)
        findViewById<ImageView>(R.id.image1).setImageResource(0)
        findViewById<ImageView>(R.id.image2).setImageResource(0)
        findViewById<ImageView>(R.id.image3).setImageResource(0)
        findViewById<ImageView>(R.id.image4).setImageResource(0)
        findViewById<ImageView>(R.id.image5).setImageResource(0)
        findViewById<ImageView>(R.id.image6).setImageResource(0)
        findViewById<ImageView>(R.id.image7).setImageResource(0)
        findViewById<ImageView>(R.id.image8).setImageResource(0)

        var status : TextView = findViewById(R.id.status)
        status.text = "X's Turn - Tap to play"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}


