package com.tecacet.finance.io.parser;

import static org.junit.Assert.assertEquals;

import com.tecacet.finance.model.Asset;
import com.tecacet.finance.model.AssetType;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Random;

public class MutualFundsAssetParserTest {

    @Test
    public void testParse() throws IOException {
        MutualFundAssetParser assetParser = new MutualFundAssetParser();
        String file = "nasdaqtrader" + File.separator + "mfundslist.txt";

        InputStream is = ClassLoader.getSystemResourceAsStream(file);

        List<Asset> assets = assetParser.parse(is);
        assertEquals(33500, assets.size());
        Random random = new Random();
        Asset randomAsset = assets.get(random.nextInt(7676));
        assertEquals(AssetType.MUTUAL_FUND, randomAsset.getAssetType());

    }

}
