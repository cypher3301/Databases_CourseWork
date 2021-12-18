package com.spring.post.entity.status;

public enum PackageType {
    OTHER("OTHER"),
    PRODUCTS("PRODUCTS"),
    ELECTRONICS("ELECTRONICS"),
    CLOTHES("CLOTHES"),
    FURNITURE("FURNITURE"),
    CHEMISTRY("CHEMISTRY"),
    MEDICATIONS("MEDICATIONS"),
    LIQUID("LIQUID")
    ;

    private static final int length = 7;
    private final String type;

    PackageType(String type) {
        this.type = type;
    }

    public static int getLength() {

        return length;
    }

    public String getType() {
        return type;
    }
}
