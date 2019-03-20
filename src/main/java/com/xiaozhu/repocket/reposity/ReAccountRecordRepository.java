package com.xiaozhu.repocket.reposity;

import com.xiaozhu.repocket.po.RAccountRecordsPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReAccountRecordRepository extends JpaRepository<RAccountRecordsPo, String>,
        JpaSpecificationExecutor<RAccountRecordsPo> {


    @Query(" select sum(ra.amount) from RAccountRecordsPo ra where ra.date = :date")
    Long querySumTotalBetAmount(@Param("date") String date);

    @Query(value = " select count(1) from ( select distinct (ra.PlayerID) from raccount_records ra where ra.date = :date) r", nativeQuery = true)
    Long queryBetPlayer(@Param("date") String date);

    @Query(value = " select count(1) from ( select distinct (ra.RoomID) from raccount_records ra where ra.date = :date) r", nativeQuery = true)
    Integer queryRoomCount(@Param("date") String date);

    @Query(" select sum (ra.commissionAmout) from RAccountRecordsPo ra where ra.date = :date")
    Long querySumCommissionAmount(@Param("date") String date);

    @Query(" select sum (ra.systemEatChips) from RAccountRecordsPo ra where ra.date = :date")
    Long querySumSystemEatChips(@Param("date") String date);
}
