import dorocy.translator.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DorocyTest {

    @Test
    @Order(1)
    @DisplayName("Created_Translator_Validation_Test")
    public void createdTranslatorValidation (){

        //given
        TranslatorType basicType = TranslatorType.BASIC;
        TranslatorType secureType = TranslatorType.SECURE;

        // when
        Translator basicTranslator = TranslatorFactory.getInstance(basicType);
        Translator secureTranslator = TranslatorFactory.getInstance(secureType);

        // then
        assertTrue(basicTranslator instanceof BasicTranslator);
        assertTrue(secureTranslator instanceof SecureTranslator);
    }

    @Test
    @Order(2)
    @DisplayName("BasicTranslator_Test")
    public void basicTranslatorTest(){

        // given
        String dirty1 = "<script> alert(\"xss attack\") </script>";
        String dirty2 = "<>&'\"/";
        Translator basicTranslator = TranslatorFactory.getInstance(TranslatorType.BASIC);

        // when
        String clean1 = basicTranslator.translate(dirty1);
        String clean2 = basicTranslator.translate(dirty2);

        // then
        String expect1 = "&lt;script&gt; alert(&quot;xss attack&quot;) &lt;/script&gt;";
        String expect2 = "&lt;&gt;&amp;'&quot;/";
        assertEquals(clean1, expect1);
        assertEquals(clean2, expect2);
    }

    @Test
    @Order(3)
    @DisplayName("SecureTranslator_Test")
    public void secureTranslatorTest(){

        // given
        String dirty1 = "<script> alert(\"xss attack\") </script>";
        String dirty2 = "<>&'\"/";
        Translator secureTranslator = TranslatorFactory.getInstance(TranslatorType.SECURE);

        // when
        String clean1 = secureTranslator.translate(dirty1);
        String clean2 = secureTranslator.translate(dirty2);

        // then
        String expect1 = "&lt;script&gt; alert(&quot;xss attack&quot;) &lt;&#x2F;script&gt;";
        String expect2 = "&lt;&gt;&amp;&#x27;&quot;&#x2F;";

        assertEquals(clean1, expect1);
        assertEquals(clean2, expect2);

    }

}
