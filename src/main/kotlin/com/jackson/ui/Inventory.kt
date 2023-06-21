package com.jackson.ui

import javafx.geometry.Insets
import javafx.scene.layout.GridPane
import javafx.scene.layout.VBox

class Inventory {
    //Have hotbar with expandable inventory

    fun getHotbar() : GridPane {
        val hotbar = GridPane()

        hotbar.apply {
            hgap = 5.0
            padding = Insets(10.0)
        }

        for(i in 0..5) {
            val vbox = VBox()
            vbox.apply {
                style = "-fx-background-color: grey;" +
                        "-fx-background-size: 40 40;" +
                        "-fx-border-radius: 10 10 10 10;" +
                        "-fx-background-radius: 10 10 10 10;"
                minHeight = 40.0
                minWidth = 40.0

                setOnMouseClicked {
                    //todo open up inventory
                }
            }


            hotbar.add(vbox, i, 0)
        }

        return hotbar
    }
}