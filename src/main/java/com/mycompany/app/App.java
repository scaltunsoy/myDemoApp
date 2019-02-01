package com.mycompany.app;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.template.mustache.MustacheTemplateEngine;

/**
 * Hello world!
 *
 */
public class App 
{
    /**
     * It adds the ascii values of the characters of the strings at specified positions
     * which are indicated by 'locations' array. Then, the values in 'values' are
     * subtracted from the sum. After that, the absolute value of the result is returned.
     * 
     * If any parameter is null,
     * If any parameter is empty,
     * If any integer in 'locations' is greater then the max indice of any string or negative,
     * If any integer in 'values' is negative,
     * it returns -1. 
     * 
     * @param locations the positions of the characters of strings which is included in the computation
     * @param values the values to be subtracted from the sum of specified characters of the strings
     * @param the first string
     * @param the second string
     * @return the absolute value of the result of computation
     */
    public static int compute(Integer[] locations, ArrayList<Integer> values, String first, String second) {
        // check if any parameter is null
        if (locations == null || values == null || first == null || second == null) {
            return -1;
        }
        
        // check if any parameter is empty
        if (locations.length == 0 || values.size() == 0 || first.length() == 0 || second.length() == 0) {
            return -1;
        }
        
        final int maxIndiceOfFirstString = first.length() - 1;
        final int maxIndiceOfSecondString = second.length() - 1;
        
        // check if any location exceeds the maximum indice of any string
        for (int i = 0; i < locations.length; i++) {
            if (locations[i] > maxIndiceOfFirstString || locations[i] > maxIndiceOfSecondString) {
                return -1;
            }
        }
        
        final int sizeOfValues = values.size();
        
        // check if any value is negative
        for (int i = 0; i < sizeOfValues; i++) {
            if (values.get(i) < 0) {
                return -1;
            }
        }
        
        int sum = 0; // the sum of the characters' ascii value
        
        for (int i = 0; i < locations.length; i++) {
            sum += first.charAt(locations[i]) + second.charAt(locations[i]);
        }
        
        for (int i = 0; i < sizeOfValues; i++) {
            sum -= values.get(i);
        }
        
        return Math.abs(sum); // return the absolute value of the sum
    }
    
    public static void main(String[] args) {
        port(getHerokuAssignedPort());

        get("/", (req, res) -> "Hello, World");

        post("/compute", (req, res) -> {
          //System.out.println(req.queryParams("input1"));
          //System.out.println(req.queryParams("input2"));

          String input1 = req.queryParams("input1");
          java.util.Scanner sc1 = new java.util.Scanner(input1);
          sc1.useDelimiter("[;\r\n]+");
          java.util.ArrayList<Integer> inputList = new java.util.ArrayList<>();
          while (sc1.hasNext())
          {
            int value = Integer.parseInt(sc1.next().replaceAll("\\s",""));
            inputList.add(value);
          }
          //System.out.println(inputList);
          Integer[] inputArray = new Integer[inputList.size()];
          
          for (int i = 0; i < inputList.size(); i++) {
              inputArray[i] = inputList.get(i);
          }
          
          
          String input2 = req.queryParams("input2");
          java.util.Scanner sc2 = new java.util.Scanner(input2);
          sc2.useDelimiter("[;\r\n]+");
          java.util.ArrayList<Integer> inputList2 = new java.util.ArrayList<>();
          while (sc1.hasNext())
          {
            int value = Integer.parseInt(sc2.next().replaceAll("\\s",""));
            inputList2.add(value);
          }


          String input3 = req.queryParams("input3").replaceAll("\\s","");
          
          String input4 = req.queryParams("input4").replaceAll("\\s","");
          
          int result = App.compute(inputArray, inputList2, input3, input4);

         Map map = new HashMap();
          map.put("result", result);
          return new ModelAndView(map, "compute.mustache");
        }, new MustacheTemplateEngine());


        get("/compute",
            (rq, rs) -> {
              Map map = new HashMap();
              map.put("result", "not computed yet!");
              return new ModelAndView(map, "compute.mustache");
            },
            new MustacheTemplateEngine());
    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
}
