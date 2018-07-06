/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiendnse.StudentDetail;

import java.io.Serializable;

/**
 *
 * @author Saisam
 */
public class tblUserCreatError implements Serializable{
    private String usernameLengthErr;
    private String passwordLengthErr;
    private String comfirmNotMatched;
    private String fullnameLengthErr;
    private String usernameIsExisted;

    public String getUsernameLengthErr() {
        return usernameLengthErr;
    }

    public void setUsernameLengthErr(String usernameLengthErr) {
        this.usernameLengthErr = usernameLengthErr;
    }

    public String getPasswordLengthErr() {
        return passwordLengthErr;
    }

    public void setPasswordLengthErr(String passwordLengthErr) {
        this.passwordLengthErr = passwordLengthErr;
    }

    public String getComfirmNotMatched() {
        return comfirmNotMatched;
    }

    public void setComfirmNotMatched(String comfirmNotMatched) {
        this.comfirmNotMatched = comfirmNotMatched;
    }

    public String getFullnameLengthErr() {
        return fullnameLengthErr;
    }

    public void setFullnameLengthErr(String fullnameLengthErr) {
        this.fullnameLengthErr = fullnameLengthErr;
    }

    public String getUsernameIsExisted() {
        return usernameIsExisted;
    }

    public void setUsernameIsExisted(String usernameIsExisted) {
        this.usernameIsExisted = usernameIsExisted;
    }
      
}
