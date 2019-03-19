package com.xiaozhu.repocket.controller.record;

import com.xiaozhu.repocket.controller.request.recharge.QueryRmoneyChangeRequest;
import com.xiaozhu.repocket.controller.request.recharge.RechargeRequest;
import com.xiaozhu.repocket.controller.response.ApiResponse;
import com.xiaozhu.repocket.controller.response.PageDataBean;
import com.xiaozhu.repocket.po.RAccountRecordsPo;
import com.xiaozhu.repocket.po.RechargeRecordsPo;
import com.xiaozhu.repocket.po.RmoneyChangeRecordsPo;
import com.xiaozhu.repocket.reposity.ReAccountRecordRepository;
import com.xiaozhu.repocket.reposity.RechargeRecordRepository;
import com.xiaozhu.repocket.reposity.RmoneyChangeRecordsRepository;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@RestController
@RequestMapping("/record")
public class RecordController {

    @Autowired
    private RechargeRecordRepository rechargeRecordRepository;

    @Autowired
    private RmoneyChangeRecordsRepository rmoneyChangeRecordsRepository;


    @Autowired
    private ReAccountRecordRepository reAccountRecordRepository;

    @PostMapping("/queryRAccountRecords")
    public ApiResponse<PageDataBean<RAccountRecordsPo>> queryRAccountRecords(@RequestBody QueryRmoneyChangeRequest request) {

        PageDataBean<RAccountRecordsPo> pageDataBean = new PageDataBean<>();
        Sort sort = Sort.by("date").descending();

        PageRequest pageRequest = PageRequest.of((request.getPage() - 1), request.getLimit(), sort);
        Specification<RAccountRecordsPo> specification = getRAccountRecordsPoWhereClause(request);

        Page<RAccountRecordsPo> page = reAccountRecordRepository.findAll(specification, pageRequest);

        pageDataBean.setDatas(page.getContent());

        pageDataBean.setTotalCount(Integer.valueOf(String.valueOf(page.getTotalElements())));

        return new ApiResponse<>(pageDataBean);
    }

    @PostMapping("/queryRechargeData")
    public ApiResponse<PageDataBean> queryRechargeData(@RequestBody RechargeRequest request) {
        PageDataBean<RechargeRecordsPo> pageDataBean = new PageDataBean<>();
        Sort sort = Sort.by("createTime").descending();

        PageRequest pageRequest = PageRequest.of((request.getPage() - 1), request.getLimit(), sort);
        Specification<RechargeRecordsPo> specification = getRechargeWhereClause(request);

        Page<RechargeRecordsPo> page = rechargeRecordRepository.findAll(specification, pageRequest);

        pageDataBean.setDatas(page.getContent());

        pageDataBean.setTotalCount(Integer.valueOf(String.valueOf(page.getTotalElements())));

        return new ApiResponse<>(pageDataBean);
    }

    @PostMapping("/queryRmoneyChangeData")
    public ApiResponse<PageDataBean> queryRmoneyChangeData(@RequestBody QueryRmoneyChangeRequest request) {
        PageDataBean<RmoneyChangeRecordsPo> pageDataBean = new PageDataBean<>();
        Sort sort = Sort.by("dateTime").descending();

        PageRequest pageRequest = PageRequest.of((request.getPage() - 1), request.getLimit(), sort);
        Specification<RmoneyChangeRecordsPo> specification = getMoneyChangeWhereClause(request);

        Page<RmoneyChangeRecordsPo> page = rmoneyChangeRecordsRepository.findAll(specification, pageRequest);

        pageDataBean.setDatas(page.getContent());

        pageDataBean.setTotalCount(Integer.valueOf(String.valueOf(page.getTotalElements())));

        return new ApiResponse<>(pageDataBean);
    }

    private Specification<RechargeRecordsPo> getRechargeWhereClause(final RechargeRequest request) {
        return new Specification<RechargeRecordsPo>() {
            @Override
            public Predicate toPredicate(Root<RechargeRecordsPo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = Lists.newArrayList();
                String startDate = "";
                String endDate = "";

                if(request.getStart() > 0){
                    DateTime dateTime = new DateTime(request.getStart());
                    startDate = dateTime.toString("yyyy/MM/dd");
                }
                if(request.getEnd() > 0){
                    DateTime dateTime = new DateTime(request.getEnd());
                    endDate = dateTime.toString("yyyy/MM/dd");
                }
                if(request.getPlayerId() != null){
                    predicate.add(cb.equal(root.get("guid").as(Long.class),request.getPlayerId()));
                }

                if(StringUtils.isNotEmpty(startDate)){
                    predicate.add(cb.greaterThanOrEqualTo(root.get("createTime").as(String.class),startDate));
                }

                if(StringUtils.isNotEmpty(endDate)){
                    predicate.add(cb.lessThanOrEqualTo(root.get("createTime").as(String.class),endDate));
                }

                Predicate[] pre = new Predicate[predicate.size()];
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
    }

    private Specification<RmoneyChangeRecordsPo> getMoneyChangeWhereClause(final QueryRmoneyChangeRequest request) {
        return new Specification<RmoneyChangeRecordsPo>() {
            @Override
            public Predicate toPredicate(Root<RmoneyChangeRecordsPo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = Lists.newArrayList();
                String startDate = "";
                String endDate = "";

                if(request.getStart() > 0){
                    DateTime dateTime = new DateTime(request.getStart());
                    startDate = dateTime.toString("yyyy/MM/dd");
                }
                if(request.getEnd() > 0){
                    DateTime dateTime = new DateTime(request.getEnd());
                    endDate = dateTime.toString("yyyy/MM/dd");
                }
                if(StringUtils.isNotEmpty(request.getNick())){
                    predicate.add(cb.equal(root.get("nick").as(String.class),request.getNick()));
                }
                if(StringUtils.isNotEmpty(request.getReason())){
                    predicate.add(cb.equal(root.get("reason").as(String.class),request.getReason()));
                }
                if(request.getPlayerId() != null){
                    predicate.add(cb.equal(root.get("guid").as(Long.class),request.getPlayerId()));
                }

                if(StringUtils.isNotEmpty(startDate)){
                    predicate.add(cb.greaterThanOrEqualTo(root.get("dateTime").as(String.class),startDate));
                }

                if(StringUtils.isNotEmpty(endDate)){
                    predicate.add(cb.lessThanOrEqualTo(root.get("dateTime").as(String.class),endDate));
                }

                Predicate[] pre = new Predicate[predicate.size()];
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
    }

    private Specification<RAccountRecordsPo> getRAccountRecordsPoWhereClause(final QueryRmoneyChangeRequest request) {
        return new Specification<RAccountRecordsPo>() {
            @Override
            public Predicate toPredicate(Root<RAccountRecordsPo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = Lists.newArrayList();
                String startDate = "";
                String endDate = "";

                if(request.getStart() > 0){
                    DateTime dateTime = new DateTime(request.getStart());
                    startDate = dateTime.toString("yyyy/MM/dd");
                }
                if(request.getEnd() > 0){
                    DateTime dateTime = new DateTime(request.getEnd());
                    endDate = dateTime.toString("yyyy/MM/dd");
                }
                if(request.getPlayerId() != null){
                    predicate.add(cb.equal(root.get("guid").as(Long.class),request.getPlayerId()));
                }
                if(request.getRoomId() != null){
                    predicate.add(cb.equal(root.get("roomId").as(Integer.class),request.getRoomId()));
                }
                if(request.getRoomGuid() != null){
                    predicate.add(cb.equal(root.get("roomGuid").as(Long.class),request.getRoomGuid()));
                }

                if(StringUtils.isNotEmpty(startDate)){
                    predicate.add(cb.greaterThanOrEqualTo(root.get("date").as(String.class),startDate));
                }

                if(StringUtils.isNotEmpty(endDate)){
                    predicate.add(cb.lessThanOrEqualTo(root.get("date").as(String.class),endDate));
                }

                Predicate[] pre = new Predicate[predicate.size()];
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
    }
}
