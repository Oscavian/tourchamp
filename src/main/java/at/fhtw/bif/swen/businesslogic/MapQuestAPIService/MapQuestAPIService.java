package at.fhtw.bif.swen.businesslogic.MapQuestAPIService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;


public class MapQuestAPIService {
    //usage:
    //getTourData -> retrieve session, distance and boundingbox

    //buildStaticMapRequests builds the map URL for our frontend
    //todo: config file for api key
    private static final String ROUTE_API = "https://www.mapquestapi.com/directions/v2/route";
    private static final String STATIC_MAP_API = "https://www.mapquestapi.com/staticmap/v5/map";
    private static final String API_KEY = "4g6z4y2ylLgCHzlKX4xnHzdGrsQ03IGG";

    public static CompletableFuture<TourMapData> getTourData(String from, String to) throws URISyntaxException, JsonProcessingException {
        String requestUrl = ROUTE_API + "?key=" + API_KEY + "&from=" + from + "&to=" + to;
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(new URI(requestUrl)).build();
        System.out.println(requestUrl);
        return httpClient.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString())
                .thenApply(
                        stringHttpResponse -> {
                            try {
                                return parseResponse(stringHttpResponse.body());
                            } catch (JsonProcessingException e) {
                                e.printStackTrace();
                            }
                            return null;
                        });
    }

    private static TourMapData parseResponse(String toParse) throws JsonProcessingException {
        var objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(toParse).path("route");
        JsonNode boundingBox = rootNode.path("boundingBox");

        return new TourMapData(rootNode.path("sessionId").asText(),
                rootNode.path("distance").asInt(),
                boundingBox.path("ul").path("lat").asText() + "," +
                        boundingBox.path("ul").path("lng").asText() + "," +
                        boundingBox.path("lr").path("lat").asText() + "," +
                        boundingBox.path("lr").path("lng").asText());
    }

    public static String buildStaticMapRequest(TourMapData tourMapData) {
        System.out.println(tourMapData.getBoundingBox());
        return STATIC_MAP_API + "?key=" + API_KEY +
                "&session=" + tourMapData.getSessionId() +
                "&boundingBox=" + tourMapData.getBoundingBox() +
                "&size=500,400" +
                "&format=jpg90";
    }
}
