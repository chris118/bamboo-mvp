package com.hh.bambooapp.account.model.entity;

import com.hh.bambooapp.api.entity.BaseResponse;

/**
 * Created by xiaopeng on 2017/11/8.
 */

public class LoginResponse extends BaseResponse {
    private String token;
    private String deviceGuid;
    private String roleName;
    private String registerType;
    private UserBean user;
    private String tokenType;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDeviceGuid() {
        return deviceGuid;
    }

    public void setDeviceGuid(String deviceGuid) {
        this.deviceGuid = deviceGuid;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRegisterType() {
        return registerType;
    }

    public void setRegisterType(String registerType) {
        this.registerType = registerType;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public static class UserBean {
        /**
         * activeDate : 2017-11-02 23:38:54
         * awarded : 0
         * birthday :
         * gender : -1
         * hxUsername : y_2
         * instId : 1
         * isvalidate : true
         * lastAccessTime : 0
         * mobile :
         * nickname : hw001
         * photourl :
         * role : groupMember
         * serverId : 0
         * trialEnd :
         * trialStart :
         * userId : 2
         * username : hw001
         */

        private String activeDate;
        private String awarded;
        private String birthday;
        private String gender;
        private String hxUsername;
        private String instId;
        private String isvalidate;
        private String lastAccessTime;
        private String mobile;
        private String nickname;
        private String photourl;
        private String role;
        private String serverId;
        private String trialEnd;
        private String trialStart;
        private String userId;
        private String username;

        public String getActiveDate() {
            return activeDate;
        }

        public void setActiveDate(String activeDate) {
            this.activeDate = activeDate;
        }

        public String getAwarded() {
            return awarded;
        }

        public void setAwarded(String awarded) {
            this.awarded = awarded;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getHxUsername() {
            return hxUsername;
        }

        public void setHxUsername(String hxUsername) {
            this.hxUsername = hxUsername;
        }

        public String getInstId() {
            return instId;
        }

        public void setInstId(String instId) {
            this.instId = instId;
        }

        public String getIsvalidate() {
            return isvalidate;
        }

        public void setIsvalidate(String isvalidate) {
            this.isvalidate = isvalidate;
        }

        public String getLastAccessTime() {
            return lastAccessTime;
        }

        public void setLastAccessTime(String lastAccessTime) {
            this.lastAccessTime = lastAccessTime;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getPhotourl() {
            return photourl;
        }

        public void setPhotourl(String photourl) {
            this.photourl = photourl;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getServerId() {
            return serverId;
        }

        public void setServerId(String serverId) {
            this.serverId = serverId;
        }

        public String getTrialEnd() {
            return trialEnd;
        }

        public void setTrialEnd(String trialEnd) {
            this.trialEnd = trialEnd;
        }

        public String getTrialStart() {
            return trialStart;
        }

        public void setTrialStart(String trialStart) {
            this.trialStart = trialStart;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
