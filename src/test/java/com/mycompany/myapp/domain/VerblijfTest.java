package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class VerblijfTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Verblijf.class);
        Verblijf verblijf1 = new Verblijf();
        verblijf1.setId(1L);
        Verblijf verblijf2 = new Verblijf();
        verblijf2.setId(verblijf1.getId());
        assertThat(verblijf1).isEqualTo(verblijf2);
        verblijf2.setId(2L);
        assertThat(verblijf1).isNotEqualTo(verblijf2);
        verblijf1.setId(null);
        assertThat(verblijf1).isNotEqualTo(verblijf2);
    }
}
