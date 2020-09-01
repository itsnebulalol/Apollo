/*⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤
 Copyright (C) 2020-2021 developed by Icovid and Apollo Development Team
 All Contributors can be found in the README.md

 JsonUtils.java is part of Apollo Client. 8/31/20, 10:34 PM

 JsonUtils.java can not be copied and/or distributed without the express
 permission of Icovid

 Contact: Icovid#3888 @ https://discord.com
 ⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤*/

package io.apollo.utils;

import io.apollo.Apollo;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

/** Various functions to help with JSON objects.
 * @author Icovid | Icovid#3888
 * @since 1.0.0 **/
public class JsonUtils {

    /** Parses JSON object from file.
     * @param file {@link File} to be parsed
     * @return {@link JSONObject} that's been parsed **/
    public static JSONObject readJSONFromFile(File file) {
        try { return (JSONObject) new JSONParser().parse(new FileReader(file)); }
        catch (ParseException|IOException parseException) { return new JSONObject(); }
    }

    /** Parses JSON object from file.
     * @param inputStream {@link InputStream} to be parsed
     * @return {@link JSONObject} that's been parsed **/
    public static JSONObject readJSONFromInputStream(InputStream inputStream) {
        StringBuilder jsonString = new StringBuilder("");
        String str;
        try { while ((str = new BufferedReader(new InputStreamReader(inputStream)).readLine()) != null) jsonString.append(str);
            return (JSONObject) new JSONParser().parse(jsonString.toString()); }
        catch (ParseException|IOException parseException) { return new JSONObject(); }
    }

    /** get JSON object from a path.
     * @param jsonPath path of object split with "/"
     * @param jsonObject object to parse
     * @return {@link JSONObject} that's been discovered **/
    public static JSONObject getOrCreateJSONObject(JSONObject jsonObject, String jsonPath) {
        JSONObject tempObject = jsonObject;
        for (String objectsName : jsonPath.split("/")) {
            if (tempObject.get(objectsName) == null) tempObject.put(objectsName, new JSONObject());
            else tempObject = (JSONObject) jsonObject.get(objectsName);
        }
        return tempObject;
    }


    public static void writeJSONtoFile(JSONObject jsonObject, File file) {
        try { new FileWriter(file).write(jsonObject.toJSONString());
        } catch (IOException ignored) { }
    }
}