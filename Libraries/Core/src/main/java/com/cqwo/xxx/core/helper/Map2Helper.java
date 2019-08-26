package com.cqwo.xxx.core.helper;

public class Map2Helper {


    public static AroundPostion findNeighPosition(double longitude, double latitude, double dis) {
        //先计算查询点的经纬度范围
        double r = 6371;//地球半径千米

        if (dis <= 0) {
            dis = 0.5;//0.5千米距离
        }

        double dlng = 2 * Math.asin(Math.sin(dis / (2 * r)) / Math.cos(latitude * Math.PI / 180));
        dlng = dlng * 180 / Math.PI;//角度转为弧度
        double dlat = dis / r;
        dlat = dlat * 180 / Math.PI;

        double minLatitude = latitude - dlat;
        double maxLatitude = latitude + dlat;
        double minLongitude = longitude - dlng;
        double maxLongitude = longitude + dlng;


//        Map<String, Double> map = new HashMap<>(4);
//
//        map.put("minLatitude", minLatitude);
//        map.put("maxLatitude", maxLatitude);
//        map.put("minLongitude", minLongitude);
//        map.put("maxLongitude", maxLongitude);


        return new AroundPostion(minLatitude, maxLatitude, minLongitude, maxLongitude);
    }


    public static class AroundPostion {

        /**
         * 最小纬度
         */
        private double minLatitude = 0.00;

        /**
         * 最大纬度
         */
        private double maxLatitude = 0.00;

        /**
         * 最小经度
         */
        private double minLongitude = 0.00;

        /**
         * 最大经度
         */
        private double maxLongitude = 0.00;

        public AroundPostion() {
        }

        public AroundPostion(double minLatitude, double maxLatitude, double minLongitude, double maxLongitude) {
            this.minLatitude = minLatitude;
            this.maxLatitude = maxLatitude;
            this.minLongitude = minLongitude;
            this.maxLongitude = maxLongitude;
        }

        public double getMinLatitude() {
            return minLatitude;
        }

        public void setMinLatitude(double minLatitude) {
            this.minLatitude = minLatitude;
        }

        public double getMaxLatitude() {
            return maxLatitude;
        }

        public void setMaxLatitude(double maxLatitude) {
            this.maxLatitude = maxLatitude;
        }

        public double getMinLongitude() {
            return minLongitude;
        }

        public void setMinLongitude(double minLongitude) {
            this.minLongitude = minLongitude;
        }

        public double getMaxLongitude() {
            return maxLongitude;
        }

        public void setMaxLongitude(double maxLongitude) {
            this.maxLongitude = maxLongitude;
        }

        @Override
        public String toString() {
            return "AroundPostion{" +
                    "minLatitude=" + minLatitude +
                    ", maxLatitude=" + maxLatitude +
                    ", minLongitude=" + minLongitude +
                    ", maxLongitude=" + maxLongitude +
                    '}';
        }
    }

    public static void main(String[] args) {
        AroundPostion postion = findNeighPosition(106.459559, 29.51167, 5); //System.out.println(postion.toString());

    }


}
