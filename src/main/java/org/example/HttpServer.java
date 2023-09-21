package org.example;


import static spark.Spark.port;
import static spark.Spark.get;
import java.lang.Math;
public class HttpServer {

    public static void main(String... args){
        port(getPort());

        get("operaciones", (req,res) -> new OperacionesPage().getPage());

        get("sin", (req,res) -> Math.sin(Double.parseDouble(req.queryParams("value"))));

        get("cos", (req,res) -> Math.cos(Double.parseDouble(req.queryParams("value"))));

        get("ispalindrome", (req, res) -> {
            String request = req.queryParams("value");
            boolean isPalindrome = true;
            String result = "es palindromo";

            for (int i = 0; i < request.length(); i++) {
                if (!(request.charAt(i) == request.charAt(request.length() - 1 - i))) {
                    isPalindrome = false;
                    result = "no es palindromo";
                    break;
                }
            }

            return result;
        });

        get("vector", (req,res) -> {
            float value1 = Float.parseFloat(req.queryParams("value1"));
            float value2 = Float.parseFloat(req.queryParams("value2"));
            return Math.sqrt(Math.pow(value1, 2)+Math.pow(value2, 2));
        });
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 35000;
    }

}