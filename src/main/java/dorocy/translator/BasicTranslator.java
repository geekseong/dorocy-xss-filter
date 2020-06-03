package dorocy.translator;

import org.apache.commons.text.translate.AggregateTranslator;
import org.apache.commons.text.translate.CharSequenceTranslator;
import org.apache.commons.text.translate.EntityArrays;
import org.apache.commons.text.translate.LookupTranslator;

public class BasicTranslator implements Translator{
    public CharSequenceTranslator getTranslator() {
        return new AggregateTranslator(
                new LookupTranslator(EntityArrays.BASIC_ESCAPE),
                new LookupTranslator(EntityArrays.ISO8859_1_UNESCAPE),
                new LookupTranslator(EntityArrays.HTML40_EXTENDED_UNESCAPE)
        );
    }
}
