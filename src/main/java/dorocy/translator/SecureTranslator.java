package dorocy.translator;

import org.apache.commons.text.translate.AggregateTranslator;
import org.apache.commons.text.translate.CharSequenceTranslator;
import org.apache.commons.text.translate.EntityArrays;
import org.apache.commons.text.translate.LookupTranslator;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SecureTranslator implements Translator {

    public CharSequenceTranslator getTranslator() {
        return new AggregateTranslator(
                new LookupTranslator(SECURE_ESCAPE()),
                new LookupTranslator(EntityArrays.ISO8859_1_ESCAPE),
                new LookupTranslator(EntityArrays.HTML40_EXTENDED_ESCAPE)
        );
    }

    private final Map<CharSequence, CharSequence> SECURE_ESCAPE() {
        final Map<CharSequence, CharSequence> initialMap = new HashMap<CharSequence, CharSequence>();
        initialMap.put("<", "&lt;");
        initialMap.put(">", "&gt;");
        initialMap.put("&", "&amp;");
        initialMap.put("\"", "&quot;");
        initialMap.put("'", "&#x27;");
        initialMap.put("/", "&#x2F;");
        return Collections.unmodifiableMap(initialMap);
    }
}
