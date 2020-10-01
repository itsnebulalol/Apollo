/*⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤
 Copyright (C) 2020-2021 developed by Icovid and Apollo Development Team.

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU Affero General Public License as published
 by the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.
 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Affero General Public License for more details.
 You should have received a copy of the GNU Affero General Public License
 along with this program.  If not, see https://www.gnu.org/licenses/.

 Contact: Icovid#3888 @ https://discord.com
 ⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤*/

package net.apolloclient.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.stream.Collectors;

/**
 * Utils to download data from data base.
 *
 * @author Icovid | Icovid#3888
 * @since b0.2
 */
public class DataUtil {

    /**
     * Download data with fallback of {@link java.io.InputStream} from
     * resources.
     *
     * @param filename name of file in database / local
     * @return string of file
     * @throws Exception any exception encountered
     */
    public static String getDataFromUrlOrLocal(String filename) throws Exception {
        try {
            URLConnection urlConnection = new URL("https://static.apolloclient.net/" + filename).openConnection();
            urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 5.1; rv:19.0) Gecko/20100101 Firefox/19.0");
            urlConnection.connect();
            BufferedReader serverResponse = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String response = serverResponse.lines().collect(Collectors.joining());
            serverResponse.close();
            return response;
        } catch (Exception exception) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(DataUtil.class.getResourceAsStream("/other/" + filename)));
            String response = bufferedReader.lines().collect(Collectors.joining());
            bufferedReader.close();
            return response;
        }
    }
}
