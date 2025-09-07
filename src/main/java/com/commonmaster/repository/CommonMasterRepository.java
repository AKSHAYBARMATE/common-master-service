package com.commonmaster.repository;

import com.commonmaster.entity.CommonMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CommonMasterRepository extends JpaRepository<CommonMaster, Integer> {

    List<CommonMaster> findByCommonMasterKeyAndStatus(String commonMasterKey, Boolean status);
}

