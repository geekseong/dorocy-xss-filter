package dorocy;


import dorocy.translator.Translator;
import dorocy.translator.TranslatorFactory;
import dorocy.translator.TranslatorType;
import org.apache.commons.text.translate.CharSequenceTranslator;

public class XssFilter {

    private final Translator translator;

    public XssFilter(){
        this.translator = TranslatorFactory.getInstance(TranslatorType.BASIC);
    }

    public XssFilter(TranslatorType type){
        this.translator = TranslatorFactory.getInstance(type);
    }

    public XssFilter(Translator translator) {
        this.translator = translator;
    }

    public String doFilter(String dirty){
        String clean = this.getTranslator().translate(dirty);
        return clean == null ? null : clean.replaceAll("'", "&#39;");
    }

    private CharSequenceTranslator getTranslator(){
        return this.translator.getTranslator();
    }
}
