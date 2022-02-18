/*
    SmoothStacks

    name: SingletonUse.java
    contributor: Sam Sessums
    description:
        "Implement a Singleton with double checked locking."

        Assumptions:

        class: SingletonUse
            Singleton()
            
        *notes:
            Not Finished and merely the slides example of what a singleton is.
*/

public class SingletonUse {
    public static class Singleton {
        //global instance
        // volatile, I assume for atomic reads & Writes
        private static volatile Singleton _instance;
        
        public static Singleton getInstance(){
            if (_instance == null){
                synchronized(Singleton.class){
                    if(_instance == null)
                        _instance = new Singleton();
                }
            }
            return _instance;
        }
        
    }

    public static void main(String[] args){
        Singleton s = new Singleton();
    }
}
