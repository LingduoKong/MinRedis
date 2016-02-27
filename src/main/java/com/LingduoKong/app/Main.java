package com.LingduoKong.app;

import java.io.UnsupportedEncodingException;

/**
 * Created by lingduokong on 2/26/16.
 */
public class Main {

    public static void main(String[] args) {
        App app = new App();

        try {
            app.start();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
