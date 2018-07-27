import com.google.gson.Gson;

/**
 * Created by sakthikumarannavakumar on 18/07/18.
 */
public class BlockHeader {

    private String payloadHash;

    private String timestamp;

    private String prevHash;

    private int totalTransactions;

    private BlockHeader(BlockHeaderBuilder blockHeader){

        this.payloadHash = blockHeader.payloadHash;
        this.timestamp = blockHeader.timestamp;
        this.prevHash = blockHeader.prevHash;
        this.totalTransactions = blockHeader.totalTransactions;

    }

    public String getPayloadHash() {
        return payloadHash;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getPrevHash() {
        return prevHash;
    }

    public int getTotalTransactions() {
        return totalTransactions;
    }

    public static class BlockHeaderBuilder{

        private String payloadHash;

        private String timestamp;

        private String prevHash;

        private int totalTransactions;


        public BlockHeaderBuilder setPayloadHash(String payloadHash) {
            this.payloadHash = payloadHash;
            return this;
        }

        public BlockHeaderBuilder setTimestamp(String timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public BlockHeaderBuilder setPrevHash(String prevHash) {
            this.prevHash = prevHash;
            return this;
        }

        public BlockHeaderBuilder setTotalTransactions(int totalTransactions) {
            this.totalTransactions = totalTransactions;
            return this;
        }

        public BlockHeader build(){
            return new BlockHeader(this);
        }
    }

    public String toString(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }

}
