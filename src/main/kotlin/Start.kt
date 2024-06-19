package ru.airdead.mirageapi

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import net.minecraft.server.command.CommandManager.literal
import net.minecraft.text.Text
import ru.airdead.uiengine.UIEngine
import ru.airdead.uiengine.UIManager
import ru.airdead.uiengine.utility.*


class Start : ClientModInitializer {
    override fun onInitializeClient() {
        UIEngine.initialize()


        CommandRegistrationCallback.EVENT.register { dispatcher, registryAccess, environment ->
            dispatcher.register(literal("foo")
                .executes { context ->
                    val rectangle = menu {
                        +rectangle {
                            size = V3(200.0, 100.0)
                            color = BLACK
                            origin = CENTER
                            align = CENTER
                            +text {
                                color = WHITE
                                content = "Hello"
                                autoFit = false
                            }
                        }
                    }

                    UIManager.addElement(rectangle)
                    rectangle.show()
                    context.getSource().sendFeedback({ Text.literal("Called /foo with no arguments") }, false)
                    1
                })
        }
    }

}
