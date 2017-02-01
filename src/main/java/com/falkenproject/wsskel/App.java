package com.falkenproject.wsskel;

public class App {

    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            (new SkeletonApplication()).run("db", "migrate", "config.yml");
            (new SkeletonApplication()).run("server", "config.yml");
        } else {
            (new SkeletonApplication()).run(args);
        }
    }
}
