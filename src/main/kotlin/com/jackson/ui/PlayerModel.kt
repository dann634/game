package com.jackson.ui

import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.value.ChangeListener
import javafx.geometry.NodeOrientation
import javafx.scene.image.Image
import javafx.scene.image.ImageView

class PlayerModel (startingX : Double, startingY : Double, gameController : GameController) : ImageView() {

    val isModelFacingRight = SimpleBooleanProperty(true)

    val aProperty = SimpleBooleanProperty()
    val dProperty = SimpleBooleanProperty()

    val speed = 10.0


    init {
        image = Image("file:src/main/resources/images/player.png")
        fitHeight = 48.0
        fitWidth = 32.0
        isPreserveRatio = true
        y = startingY
        x = startingX

        this.isModelFacingRight.addListener { observableValue, t, t2 ->
            nodeOrientation = if(t2) NodeOrientation.LEFT_TO_RIGHT
            else NodeOrientation.RIGHT_TO_LEFT


        }

    }

}