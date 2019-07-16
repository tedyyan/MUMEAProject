package mum.edu.ea.xing.songsmicroService.util;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.model_objects.credentials.AuthorizationCodeCredentials;

public class SpotifyUtil {
    public static SpotifyApi spotifyApi(){
        try {
            String clientId = "9bb4d61b35974a86a44f82ece4a09086";
            String clientSecret = "469e4131940745a69f828df081275ec4";

//            String accessToken = "BQC0oqa56tWCZk8pBUEg_WGvyqJz0xdNXBfr0x8lHYWqwuIf5mfXs-tniFLZLuKb4kA-qZkMLMP5EyBOwwOqEi9Q6bYhUvSIEoEmpLIBim6We0If_hYn8NYRx4WJpjYqGONRULoqGShGZidS8rbWEya9PRIqU1NT7N0ImWdli548zsjmRvC8Zk1iHDMJHtU45iidVyZMKAuhQO3IPCZnvNH48GxLR1Tz2PjUo9562HaO-mE3pRP38Lnv-RpK0TQ0WifnUROGDyTK6hz6IQ828yNCftPBOzHZjtFoYWSOMzIijmU";
            String refreshToken = "AQD-h6HmDvoAWnU3X90jpk_E11cjQ7LTRfkaaGq_8RNDjNVGbECP0x6lg-cdjSZnKeesScaBlJPzTtTtVpvoSqd9jLCHDJ8YE5enwjYcNebLEMUL1KZWVS5imy9xmwsIgNF7CA";

            SpotifyApi spotifyApi = new SpotifyApi.Builder()
                    .setClientId(clientId)
                    .setClientSecret(clientSecret)
                    .setRefreshToken(refreshToken)
                    .build();

            AuthorizationCodeCredentials authorizationCodeCredentials = spotifyApi.authorizationCodeRefresh()
                    .build().execute();

            spotifyApi.setAccessToken(authorizationCodeCredentials.getAccessToken());
            spotifyApi.setRefreshToken(authorizationCodeCredentials.getRefreshToken());
            return spotifyApi;
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
}
