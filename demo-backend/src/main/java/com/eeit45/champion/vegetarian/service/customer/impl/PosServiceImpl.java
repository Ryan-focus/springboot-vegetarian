package com.eeit45.champion.vegetarian.service.customer.impl;

import com.eeit45.champion.vegetarian.dao.customer.BusinessDao;
import com.eeit45.champion.vegetarian.dao.customer.PosDao;
import com.eeit45.champion.vegetarian.dto.customer.PosQueryParams;
import com.eeit45.champion.vegetarian.dto.customer.PosRequest;
import com.eeit45.champion.vegetarian.model.customer.Business;
import com.eeit45.champion.vegetarian.model.customer.Pos;
import com.eeit45.champion.vegetarian.model.customer.PosBusiness;
import com.eeit45.champion.vegetarian.service.customer.PosService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Component
public class PosServiceImpl implements PosService {

    private final static Logger log = LoggerFactory.getLogger(PosServiceImpl.class);


    @Autowired
    private PosDao posDao;

    @Autowired
    private BusinessDao businessDao;


    @Transactional
    @Override
    public Integer buildPos(Integer businessId, PosRequest posRequest) {
        Business business = businessDao.getBusinessById(businessId);

        if(business == null ){
            log.warn("此BusinessId :{}不存在，無法執行建立Pos功能權限",businessId);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        Pos pos  = posDao.getPosByBusinessId(businessId);

        if(pos != null ){
            log.warn("此商家用戶 : {} , 已申請過POS系統" , businessId);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        //更新business 系統狀態
        businessDao.updateStatus(business.getBusinessId(),"未開通" , null);

        Integer posId = posDao.buildPos(businessId,posRequest);

        posDao.buildPosBusiness(posId,businessId);

        return posId;
    }

    @Override
    public Pos getPosById(Integer posId) {
        Pos pos = posDao.getPosById(posId);
        List<PosBusiness> posBusinesses = posDao.getPosBusinessByPosId(posId);
        pos.setPosBusinessList(posBusinesses);
        return pos;
    }

    @Override
    public List<Pos> getAllPosList(PosQueryParams posQueryParams) {
        List<Pos> posList = posDao.getAllPosList(posQueryParams);

        for(Pos pos : posList){
            List<PosBusiness> posBusinesses = posDao.getPosBusinessByPosId(pos.getPosId());

            pos.setPosBusinessList(posBusinesses);
        }

        return posList;
    }

    @Override
    public Integer totalPos(PosQueryParams posQueryParams) {
        return posDao.totalPos(posQueryParams);
    }

    @Override
    public void updateStatus(Integer posId, PosRequest posRequest) {
        Pos pos = posDao.getPosById(posId);
        if(pos == null ) {
            log.warn("posId :{}不存在，無法執行更新Pos Status功能權限",posId);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Business business = businessDao.getBusinessById(pos.getBusinessId());
        if(business == null ) {
            log.warn("businessId :{}不存在，請透過正常方式建立pos申請",pos.getBusinessId());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        String token = UUID.randomUUID().toString();
        if(business != null) {
            token = token.toUpperCase();
            token = token.replaceAll("-","");
            businessDao.updateStatus(pos.getBusinessId(),posRequest.getValidDate().toString() , token);
            posRequest.setUUID(token);
            posDao.updateStatus(posId,posRequest);
        }
    }
}
