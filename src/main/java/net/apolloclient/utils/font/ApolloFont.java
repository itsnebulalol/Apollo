package net.apolloclient.utils.font;

/**
 * Hold all available fonts for Apollo with there
 * italic counter parts
 *
 * <p>Bold is determined by subtracting current font order by 1</p>
 *
 * @author Icovid | Icovid#3888
 * @since b0.2
 */
public enum ApolloFont {

    // ROBOTO FONTS
    ROBOTO_BLACK("/fonts/roboto/Roboto-Black.ttf", "/fonts/roboto/Roboto-BlackItalic.ttf", 1),
    ROBOTO_BOLD("/fonts/roboto/Roboto-Bold.ttf", "/fonts/roboto/Roboto-BoldItalic.ttf", 2),
    ROBOTO_MEDIUM("/fonts/roboto/Roboto-Medium.ttf", "/fonts/roboto/Roboto-MediumItalic.ttf", 3),
    ROBOTO_REGULAR("/fonts/roboto/Roboto-Regular.ttf", "/fonts/roboto/Roboto-RegularItalic.ttf", 4),
    ROBOTO_LIGHT("/fonts/roboto/Roboto-Light.ttf", "/fonts/roboto/Roboto-LightItalic.ttf", 5),
    ROBOTO_THIN("/fonts/roboto/Roboto-Thin.ttf", "/fonts/roboto/Roboto-ThinItalic.ttf", 6),

    // MONTSERRAT FONTS
    MONTSERRAT_BLACK("/fonts/montserrat/Montserrat-Black.ttf", "/fonts/montserrat/Montserrat-BlackItalic.ttf", 8),
    MONTSERRAT_BOLD("/fonts/montserrat/Montserrat-Bold.ttf", "/fonts/montserrat/Montserrat-BoldItalic.ttf", 9),
    MONTSERRAT_MEDIUM("/fonts/montserrat/Montserrat-Medium.ttf", "/fonts/montserrat/Montserrat-MediumItalic.ttf", 10),
    MONTSERRAT_REGULAR("/fonts/montserrat/Montserrat-Regular.ttf", "/fonts/montserrat/Montserrat-RegularItalic.ttf", 11),
    MONTSERRAT_LIGHT("/fonts/montserrat/Montserrat-Light.ttf", "/fonts/montserrat/Montserrat-LightItalic.ttf", 12),
    MONTSERRAT_THIN("/fonts/montserrat/Montserrat-Thin.ttf", "/fonts/montserrat/Montserrat-ThinItalic.ttf", 13),

    // ARIAL FONTS
    ARIAL_BLACK("/fonts/arial/Arial-Black.ttf", "/fonts/arial/Arial-BlackItalic.ttf", 15),
    ARIAL_BOLD("/fonts/arial/Arial-Bold.ttf", "/fonts/arial/Arial-BoldItalic.ttf", 16),
    ARIAL_MEDIUM("/fonts/arial/Arial-Medium.ttf", "/fonts/arial/Arial-MediumItalic.ttf", 17),
    ARIAL_REGULAR("/fonts/arial/Arial-Regular.ttf", "/fonts/arial/Arial-RegularItalic.ttf", 18),
    ARIAL_LIGHT("/fonts/arial/Arial-Light.ttf", "/fonts/arial/Arial-LightItalic.ttf", 19),
    ARIAL_THIN("/fonts/arial/Arial-Thin.ttf", "/fonts/arial/Arial-ThinItalic.ttf", 20);

    public final String regular;
    public final String italic;
    public final int order;

    /**
     * @param regular normal font path
     * @param italic italic font path
     * @param order font order for bold
     */
    ApolloFont(String regular, String italic, int order) {
        this.regular = regular;
        this.italic = italic;
        this.order = order;
    }

    /**
     * Get font by order
     *
     * @param order order of font
     * @return font with given order
     */
    public static ApolloFont getByOrder(int order) {
        for (ApolloFont font : ApolloFont.values())
            if (font.order == order) return font;
            return null;
    }
}
