package com.jackson.ui

import kotlin.math.abs
import kotlin.math.sign

class AnimationRunnable(private val playerModel: PlayerModel, private val fps : Double, private val gameController: GameController) : Runnable { //Key to smooth movement (has fps clock)

    private var prevTime : Long = 0
    private var brakingForce = 0.2
    private val gravity = 0.2

    private fun calcXProperties() {
        this.playerModel.apply {
            xVelocity += xAcceleration
            x += xVelocity

            if(abs(xVelocity) > abs(maxXVelocity)) { //Speed Cap
                xVelocity = maxXVelocity * xVelocity.sign
            }

            if(!gameController.isAPressed && !gameController.isDPressed) { //slowing down
                xVelocity *= brakingForce
                xAcceleration = 0.0
            }

        }
    }

    private fun calcYProperties() {

        //gravity and jumping
        if(playerModel.yAcceleration >= 0 && gameController.isSpriteTouchingGround()) {
            playerModel.apply {
                yAcceleration = 0.0
                yVelocity = 0.0
            }
            return
        }



        playerModel.apply {

            if(yAcceleration > 0) {
                yAcceleration = gravity
            } else {
                yAcceleration += 0.2
            }

            yVelocity += yAcceleration
            if(abs(yVelocity) > maxYVelocity) {
                yVelocity = maxYVelocity * yVelocity.sign
            }

            y += yVelocity
        }
    }

    override fun run() { //Blocking main thread??
        while(true) {

            if(System.currentTimeMillis() - prevTime < 1000/fps) { //FPS lock
                continue
            }
            prevTime = System.currentTimeMillis()

            calcXProperties()
            calcYProperties()

        }
    }

}