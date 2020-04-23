package com.plivo.contactbook.dao.redis;

import com.plivo.contactbook.model.entity.ContactDetails;
import com.plivo.contactbook.model.entity.PaginatedList;
import com.plivo.contactbook.model.request.ContactInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

@Repository
public class RedisContactDao extends RedisCommonDao{

    private static final Logger LOGGER= LoggerFactory.getLogger(RedisContactDao.class);

    private static final String REDIS_CONTACT_DETAILS_KEY_PREFIX = "contactdetails";

    public void setContactDetailsList(String emailId, String firstName, long pageNumber, PaginatedList<ContactInfo> list, int expiryInHours)
    {
        try {
            String detailsKey = REDIS_CONTACT_DETAILS_KEY_PREFIX+"_"+buildKey(emailId,firstName,pageNumber);
            putValueWithExpireTime(detailsKey, list, expiryInHours, TimeUnit.HOURS);
            LOGGER.info("Data set in redis for key:{}",detailsKey);

        } catch (Exception e) {
            LOGGER.error("Exception in setting data in redis");
            LOGGER.error(e.getLocalizedMessage());
        }

    }


    public PaginatedList<ContactInfo> getContactDetailsList(String emailId,String firstName,long pageNumber)
    {
        PaginatedList<ContactInfo> result=null;
        try {
            String detailsKey = REDIS_CONTACT_DETAILS_KEY_PREFIX+"_"+buildKey(emailId,firstName,pageNumber);
            return (PaginatedList<ContactInfo>)getValue(detailsKey);

        } catch (Exception e) {
            LOGGER.error("Exception in getting data from redis");
            LOGGER.error(e.getLocalizedMessage());
        }

        return result;

    }

    private String buildKey(String emailId,String firstName,long pageNumber)
    {
        StringBuilder key=new StringBuilder();
        if(!StringUtils.isEmpty(emailId))
            key.append(emailId);
        if(!StringUtils.isEmpty(firstName))
            key.append(firstName);
        key.append(pageNumber);
        return key.toString();

    }

}
