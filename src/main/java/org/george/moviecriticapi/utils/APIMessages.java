package org.george.moviecriticapi.utils;

public class APIMessages {
    public final static String
            INVALID_REQUEST_MSG = "Invalid request.";
    public static final String
            BAD_REQUEST_MSG = "Request not valid due to field validation error (check data).";

    public final static String
            INVALID_REQUEST_USER_NOT_FOUND_DSC = "The user was not found on the server.";
    public final static String
            INVALID_REQUEST_EMAIL_ALREADY_REGISTERED_DSC = "The email was already registered on the server.";
    public static final String
            INVALID_REQUEST_INCORRECT_PROFILE_DSC = "The operation only supports the reader profile.";
    public static final String
            INVALID_REQUEST_INVALID_SCORE_DSC = "Invalid score (should be 0).";

    public static final String
            INVALID_REQUEST_MOVIE_NOT_FOUND_DSC = "The movie was not found on the server.";
    public static final String
            INVALID_REQUEST_MOVIE_TITLE_NOT_FOUND_DSC = "A movie with this title was not found on the server.";
    public static final String
            INVALID_REQUEST_USER_NOT_AUTH_DSC = "User not authenticated.";
    public static final String
            INVALID_JWT_TOKEN_DSC = "Invalid JWT Token";

}
