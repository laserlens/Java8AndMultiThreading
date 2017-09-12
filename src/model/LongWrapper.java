package model;

public class LongWrapper {

    private final Object key = new Object();

    private long l;

    public LongWrapper(long l){
        this.l=l;
    }

    public long getValue(){
        return l;
    }

    public void incrementValue() {
        synchronized (key) {//synchronized added to stop Run Condition since method reads and writes
            l += 1;
        }
    }
}
