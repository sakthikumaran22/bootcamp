import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Blockchain {
    private List blockchain = new ArrayList<Block>();

    public void add_blocks(Block blk){
        this.blockchain.add(blk);
    }

    public Block get_latest_block(){
        return (Block) this.blockchain.get(blockchain.size()-1);
    }

    public List<Block> getBlockChain(){
        return this.blockchain;
    }
    public boolean validateChain(){
        boolean isValid = true;

        //TODO implement this,
        /*
        1. Iterate over all blocks and validate()
        2. Check if the prev_hash of current block is same as the hash
        of previous block
        block.prev_hash == previous_block.hash
         */
        String prevBlockHash = null;
        for(Block block: this.getBlockChain()){
            if(block.validate()) {
                if (block.getPrev_hash() != null) {
                    continue;
                } else {
                    if (!block.getHash().equalsIgnoreCase(prevBlockHash))
                        return false;
                }
                prevBlockHash = block.getHash();
            }
            else
                return false;
        }
        return isValid;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static Blockchain blockchainFromJson(String json){
        Gson gson = new Gson();
        return gson.fromJson(json, Blockchain.class);
    }
    public static Blockchain blockchainFromJson(BufferedReader json){
        Gson gson = new Gson();
        return gson.fromJson(json, Blockchain.class);
    }

    public void saveAsJson(){
        try {
            FileWriter writer = new FileWriter("blockchain.json");
            writer.write(this.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Blockchain loadFromJson(){
        try {
            BufferedReader br = new BufferedReader(
                    new FileReader("blockchain.json"));
            return blockchainFromJson(br);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] strings){
        Block block = new Block();
        Blockchain blockChain = new Blockchain();
        for(int i=0;i<5;i++){
            block.add_transaction(new Transaction("Satheesh", "Jeeva", 10));
            blockChain.add_blocks(block);
            block.finalizeBlock();
            block = new Block(block);
        }
        blockChain.saveAsJson();
    }
}
