package com.huawei.skywalker.server.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509ExtendedTrustManager;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.huawei.skywalker.server.client.dto.GetCollectionResponse;

public class RestClient
{
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 10000;
    private String hostname;
    private String basicAuth;

    static
    {
        disableSslVerification();
    }

    public RestClient(String hostname, String username, String password)
    {
        this.hostname = hostname;
        String userpass = username + ":" + password;
        this.basicAuth = "Basic " + new String(Base64.getEncoder().encode(userpass.getBytes()));
    }

    private static void disableSslVerification()
    {
        try
        {
            // Create a trust manager that does not validate certificate chains
            TrustManager[] trustAllCerts = new TrustManager[] {
                    new X509ExtendedTrustManager()
                    {

                        @Override
                        public void checkClientTrusted(X509Certificate[] chain, String authType)
                                throws CertificateException
                        {
                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[] chain, String authType)
                                throws CertificateException
                        {
                        }

                        @Override
                        public X509Certificate[] getAcceptedIssuers()
                        {
                            return null;
                        }

                        @Override
                        public void checkClientTrusted(X509Certificate[] chain, String authType, Socket socket)
                                throws CertificateException
                        {
                        }

                        @Override
                        public void checkClientTrusted(X509Certificate[] chain, String authType, SSLEngine engine)
                                throws CertificateException
                        {
                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[] chain, String authType, Socket socket)
                                throws CertificateException
                        {
                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[] chain, String authType, SSLEngine engine)
                                throws CertificateException
                        {
                        }

                    }
            };

            // Install the all-trusting trust manager
            // SSLContext sc = SSLContext.getInstance("SSL");
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            // Create all-trusting host name verifier
            HostnameVerifier allHostsValid = new HostnameVerifier()
            {
                public boolean verify(String hostname, SSLSession session)
                {
                    return true;
                }
            };

            // Install the all-trusting host verifier
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch (KeyManagementException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * The general HTTP GET method.
     *
     */
    public <T> ResponseEntity<T> get(String url, Class<T> responseClass)
    {
        url = "https://" + hostname + url;

        HttpURLConnection c = null;
        try
        {
            final URL u = new URL(url);
            c = (HttpURLConnection) u.openConnection();

            c.setRequestMethod("GET");
            c.setConnectTimeout(CONNECTION_TIMEOUT);
            c.setReadTimeout(READ_TIMEOUT);
            c.setDoInput(true);
            c.setRequestProperty("Content-Type", "application/json");
            c.setRequestProperty("Accept", "application/json");
            c.setRequestProperty("Authorization", basicAuth);

            c.setUseCaches(false);
            c.setAllowUserInteraction(false);
            c.connect();
            final int status = c.getResponseCode();
            BufferedReader br;
            switch (status)
            {
                case HttpURLConnection.HTTP_OK:
                    br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                    break;
                case HttpURLConnection.HTTP_BAD_REQUEST:
                    br = new BufferedReader(new InputStreamReader(c.getErrorStream()));
                    break;
                default:
                    br = new BufferedReader(new InputStreamReader(c.getErrorStream()));
                    break;
            }

            final StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null)
            {
                sb.append(line + "\n");
            }
            br.close();
            final ObjectMapper mapper = new ObjectMapper();
            mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
            return new ResponseEntity<>(mapper.readValue(sb.toString(), responseClass), HttpStatus.valueOf(status));
        }
        catch (final MalformedURLException ex)
        {
            System.out.println(ex);
        }
        catch (final IOException ex)
        {
            System.out.println(ex);
        }
        finally
        {
            if (c != null)
            {
                try
                {
                    c.disconnect();
                }
                catch (final Exception ex)
                {
                    System.out.println(ex);
                }
            }
        }
        return null;
    }

    /**
     * The general HTTP GET method.
     *
     */
    public <T> ResponseEntity<T> get(String url, TypeReference<T> responseClass)
    {
        url = "https://" + hostname + url;
        HttpURLConnection c = null;
        try
        {
            final URL u = new URL(url);
            c = (HttpURLConnection) u.openConnection();
            c.setRequestMethod("GET");
            c.setConnectTimeout(CONNECTION_TIMEOUT);
            c.setReadTimeout(READ_TIMEOUT);
            c.setDoInput(true);
            c.setRequestProperty("Content-Type", "application/json");
            c.setRequestProperty("Accept", "application/json");
            c.setRequestProperty("Authorization", basicAuth);

            c.setUseCaches(false);
            c.setAllowUserInteraction(false);
            c.connect();
            final int status = c.getResponseCode();
            BufferedReader br;
            switch (status)
            {
                case HttpURLConnection.HTTP_OK:
                    br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                    break;
                case HttpURLConnection.HTTP_BAD_REQUEST:
                    br = new BufferedReader(new InputStreamReader(c.getErrorStream()));
                    break;
                default:
                    br = new BufferedReader(new InputStreamReader(c.getErrorStream()));
                    break;
            }

            final StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null)
            {
                sb.append(line + "\n");
            }
            br.close();
            final ObjectMapper mapper = new ObjectMapper();
            mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
            final T v = mapper.readValue(sb.toString(), responseClass);
            return new ResponseEntity<>(v, HttpStatus.valueOf(status));

        }
        catch (final MalformedURLException ex)
        {
            System.out.println(ex);
        }
        catch (final IOException ex)
        {
            System.out.println(ex);
        }
        finally
        {
            if (c != null)
            {
                try
                {
                    c.disconnect();
                }
                catch (final Exception ex)
                {
                    System.out.println(ex);
                }
            }
        }
        return null;
    }

    public <R, T> ResponseEntity<T> post(String url, R request, Class<T> responseClass)
    {
        url = "https://" + hostname + url;
        HttpURLConnection c = null;
        try
        {
            final URL u = new URL(url);
            c = (HttpURLConnection) u.openConnection();
            c.setRequestMethod("POST");
            c.setConnectTimeout(CONNECTION_TIMEOUT);
            c.setReadTimeout(READ_TIMEOUT);
            c.setDoInput(true);
            c.setDoOutput(true);
            c.setRequestProperty("Content-Type", "application/json");
            c.setRequestProperty("Accept", "application/json");
            c.setRequestProperty("Authorization", basicAuth);

            c.setUseCaches(false);
            c.setAllowUserInteraction(false);
            final OutputStream os = c.getOutputStream();
            os.write(new ObjectMapper().writeValueAsBytes(request));
            os.flush();
            c.connect();
            final int status = c.getResponseCode();
            BufferedReader br;
            switch (status)
            {
                case HttpURLConnection.HTTP_OK:
                case HttpURLConnection.HTTP_CREATED:
                case HttpURLConnection.HTTP_ACCEPTED:
                    br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                    break;
                case HttpURLConnection.HTTP_BAD_REQUEST:
                    br = new BufferedReader(new InputStreamReader(c.getErrorStream()));
                    break;
                default:
                    br = new BufferedReader(new InputStreamReader(c.getErrorStream()));
                    break;
            }
            final StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null)
            {
                sb.append(line + "\n");
            }
            br.close();
            final ObjectMapper mapper = new ObjectMapper();
            mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
            return new ResponseEntity<>(mapper.readValue(sb.toString(), responseClass), HttpStatus.valueOf(status));
        }
        catch (final MalformedURLException ex)
        {
            System.out.println(ex);
        }
        catch (final IOException ex)
        {
            System.out.println(ex);
        }
        finally
        {
            if (c != null)
            {
                try
                {
                    c.disconnect();
                }
                catch (final Exception ex)
                {
                    System.out.println(ex);
                }
            }
        }
        return null;
    }

    public <R, T> ResponseEntity<T> post(String url, R request, TypeReference<T> responseClass)
    {
        url = "https://" + hostname + url;
        HttpURLConnection c = null;
        try
        {
            final URL u = new URL(url);
            c = (HttpURLConnection) u.openConnection();
            c.setRequestMethod("POST");
            c.setConnectTimeout(CONNECTION_TIMEOUT);
            c.setReadTimeout(READ_TIMEOUT);
            c.setDoInput(true);
            c.setDoOutput(true);
            c.setRequestProperty("Content-Type", "application/json");
            c.setRequestProperty("Accept", "application/json");
            c.setRequestProperty("Authorization", basicAuth);

            c.setUseCaches(false);
            c.setAllowUserInteraction(false);
            final OutputStream os = c.getOutputStream();
            os.write(new ObjectMapper().writeValueAsBytes(request));
            os.flush();
            c.connect();
            final int status = c.getResponseCode();
            BufferedReader br;
            switch (status)
            {
                case HttpURLConnection.HTTP_ACCEPTED:
                case HttpURLConnection.HTTP_OK:
                case HttpURLConnection.HTTP_CREATED:
                    br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                    break;
                case HttpURLConnection.HTTP_BAD_REQUEST:
                    br = new BufferedReader(new InputStreamReader(c.getErrorStream()));
                    break;
                default:
                    br = new BufferedReader(new InputStreamReader(c.getErrorStream()));
                    break;
            }
            final StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null)
            {
                sb.append(line + "\n");
            }
            br.close();
            final ObjectMapper mapper = new ObjectMapper();
            mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
            final T v = mapper.readValue(sb.toString(), responseClass);
            return new ResponseEntity<>(v, HttpStatus.valueOf(status));
        }
        catch (final MalformedURLException ex)
        {
            System.out.println(ex);
        }
        catch (final IOException ex)
        {
            System.out.println(ex);
        }
        finally
        {
            if (c != null)
            {
                try
                {
                    c.disconnect();
                }
                catch (final Exception ex)
                {
                    System.out.println(ex);
                }
            }
        }
        return null;
    }

    public <T> ResponseEntity<T> delete(String url, Class<T> responseClass)
    {
        url = "https://" + hostname + url;
        HttpURLConnection c = null;
        try
        {
            final URL u = new URL(url);
            c = (HttpURLConnection) u.openConnection();
            c.setRequestMethod("DELETE");
            c.setConnectTimeout(CONNECTION_TIMEOUT);
            c.setReadTimeout(READ_TIMEOUT);
            c.setDoInput(true);
            c.setDoOutput(true);
            c.setRequestProperty("Content-Type", "application/json");
            c.setRequestProperty("Accept", "application/json");
            c.setRequestProperty("Authorization", basicAuth);

            c.setUseCaches(false);
            c.setAllowUserInteraction(false);
            c.connect();
            final int status = c.getResponseCode();
            BufferedReader br;
            switch (status)
            {
                case HttpURLConnection.HTTP_BAD_REQUEST:
                    br = new BufferedReader(new InputStreamReader(c.getErrorStream()));
                    break;
                case HttpURLConnection.HTTP_ACCEPTED:
                    br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                    break;
                default:
                    br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                    break;
            }
            final StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null)
            {
                sb.append(line + "\n");
            }
            br.close();
            final ObjectMapper mapper = new ObjectMapper();
            mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
            return new ResponseEntity<>(mapper.readValue(sb.toString(), responseClass), HttpStatus.valueOf(status));
        }
        catch (final MalformedURLException ex)
        {
            System.out.println(ex);
        }
        catch (final IOException ex)
        {
            System.out.println(ex);
        }
        finally
        {
            if (c != null)
            {
                try
                {
                    c.disconnect();
                }
                catch (final Exception ex)
                {
                    System.out.println(ex);
                }
            }
        }
        return null;
    }

    public <T> ResponseEntity<T> delete(String url, TypeReference<T> responseClass)
    {
        url = "https://" + hostname + url;
        HttpURLConnection c = null;
        try
        {
            final URL u = new URL(url);
            c = (HttpURLConnection) u.openConnection();
            c.setRequestMethod("DELETE");
            c.setConnectTimeout(CONNECTION_TIMEOUT);
            c.setReadTimeout(READ_TIMEOUT);
            c.setDoInput(true);
            c.setDoOutput(true);
            c.setRequestProperty("Content-Type", "application/json");
            c.setRequestProperty("Accept", "application/json");

            c.setRequestProperty("Authorization", basicAuth);

            c.setUseCaches(false);
            c.setAllowUserInteraction(false);
            c.connect();
            final int status = c.getResponseCode();
            BufferedReader br;
            switch (status)
            {
                case HttpURLConnection.HTTP_BAD_REQUEST:
                    br = new BufferedReader(new InputStreamReader(c.getErrorStream()));
                    break;
                case HttpURLConnection.HTTP_ACCEPTED:
                    br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                    break;
                default:
                    br = new BufferedReader(new InputStreamReader(c.getErrorStream()));
                    break;
            }
            final StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null)
            {
                sb.append(line + "\n");
            }
            br.close();

            final ObjectMapper mapper = new ObjectMapper();
            mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
            final T v = mapper.readValue(sb.toString(), responseClass);
            return new ResponseEntity<>(v, HttpStatus.valueOf(status));

        }
        catch (final MalformedURLException ex)
        {
            System.out.println(ex);
        }
        catch (final IOException ex)
        {
            System.out.println(ex);
        }
        finally
        {
            if (c != null)
            {
                try
                {
                    c.disconnect();
                }
                catch (final Exception ex)
                {
                    System.out.println(ex);
                }
            }
        }
        return null;
    }

    public <T> List<T> getCollection(String url, Class<T> responseClass)
    {
        ResponseEntity<GetCollectionResponse> collection = get(url, GetCollectionResponse.class);
        if (collection.getStatusCode().equals(HttpStatus.OK))
        {
            GetCollectionResponse body = collection.getBody();
            return body.members.stream()
                    .map(each -> each.odataid)
                    .map(eachUri -> get(eachUri, responseClass))
                    .map(response -> response.getBody())
                    .collect(Collectors.toList());

        }
        return null;
    }
}
