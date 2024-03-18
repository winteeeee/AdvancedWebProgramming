package com.kit.dormitory.fee;

import com.kit.dormitory.book.DormName;
import com.kit.dormitory.member.Member;
import org.springframework.stereotype.Component;

@Component
public class OldFeePolicy implements FeePolicy{
    private int fee = 0;
    @Override
    public int fee(Member member, DormName dormName) {
        if(dormName==DormName.PUREUM){
            fee =  1000;
        }else if(dormName==DormName.OREUM){
            fee =  2000;
        }else{
            fee = 500;
        }
        return fee;
    }
}
