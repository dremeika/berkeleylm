package edu.berkeley.nlp.lm.io;

import edu.berkeley.nlp.lm.ArrayEncodedNgramLanguageModel;
import edu.berkeley.nlp.lm.ConfigOptions;
import edu.berkeley.nlp.lm.StringWordIndexer;
import edu.berkeley.nlp.lm.cache.ArrayEncodedCachingLmWrapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;

public class GoogleReaderTest {

    private String googleDir;

    @Before
    public void setup() {
        googleDir = FileUtils.getFile("googledir").getPath();
    }

    @Ignore
    @Test
    public void testHashCached() {
        ArrayEncodedNgramLanguageModel<String> lm = LmReaders.readLmFromGoogleNgramDir(googleDir, false, false);
        checkScores(ArrayEncodedCachingLmWrapper.wrapWithCacheNotThreadSafe(lm));
    }

    @Ignore
    @Test
    public void testHash() {
        ArrayEncodedNgramLanguageModel<String> lm = LmReaders.readLmFromGoogleNgramDir(googleDir, false, false);
        checkScores(lm);
    }

    @Ignore
    @Test
    public void testCompressed() {
        final ArrayEncodedNgramLanguageModel<String> lm = LmReaders.readLmFromGoogleNgramDir(googleDir, true, false);
        checkScores(lm);
    }

    @Test
    public void testHashKneserNey() {
        ConfigOptions opts = new ConfigOptions();
        opts.kneserNeyDiscounts = new double[]{0, 0, 0, 0, 0};

        final ArrayEncodedNgramLanguageModel<String> lm = LmReaders.readLmFromGoogleNgramDir(googleDir, false, true, new StringWordIndexer(), opts);
        checkScoresKneserNey(lm);
    }

    @Test
    public void testCompressedKneserNey() {
        ConfigOptions opts = new ConfigOptions();
        opts.kneserNeyDiscounts = new double[]{0, 0, 0, 0, 0};
        final ArrayEncodedNgramLanguageModel<String> lm = LmReaders.readLmFromGoogleNgramDir(googleDir, true, true, new StringWordIndexer(), opts);
        checkScoresKneserNey(lm);
    }

    private void checkScores(final ArrayEncodedNgramLanguageModel<String> lm) {
        Assert.assertEquals(lm.getLogProb(Arrays.asList("of", "xxx")), Math.log(1f / 12765289150L), 1e-3);
        Assert.assertEquals(lm.getLogProb(Arrays.asList("the", "(")), Math.log(40000f / 19401194714L), 1e-3);
        Assert.assertEquals(lm.getLogProb(Arrays.asList("of", "the", "(")), Math.log(50f / 854) + 0 * Math.log(0.4), 1e-3);
        Assert.assertEquals(lm.getLogProb(Arrays.asList("a", "the", "(")), Math.log(40000f / 19401194714L) + Math.log(0.4), 1e-3);
        Assert.assertEquals(lm.getLogProb(Arrays.asList("a", ")", "(")), Math.log(8912668768L * 1.0f / 408012035092L) + 2 * Math.log(0.4), 1e-3);
        Assert.assertEquals(lm.getLogProb(Arrays.asList("the", "of", "a")), Math.log(3668f / 12765289150L) + Math.log(0.4), 1e-3);
        Assert.assertEquals(lm.getLogProb(Arrays.asList("of", "the", "(")), Math.log(50f / 854), 1e-3);
    }

    private void checkScoresKneserNey(final ArrayEncodedNgramLanguageModel<String> lm) {
        Assert.assertEquals(lm.getLogProb(Arrays.asList("of", "xxx")), -1.0, 1e-3);
        Assert.assertEquals(lm.getLogProb(Arrays.asList("of", "the", "(")), -0.414973, 1e-3);
    }

}
