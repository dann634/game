package com.jackson.ui

import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleDoubleProperty
import javafx.geometry.NodeOrientation
import javafx.scene.Node
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.shape.Rectangle
import kotlin.math.hypot

class PlayerModel (startingX : Double, startingY : Double) : ImageView() {

    val isModelFacingRight = SimpleBooleanProperty(true)


    var xVelocity = 0.0
    var xAcceleration = 0.0
    val maxXVelocity = 5.0

    var yVelocity = 0.0
    var yAcceleration = 0.0
    val maxYVelocity = 3.0
    var isJumping = false
    var isBanged = false

    val feetCollision = Rectangle()
    val headCollision = Rectangle()
    val leftCollision = Rectangle()
    val rightCollision = Rectangle()

    private val centreX = SimpleDoubleProperty()
    private val centreY = SimpleDoubleProperty()

    private var idleImage = Image("file:src/main/resources/images/playerIdle.png")
    var run1Image = Image("file:src/main/resources/images/playerRun1.png")
    var run2Image = Image("file:src/main/resources/images/playerRun2.png")


    val range = 150 //pixels


    init {
        image = run1Image
        fitHeight = 48.0
        fitWidth = 32.0
        isPreserveRatio = true
        y = startingY
        x = startingX
        isMouseTransparent = true


        centreX.bind(xProperty().add(fitWidth / 2))
        centreY.bind(yProperty().add(fitWidth / 2))

        //Feet Collision
        this.feetCollision.apply {
            height = 1.0
//            isVisible = false
            widthProperty().bind(this@PlayerModel.fitWidthProperty().subtract(10))
            xProperty().bind(this@PlayerModel.xProperty().add(5))
            yProperty().bind(this@PlayerModel.yProperty().add(48))
        }

        //Head Collision
        this.headCollision.apply {
            height = 1.0
            isVisible = true
            widthProperty().bind(this@PlayerModel.fitWidthProperty().subtract(10))
            xProperty().bind(this@PlayerModel.xProperty().add(5))
            yProperty().bind(this@PlayerModel.yProperty())

        }








        this.isModelFacingRight.addListener { observableValue, t, t2 ->
            nodeOrientation = if(t2) NodeOrientation.LEFT_TO_RIGHT
            else NodeOrientation.RIGHT_TO_LEFT

        }

    }

    fun getDistanceFromBlock(block : Block) : Double {

        //X values
        val blockCentreX = block.layoutX + (block.width / 2) //calc centre x coords of block
        val xDist = if(blockCentreX < centreX.get()) { //check if block is left or right of player
            centreX.get() - blockCentreX
        } else {
            blockCentreX - centreX.get()
        }

        //Y values
        val blockCentreY = block.layoutY + (block.height / 2) //calc centre y coords of block
        val yDist = if(blockCentreY < centreY.get()) { //check if block is above or below the player
            centreY.get() - blockCentreY
        } else {
            blockCentreY - centreY.get()
        }

        return hypot(xDist, yDist) //return hypotenuse
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

    fun getAllNodeElements() : List<Node> {
        val list = mutableListOf<Node>()
        list.add(this)
        return list
    }




}