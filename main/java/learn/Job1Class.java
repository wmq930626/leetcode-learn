package learn;

/**
 * @author WMQ
 * @date 2020/12/11
 * @description
 */
public class Job1Class implements JobClass{

    private JobClass jobClass;



    public Job1Class(JobClass jobClass) {
        this.jobClass = jobClass;
    }

    public void handler(boolean can){
        if (can){
            System.out.println("=============");
        }else {
            jobClass.handler(true);
        }
    }
}
