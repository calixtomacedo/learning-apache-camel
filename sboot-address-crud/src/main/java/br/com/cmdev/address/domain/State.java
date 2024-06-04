package br.com.cmdev.address.domain;

import org.springframework.data.relational.core.sql.In;

public enum State {
    AC(12, "ACRE"),
    AL(27, "ALAGOAS"),
    AP(16, "AMAPÁ"),
    AM(13, "AMAZONAS"),
    BA(29, "BAHIA"),
    CE(23, "CEARÁ"),
    DF(53, "DISTRITO FEDERAL"),
    ES(32, "ESPÍRITO SANTO"),
    GO(52, "GOIÁS"),
    MA(21, "MARANHÃO"),
    MT(51, "MATO GROSSO"),
    MS(50, "MATO GROSSO DO SUL"),
    MG(31, "MINAS GERAIS"),
    PA(15, "PARÁ"),
    PB(25, "PARAÍBA"),
    PR(41, "PARANÁ"),
    PE(26, "PERNAMBUCO"),
    PI(22, "PIAUÍ"),
    RJ(33, "RIO DE JANEIRO"),
    RN(24, "RIO GRANDE DO NORTE"),
    RS(43, "RIO GRANDE DO SUL"),
    RO(11, "RONDÔNIA"),
    RR(14, "RORAIMA"),
    SC(42, "SANTA CATARINA"),
    SP(35, "SÃO PAULO"),
    SE(28, "SERGIPE"),
    TO(17, "TOCANTINS");

    private Integer code;
    private String description;

    private State(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
