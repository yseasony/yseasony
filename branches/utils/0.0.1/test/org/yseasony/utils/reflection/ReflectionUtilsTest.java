package org.yseasony.utils.reflection;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class ReflectionUtilsTest {

    @Test
    public void testGetSuperClassGenricType() throws Exception {
        assertEquals(String.class,ReflectionUtils.getSuperClassGenricType(TestBean.class));
        assertEquals(String.class,ReflectionUtils.getSuperClassGenricType(TestBean.class,0));
        assertEquals(Long.class,ReflectionUtils.getSuperClassGenricType(TestBean.class,1));
    }

    public static class ParentBean<T, PK> {
    }

    public static class TestBean extends ParentBean<String, Long> {
        /**
         * 没有getter/setter的field
         */
        private int privateField = 1;
        /**
         * 有getter/setter的field
         */
        private int publicField = 1;

        public int getPublicField() {
            return publicField + 1;
        }

        public void setPublicField(int publicField) {
            this.publicField = publicField + 1;
        }

        public int inspectPrivateField() {
            return privateField;
        }

        public int inspectPublicField() {
            return publicField;
        }

        @SuppressWarnings("unused")
        private String privateMethod(String text) {
            return "hello " + text;
        }
    }
}