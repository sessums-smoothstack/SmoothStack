/*
    SmoothStacks

    name: Q5.java
    contributor: Sam Sessums
    description:

            Question 2
            Fix the singleton

    *notes:
        

*/

//Question 6 Singleton imports
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class Q6 {
    final static class SampleSingleton {
        private static volatile Connection conn = null;
        private static volatile SampleSingleton _instance = null;
	
	    public static SampleSingleton getInstance() {
            if (_instance == null){
                synchronized(SampleSingleton.class){
                    if(_instance == null)
                        _instance = new SampleSingleton();
                }
            }
            return _instance;
	    }
	
    }

}
