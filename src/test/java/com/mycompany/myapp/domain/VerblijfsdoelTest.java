package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class VerblijfsdoelTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Verblijfsdoel.class);
        Verblijfsdoel verblijfsdoel1 = new Verblijfsdoel();
        verblijfsdoel1.setId(1L);
        Verblijfsdoel verblijfsdoel2 = new Verblijfsdoel();
        verblijfsdoel2.setId(verblijfsdoel1.getId());
        assertThat(verblijfsdoel1).isEqualTo(verblijfsdoel2);
        verblijfsdoel2.setId(2L);
        assertThat(verblijfsdoel1).isNotEqualTo(verblijfsdoel2);
        verblijfsdoel1.setId(null);
        assertThat(verblijfsdoel1).isNotEqualTo(verblijfsdoel2);
    }
}
