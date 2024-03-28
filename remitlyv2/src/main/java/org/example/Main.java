package org.example;

import com.google.gson.*;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("USAGE -> .\\gradlew.bat run --args \"<name_of_file>\"");
            return;
        }
        String filePath = args[0]; // Path to the JSON file

        try (FileReader fileReader = new FileReader(filePath)) {
            JsonObject jsonObject;
            try{
                jsonObject = JsonParser.parseReader(fileReader).getAsJsonObject();
            } catch (JsonSyntaxException e){
                System.out.println("WRONG JSON SYNTAX");
                return;
            }
            if (jsonObject != null){
                try {
                    System.out.println(Main.VerifyJson(jsonObject));
                } catch (WrongJsonSyntaxException e){
                    System.out.println(e.getMessage());
                }

            }
        } catch (IOException e) {
            System.out.println("error opening a file");
        }
    }

    static Boolean VerifyJson(JsonObject jsonObject) throws WrongJsonSyntaxException {
        jsonObject = jsonObject.getAsJsonObject("PolicyDocument");
        if (jsonObject == null){
            throw new WrongJsonSyntaxException("no field PolicyDocument");
        }
        JsonArray jsonArray = jsonObject.getAsJsonArray("Statement");
        if (jsonArray == null){
            throw new WrongJsonSyntaxException("no field Statement");
        }
        jsonObject = jsonArray.get(0).getAsJsonObject();
        if (jsonObject == null){
            throw new WrongJsonSyntaxException("no elements in Statement array");
        }
        JsonPrimitive jsonPrimitive = jsonObject.getAsJsonPrimitive("Resource");
        if (jsonPrimitive == null){
            throw new WrongJsonSyntaxException("no Resource field");
        }
        String resources = jsonPrimitive.toString();
        return resources.substring(1, resources.length() - 1).equals("*");
    }
}