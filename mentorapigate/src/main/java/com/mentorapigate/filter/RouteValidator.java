package com.mentorapigate.filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Predicate;

@Component
public class RouteValidator {

    public static final List<String> openApiEndpoints = List.of(
            "/auth/register",
            "/auth/token",
            "/eureka",
            "/admins/welcome",
            "/admins/welcome",
            "/users/welcome",
            "/userown/welcome",
            "/mentors/welcome",
            "/technicalCourses/welcome",
            "/trainingTrackers/welcome",
            "/jwt/welcome",
            "/jwt/token",
            "/persons/welcome"
    );

    public Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndpoints
                    .stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));

}
