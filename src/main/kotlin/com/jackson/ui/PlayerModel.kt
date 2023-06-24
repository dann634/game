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
    val maxXVelocity = 5.0

    var yVelocity = 0.0
    var yAcceleration = 0.0
    val maxYVelocity = 3.0
    var isJumping = false

    val feetCollision = Rectangle()

    private val centreX = 0.0
    private val centreY = 0.0

    var idleImage = Image("file:src/main/resources/images/playerIdle.png")
    var run1Image = Image("file:src/main/resources/images/playerRun1.png")
    var run2Image = Image("file:src/main/resources/images/playerRun2.png")


    val range = 500


    init {
        image = run1Image
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



    fun setRun1Image() {
        image = run1Image
    }

    fun setRun2Image() {
        image = run2Image
    }

    fun setIdleImage() {
        image = idleImage
    }

    fun getCentreX() : Double {
        return centreX + (fitWidth / 2)
    }

    fun getCentreY() : Double {
        return centreY + (fitHeight / 2)
    }



}