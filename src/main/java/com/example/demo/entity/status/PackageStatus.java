package com.example.demo.entity.status;

import lombok.Getter;

public enum PackageStatus {
    AWAITING_DISPATCH, IN_TRANSIT, ARRIVED;

    public static PackageStatus getCurrentStatus(String status){
        if(status.equals(PackageStatus.ARRIVED.toString())){
            return PackageStatus.ARRIVED;
        } else if(status.equals(PackageStatus.IN_TRANSIT.toString())){
            return PackageStatus.IN_TRANSIT;
        }
        return PackageStatus.AWAITING_DISPATCH;
    }
}
