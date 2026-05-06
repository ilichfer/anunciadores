  package  com.anunciadores.dto;
  
  import com.anunciadores.dto.CountryDto;
  import com.anunciadores.dto.LanguageDto;
  import java.time.OffsetDateTime;
  import java.util.List;
  
  
  
  
  
  
  public class BibleFeingDto
  {
    private String id;
    private String dblID;
    private Object relatedDbl;
    private String name;
    private String nameLocal;
    private String abbreviation;
    private String abbreviationLocal;
    
    public String getId() {
/*  24 */     return this.id;
    } private String description; private String descriptionLocal; private LanguageDto language; private List<CountryDto> countries; private String type; private OffsetDateTime updatedAt; private List<Object> audioBibles;
    public void setId(String id) {
/*  27 */     this.id = id;
    }
    public String getDblID() {
/*  30 */     return this.dblID;
    }
    public void setDblID(String dblID) {
/*  33 */     this.dblID = dblID;
    }
    public Object getRelatedDbl() {
/*  36 */     return this.relatedDbl;
    }
    public void setRelatedDbl(Object relatedDbl) {
/*  39 */     this.relatedDbl = relatedDbl;
    }
    public String getName() {
/*  42 */     return this.name;
    }
    public void setName(String name) {
/*  45 */     this.name = name;
    }
    public String getNameLocal() {
/*  48 */     return this.nameLocal;
    }
    public void setNameLocal(String nameLocal) {
/*  51 */     this.nameLocal = nameLocal;
    }
    public String getAbbreviation() {
/*  54 */     return this.abbreviation;
    }
    public void setAbbreviation(String abbreviation) {
/*  57 */     this.abbreviation = abbreviation;
    }
    public String getAbbreviationLocal() {
/*  60 */     return this.abbreviationLocal;
    }
    public void setAbbreviationLocal(String abbreviationLocal) {
/*  63 */     this.abbreviationLocal = abbreviationLocal;
    }
    public String getDescription() {
/*  66 */     return this.description;
    }
    public void setDescription(String description) {
/*  69 */     this.description = description;
    }
    public String getDescriptionLocal() {
/*  72 */     return this.descriptionLocal;
    }
    public void setDescriptionLocal(String descriptionLocal) {
/*  75 */     this.descriptionLocal = descriptionLocal;
    }
    public LanguageDto getLanguage() {
/*  78 */     return this.language;
    }
    public void setLanguage(LanguageDto language) {
/*  81 */     this.language = language;
    }
    public List<CountryDto> getCountries() {
/*  84 */     return this.countries;
    }
    public void setCountries(List<CountryDto> countries) {
/*  87 */     this.countries = countries;
    }
    public String getType() {
/*  90 */     return this.type;
    }
    public void setType(String type) {
/*  93 */     this.type = type;
    }
    public OffsetDateTime getUpdatedAt() {
/*  96 */     return this.updatedAt;
    }
    public void setUpdatedAt(OffsetDateTime updatedAt) {
/*  99 */     this.updatedAt = updatedAt;
    }
    public List<Object> getAudioBibles() {
/* 102 */     return this.audioBibles;
    }
    public void setAudioBibles(List<Object> audioBibles) {
/* 105 */     this.audioBibles = audioBibles;
    }
  }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\dto\BibleFeingDto.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */