package fury.marvel.shiva.transformer.modifier;

import javassist.CtClass;

/**
 * Created by poets11 on 15. 1. 28..
 */
public interface ClassModifier {
    void modify(String className, CtClass target) throws Exception;
}
