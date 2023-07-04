package com.jackson.main

import com.jackson.ui.GameController
import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage

class Main : Application() {
    override fun start(stage: Stage) {
        stage.apply {
            title = "Game"
            isResizable = false
            scene = GameController().getScene()
            show()

        }
    }
}

fun main() {
    Application.launch(Main::class.java)
}