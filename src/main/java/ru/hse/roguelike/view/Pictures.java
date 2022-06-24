package ru.hse.roguelike.view;

public enum Pictures {
    CAT("AAAWAAAAAAAAAWAAA" +
        "AAWAWAAAAAAAWAWAA" +
        "AAWAAWAAAAAWAAWAA" +
        "AWAAAAWWWWWAAAAWA" +
        "AWAAAAAAAAAAAAAWA" +
        "AWAAAAAAAAAAAAAWA" +
        "WAAAAAAAAAAAAAAAW" +
        "WAAWWWAAAAAWWWAAW" +
        "WAWGAGWAAAWGAGWAW" +
        "WAAWWWAAAAAWWWAAW" +
        "WAAAAAAAMAAAAAAAW" +
        "WAAAAAWAWAWAAAAAW" +
        "WAAAAAAWAWAAAAAAW" +
        "AWAAAAAAAAAAAAAWA" +
        "AAWWAAAAAAAAAWWAA" +
        "AAAAWWWWWWWWWAAAA"),
    HERO("AAAA^___^AAAA" +
        "AAA<AAAAA>AAA" +
        "AAA<AYAYA>AAA" +
        "AAA<AATAA>AAA" +
        "AAAA<AAA>AAAA" +
        "<@----W----@>" +
        "AAAAA<W>AAAAA" +
        "AAAAA<W>AAAAA" +
        "AAAAA<W>AAAAA" +
        "AAAAA<W>AAAAA" +
        "AAAAA<A>AAAAA" +
        "AAAAA<A>AAAAA" +
        "AAAAA<A>AAAAA" +
        "AAAAWWAWWAAAA");

    private final String picture;

    private Pictures(String picture) {
        this.picture = picture;
    }

    public String getPicture() {
        return picture;
    }
}
