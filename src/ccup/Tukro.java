package ccup;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Question From here: http://www.careercup.com/question?id=6574215624916992
 * 
 * @author plakhina
 * 
 */
public class Tukro {
    /**
     * the bit set will be as large as number of people who saw the post. A 1 in
     * the bitset represents inclusion
     **/
    Map<Integer, BitSet> listMemberShipMap = new HashMap<>();
    private int v;// Number of people who saw the post

    private boolean postCompromised(List<Integer> includesFilter, List<Integer> excludesFilter) {
        BitSet compromisedBitSet = new BitSet(v); //This bit set should have a 1 if someone was not eligible to see a post but did see it
        if(includesFilter.size() > 0) {
            compromisedBitSet.set(0, v, true); //Initially everyone is disallowed if an inclusion filter is in effect    
        } 
        
        for(Integer included:includesFilter) { 
            compromisedBitSet.andNot(listMemberShipMap.get(included)); //If included then set the compromised bitset bit to false
        }
        
        for(Integer  excluded:excludesFilter) {
            compromisedBitSet.or(listMemberShipMap.get(excluded));
        }
        
        return compromisedBitSet.nextSetBit(0) != -1;
    }
    
    /**
     * @param args
     */
    @SuppressWarnings("unused")
    public static void main(String[] args) throws Exception {
        Tukro tukro = new Tukro();        
        BufferedReader br = new BufferedReader(new FileReader("/tmp/tukro2"));
        int numberOfFriends = Integer.parseInt(readLine(br));
        String[] friendsWhoViewedVals = readLine(br).split("\\s+");
        tukro.v = Integer.parseInt(friendsWhoViewedVals[0]);
        Map<Integer, Integer> friendsWhoViewedToBitSetIndexMap = new HashMap<>(); //This represents the index in all bit sets of a friendid
        int currentBitSetIndex = 0;
        
        for(int i=1;i<friendsWhoViewedVals.length;i++) {
            friendsWhoViewedToBitSetIndexMap.put(Integer.parseInt(friendsWhoViewedVals[i]), currentBitSetIndex++);
        }
        int numberOfLists = Integer.parseInt(readLine(br));
        for(int i=0;i<numberOfLists;i++) {
            BitSet listBitSet = new BitSet(tukro.v);
            tukro.listMemberShipMap.put(i, listBitSet);
            String[] lineVals = readLine(br).split("\\s+");
            int numberOfMembers = Integer.parseInt(lineVals[0]);
            for(int j=0;j<numberOfMembers;j++) {
                Integer member = Integer.parseInt(lineVals[j+1]);
                if(friendsWhoViewedToBitSetIndexMap.containsKey(member)) {
                    listBitSet.set(friendsWhoViewedToBitSetIndexMap.get(member));
                }
                
            }
        }
        
        int numberOfPosts = Integer.parseInt(readLine(br));
        int compromisedPosts = 0;
        for(int i=0;i<numberOfPosts;i++) {
            List<Integer> inclusionLists = new ArrayList<>();
            List<Integer> exclusionLists = new ArrayList<>();
            String[] includeLineVals = readLine(br).split("\\s+");
            for(int j=1;j<includeLineVals.length;j++) {
                inclusionLists.add(Integer.parseInt(includeLineVals[j]));
            }
            String[] excludeLineVals = readLine(br).split("\\s+");
            for(int j=1;j<excludeLineVals.length;j++) {
                exclusionLists.add(Integer.parseInt(excludeLineVals[j]));
            }
            if(tukro.postCompromised(inclusionLists, exclusionLists)) {
                System.out.println(String.format("Post: %d compromised: true", i));
                compromisedPosts++;
            } else {
                System.out.println(String.format("Post: %d compromised: false", i));
            }
        }
        System.out.println(compromisedPosts);
    }

    private static String readLine(BufferedReader br) throws IOException {
        return br.readLine().trim();
    }

}
