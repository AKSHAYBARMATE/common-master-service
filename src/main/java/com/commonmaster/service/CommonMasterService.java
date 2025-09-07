package com.commonmaster.service;

import com.commonmaster.entity.CommonMaster;
import com.commonmaster.repository.CommonMasterRepository;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class CommonMasterService {
    private static final Logger logger = LoggerFactory.getLogger(CommonMasterService.class);
    private final CommonMasterRepository commonMasterRepository;

    public CommonMasterService(CommonMasterRepository commonMasterRepository) {
        this.commonMasterRepository = commonMasterRepository;
    }

    public List<CommonMaster> getActiveCommonMastersByKey(String key) {
        logger.info("Entering getActiveCommonMastersByKey with key: {}", key);
        try {
            List<CommonMaster> result = commonMasterRepository.findByCommonMasterKeyAndStatus(key, true);
            logger.info("Exiting getActiveCommonMastersByKey with {} records found for key: {}", (result != null ? result.size() : 0), key);
            return result;
        } catch (Exception e) {
            logger.error("Error in getActiveCommonMastersByKey for key: {}: {}", key, e.getMessage(), e);
            throw new RuntimeException("Failed to fetch CommonMaster data for key: " + key, e);
        }
    }
}
