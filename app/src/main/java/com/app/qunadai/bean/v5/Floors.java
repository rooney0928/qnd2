package com.app.qunadai.bean.v5;

import java.util.List;

/**
 * Created by wayne on 2017/9/5.
 */

public class Floors {

    private List<FloorsBean> floors;

    public List<FloorsBean> getFloors() {
        return floors;
    }

    public void setFloors(List<FloorsBean> floors) {
        this.floors = floors;
    }

    public static class FloorsBean {
        /**
         * id : 930c8175-7063-4e8e-84e1-7d926e807f5c
         * createdTime : 1504495431000
         * updatedTime : 1504495431000
         * position : 0
         * floorType : RECOMMEND
         * floorContents : [{"id":"711e3ed4-c666-429d-840f-c2fef84886d8","createdTime":1504495431000,"updatedTime":1504495431000,"floorId":"930c8175-7063-4e8e-84e1-7d926e807f5c","contentName":"随手借"}]
         */

        private String id;
        private long createdTime;
        private long updatedTime;
        private int position;
        private String floorType;
        private List<RoomBean> floorContents;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public long getCreatedTime() {
            return createdTime;
        }

        public void setCreatedTime(long createdTime) {
            this.createdTime = createdTime;
        }

        public long getUpdatedTime() {
            return updatedTime;
        }

        public void setUpdatedTime(long updatedTime) {
            this.updatedTime = updatedTime;
        }

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public String getFloorType() {
            return floorType;
        }

        public void setFloorType(String floorType) {
            this.floorType = floorType;
        }

        public List<RoomBean> getFloorContents() {
            return floorContents;
        }

        public void setFloorContents(List<RoomBean> floorContents) {
            this.floorContents = floorContents;
        }

    }
}
