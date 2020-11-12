package ai.mindslab.maumacademy.common.vo;

public class SupportForm extends CommonForm {

    private int id;
    private String fromaddr;
    private String toaddr;
    private String subject;
    private String message;
    private int status;
    private String senddate;
    private String name;
    private String company;
    private String mailAddr;
    private String content;
    private String phone;
    private String userEmail;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getFromaddr() {
        return fromaddr;
    }
    public void setFromaddr(String fromaddr) {
        this.fromaddr = fromaddr;
    }
    public String getToaddr() {
        return toaddr;
    }
    public void setToaddr(String toaddr) {
        this.toaddr = toaddr;
    }
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public String getSenddate() {
        return senddate;
    }
    public void setSenddate(String senddate) {
        this.senddate = senddate;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCompany() {
        return company;
    }
    public void setCompany(String company) {
        this.company = company;
    }
    public String getMailAddr() {
        return mailAddr;
    }
    public void setMailAddr(String mailAddr) {
        this.mailAddr = mailAddr;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getUserEmail() {
        return userEmail;
    }
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
}
