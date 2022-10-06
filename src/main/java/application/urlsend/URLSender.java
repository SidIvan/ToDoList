package application.urlsend;

import application.utils.Utils;

import java.net.http.HttpClient;

public class URLSender {

    public final static Utils connection = new Utils();
    public final static HttpClient client = HttpClient.newHttpClient();

}
