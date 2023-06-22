package com.jackson.ui

import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.value.ChangeListener
import javafx.scene.Scene
import javafx.scene.input.KeyCode
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.VBox

class GameController {

    /*
    Only have ui elements in this class
    Draw etc
     */

    private var root: AnchorPane = AnchorPane()

    private val inventory = Inventory()

    private val blockList = mutableListOf<Block>()

    private val maxBlockWidth = 32
    private val maxBlockHeight = 8

    private val fps = 60.0


    private val playerModel =
        PlayerModel(((1024 / 2) - 16).toDouble(), (556 - (maxBlockHeight * 32) - 48).toDouble())

    private val animationThread = Thread(AnimationRunnable(playerModel, fps, this))





    init {
        this.root.apply {
            prefHeight = 384.0
            style = "-fx-background-color: lightblue" //Background colour
            children.add(inventory.getHotbar()) //Hotbar
        }

        //https://gist.github.com/Da9el00/421d6f02d52093ac07a9e65b99241bf8


    }

    fun getScene(): Scene {

        drawScreen()

        val scene = Scene(root)
        initKeyPressedListeners(scene)

        animationThread.apply {
            isDaemon = true
            start()
        }



        return scene
    }

    private fun drawScreen() { //primitive map making - use text file and read in future
        var blockType: String?

        for (i in 0 until maxBlockWidth) {

            for (j in 0 until maxBlockHeight) {
                blockType = if (j == 0) {
                    "grass"
                } else {
                    "dirt"
                }
                var block = Block(blockType, (i * 32).toDouble(), (j * 32 + 300).toDouble(), this)
                this.blockList.add(block)
                this.root.children.add(block)
            }
        }
        this.root.children.addAll(this.playerModel, this.playerModel.feetCollision)

    }

    fun setBlockOnTop(block: Block) { //For border indicator
        block.toFront()
        playerModel.toFront()
    }

    private fun initKeyPressedListeners(scene: Scene) {

        fun moveSpriteLeft() {
            playerModel.isModelFacingRight.value = false
            playerModel.xAcceleration = -0.75
    }

        fun moveSpriteRight() {
            playerModel.isModelFacingRight.value = true
            playerModel.xAcceleration = 0.75
        }

        fun jump() {

            if(playerModel.isJumping) {
                return
            }

            playerModel.yAcceleration = -2.0
            playerModel.isJumping = true
        }

        scene.setOnKeyPressed {
            when (it.code) {
                KeyCode.A -> moveSpriteLeft()
                KeyCode.D -> moveSpriteRight()
                KeyCode.W -> jump()
                else -> {}
            }
        }

        scene.setOnKeyReleased {//Stops sprite if any other button is pressed
            playerModel.xAcceleration = 0.0
        }



    }

    fun isSpriteTouchingGround() : Boolean {
        for (block in blockList) {
            if(playerModel.feetCollision.intersects(block.boundsInParent)) {
                playerModel.isJumping = false
                println("ground touched")
                return true
            }
        }
        return false
    }




}