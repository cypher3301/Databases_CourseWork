package com.spring.post.dto;

import java.util.Arrays;

public class WorkShiftDTO {
    private long[] ids;

    public WorkShiftDTO(long[] ids) {
        this.ids = ids;
    }

    public WorkShiftDTO() {
    }

    public long[] getIds() {
        return ids;
    }

    public void setIds(long[] ids) {
        this.ids = ids;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WorkShiftDTO)) return false;

        WorkShiftDTO that = (WorkShiftDTO) o;

        return Arrays.equals(getIds(), that.getIds());
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(getIds());
    }
}
