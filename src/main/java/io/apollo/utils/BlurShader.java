package io.apollo.utils;

public class BlurShader extends ShaderProgram {
    private static final String VERTEX_FILE = "resources/shaders/hblur.vsh";
    private static final String FRAGMENT_FILE = "resources/shaders/hblur.fsh";

    public BlurShader() {
        super(VERTEX_FILE, FRAGMENT_FILE);
    }

    @Override
    protected void bindAttributes() {
        // super.bindAttribute(0, "Something")
    }
}
