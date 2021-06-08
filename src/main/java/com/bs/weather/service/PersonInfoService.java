package com.bs.weather.service;

import com.bs.weather.entity.PersonInfo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 */
public interface PersonInfoService {
    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @return
     */
    PersonInfo login(String username, String password);

    /**
     * @param personId
     * @return
     */
    PersonInfo getPersonById(Integer personId);

    /**
     * @return
     */
    List<PersonInfo> getCollegePerson();

    /**
     * @param collegeId
     * @return
     */
    List<PersonInfo> getPersonByCollegeId(Integer collegeId);

    /**
     * @param personInfo
     * @return
     */
    Boolean insertPerson(PersonInfo personInfo);

    /**
     * @param personInfo
     * @return
     */
    Boolean updatePerson(PersonInfo personInfo);

    /**
     * @param personId
     * @return
     */
    Boolean delPerson(Integer personId);

    /**
     * @return
     */
    List<PersonInfo> getPerson1();

    /**
     * @param personInfo
     * @param faseImage
     * @return
     */
    Boolean addFace(PersonInfo personInfo, String faseImage);


    PersonInfo checkFace(String image);

}
