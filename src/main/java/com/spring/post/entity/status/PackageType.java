package com.spring.post.entity.status;

public enum PackageType {
    PRODUCTS, ELECTRONICS, CLOTHES, FURNITURE, CHEMISTRY, MEDICATIONS, LIQUID;

    private static final int length = 7;

    public static int getLength() {

        return length;
    }
}
