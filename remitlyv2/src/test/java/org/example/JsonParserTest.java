package org.example;

import com.google.gson.JsonParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JsonParserTest {
    @Test
    public void testJsonParser(){
        String jsonString1 = "{\"PolicyName\":\"root\",\"PolicyDocument\":{\"Version\":\"2012-10-17\",\"Statement\":[{\"Sid\":\"IamListAccess\",\"Effect\":\"Allow\",\"Action\":[\"iam:ListRoles\",\"iam:ListUsers\"],\"Resource\":\"*\"}]}}";
        String jsonString2 = "{\"PolicyName\":\"root\",\"PolicyDocument\":{\"Version\":\"2012-10-17\",\"Statement\":[{\"Sid\":\"IamListAccess\",\"Effect\":\"Allow\",\"Action\":[\"iam:ListRoles\",\"iam:ListUsers\"],\"Resource\":\"\"}]}}";
        Assertions.assertTrue(Main.VerifyJson(JsonParser.parseString(jsonString1).getAsJsonObject()));
        Assertions.assertFalse(Main.VerifyJson(JsonParser.parseString(jsonString2).getAsJsonObject()));
    }
}
