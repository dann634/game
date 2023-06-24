package com.jackson.ui

import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.VBox
import java.lang.Math.abs
import kotlin.math.pow
import kotlin.math.sqrt

class Block (blockName : String, x : Double, y : Double, gameController : GameController, playerModel: PlayerModel) : VBox() {
    private val fileName = "file:src/main/resources/images/$blockName.png"
    private val scale = 2.0
    private val dimensions = 16 * scale
    private val imageView = ImageView()

    init {
        layoutX = x
        layoutY = y

        setOnMouseEntered {

            val hypo = sqrt((abs(playerModel.getCentreX()) - abs(x)).pow(2.0) + (abs(playerModel.getCentreY()) - abs(y)).pow(2.0))
            println(hypo)
            if(hypo < playerModel.range) {
                style = "-fx-border-color: black ;" +
                        "-fx-border-width: 2; " +
                        "-fx-border-style: solid ;"
                gameController.setBlockOnTop(this) //So border shows
            }
        }



        setOnMouseExited {
            style = "-fx-border-width: 0"
        }

        height = dimensions
        width = dimensions

        initImageView()
        children.add(this.imageView)


    }

    private fun initImageView() {
        this.imageView.apply {
            image = Image(fileName)
            fitHeight = height
            fitWidth = width
            isPreserveRatio = true

        }
    }


    fun getDimension() : Double {
        return dimensions
    }

}