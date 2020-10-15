package net.apolloclient.utils.font;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.opengl.renderer.Renderer;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Custom unicode font renderer used through out the client with
 * support for discord emoji format as well as the following formatting codes:
 * <p></p>
 * <p>COLOR CODES:</p>
 * <ul>
 * <li>{@code §0} : black text color.</li>
 * <li>{@code §1} : dark blue text color.</li>
 * <li>{@code §2} : dark green text color.</li>
 * <li>{@code §3} : dark aqua text color.</li>
 * <li>{@code §4} : dark red text color.</li>
 * <li>{@code §5} : dark purple text color.</li>
 * <li>{@code §6} : gold text color.</li>
 * <li>{@code §7} : grey text color.</li>
 * <li>{@code §8} : dark grey text color.</li>
 * <li>{@code §9} : blue text color.</li>
 * <li>{@code §a} : green text color.</li>
 * <li>{@code §b} : aqua text color.</li>
 * <li>{@code §c} : red text color.</li>
 * <li>{@code §d} : magenta text color.</li>
 * <li>{@code §e} : yellow text color.</li>
 * <li>{@code §f} : white text color.</li>
 * <li>{@code §!} : chroma text color.</li>
 * <li>{@code §r} : reset style to default.</li>
 * </ul>
 * <p>FORMAT CODES:</p>
 * <ul>
 * <li>{@code §k} : obfuscated text style.</li>
 * <li>{@code §l} : bold text style.</li>
 * <li>{@code §m} : strike through text style.</li>
 * <li>{@code §n} : underline text style.</li>
 * <li>{@code §o} : italic text style.</li>
 * </ul>
 *
 * @author Icovid | Icovid#3888
 * @since b0.2
 */
public class ApolloFontRenderer {

    // Fonts used in current renderer instance
    public final UnicodeFont unicode_regular;
    public final UnicodeFont unicode_regular_italic;
    public final UnicodeFont unicode_bold;
    public final UnicodeFont unicode_bold_italic;
    public final UnicodeFont unicode_obfuscated;

    // Emoji information.
    public static int EMOJI_ATLAS_SIZE = 8468;
    public static final HashMap<String, Emoji> emojis = new HashMap<>();

    // Cache of strings widths and heights to improve performance.
    public static final Map<String, ApolloFontRenderer> cached_renderers = new HashMap<>();

    // Resource location for emoji texture map
    private static final ResourceLocation emoji_texture = new ResourceLocation("/font/default-emojis.png");

    // Pattern and codes to implement color codes into renderer.
    private static final Pattern COLOR_CODE_PATTERN = Pattern.compile("§[0123456789abcdefklmnor!]");
    private static final Pattern EMOJI_PATTERN = Pattern.compile(":[^ :]*:");

    // Color codes for all minecraft colors and shows of those colors.
    private static final int[] COLOR_CODES = {0x333333, 0x005CDB, 0x009A72, 0x00B0B1, 0xE53941, 0xA834EC, 0xFFD300, 0xC7C1BA, 0x797A7E, 0x0088FF, 0x0DD490, 0x00EAEE, 0xFF5A61, 0xF515A0, 0xFFE262, 0xFAFAFA,};

    /**
     * @param unicode_regular unicode font of normal text
     * @param unicode_regular_italic unicode font on normal text italicized
     * @param unicode_bold unicode font of bold text
     * @param unicode_bold_italic unicode font on bold text italicized
     */
    public ApolloFontRenderer(UnicodeFont unicode_regular, UnicodeFont unicode_regular_italic, UnicodeFont unicode_bold, UnicodeFont unicode_bold_italic, UnicodeFont unicode_obfuscated) {
        this.unicode_regular        = unicode_regular;
        this.unicode_regular_italic = unicode_regular_italic;
        this.unicode_bold           = unicode_bold;
        this.unicode_bold_italic    = unicode_bold_italic;
        this.unicode_obfuscated     = unicode_obfuscated;
    }

    /**
     * Loads emoji info from {@code /fonts/default-emojis.json } and imports it into
     * the emoji hash.
     */
    public static void loadEmojis() throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(ApolloFontRenderer.class.getResourceAsStream("/fonts/default-emojis.json")));
        String reader = bufferedReader.lines().collect(Collectors.joining());
        bufferedReader.close();

        JsonObject response = new Gson().fromJson(reader, JsonObject.class);

        for (JsonElement element : response.getAsJsonArray("frames")) {
            JsonObject emoji_object = element.getAsJsonObject();

            String emoji_name = emoji_object.get("filename").getAsString();
            JsonObject emoji_frame = emoji_object.getAsJsonObject("frame");

            emojis.put(emoji_name, new Emoji(
                    emoji_frame.get("x").getAsInt(),
                    emoji_frame.get("y").getAsInt(),
                    emoji_frame.get("w").getAsInt(),
                    emoji_frame.get("h").getAsInt()));
        }
    }

    /**
     * Creates font renderer using data from {@link ApolloFont}
     *
     * @param font ApolloFont to use
     * @param size size of font
     *
     * @return font renderer created
     */
    public static ApolloFontRenderer create(ApolloFont font, float size) {
        try {
            if (cached_renderers.get(font.regular + "-" + size) != null) return cached_renderers.get(font.regular + "-" + size);

            ScaledResolution resolution = new ScaledResolution(Minecraft.getMinecraft());

            UnicodeFont unicode_obfuscated = new UnicodeFont(Font.createFont(Font.TRUETYPE_FONT, ApolloFontRenderer.class.getResourceAsStream("/fonts/GalacticAlphabet.ttf")).deriveFont(size * resolution.getScaleFactor() / 2));

            unicode_obfuscated.addAsciiGlyphs();
            unicode_obfuscated.getEffects().add(new ColorEffect(Color.WHITE));
            unicode_obfuscated.loadGlyphs();

            UnicodeFont unicode_regular = new UnicodeFont(Font.createFont(Font.TRUETYPE_FONT, ApolloFontRenderer.class.getResourceAsStream(font.regular)).deriveFont(size * resolution.getScaleFactor() / 2));

            UnicodeFont unicode_regular_italic = new UnicodeFont(Font.createFont(Font.TRUETYPE_FONT, ApolloFontRenderer.class.getResourceAsStream(font.italic)).deriveFont(size * (resolution.getScaleFactor()) / 2));

            unicode_regular.addAsciiGlyphs();
            unicode_regular.getEffects().add(new ColorEffect(Color.WHITE));
            unicode_regular.loadGlyphs();

            unicode_regular_italic.addAsciiGlyphs();
            unicode_regular_italic.getEffects().add(new ColorEffect(Color.WHITE));
            unicode_regular_italic.loadGlyphs();

            UnicodeFont unicode_bold = unicode_regular;
            UnicodeFont unicode_bold_italic = unicode_regular_italic;

            if (ApolloFont.getByOrder(font.order - 1) != null) {
                ApolloFont boldFont = ApolloFont.getByOrder(font.order - 1);

                assert boldFont != null;

                unicode_bold = new UnicodeFont(Font.createFont(Font.TRUETYPE_FONT, ApolloFontRenderer.class.getResourceAsStream(boldFont.regular)).deriveFont(size * resolution.getScaleFactor() / 2));
                unicode_bold_italic = new UnicodeFont(Font.createFont(Font.TRUETYPE_FONT, ApolloFontRenderer.class.getResourceAsStream(boldFont.italic)).deriveFont(size * resolution.getScaleFactor() / 2));

                unicode_bold.addAsciiGlyphs();
                unicode_bold.getEffects().add(new ColorEffect(Color.WHITE));
                unicode_bold.loadGlyphs();

                unicode_bold_italic.addAsciiGlyphs();
                unicode_bold_italic.getEffects().add(new ColorEffect(Color.WHITE));
                unicode_bold_italic.loadGlyphs();

                ApolloFontRenderer renderer = new ApolloFontRenderer(unicode_regular, unicode_regular_italic, unicode_bold, unicode_bold_italic, unicode_obfuscated);
                cached_renderers.put(font.regular + "-" + size, renderer);
                return renderer;
            }
            ApolloFontRenderer renderer = new ApolloFontRenderer(unicode_regular, unicode_regular_italic, unicode_regular, unicode_regular_italic, unicode_obfuscated);
            cached_renderers.put(font.regular + "-" + size, renderer);
            return renderer;
        } catch (Exception e) { throw new RuntimeException(e); }
    }

    /**
     * Draw String on screen using minecraft formatting codes
     * with {@code /n} support.
     *
     * @param str string to render
     * @param xPosition x position of text
     * @param yPosition y position of text
     * @param color color of text
     * @param shadow should text have shadow
     */
    public void drawString(String str, int xPosition, int yPosition, Color color, boolean shadow) {
        UnicodeFont active_font = this.unicode_regular;
        HashMap<int[], Emoji> emojis_to_render = new HashMap<>();

        boolean scramble_letters = false;
        boolean strikethrough    = false;
        boolean underline        = false;
        boolean is_italic        = false;
        boolean is_bold          = false;
        boolean is_chroma        = false;

        int active_color = color.hashCode();
        int default_color = active_color;

        ScaledResolution resolution = new ScaledResolution(Minecraft.getMinecraft());
        GlStateManager.bindTexture(0);
        xPosition *= resolution.getScaleFactor();
        yPosition *= resolution.getScaleFactor();

        int origin_x = xPosition;

        GlStateManager.pushMatrix();
        GlStateManager.scale(1 / (float) resolution.getScaleFactor(), 1 / (float) resolution.getScaleFactor(), 1 / (float) resolution.getScaleFactor());
        GlStateManager.color((float) color.getRed() / 255.0F, (float) color.getGreen() / 255.0F, (float) color.getBlue() / 255.0F, (float) color.getAlpha() / 255.0F);

        GlStateManager.disableLighting();
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ZERO);
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        char[] characters = str.toCharArray();
        int index = 0;

        int portion_height = unicode_regular.getHeight("abc");
        float shadow_offset = 2.6f * (float) (portion_height / 20);

        for (String line : str.split("\n")) {

            for (String portion : EMOJI_PATTERN.split(line)) {

                for (String render : COLOR_CODE_PATTERN.split(portion)) {

                    int render_width = 0;

                    if (active_font.equals(unicode_obfuscated)) render_width = (int) (render_width * 1.2f);

                    if (shadow && is_chroma) {
                        int x = xPosition;
                        for (final char c : render.toCharArray()) {
                            active_font.drawString((x + shadow_offset), (yPosition + shadow_offset), scramble_letters ? getAlphaNumericString(1) : String.valueOf(c), new org.newdawn.slick.Color(0,0,0, 75));
                            x += (active_font.getWidth(String.valueOf(c)));
                        }
                    }

                    else if (shadow) active_font.drawString((xPosition + shadow_offset), (yPosition + shadow_offset), scramble_letters ? getAlphaNumericString(render.length()) : render, new org.newdawn.slick.Color(0,0,0, 75));


                    Tessellator tessellator = Tessellator.getInstance();
                    WorldRenderer worldrenderer = tessellator.getWorldRenderer();

                    if (underline && shadow) {
                        GL11.glLineWidth(portion_height * 0.05f);
                        GlStateManager.disableTexture2D();
                        worldrenderer.begin(GL11.GL_LINES, DefaultVertexFormats.POSITION);
                        worldrenderer.pos(xPosition + (portion_height * 0.035f), yPosition + (portion_height * 1.02) + (portion_height * 0.035f), 0.0D).endVertex();
                        worldrenderer.pos(xPosition + render_width + (portion_height * 0.035f), yPosition + (portion_height * 1.02) + (portion_height * 0.035f), 0.0D).endVertex();
                        tessellator.draw();
                        GlStateManager.enableTexture2D();
                    }

                    if (strikethrough && shadow) {
                        GL11.glLineWidth(portion_height * 0.05f);
                        GlStateManager.disableTexture2D();
                        worldrenderer.begin(GL11.GL_LINES, DefaultVertexFormats.POSITION);
                        worldrenderer.pos(xPosition + (portion_height * 0.035f), yPosition + (portion_height * 0.6f) + (portion_height * 0.035f), 0.0D).endVertex();
                        worldrenderer.pos(xPosition + render_width + (portion_height * 0.035f), yPosition + (portion_height * 0.6f) + (portion_height * 0.035f), 0.0D).endVertex();
                        tessellator.draw();
                        GlStateManager.enableTexture2D();
                    }

                    if (is_chroma) {
                        int x = xPosition;
                        for (final char c : render.toCharArray()) {
                            long dif = x * 10 - yPosition * 10;
                            final long l = System.currentTimeMillis() - dif;
                            final float ff = 2000.0f;
                            final int i = Color.HSBtoRGB(l % (int)ff / ff, 0.3f, 0.9f);
                            final String tmp = String.valueOf(c);
                            active_font.drawString(x, yPosition, scramble_letters ? getAlphaNumericString(1) : tmp, new org.newdawn.slick.Color(i));
                            int char_width = (active_font.getWidth(String.valueOf(c)));
                            x += char_width;
                            render_width += char_width;
                        }
                    } else {
                        active_font.drawString(xPosition, yPosition, scramble_letters ? getAlphaNumericString(render.length()) : render, new org.newdawn.slick.Color(active_color));
                        render_width = active_font.getWidth(render);
                    }

                    if (underline) {
                        GL11.glLineWidth(portion_height * 0.05f);
                        GlStateManager.disableTexture2D();
                        worldrenderer.begin(GL11.GL_LINES, DefaultVertexFormats.POSITION);
                        worldrenderer.pos(xPosition, yPosition + (portion_height * 1.02), 0.0D).endVertex();
                        worldrenderer.pos(xPosition + render_width, yPosition + (portion_height * 1.02), 0.0D).endVertex();
                        tessellator.draw();
                        GlStateManager.enableTexture2D();
                    }

                    if (strikethrough) {
                        GL11.glLineWidth(portion_height * 0.05f);
                        GlStateManager.disableTexture2D();
                        worldrenderer.begin(GL11.GL_LINES, DefaultVertexFormats.POSITION);
                        worldrenderer.pos(xPosition, yPosition + (portion_height * 0.6f), 0.0D).endVertex();
                        worldrenderer.pos(xPosition + render_width, yPosition + (portion_height * 0.6f), 0.0D).endVertex();
                        tessellator.draw();
                        GlStateManager.enableTexture2D();
                    }

                    index += render.length();

                    xPosition += render_width;

                    if (index < characters.length && characters[index] == '§') {

                        int color_index = ("0123456789abcdef").indexOf(characters[index + 1]);

                        if (color_index >= 0) {
                            active_color = COLOR_CODES[color_index];
                            is_chroma = false;
                        }

                        switch(characters[index + 1]) {
                            case 'l' :  active_font = is_italic ? unicode_bold_italic : unicode_bold;
                                        is_bold = true;
                                        break;
                            case 'o' :  active_font = is_bold ? unicode_bold_italic : unicode_regular_italic;
                                        is_italic = true;
                                        break;
                            case 'k' :  active_font = unicode_obfuscated;
                                        scramble_letters = true;
                                        break;
                            case '!' :  active_color = java.awt.Color.HSBtoRGB(System.currentTimeMillis() * 2 % (int) 10000.0F / 10000.0F, 0.7f, 0.9f);
                                        is_chroma = true;
                                         break;
                            case 'n' :  underline = true;
                                        strikethrough = false;
                                        break;
                            case 'm' :  strikethrough = true;
                                        underline = false;
                                        break;
                            case 'r' :  active_font = unicode_regular;
                                        is_italic = false;
                                        is_bold = false;
                                        is_chroma = false;
                                        scramble_letters = false;
                                        active_color = default_color;
                                         break;
                        }
                        index += 2;
                    }
                }

                if (index < characters.length && characters[index] == ':') {

                    StringBuilder emoji_name = new StringBuilder();

                    for (int i = index + 1; i < characters.length; i++) {
                        if (characters[i] != ':')  emoji_name.append(characters[i]);
                        else break;
                    }

                    if (emojis.get(emoji_name.toString()) != null) {
                        Emoji emoji = emojis.get(emoji_name.toString());
                        int[] positions = {xPosition, (int) (yPosition +  (portion_height * 0.3f)), (int) (portion_height * 0.7f)};
                        emojis_to_render.put(positions, emoji);
                        xPosition += emoji.getWidth(positions[2]);
                    }

                    index += emoji_name.length() + 2;
                }
            }
            if (index < characters.length && characters[index] == '\n') {
                xPosition = origin_x;
                yPosition += portion_height * 1.25f;
                index++;
            }
        }

        Minecraft.getMinecraft().getTextureManager().bindTexture(emoji_texture);

        if (shadow) {
            Renderer.get().glColor4f(0, 0, 0, 0.45f);
            for (Map.Entry<int[], Emoji> entry : emojis_to_render.entrySet())
                entry.getValue().draw((int) (entry.getKey()[0] + (is_bold ? shadow_offset + 0.6f : shadow_offset)), (int) (entry.getKey()[1] + (is_bold ? shadow_offset + 0.6f : shadow_offset)), entry.getKey()[2]);
        }

        Renderer.get().glColor4f(1.0f,1.0f,1.0f,1.0f);

        for (Map.Entry<int[], Emoji> entry : emojis_to_render.entrySet())
            entry.getValue().draw(entry.getKey()[0], entry.getKey()[1], entry.getKey()[2]);

        GlStateManager.color(1F, 1F, 1F, 1F);
        GlStateManager.bindTexture(0);
        GlStateManager.popMatrix();
    }

    private String getAlphaNumericString(int str) {

        String AlphaNumericString = "abcdefghijklmnopqrstuvxyz";

        StringBuilder sb = new StringBuilder(str);

        for (int i = 0; i < str; i++) {

            int index = (int)(AlphaNumericString.length() * Math.random());

            sb.append(AlphaNumericString.charAt(index));
        }

        return sb.toString();
    }
}
