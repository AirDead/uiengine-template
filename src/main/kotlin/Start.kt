package ru.airdead.uitemplate

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import net.minecraft.server.command.CommandManager.literal
import net.minecraft.text.Text
import ru.airdead.hudrenderer.HudEngine
import ru.airdead.hudrenderer.HudEngine.clientApi
import ru.airdead.hudrenderer.HudManager
import ru.airdead.hudrenderer.event.MouseMoveEvent
import ru.airdead.hudrenderer.utility.*

class Mod : ClientModInitializer {
    override fun onInitializeClient() {
        HudEngine.initialize()

        // Example of a menu with a text element that moves behind the mouse
        val infoItem = text {
            text = "Info"
            origin = CENTER
            align = TOP_LEFT
            color = WHITE
        }
        val bankMenu = menu {
            +infoItem
        }

        clientApi.onEvent<MouseMoveEvent> {
            val x = it.mouseX
            val y = it.mouseY
            infoItem.offset = x x y
        }

        CommandRegistrationCallback.EVENT.register { dispatcher, registryAccess, environment ->
            dispatcher.register(literal("foo")
                .executes { context ->
                    bankMenu.show()
                    context.getSource().sendFeedback({ Text.literal("Called /foo with no arguments") }, false)
                    1
                })
        }

        HudManager.addElement(bankMenu)
    }
}
