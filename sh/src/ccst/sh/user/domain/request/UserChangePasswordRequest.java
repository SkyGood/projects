package ccst.sh.user.domain.request;

public class UserChangePasswordRequest {
    
    private int userId;
    
    private String userPassWord;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserPassWord() {
        return userPassWord;
    }

    public void setUserPassWord(String userPassWord) {
        this.userPassWord = userPassWord;
    }
    
}
