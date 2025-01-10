package com.blogjavaspringboot.common.routing;

public final class Router {
    private static final String ROOT = "/api/";

    public static final class User {
        public static final String GET_USERS = ROOT + "users";
        public static final String GET_USER = ROOT + "users/{id}";
        public static final String CREATE_USER = ROOT + "users";
        public static final String UPDATE_USER = ROOT + "users/{id}";
        public static final String DELETE_USER = ROOT + "users/{id}";
    }

    public static final class Post {
        public static final String GET_POSTS = ROOT + "posts";
        public static final String GET_POST = ROOT + "posts/{id}";
        public static final String CREATE_POST = ROOT + "posts";
        public static final String UPDATE_POST = ROOT + "posts/{id}";
        public static final String DELETE_POST = ROOT + "posts/{id}";
    }
}
