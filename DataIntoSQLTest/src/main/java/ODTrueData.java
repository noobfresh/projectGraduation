public class ODTrueData {

    private String ticketId;

    private String startTime;

    private String endTime;

    private String startStation;

    private String endStation;

    private String date;

    public ODTrueData() {
    }

    public ODTrueData(String ticketId, String startTime,
                      String endTime, String startStation,
                      String endStation, String date) {
        this.ticketId = ticketId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.startStation = startStation;
        this.endStation = endStation;
        this.date = date;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
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

    public void setEndStation(String endStation) {
        this.endStation = endStation;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
