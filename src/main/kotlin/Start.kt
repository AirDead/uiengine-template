package ru.airdead.mirageapi

import net.fabricmc.api.ClientModInitializer
import ru.airdead.uiengine.UIEngine
import ru.airdead.uiengine.UIManager
import ru.airdead.uiengine.utility.BLACK
import ru.airdead.uiengine.utility.CENTER
import ru.airdead.uiengine.utility.V3
import ru.airdead.uiengine.utility.rectangle


class Start : ClientModInitializer {
    override fun onInitializeClient() {
        UIEngine.initialize()
        
        val rectangle = rectangle { 
            align = CENTER
            origin = CENTER
            color = BLACK
            size = V3(200.0, 100.0)
        }
        
        UIManager.addElement(rectangle)
    }

}
