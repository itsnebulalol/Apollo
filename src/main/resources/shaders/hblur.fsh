/** Gaussian blur x shader.
    * @author Basilicous | Basilicous#7866
**/
/* DRAWBUFFERS: 01*/
#version 120

uniform sampler2D gcolor;
uniform float viewHeight;
varying vec2 texcoord;

float sigma = 11.0;
float blurSize = 1.0 / (viewHeight);

const float numBlurPixelsPerSide = 15.0;
const vec2 blurMultiplyVec = vec2(1.0, 0.0);

const float pi = 3.14159265;

void main()
{
	vec4 col = gl_FragData[0];
    vec3 incrementalGaussian;
    incrementalGaussian.x = 1.0 / (sqrt(2.0 * pi) * sigma);
    incrementalGaussian.y = exp(-0.5 / (sigma * sigma));
    incrementalGaussian.z = incrementalGaussian.y * incrementalGaussian.y;

    vec4 avgValue = vec4(0.0, 0.0, 0.0, 0.0);
    float coefficientSum = 0.0;

    avgValue += gl_FragData[1] * incrementalGaussian.x;
    coefficientSum += incrementalGaussian.x;
    incrementalGaussian.xy *= incrementalGaussian.yz;

    for (float i = 1.0; i <= numBlurPixelsPerSide; i++)
    {
        avgValue += texture2D(gcolor, texcoord.xy - i * blurSize *
                                blurMultiplyVec) * incrementalGaussian.x;
        avgValue += texture2D(gcolor, texcoord.xy+ i * blurSize *
                                blurMultiplyVec) * incrementalGaussian.x;
        coefficientSum += 2 * incrementalGaussian.x;
        incrementalGaussian.xy *= incrementalGaussian.yz;
    }
    vec3 color = texture2D(gcolor, texcoord).rgb;
    gl_FragData[0] = avgValue / coefficientSum;
}