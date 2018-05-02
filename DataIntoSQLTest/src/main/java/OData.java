import com.opencsv.bean.CsvBindByName;

public class OData {

    public OData() {
    }

    public OData(String ticket_id, String txn_date,
                 String txn_time, String txn_station_id,
                 String ticket_type, String trans_code,
                 String txn_amt) {
        this.ticket_id = ticket_id;
        this.txn_date = txn_date;
        this.txn_time = txn_time;
        this.txn_station_id = txn_station_id;
        this.ticket_type = ticket_type;
        this.trans_code = trans_code;
        this.txn_amt = txn_amt;
    }

    @CsvBindByName(column = "TICKET_ID", required = true)
    private String ticket_id;

    @CsvBindByName(column = "TXN_DATE", required = true)
    private String txn_date;

    @CsvBindByName(column = "TXN_TIME", required = true)
    private String txn_time;

    @CsvBindByName(column = "TXN_STATION_ID", required = true)
    private String txn_station_id;

    @CsvBindByName(column = "TICKET_TYPE", required = true)
    private String ticket_type;

    @CsvBindByName(column = "TRANS_CODE", required = true)
    private String trans_code;

    @CsvBindByName(column = "TXN_AMT", required = true)
    private String txn_amt;

    public String getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(String ticket_id) {
        this.ticket_id = ticket_id;
    }

    public String getTxn_date() {
        return txn_date;
    }

    public void setTxn_date(String txn_date) {
        this.txn_date = txn_date;
    }

    public String getTxn_time() {
        return txn_time;
    }

    public void setTxn_time(String txn_time) {
        this.txn_time = txn_time;
    }

    public String getTxn_station_id() {
        return txn_station_id;
    }

    public void setTxn_station_id(String txn_station_id) {
        this.txn_station_id = txn_station_id;
    }

    public String getTicket_type() {
        return ticket_type;
    }

    public void setTicket_type(String ticket_type) {
        this.ticket_type = ticket_type;
    }

    public String getTrans_code() {
        return trans_code;
    }

    public void setTrans_code(String trans_code) {
        this.trans_code = trans_code;
    }

    public String getTxn_amt() {
        return txn_amt;
    }

    public void setTxn_amt(String txn_amt) {
        this.txn_amt = txn_amt;
    }
}
