/*
 * Copyright [2006] [University Corporation for Advanced Internet Development, Inc.]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.opensaml.util;

import java.net.MalformedURLException;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.opensaml.util.http.HttpSupport;

/**
 * Utility class for building URLs. May also be used to parse a URL into its invidual components. All components will be
 * converted UTF-8 encoding and then application/x-www-form-urlencoded when built.
 * 
 * This class is not thread-safe.
 */
public class URLBuilder {

    /** URL schema (http, https, etc). */
    private String scheme;

    /** User name in the URL. */
    private String username;

    /** Password in the URL. */
    private String password;

    /** Host for the URL. */
    private String host;

    /** URL port number. */
    private int port;

    /** URL path. */
    private String path;

    /** Parameters in the query string. */
    private List<Pair<String, String>> queryParams;

    /** URL fragment. */
    private String fragement;

    /**
     * Constructor.
     */
    public URLBuilder() {
        queryParams = new ArrayList<Pair<String, String>>();
    }

    /**
     * Constructor.
     * 
     * @param baseURL URL to parse and use as basis for creating other URLs
     * 
     * @throws IllegalArgumentException thrown if the given base URL is not well formed
     */
    public URLBuilder(String baseURL) {
        try {
            URL url = new URL(baseURL);

            setScheme(url.getProtocol());

            String userInfo = url.getUserInfo();
            if (!StringSupport.isNullOrEmpty(userInfo)) {
                if (userInfo.contains(":")) {
                    String[] userInfoComps = userInfo.split(":");
                    setUsername(HttpSupport.urlDecode(userInfoComps[0]));
                    setPassword(HttpSupport.urlDecode(userInfoComps[1]));
                } else {
                    setUsername(userInfo);
                }
            }

            setHost(url.getHost());
            setPort(url.getPort());
            setPath(url.getPath());

            queryParams = new ArrayList<Pair<String, String>>();
            String queryString = url.getQuery();
            if (!StringSupport.isNullOrEmpty(queryString)) {
                String[] queryComps = queryString.split("&");
                String queryComp;
                String[] paramComps;
                String paramName;
                String paramValue;
                for (int i = 0; i < queryComps.length; i++) {
                    queryComp = queryComps[i];
                    if (!queryComp.contains("=")) {
                        paramName = HttpSupport.urlDecode(queryComp);
                        queryParams.add(new Pair<String, String>(paramName, null));
                    } else {
                        paramComps = queryComp.split("=");
                        paramName = HttpSupport.urlDecode(paramComps[0]);
                        paramValue = HttpSupport.urlDecode(paramComps[1]);
                        queryParams.add(new Pair<String, String>(paramName, paramValue));
                    }
                }
            }

            setFragment(url.getRef());
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Given URL is not well formed", e);
        }
    }

    /**
     * Gets the URL fragment in its decoded form.
     * 
     * @return URL fragment in its decoded form
     */
    public String getFragment() {
        return fragement;
    }

    /**
     * Sets the URL fragment in its decoded form.
     * 
     * @param newFragment URL fragment in its decoded form
     */
    public void setFragment(String newFragment) {
        fragement = StringSupport.trimOrNull(newFragment);
    }

    /**
     * Gets the host component of the URL.
     * 
     * @return host component of the URL
     */
    public String getHost() {
        return host;
    }

    /**
     * Sets the host component of the URL.
     * 
     * @param newHost host component of the URL
     */
    public void setHost(String newHost) {
        host = StringSupport.trimOrNull(newHost);
    }

    /**
     * Gets the user's password in the URL.
     * 
     * @return user's password in the URL
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the user's password in the URL.
     * 
     * @param newPassword user's password in the URL
     */
    public void setPassword(String newPassword) {
        password = StringSupport.trimOrNull(newPassword);
    }

    /**
     * Gets the path component of the URL.
     * 
     * @return path component of the URL
     */
    public String getPath() {
        return path;
    }

    /**
     * Sets the path component of the URL.
     * 
     * @param newPath path component of the URL
     */
    public void setPath(String newPath) {
        path = StringSupport.trimOrNull(newPath);
    }

    /**
     * Gets the port component of the URL.
     * 
     * @return port component of the URL
     */
    public int getPort() {
        return port;
    }

    /**
     * Sets the port component of the URL.
     * 
     * @param newPort port component of the URL
     */
    public void setPort(int newPort) {
        port = newPort;
    }

    /**
     * Gets the query string parameters for the URL. Params may be added and removed through the map interface.
     * 
     * @return query string parameters for the URL
     */
    public List<Pair<String, String>> getQueryParams() {
        return queryParams;
    }

    /**
     * Gets the URL scheme (http, https, etc).
     * 
     * @return URL scheme (http, https, etc)
     */
    public String getScheme() {
        return scheme;
    }

    /**
     * Sets the URL scheme (http, https, etc).
     * 
     * @param newScheme URL scheme (http, https, etc)
     */
    public void setScheme(String newScheme) {
        scheme = StringSupport.trimOrNull(newScheme);
    }

    /**
     * Gets the user name component of the URL.
     * 
     * @return user name component of the URL
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the user name component of the URL.
     * 
     * @param newUsername user name component of the URL
     */
    public void setUsername(String newUsername) {
        username = StringSupport.trimOrNull(newUsername);
    }

    /**
     * Builds a URL from the given data. The constructured URL may not be valid if sufficient information is not
     * provided. The returned URL will be appropriately encoded using application/x-www-form-urlencoded with appropriate
     * encoding of UTF-8 characters.
     * 
     * @return URL built from the given data
     */
    public String buildURL() {
        StringBuilder builder = new StringBuilder();

        if (!StringSupport.isNullOrEmpty(scheme)) {
            builder.append(scheme);
            builder.append("://");
        }

        if (!StringSupport.isNullOrEmpty(username)) {
            builder.append(username);
            if (!StringSupport.isNullOrEmpty(password)) {
                builder.append(":");
                builder.append(password);
            }

            builder.append("@");
        }

        if (!StringSupport.isNullOrEmpty(host)) {
            builder.append(host);
            if (port > 0) {
                builder.append(":");
                builder.append(Integer.toString(port));
            }
        }

        if (!StringSupport.isNullOrEmpty(path)) {
            if (!path.startsWith("/")) {
                builder.append("/");
            }
            builder.append(path);
        }

        String queryString = buildQueryString();
        if (!StringSupport.isNullOrEmpty(queryString)) {
            builder.append("?");
            builder.append(queryString);
        }

        if (!StringSupport.isNullOrEmpty(fragement)) {
            builder.append("#");
            builder.append(fragement);
        }

        return builder.toString();
    }

    /**
     * Builds the query string for the URL.
     * 
     * @return query string for the URL or null if there are now query parameters
     */
    public String buildQueryString() {
        StringBuilder builder = new StringBuilder();
        if (queryParams.size() > 0) {
            String name;
            String value;

            Pair<String, String> param;
            for (int i = 0; i < queryParams.size(); i++) {
                param = queryParams.get(i);
                name = StringSupport.trimOrNull(param.getFirst());

                if (name != null) {
                    builder.append(HttpSupport.urlEncode(name));
                    value = StringSupport.trimOrNull(param.getSecond());
                    if (value != null) {
                        builder.append("=");
                        builder.append(HttpSupport.urlEncode(value));
                    }
                    if (i < queryParams.size() - 1) {
                        builder.append("&");
                    }
                }
            }
            return builder.toString();
        }

        return null;
    }
}