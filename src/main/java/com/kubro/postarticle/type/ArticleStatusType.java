package com.kubro.postarticle.type;

public enum ArticleStatusType {
  PUBLISH("publish"),
  DRAFT("draft"),
  THRASH("thrash");

  public final String label;

  private ArticleStatusType(String label) {
      this.label = label;
  }
  
  public static ArticleStatusType fromString(String text) {
    for (ArticleStatusType type : ArticleStatusType.values()) {
        if (type.label.equalsIgnoreCase(text)) {
            return type;
        }
    }
    throw new IllegalArgumentException("No constant with text " + text + " found");
  }

}
