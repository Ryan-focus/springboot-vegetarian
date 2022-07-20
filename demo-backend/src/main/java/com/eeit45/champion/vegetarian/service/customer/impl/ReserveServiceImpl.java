package com.eeit45.champion.vegetarian.service.customer.impl;

import com.eeit45.champion.vegetarian.dao.RestaurantDao;
import com.eeit45.champion.vegetarian.dao.customer.ReserveDao;
import com.eeit45.champion.vegetarian.dto.customer.ReserveRequest;
import com.eeit45.champion.vegetarian.model.Restaurant;
import com.eeit45.champion.vegetarian.model.customer.Reserve;
import com.eeit45.champion.vegetarian.service.customer.ReserveService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Component
public class ReserveServiceImpl implements ReserveService {

    private final static Logger log = LoggerFactory.getLogger(BusinessServiceImpl.class);


    @Autowired
    private ReserveDao reserveDao;

    @Autowired
    private RestaurantDao restaurantDao;

    @Autowired
    private JavaMailSender javaMailSender;


    @Override
    public Reserve getReserveById(Integer businessId , Integer reserveId) {
        return reserveDao.getReserveById(businessId,reserveId);
    }

    @Override
    public Integer createReserve(Integer businessId ,ReserveRequest reserveRequest) {

        Integer reserveId = reserveDao.createReserve(businessId,reserveRequest);

        if(reserveId == null ){
            log.warn("訂單建立失敗");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Reserve reserve = reserveDao.getReserveById(businessId,reserveId);

        Restaurant restaurant = restaurantDao.getRestaurantByNumber(reserveRequest.getRestaurantId());

        if(restaurant == null ){
            log.warn("餐廳編號 : {} 不存在，訂單建立失敗" , reserveRequest.getRestaurantId());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if(reserveRequest.getReserveEmail() != null ){
            SimpleMailMessage smm = new SimpleMailMessage();
            smm.setFrom("eeit45no1@gmail.com");		//發送者
            smm.setTo(reserveRequest.getReserveEmail());	//收件者
            smm.setSubject("訂位成功通知 : " + restaurant.getRestaurantName() );	//主旨
            smm.setText("您好，感謝您透過愛蔬網 訂位，以下是您的訂位明細\n\n" +
                    "訂位餐廳 : " + restaurant.getRestaurantName() + "\n\n" +
                    "訂位人 : " + reserveRequest.getReserveName() + "\n\n" +
                    "訂位電話 : " + reserveRequest.getReservePhone() + "\n\n " +
                    "訂位人數 : 大人 " + reserveRequest.getAdult() + "位 小孩 " + reserveRequest.getChild() + "位 ,嬰兒座位 共" + reserveRequest.getBaby() +" 位 \n\n" +
                    "預訂前往日期 : " +reserveRequest.getReserveDate()  + "\n\n " +
                    "訂單建立時間 : " + reserve.getReserveTime() + "\n\n " +
                    "期待您的蒞臨                                  愛蔬網 團隊敬啟"
                    );
            javaMailSender.send(smm);
        }

        return reserveId;
    }

    @Override
    public List<Reserve> getAllReserve(Integer businessId) {
        return reserveDao.getAllReserve(businessId);
    }
}
