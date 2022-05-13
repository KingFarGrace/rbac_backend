package com.kingfar.rbac_backend.service;

import com.kingfar.rbac_backend.dto.Response;
import com.kingfar.rbac_backend.dto.UserInfoResp;
import com.kingfar.rbac_backend.pojo.UserBasicInfo;
import com.kingfar.rbac_backend.vo.AccountInfoOptForm;
import com.kingfar.rbac_backend.pojo.PersonalInfo;
import com.kingfar.rbac_backend.vo.UserAuthenticationForm;
import com.kingfar.rbac_backend.vo.UserRegisterForm;

import java.util.List;

/**
 * @author ZKH
 */
public interface AccountService {

    /**
     * query user basic info by key
     * these 3 types of key can be distinguished automatically by regular expression
     * @param key could be uid, username, telephone number, email
     * @return see details in {@link com.kingfar.rbac_backend.pojo.UserBasicInfo}
     */
    UserBasicInfo queryBasicInfo(String key);

    /**
     * decrypt password and compare it
     * @param password pwd in database
     * @param toVerify pwd in form
     * @return
     * true: password has been verified,
     * false: there are differences between 2 passwords
     */
    boolean verify(String password, String toVerify);

    /**
     * query all information, extended by basicInfo
     * @param basicInfo parameter come from method queryBasicInfo
     * @return see details in {@link com.kingfar.rbac_backend.dto.UserInfoResp}
     */
    UserInfoResp queryFullInfo(UserBasicInfo basicInfo);

    /**
     * main method which will be called by login controller
     * @param form {@link com.kingfar.rbac_backend.vo.UserAuthenticationForm}
     * @return see details in {@link com.kingfar.rbac_backend.dto.UserInfoResp}
     */
    Response login(UserAuthenticationForm form);

    /**
     * main method which will be called by register controller
     * @param form {@link com.kingfar.rbac_backend.vo.UserRegisterForm}
     * @return see details in {@link com.kingfar.rbac_backend.dto.RegisterResp}
     */
    Response register(UserRegisterForm form);

    /**
     * update user account ID: username
     * @param uid
     * @param newUsername
     * @return see details in {@link com.kingfar.rbac_backend.dto.InfoOptResp}
     */
    Response updateUsername(String uid, String newUsername);

    /**
     * update password
     * @param uid
     * @param newPassword
     * @return see details in {@link com.kingfar.rbac_backend.dto.InfoOptResp}
     */
    Response updatePassword(String uid, String newPassword);

    /**
     * update user account ID: telenum
     * @param uid
     * @param newTelenum
     * @return see details in {@link com.kingfar.rbac_backend.dto.InfoOptResp}
     */
    Response updateTelenum(String uid, String newTelenum);

    /**
     * update user account ID: email
     * @param uid
     * @param newEmail
     * @return see details in {@link com.kingfar.rbac_backend.dto.InfoOptResp}
     */
    Response updateEmail(String uid, String newEmail);

    /**
     * main method to update user account ID(include username, telephone number, email address)
     * @param form {@link com.kingfar.rbac_backend.vo.AccountInfoOptForm}
     * @return see details in {@link com.kingfar.rbac_backend.dto.UserInfoResp}
     */
    Response updateAccountInfo(AccountInfoOptForm form);

    /**
     * update method to batch entry user personal information (include realname, age, gender)
     * these information can be read by everyone, but only be updated by administer
     * @param infos {@link com.kingfar.rbac_backend.pojo.PersonalInfo}
     * @return see details in {@link com.kingfar.rbac_backend.dto.InfoOptResp}
     * return success message when all queries (data in form) has been done successfully
     * otherwise return failed message, but those queries which are successfully done will be kept
     */
    Response batchPersonalInfosEntry(List<PersonalInfo> infos);

}
