package cn.peng.studygodpath.design.build.factorymethod;

/**
 * Created by remote on 2017/7/10.
 */
public class SenderInstance implements Sender{

    public static final String SEND_SUCCESS = "发送成功";
    public static final String SEND_FAIL = "发送失败";

    private Sender sender;

    public SenderInstance(Sender sender){
        this.sender = sender;
    }

    public boolean send(){
        if(null != sender){
            if (sender.send()){
                System.out.println(sender.getSenderName() + SEND_SUCCESS);
                return true;
            }else{
                System.out.println(sender.getSenderName() + SEND_FAIL);
                return false;
            }
        }
        return false;
    }

    public Sender getSender() {
        return sender;
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }

    public SenderEnum getSenderName(){
        if(null != sender){
            return this.sender.getSenderName();
        }
        return null;
    }
}
