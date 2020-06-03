package dorocy.translator;

import org.apache.commons.text.translate.CharSequenceTranslator;

public abstract class Translator {

    abstract protected CharSequenceTranslator getTranslator();
    public String translate(String dirty){
        return getTranslator().translate(dirty);
    }
}
