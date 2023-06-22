package com.jackson.ui

import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.value.ChangeListener
import javafx.geometry.NodeOrientation
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.shape.Rectangle

class PlayerModel (startingX : Double, startingY : Double) : ImageView() {

    val isModelFacingRight = SimpleBooleanProperty(true)


    var xVelocity = 0.0
    var xAcceleration = 0.0
    val maxXVelocity = 3.0

    var yVelocity = 0.0
    var yAcceleration = 0.0
    val maxYVelocity = 2.0
    var isJumping = false

    val feetCollision = Rectangle()


    init {
        image = Image("file:src/main/resources/images/player.png")
        fitHeight = 48.0
        fitWidth = 32.0
        isPreserveRatio = true
        y = startingY
        x = startingX

        feetCollision.apply {
            height = 5.0
            isVisible = false
        }
        feetCollision.widthProperty().bind(fitWidthProperty())
        feetCollision.xProperty().bind(xProperty())
        feetCollision.yProperty().bind(yProperty().add(43))

        this.isModelFacingRight.addListener { observableValue, t, t2 ->
            nodeOrientation = if(t2) NodeOrientation.LEFT_TO_RIGHT
            else NodeOrientation.RIGHT_TO_LEFT


        }

    }

}