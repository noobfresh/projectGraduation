import java.util.Objects;

public class AvgTimeData {

    private String startStation;

    private String endStation;

    private double duration;

    public AvgTimeData() {
    }

    public AvgTimeData(String startStation, String endStation, double duration) {
        this.startStation = startStation;
        this.endStation = endStation;
        this.duration = duration;
    }

    public String getStartStation() {
        return startStation;
    }

    public void setStartStation(String startStation) {
        this.startStation = startStation;
    }

    public String getEndStation() {
        return endStation;
    }

    @Override
    public String toString() {
        return "AvgTimeData{" +
                "startStation='" + startStation + '\'' +
                ", endStation='" + endStation + '\'' +
                ", duration=" + duration +
                '}';
    }

    public void setEndStation(String endStation) {
        this.endStation = endStation;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public static class PrimaryKey{
        private String startStation;
        private String endStation;

        public PrimaryKey(String startStation, String endStation) {
            this.startStation = startStation;
            this.endStation = endStation;
        }

        public String getStartStation() {
            return startStation;
        }

        public String getEndStation() {
            return endStation;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PrimaryKey that = (PrimaryKey) o;
            boolean flag = Objects.equals(startStation, that.startStation) &&
                    Objects.equals(endStation, that.endStation);
            return flag;
        }

        @Override
        public int hashCode() {
            return Objects.hash(startStation, endStation);
        }

        @Override
        public String toString() {
            return "PrimaryKey{" +
                    "startStation='" + startStation + '\'' +
                    ", endStation='" + endStation + '\'' +
                    '}' + hashCode();
        }
    }
}
