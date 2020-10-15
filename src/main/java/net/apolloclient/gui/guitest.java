package net.apolloclient.gui;

import net.apolloclient.gui.elements.ChatTextField;
import net.apolloclient.utils.font.ApolloFont;
import net.apolloclient.utils.font.ApolloFontRenderer;
import net.minecraft.client.gui.GuiScreen;

import java.awt.*;
import java.io.IOException;

public class guitest extends GuiScreen {

    public static ApolloFontRenderer fontRenderer;
    public ChatTextField chatTextField;

    @Override
    public void initGui() {
        fontRenderer = ApolloFontRenderer.create(ApolloFont.ARIAL_REGULAR, 20);
        chatTextField = new ChatTextField(fontRenderer, 50, 150, 200, 15);
    }

    @Override
    protected void keyTyped(char p_keyTyped_1_, int p_keyTyped_2_) throws IOException {
        chatTextField.keyTyped(p_keyTyped_1_, p_keyTyped_2_);
        super.keyTyped(p_keyTyped_1_, p_keyTyped_2_);
    }

    @Override
    public void drawScreen(int p_drawScreen_1_, int p_drawScreen_2_, float p_drawScreen_3_) {

        chatTextField.draw(p_drawScreen_1_, p_drawScreen_2_, p_drawScreen_3_);
//        fontRenderer.drawString(
//                "§!Class aptent §rtaciti sociosqu :hypixel_crush: ad litora torquent per" + '\n' +
//                        "conubia nostra, §!§kApolloFont himenaeos. Nam nec ante. §r"  + '\n' +
//                        "§!Sed :bacon: lacinia, urna non :a: tincidunt :yellow_circle: mattis, tortor neque"  + '\n' +
//                        ":green_circle: adipiscing diam, a cursus ipsum ante quis turpis. Nulla facilisi."  + '\n' +
//                        "Ut fringilla. :pepe_kek_pointing: Suspendisse potenti. :c: §lNunc feugiat mi a tellus consequat"  + '\n' +
//                        "Proin quam. Etiam ultrices. Suspendisse in justo §meu magna luctus suscipit. :grinning_face:" , 100, 100, Color.WHITE, true
//        );
    }
}
