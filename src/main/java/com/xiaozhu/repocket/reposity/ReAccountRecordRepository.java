package com.xiaozhu.repocket.reposity;

import com.xiaozhu.repocket.po.RAccountRecordsPo;
import com.xiaozhu.repocket.po.RechargeRecordsPo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface ReAccountRecordRepository extends JpaRepository<RAccountRecordsPo, String>,
        JpaSpecificationExecutor<RAccountRecordsPo> {


    @Query(" select sum(ra.amount) from RAccountRecordsPo ra where ra.date = :date")
    Long querySumTotalBetAmount(@Param("date") String date);

    @Query(" select distinct (ra.guid) from RAccountRecordsPo ra where ra.date = :date")
    Integer queryBetPlayer(String date);

    @Query(" select distinct (ra.roomId) from RAccountRecordsPo ra where ra.date = :date")
    Integer queryRoomCount(String date);

    @Query(" select sum (ra.commissionAmout) from RAccountRecordsPo ra where ra.date = :date")
    Long querySumCommissionAmount(String date);

    @Query(" select sum (ra.systemEatChips) from RAccountRecordsPo ra where ra.date = :date")
    Long querySumSystemEatChips(String date);
}
