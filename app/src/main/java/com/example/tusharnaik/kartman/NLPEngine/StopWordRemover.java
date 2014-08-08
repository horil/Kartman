package com.example.tusharnaik.kartman.NLPEngine;

import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * Created by tushar.naik on 07/08/14.
 */
public class StopWordRemover {

    public final static HashMap<String, Integer> nWords = new HashMap<String, Integer>();
    int k=1;

    public StopWordRemover() throws IOException {

        String stp="a about above across after afterwards again against all almost alone along already also although always am among amongst amoungst amount an and another any anyhow anyone anything anyway anywhere are around as at back be became because become becomes becoming been before beforehand behind being below beside besides between beyond bill both bottom but by call can cannot cant co con could couldnt cry de describe detail do done down due during each eg either else elsewhere empty enough etc even ever every everyone everything everywhere except few fill find for former formerly found from front full further get give go had has hasnt have he hence her here hereafter hereby herein hereupon hers herse” him himse” his how however i ie if in inc indeed into is it its itse” keep last latter latterly least less ltd made many may me meanwhile might mill mine more moreover most mostly move much must my myse” name namely neither never nevertheless no nobody none noone nor not nothing now nowhere of off often on once only onto or other others otherwise our ours ourselves out over own part per perhaps please put rather re same see seem seemed seeming seems serious several she should show side since sincere so some somehow someone something sometime sometimes somewhere still such system take than that the their them themselves then thence there thereafter thereby therefore therein thereupon these they thick thin this those though through throughout thru thus to together too toward towards un under until up upon us very via was we well were what whatever when whence whenever where whereafter whereas whereby wherein whereupon wherever whether which while whither who whoever whole whom whose why will with within without would yet you your yours yourself yourselves price ratings review megapixels resolution range language binding publisher author product status brand contributor media format color colour type genre occasion size series pattern ideal for decade season sleeve india size artificial age group features age capacity sales paper orientation category fabric actor length setting video_encoding sub_category heel_height Length decorative accents shop skillset occassion curtain compartments country of manufacture usage style quantity standard platformrise plating number closure theme pack ";
        List<String> a =Tokenizer.tokenizeString(stp);
            for(String s:a)
                if(s.length()>0)
                 nWords.put(s,k++);
    }
    List<String> removeWords(List<String> keywords)
    {
        for(String s:keywords)
        {
            if(nWords.containsValue(s))
            {
                keywords.remove(s);
            }
        }
        return keywords;

    }

    public static void main(String[] args) throws IOException {

        StopWordRemover swr=new StopWordRemover();
        System.out.println(nWords.toString());
    }
}
