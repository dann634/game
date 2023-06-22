package com.jackson.ui

import javafx.animation.TranslateTransition
import javafx.beans.binding.BooleanBinding
import javafx.beans.value.ChangeListener
import javafx.scene.Scene
import javafx.scene.input.KeyCode
import javafx.scene.layout.AnchorPane
import javafx.util.Duration

class GameController {

    /*
    Only have ui elements in this class
    Draw etc
     */

    private var root: AnchorPane = AnchorPane()

    private val inventory = Inventory()

    private val maxBlockWidth = 32
    private val maxBlockHeight = 8

    private val FPS = 60.0


    private val playerModel =
        PlayerModel(((1024 / 2) - 16).toDouble(), (556 - (maxBlockHeight * 32) - 48).toDouble(), this)

    val spriteAnimation  = TranslateTransition()
    val xMove = playerModel.aProperty.or(playerModel.dProperty)


    init {
        this.root.apply {
            prefHeight = 384.0
            style = "-fx-background-color: lightblue" //Background colour
            children.add(inventory.getHotbar()) //Hotbar
        }

        this.spriteAnimation.apply {
            duration = Duration.millis(1000/FPS)
            node = playerModel
        }

        xMove.addListener { observableValue, t, t2 ->

            if(playerModel.dProperty.get()) {
                playerModel.x += playerModel.speed
                playerModel.dProperty.set(false)
            }

            if(playerModel.aProperty.get()) {
                playerModel.x -= playerModel.speed
                playerModel.aProperty.set(false)
            }
        }

        //https://gist.github.com/Da9el00/421d6f02d52093ac07a9e65b99241bf8


    }

    fun getScene(): Scene {

        drawScreen()



        val scene = Scene(root)
        initKeyPressedListeners(scene)


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
                this.root.children.add(Block(blockType, (i * 32).toDouble(), (j * 32 + 300).toDouble(), this))
            }
        }
        this.root.children.add(this.playerModel)
    }

    fun setBlockOnTop(block: Block) { //For border indicator
        block.toFront()
        playerModel.toFront()
    }

    private fun initKeyPressedListeners(scene: Scene) {

        fun moveSpriteLeft() {
            playerModel.isModelFacingRight.value = false
            playerModel.aProperty.set(true)
        }

        fun moveSpriteRight() {
            playerModel.isModelFacingRight.value = true
            playerModel.dProperty.set(true)
        }

        scene.setOnKeyPressed {
            when (it.code) {
                KeyCode.A -> moveSpriteLeft()
                KeyCode.D -> moveSpriteRight()
                else -> {}
            }

        }



    }




}