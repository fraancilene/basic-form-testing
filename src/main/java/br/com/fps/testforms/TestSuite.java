package br.com.fps.testforms;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestCadastroForms.class,
        TestRegistrationRules.class,
        TestesElementosBasicos.class
})
public class TestSuite {
}
