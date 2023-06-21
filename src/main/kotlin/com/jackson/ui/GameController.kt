package com.jackson.ui

import javafx.scene.Scene
import javafx.scene.layout.AnchorPane

class GameController {

    /*
    Only have ui elements in this class
    Draw etc
     */

    private var root: AnchorPane = AnchorPane()

    private val inventory = Inventory()

    private val maxBlockWidth = 32
    private val maxBlockHeight = 8

    private val playerModel =
        PlayerModel(((1024 / 2) - 16).toDouble(), (556 - (maxBlockHeight * 32) - 48).toDouble(), this)

    init {
        this.root.apply {
            prefHeight = 384.0
            style = "-fx-background-color: lightblue"
            children.add(inventory.getHotbar())
        }


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
        scene.setOnKeyTyped {
            when (it.character[0]) {
                'a' -> playerModel.isModelFacingRight.value = false
                'd' -> playerModel.isModelFacingRight.value = true
                else -> {}
            }
        }

    }




}