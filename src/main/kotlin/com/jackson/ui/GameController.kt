package com.jackson.ui

import javafx.scene.Scene
import javafx.scene.layout.AnchorPane
import javafx.scene.shape.Rectangle

class GameController {

    /*
    Only have ui elements in this class
    Draw etc
     */

    public fun getScene() : Scene {
        val root = AnchorPane()
        val rect = Rectangle()
        root.children.add(rect)
        val scene = Scene(root)

        return scene
    }

}