package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class PersoonsnaamVolgensDocumentVoorGrensoverschrijdingTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PersoonsnaamVolgensDocumentVoorGrensoverschrijding.class);
        PersoonsnaamVolgensDocumentVoorGrensoverschrijding persoonsnaamVolgensDocumentVoorGrensoverschrijding1 = new PersoonsnaamVolgensDocumentVoorGrensoverschrijding();
        persoonsnaamVolgensDocumentVoorGrensoverschrijding1.setId(1L);
        PersoonsnaamVolgensDocumentVoorGrensoverschrijding persoonsnaamVolgensDocumentVoorGrensoverschrijding2 = new PersoonsnaamVolgensDocumentVoorGrensoverschrijding();
        persoonsnaamVolgensDocumentVoorGrensoverschrijding2.setId(persoonsnaamVolgensDocumentVoorGrensoverschrijding1.getId());
        assertThat(persoonsnaamVolgensDocumentVoorGrensoverschrijding1).isEqualTo(persoonsnaamVolgensDocumentVoorGrensoverschrijding2);
        persoonsnaamVolgensDocumentVoorGrensoverschrijding2.setId(2L);
        assertThat(persoonsnaamVolgensDocumentVoorGrensoverschrijding1).isNotEqualTo(persoonsnaamVolgensDocumentVoorGrensoverschrijding2);
        persoonsnaamVolgensDocumentVoorGrensoverschrijding1.setId(null);
        assertThat(persoonsnaamVolgensDocumentVoorGrensoverschrijding1).isNotEqualTo(persoonsnaamVolgensDocumentVoorGrensoverschrijding2);
    }
}
