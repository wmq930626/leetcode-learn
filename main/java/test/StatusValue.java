package test;

public class StatusValue {

    private int status = 0;

    public static void main(String[] args) {
        StatusValue statusValue = new StatusValue();
        statusValue.setCanRead(true);
        statusValue.setCanWrite(true);
        statusValue.setCanEat(false);
        System.out.println(statusValue.canRead());
        System.out.println(statusValue.canWrite());
        System.out.println(statusValue.canEat());
        statusValue.setCanEat(true);
        statusValue.setCanWrite(false);
        statusValue.setCanRead(false);
        System.out.println(statusValue.canRead());
        System.out.println(statusValue.canWrite());
        System.out.println(statusValue.canEat());
    }

    /**
     * 0号位表示是否可读
     * @param flag
     */
    public void setCanRead(boolean flag){
        if (flag) {
            status |= (1 << 0);
        } else {
            status &= ~(1 << 0);
        }
    }

    public boolean canRead(){
        return (status & (1<<0)) > 0;
    }

    /**
     * 1号位表示是否可写
     * @param flag
     */
    public void setCanWrite(boolean flag){
        if (flag) {
            status |= (1 << 1);
        } else {
            status &= ~(1 << 1);
        }
    }

    public boolean canWrite(){
        return (status & (1<<1)) > 0;
    }

    /**
     * 2号位表示是否可吃
     * @param flag
     */
    public void setCanEat(boolean flag){
        if (flag) {
            status |= (1 << 2);
        } else {
            status &= ~(1 << 2);
        }
    }

    public boolean canEat(){
        return (status & (1<<2)) > 0;
    }
}