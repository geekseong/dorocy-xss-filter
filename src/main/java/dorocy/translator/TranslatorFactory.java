package dorocy.translator;

public final class TranslatorFactory {

    public static Translator getInstance(TranslatorType type){

        switch (type){
            case BASIC:
                return new BasicTranslator();
            case SECURE:
                return new SecureTranslator();
        }

        throw new IllegalArgumentException("invalid TranslatorType.");
    }
}
