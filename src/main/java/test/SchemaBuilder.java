/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.HashMap;
import javax.persistence.Persistence;

public class SchemaBuilder {
    public static void main(String[] args) {
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.sql-load-script-source", "Scripts/ClearDB.sql");
        Persistence.generateSchema("jpapu", properties);
        
        properties.remove("javax.persistence.sql-load-script-source");
        properties.put("javax.persistence.sql-load-script-source", "Scripts/PopulateCityInfo.sql");
        Persistence.generateSchema("jpapu", properties);
        
    }
}
