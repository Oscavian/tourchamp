package at.fhtw.bif.swen.presentation.service.MapQuestAPIService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MapQuestAPIServiceTest {

    @BeforeEach
    void setUp() {

    }

    @Test
    void getTourData_ReturnsCompletableFutureWithTourMapData() throws URISyntaxException, ExecutionException, InterruptedException {
        // Arrange
        String from = "LocationA";
        String to = "LocationB";
        String mockResponse = "{\"route\":{\"sessionId\":\"AHgA5wcAAJwAAAAPAAAAAgAAAJEAAAB42mOYyMDAyMTAwMCekVqUapWc6_L8LwsDFHAsr1jdXR_smgSiG4A0AxYA06j076osiF8sxjKNIU1lSXfAN_ek2UDaGUgz4ABLOj2sQJIO3UKPQloZlAQdNRQZALTsH-OrPBJG:car\",\"realTime\":819,\"distance\":2.9726,\"time\":609,\"formattedTime\":\"00:10:09\",\"hasHighway\":false,\"hasTollRoad\":false,\"hasBridge\":false,\"hasSeasonalClosure\":false,\"hasTunnel\":false,\"hasFerry\":false,\"hasUnpaved\":false,\"hasTimedRestriction\":false,\"hasCountryCross\":false,\"legs\":[{\"index\":0,\"hasTollRoad\":false,\"hasHighway\":false,\"hasBridge\":false,\"hasUnpaved\":false,\"hasTunnel\":false,\"hasSeasonalClosure\":false,\"hasFerry\":false,\"hasCountryCross\":false,\"hasTimedRestriction\":false,\"distance\":2.9726,\"time\":819,\"formattedTime\":\"00:13:39\",\"origIndex\":0,\"origNarrative\":\"\",\"destIndex\":0,\"destNarrative\":\"\",\"maneuvers\":[{\"index\":0,\"distance\":0.1858,\"narrative\":\"Head northwest on Erdbergstraße. Go for 0.2 mi.\",\"time\":63,\"direction\":2,\"directionName\":\"Northwest\",\"signs\":[],\"maneuverNotes\":[],\"formattedTime\":\"00:01:03\",\"transportMode\":\"car\",\"startPoint\":{\"lat\":48.19332,\"lng\":16.41067},\"turnType\":0,\"attributes\":0,\"iconUrl\":\"\",\"streets\":[\"Erdbergstraße\"],\"mapUrl\":\"https://www.mapquestapi.com/staticmap/v5/map?key=4g6z4y2ylLgCHzlKX4xnHzdGrsQ03IGG&size=225,160&locations=48.19332,16.41067|marker-1||48.19527999999999,16.40796|marker-2||&center=48.1943,16.409315&defaultMarker=none&zoom=15&session=AHgA5wcAAJwAAAAPAAAAAgAAAJEAAAB42mOYyMDAyMTAwMCekVqUapWc6_L8LwsDFHAsr1jdXR_smgSiG4A0AxYA06j076osiF8sxjKNIU1lSXfAN_ek2UDaGUgz4ABLOj2sQJIO3UKPQloZlAQdNRQZALTsH-OrPBJG:car\"},{\"index\":1,\"distance\":0.5773,\"narrative\":\"Turn left onto Schlachthausgasse (B221). Go for 0.6 mi.\",\"time\":142,\"direction\":6,\"directionName\":\"Southwest\",\"signs\":[],\"maneuverNotes\":[],\"formattedTime\":\"00:02:22\",\"transportMode\":\"car\",\"startPoint\":{\"lat\":48.19527999999999,\"lng\":16.40796},\"turnType\":6,\"attributes\":0,\"iconUrl\":\"\",\"streets\":[\"B221\",\"Schlachthausgasse\",\"Landstraßer Hauptstraße\"],\"mapUrl\":\"https://www.mapquestapi.com/staticmap/v5/map?key=4g6z4y2ylLgCHzlKX4xnHzdGrsQ03IGG&size=225,160&locations=48.19527999999999,16.40796|marker-2||48.189049999999995,16.399750000000008|marker-3||&center=48.19216499999999,16.403855000000004&defaultMarker=none&zoom=13&session=AHgA5wcAAJwAAAAPAAAAAgAAAJEAAAB42mOYyMDAyMTAwMCekVqUapWc6_L8LwsDFHAsr1jdXR_smgSiG4A0AxYA06j076osiF8sxjKNIU1lSXfAN_ek2UDaGUgz4ABLOj2sQJIO3UKPQloZlAQdNRQZALTsH-OrPBJG:car\"},{\"index\":2,\"distance\":1.2111,\"narrative\":\"Turn right onto Rennweg toward Zentrum. Go for 1.2 mi.\",\"time\":278,\"direction\":7,\"directionName\":\"West\",\"signs\":[],\"maneuverNotes\":[],\"formattedTime\":\"00:04:38\",\"transportMode\":\"car\",\"startPoint\":{\"lat\":48.189049999999995,\"lng\":16.399750000000008},\"turnType\":2,\"attributes\":0,\"iconUrl\":\"\",\"streets\":[\"Rennweg\"],\"mapUrl\":\"https://www.mapquestapi.com/staticmap/v5/map?key=4g6z4y2ylLgCHzlKX4xnHzdGrsQ03IGG&size=225,160&locations=48.189049999999995,16.399750000000008|marker-3||48.19810000000001,16.377570000000006|marker-4||&center=48.193575,16.38866000000001&defaultMarker=none&zoom=12&session=AHgA5wcAAJwAAAAPAAAAAgAAAJEAAAB42mOYyMDAyMTAwMCekVqUapWc6_L8LwsDFHAsr1jdXR_smgSiG4A0AxYA06j076osiF8sxjKNIU1lSXfAN_ek2UDaGUgz4ABLOj2sQJIO3UKPQloZlAQdNRQZALTsH-OrPBJG:car\"},{\"index\":3,\"distance\":0.1007,\"narrative\":\"Continue on Schwarzenbergplatz. Go for 0.1 mi.\",\"time\":28,\"direction\":2,\"directionName\":\"Northwest\",\"signs\":[],\"maneuverNotes\":[],\"formattedTime\":\"00:00:28\",\"transportMode\":\"car\",\"startPoint\":{\"lat\":48.19810000000001,\"lng\":16.377570000000006},\"turnType\":0,\"attributes\":0,\"iconUrl\":\"\",\"streets\":[\"Schwarzenbergplatz\"],\"mapUrl\":\"https://www.mapquestapi.com/staticmap/v5/map?key=4g6z4y2ylLgCHzlKX4xnHzdGrsQ03IGG&size=225,160&locations=48.19810000000001,16.377570000000006|marker-4||48.19923000000001,16.376230000000007|marker-5||&center=48.198665000000005,16.376900000000006&defaultMarker=none&zoom=16&session=AHgA5wcAAJwAAAAPAAAAAgAAAJEAAAB42mOYyMDAyMTAwMCekVqUapWc6_L8LwsDFHAsr1jdXR_smgSiG4A0AxYA06j076osiF8sxjKNIU1lSXfAN_ek2UDaGUgz4ABLOj2sQJIO3UKPQloZlAQdNRQZALTsH-OrPBJG:car\"},{\"index\":4,\"distance\":0.1932,\"narrative\":\"Turn slightly right onto Schwarzenbergplatz. Go for 0.2 mi.\",\"time\":95,\"direction\":2,\"directionName\":\"Northwest\",\"signs\":[],\"maneuverNotes\":[],\"formattedTime\":\"00:01:35\",\"transportMode\":\"car\",\"startPoint\":{\"lat\":48.19923000000001,\"lng\":16.376230000000007},\"turnType\":1,\"attributes\":0,\"iconUrl\":\"\",\"streets\":[\"Schwarzenbergplatz\"],\"mapUrl\":\"https://www.mapquestapi.com/staticmap/v5/map?key=4g6z4y2ylLgCHzlKX4xnHzdGrsQ03IGG&size=225,160&locations=48.19923000000001,16.376230000000007|marker-5||48.20162,16.374240000000004|marker-6||&center=48.200425,16.375235000000004&defaultMarker=none&zoom=15&session=AHgA5wcAAJwAAAAPAAAAAgAAAJEAAAB42mOYyMDAyMTAwMCekVqUapWc6_L8LwsDFHAsr1jdXR_smgSiG4A0AxYA06j076osiF8sxjKNIU1lSXfAN_ek2UDaGUgz4ABLOj2sQJIO3UKPQloZlAQdNRQZALTsH-OrPBJG:car\"},{\"index\":5,\"distance\":0.2268,\"narrative\":\"Turn left onto Kärntner Ring. Go for 0.2 mi.\",\"time\":71,\"direction\":7,\"directionName\":\"West\",\"signs\":[],\"maneuverNotes\":[],\"formattedTime\":\"00:01:11\",\"transportMode\":\"car\",\"startPoint\":{\"lat\":48.20162,\"lng\":16.374240000000004},\"turnType\":6,\"attributes\":0,\"iconUrl\":\"\",\"streets\":[\"Kärntner Ring\"],\"mapUrl\":\"https://www.mapquestapi.com/staticmap/v5/map?key=4g6z4y2ylLgCHzlKX4xnHzdGrsQ03IGG&size=225,160&locations=48.20162,16.374240000000004|marker-6||48.20234,16.369580000000006|marker-7||&center=48.20198,16.371910000000007&defaultMarker=none&zoom=15&session=AHgA5wcAAJwAAAAPAAAAAgAAAJEAAAB42mOYyMDAyMTAwMCekVqUapWc6_L8LwsDFHAsr1jdXR_smgSiG4A0AxYA06j076osiF8sxjKNIU1lSXfAN_ek2UDaGUgz4ABLOj2sQJIO3UKPQloZlAQdNRQZALTsH-OrPBJG:car\"},{\"index\":6,\"distance\":0.0957,\"narrative\":\"Turn right onto Kärntner Straße. Go for 505 ft.\",\"time\":34,\"direction\":3,\"directionName\":\"Northeast\",\"signs\":[],\"maneuverNotes\":[],\"formattedTime\":\"00:00:34\",\"transportMode\":\"car\",\"startPoint\":{\"lat\":48.20234,\"lng\":16.369580000000006},\"turnType\":2,\"attributes\":0,\"iconUrl\":\"\",\"streets\":[\"Kärntner Straße\"],\"mapUrl\":\"https://www.mapquestapi.com/staticmap/v5/map?key=4g6z4y2ylLgCHzlKX4xnHzdGrsQ03IGG&size=225,160&locations=48.20234,16.369580000000006|marker-7||48.20361,16.370320000000007|marker-8||&center=48.202974999999995,16.369950000000006&defaultMarker=none&zoom=16&session=AHgA5wcAAJwAAAAPAAAAAgAAAJEAAAB42mOYyMDAyMTAwMCekVqUapWc6_L8LwsDFHAsr1jdXR_smgSiG4A0AxYA06j076osiF8sxjKNIU1lSXfAN_ek2UDaGUgz4ABLOj2sQJIO3UKPQloZlAQdNRQZALTsH-OrPBJG:car\"},{\"index\":7,\"distance\":0.046,\"narrative\":\"Turn left onto Philharmonikerstraße. Go for 243 ft.\",\"time\":12,\"direction\":7,\"directionName\":\"West\",\"signs\":[],\"maneuverNotes\":[],\"formattedTime\":\"00:00:12\",\"transportMode\":\"car\",\"startPoint\":{\"lat\":48.20361,\"lng\":16.370320000000007},\"turnType\":6,\"attributes\":0,\"iconUrl\":\"\",\"streets\":[\"Philharmonikerstraße\"],\"mapUrl\":\"https://www.mapquestapi.com/staticmap/v5/map?key=4g6z4y2ylLgCHzlKX4xnHzdGrsQ03IGG&size=225,160&locations=48.20361,16.370320000000007|marker-8||48.203829999999996,16.369380000000003|marker-9||&center=48.20372,16.369850000000007&defaultMarker=none&zoom=16&session=AHgA5wcAAJwAAAAPAAAAAgAAAJEAAAB42mOYyMDAyMTAwMCekVqUapWc6_L8LwsDFHAsr1jdXR_smgSiG4A0AxYA06j076osiF8sxjKNIU1lSXfAN_ek2UDaGUgz4ABLOj2sQJIO3UKPQloZlAQdNRQZALTsH-OrPBJG:car\"},{\"index\":8,\"distance\":0.0155,\"narrative\":\"Continue on Albertinaplatz. Go for 82 ft.\",\"time\":5,\"direction\":7,\"directionName\":\"West\",\"signs\":[],\"maneuverNotes\":[],\"formattedTime\":\"00:00:05\",\"transportMode\":\"car\",\"startPoint\":{\"lat\":48.203829999999996,\"lng\":16.369380000000003},\"turnType\":0,\"attributes\":0,\"iconUrl\":\"\",\"streets\":[\"Albertinaplatz\"],\"mapUrl\":\"https://www.mapquestapi.com/staticmap/v5/map?key=4g6z4y2ylLgCHzlKX4xnHzdGrsQ03IGG&size=225,160&locations=48.203829999999996,16.369380000000003|marker-9||48.20390999999999,16.369070000000004|marker-10||&center=48.203869999999995,16.369225000000004&defaultMarker=none&zoom=16&session=AHgA5wcAAJwAAAAPAAAAAgAAAJEAAAB42mOYyMDAyMTAwMCekVqUapWc6_L8LwsDFHAsr1jdXR_smgSiG4A0AxYA06j076osiF8sxjKNIU1lSXfAN_ek2UDaGUgz4ABLOj2sQJIO3UKPQloZlAQdNRQZALTsH-OrPBJG:car\"},{\"index\":9,\"distance\":0.0385,\"narrative\":\"Turn slightly right onto Albertinaplatz. Go for 203 ft.\",\"time\":10,\"direction\":1,\"directionName\":\"North\",\"signs\":[],\"maneuverNotes\":[],\"formattedTime\":\"00:00:10\",\"transportMode\":\"car\",\"startPoint\":{\"lat\":48.20390999999999,\"lng\":16.369070000000004},\"turnType\":1,\"attributes\":0,\"iconUrl\":\"\",\"streets\":[\"Albertinaplatz\"],\"mapUrl\":\"https://www.mapquestapi.com/staticmap/v5/map?key=4g6z4y2ylLgCHzlKX4xnHzdGrsQ03IGG&size=225,160&locations=48.20390999999999,16.369070000000004|marker-10||48.204449999999994,16.369080000000004|marker-11||&center=48.204179999999994,16.369075000000002&defaultMarker=none&zoom=16&session=AHgA5wcAAJwAAAAPAAAAAgAAAJEAAAB42mOYyMDAyMTAwMCekVqUapWc6_L8LwsDFHAsr1jdXR_smgSiG4A0AxYA06j076osiF8sxjKNIU1lSXfAN_ek2UDaGUgz4ABLOj2sQJIO3UKPQloZlAQdNRQZALTsH-OrPBJG:car\"},{\"index\":10,\"distance\":0.0367,\"narrative\":\"Continue on Tegetthoffstraße. Go for 194 ft.\",\"time\":9,\"direction\":3,\"directionName\":\"Northeast\",\"signs\":[],\"maneuverNotes\":[],\"formattedTime\":\"00:00:09\",\"transportMode\":\"car\",\"startPoint\":{\"lat\":48.204449999999994,\"lng\":16.369080000000004},\"turnType\":0,\"attributes\":0,\"iconUrl\":\"\",\"streets\":[\"Tegetthoffstraße\"],\"mapUrl\":\"https://www.mapquestapi.com/staticmap/v5/map?key=4g6z4y2ylLgCHzlKX4xnHzdGrsQ03IGG&size=225,160&locations=48.204449999999994,16.369080000000004|marker-11||48.204899999999995,16.369490000000003|marker-12||&center=48.204674999999995,16.369285000000005&defaultMarker=none&zoom=16&session=AHgA5wcAAJwAAAAPAAAAAgAAAJEAAAB42mOYyMDAyMTAwMCekVqUapWc6_L8LwsDFHAsr1jdXR_smgSiG4A0AxYA06j076osiF8sxjKNIU1lSXfAN_ek2UDaGUgz4ABLOj2sQJIO3UKPQloZlAQdNRQZALTsH-OrPBJG:car\"},{\"index\":11,\"distance\":0.0528,\"narrative\":\"Continue on Tegetthoffstraße. Go for 279 ft.\",\"time\":13,\"direction\":3,\"directionName\":\"Northeast\",\"signs\":[],\"maneuverNotes\":[],\"formattedTime\":\"00:00:13\",\"transportMode\":\"car\",\"startPoint\":{\"lat\":48.204899999999995,\"lng\":16.369490000000003},\"turnType\":0,\"attributes\":0,\"iconUrl\":\"\",\"streets\":[\"Tegetthoffstraße\"],\"mapUrl\":\"https://www.mapquestapi.com/staticmap/v5/map?key=4g6z4y2ylLgCHzlKX4xnHzdGrsQ03IGG&size=225,160&locations=48.204899999999995,16.369490000000003|marker-12||48.20554,16.37011|marker-13||&center=48.20522,16.3698&defaultMarker=none&zoom=16&session=AHgA5wcAAJwAAAAPAAAAAgAAAJEAAAB42mOYyMDAyMTAwMCekVqUapWc6_L8LwsDFHAsr1jdXR_smgSiG4A0AxYA06j076osiF8sxjKNIU1lSXfAN_ek2UDaGUgz4ABLOj2sQJIO3UKPQloZlAQdNRQZALTsH-OrPBJG:car\"},{\"index\":12,\"distance\":0.0485,\"narrative\":\"Continue on Neuer Markt. Go for 256 ft.\",\"time\":20,\"direction\":3,\"directionName\":\"Northeast\",\"signs\":[],\"maneuverNotes\":[],\"formattedTime\":\"00:00:20\",\"transportMode\":\"car\",\"startPoint\":{\"lat\":48.20554,\"lng\":16.37011},\"turnType\":0,\"attributes\":0,\"iconUrl\":\"\",\"streets\":[\"Neuer Markt\"],\"mapUrl\":\"https://www.mapquestapi.com/staticmap/v5/map?key=4g6z4y2ylLgCHzlKX4xnHzdGrsQ03IGG&size=225,160&locations=48.20554,16.37011|marker-13||48.206179999999996,16.37052|marker-14||&center=48.20586,16.370314999999998&defaultMarker=none&zoom=16&session=AHgA5wcAAJwAAAAPAAAAAgAAAJEAAAB42mOYyMDAyMTAwMCekVqUapWc6_L8LwsDFHAsr1jdXR_smgSiG4A0AxYA06j076osiF8sxjKNIU1lSXfAN_ek2UDaGUgz4ABLOj2sQJIO3UKPQloZlAQdNRQZALTsH-OrPBJG:car\"},{\"index\":13,\"distance\":0.0503,\"narrative\":\"Turn left onto Plankengasse. Go for 266 ft.\",\"time\":15,\"direction\":7,\"directionName\":\"West\",\"signs\":[],\"maneuverNotes\":[],\"formattedTime\":\"00:00:15\",\"transportMode\":\"car\",\"startPoint\":{\"lat\":48.206179999999996,\"lng\":16.37052},\"turnType\":6,\"attributes\":0,\"iconUrl\":\"\",\"streets\":[\"Plankengasse\"],\"mapUrl\":\"https://www.mapquestapi.com/staticmap/v5/map?key=4g6z4y2ylLgCHzlKX4xnHzdGrsQ03IGG&size=225,160&locations=48.206179999999996,16.37052|marker-14||48.206559999999996,16.3696|marker-15||&center=48.20636999999999,16.37006&defaultMarker=none&zoom=16&session=AHgA5wcAAJwAAAAPAAAAAgAAAJEAAAB42mOYyMDAyMTAwMCekVqUapWc6_L8LwsDFHAsr1jdXR_smgSiG4A0AxYA06j076osiF8sxjKNIU1lSXfAN_ek2UDaGUgz4ABLOj2sQJIO3UKPQloZlAQdNRQZALTsH-OrPBJG:car\"},{\"index\":14,\"distance\":0.0938,\"narrative\":\"Turn right onto Spiegelgasse. Go for 495 ft.\",\"time\":24,\"direction\":3,\"directionName\":\"Northeast\",\"signs\":[],\"maneuverNotes\":[],\"formattedTime\":\"00:00:24\",\"transportMode\":\"car\",\"startPoint\":{\"lat\":48.206559999999996,\"lng\":16.3696},\"turnType\":2,\"attributes\":0,\"iconUrl\":\"\",\"streets\":[\"Spiegelgasse\"],\"mapUrl\":\"https://www.mapquestapi.com/staticmap/v5/map?key=4g6z4y2ylLgCHzlKX4xnHzdGrsQ03IGG&size=225,160&locations=48.206559999999996,16.3696|marker-15||48.207799,16.370414|marker-16||&center=48.207179499999995,16.370007&defaultMarker=none&zoom=16&session=AHgA5wcAAJwAAAAPAAAAAgAAAJEAAAB42mOYyMDAyMTAwMCekVqUapWc6_L8LwsDFHAsr1jdXR_smgSiG4A0AxYA06j076osiF8sxjKNIU1lSXfAN_ek2UDaGUgz4ABLOj2sQJIO3UKPQloZlAQdNRQZALTsH-OrPBJG:car\"},{\"index\":15,\"distance\":0,\"narrative\":\"Arrive at Spiegelgasse.\",\"time\":0,\"direction\":0,\"directionName\":\"None\",\"signs\":[],\"maneuverNotes\":[],\"formattedTime\":\"00:00:00\",\"transportMode\":\"car\",\"startPoint\":{\"lat\":48.207799,\"lng\":16.370414},\"turnType\":0,\"attributes\":0,\"iconUrl\":\"\",\"streets\":[]}]}],\"options\":{\"routeType\":\"FASTEST\",\"narrativeType\":\"text\",\"enhancedNarrative\":false,\"walkingSpeed\":-1,\"highwayEfficiency\":22,\"avoids\":false,\"generalize\":-1,\"shapeFormat\":\"raw\",\"unit\":\"M\",\"locale\":\"en_US\",\"useTraffic\":false,\"timeType\":0,\"dateType\":0,\"doReverseGeocode\":true,\"manMaps\":true,\"sideOfStreetDisplay\":true},\"boundingBox\":{\"ul\":{\"lat\":48.207799,\"lng\":16.368970000000004},\"lr\":{\"lat\":48.189049999999995,\"lng\":16.41067}},\"routeWarnings\":[],\"name\":\"Rennweg and B221\",\"maxRoutes\":\"\",\"locations\":[{\"street\":\"Erdbergstraße\",\"adminArea6\":\"\",\"adminArea6Type\":\"Neighborhood\",\"adminArea5\":\"Wien\",\"adminArea5Type\":\"City\",\"adminArea4\":\"Wien\",\"adminArea4Type\":\"County\",\"adminArea3\":\"W\",\"adminArea3Type\":\"State\",\"adminArea1\":\"AT\",\"adminArea1Type\":\"Country\",\"postalCode\":\"\",\"geocodeQualityCode\":\"B1BAA\",\"geocodeQuality\":\"STREET\",\"dragPoint\":false,\"sideOfStreet\":\"N\",\"linkId\":\"0\",\"unknownInput\":\"\",\"type\":\"s\",\"latLng\":{\"lat\":48.19332,\"lng\":16.41067},\"displayLatLng\":{\"lat\":48.19332,\"lng\":16.41067},\"mapUrl\":\"\"},{\"street\":\"3 Spiegelgasse\",\"adminArea6\":\"1. Bezirk-Innere Stadt\",\"adminArea6Type\":\"Neighborhood\",\"adminArea5\":\"Wien\",\"adminArea5Type\":\"City\",\"adminArea4\":\"Wien\",\"adminArea4Type\":\"County\",\"adminArea3\":\"W\",\"adminArea3Type\":\"State\",\"adminArea1\":\"AT\",\"adminArea1Type\":\"Country\",\"postalCode\":\"1010\",\"geocodeQualityCode\":\"P1AAA\",\"geocodeQuality\":\"POINT\",\"dragPoint\":false,\"sideOfStreet\":\"R\",\"linkId\":\"0\",\"unknownInput\":\"\",\"type\":\"s\",\"latLng\":{\"lat\":48.2078,\"lng\":16.37041},\"displayLatLng\":{\"lat\":48.20778,\"lng\":16.37055},\"mapUrl\":\"\"}],\"locationSequence\":[0,1]},\"info\":{\"statuscode\":0,\"copyright\":{\"text\":\"© 2022 MapQuest, Inc.\",\"imageUrl\":\"http://api.mqcdn.com/res/mqlogo.gif\",\"imageAltText\":\"© 2022 MapQuest, Inc.\"},\"messages\":[]}}"; // Replace with your expected response


        HttpClient httpClientMock = mock(HttpClient.class);
        HttpResponse<String> httpResponseMock = mock(HttpResponse.class);
        when(httpResponseMock.body()).thenReturn(mockResponse);
        CompletableFuture<HttpResponse<String>> completedFutureMock = CompletableFuture.completedFuture(httpResponseMock);
        when(httpClientMock.sendAsync(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(completedFutureMock);

        // Act
        CompletableFuture<TourMapData> resultFuture = MapQuestAPIService.getTourData(from, to);

        // Assert
        resultFuture.thenAccept(Assertions::assertNotNull);
    }

    @Test
    void getTourData_ReturnsNullOnParsingError() throws URISyntaxException, ExecutionException, InterruptedException {
        // Arrange
        String from = "LocationA";
        String to = "LocationB";
        String mockResponse = "{\"invalidJson\"}"; // Invalid JSON to trigger parsing error

        HttpClient httpClientMock = mock(HttpClient.class);
        HttpResponse<String> httpResponseMock = mock(HttpResponse.class);
        when(httpResponseMock.body()).thenReturn(mockResponse);
        CompletableFuture<HttpResponse<String>> completedFutureMock = CompletableFuture.completedFuture(httpResponseMock);
        when(httpClientMock.sendAsync(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(completedFutureMock);

        // Act
        CompletableFuture<TourMapData> resultFuture = MapQuestAPIService.getTourData(from, to);
        TourMapData result = resultFuture.get();

        // Assert
        assertNull(result);
    }

    @Test
    void buildStaticMapRequest_ReturnsValidUrl() {

        // Arrange
        TourMapData tourMapData = new TourMapData("sessionId123",1,"boundingBox");
        String expectedUrl = "https://www.mapquestapi.com/staticmap/v5/map" +
                "?key=" + System.getProperty("MAP_API_KEY") +
                "&session=sessionId123" +
                "&boundingBox=boundingBox" +
                "&size=500,400" +
                "&format=jpg90";

        // Act
        String result = MapQuestAPIService.buildStaticMapRequest(tourMapData);

        // Assert
        assertEquals(expectedUrl, result);
    }

    @Test
    void buildStaticMapRequest_ReturnsNullForNullInput() {
        // Arrange
        TourMapData tourMapData = null;

        // Act
        String result = MapQuestAPIService.buildStaticMapRequest(tourMapData);

        // Assert
        assertNull(result);
    }
}