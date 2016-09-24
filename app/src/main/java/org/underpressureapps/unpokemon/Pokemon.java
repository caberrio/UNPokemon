package org.underpressureapps.unpokemon;

public class Pokemon {

    private int id;
    private String name;
    private String type;
    private int total;
    private int hp;
    private int attack;
    private int defense;
    private int spAtk;
    private int spDef;
    private int speed;
    private String imgFront;
    private String imgBack;
    private String gifFront;
    private String gifBack;
    private String imgUrl;
    private int evId;

    public Pokemon(int id, String name, String type, int total, int hp, int attack, int defense, int spAtk, int spDef, int speed, String imgFront, String imgBack, String gifFront, String gifBack, String imgUrl, int evId) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.total = total;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.spAtk = spAtk;
        this.spDef = spDef;
        this.speed = speed;
        this.imgFront = imgFront;
        this.imgBack = imgBack;
        this.gifFront = gifFront;
        this.gifBack = gifBack;
        this.imgUrl = imgUrl;
        this.evId = evId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getSpAtk() {
        return spAtk;
    }

    public void setSpAtk(int spAtk) {
        this.spAtk = spAtk;
    }

    public int getSpDef() {
        return spDef;
    }

    public void setSpDef(int spDef) {
        this.spDef = spDef;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getImgFront() {
        return imgFront;
    }

    public void setImgFront(String imgFront) {
        this.imgFront = imgFront;
    }

    public String getImgBack() {
        return imgBack;
    }

    public void setImgBack(String imgBack) {
        this.imgBack = imgBack;
    }

    public String getGifFront() {
        return gifFront;
    }

    public void setGifFront(String gifFront) {
        this.gifFront = gifFront;
    }

    public String getGifBack() {
        return gifBack;
    }

    public void setGifBack(String gifBack) {
        this.gifBack = gifBack;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getEvId() {
        return evId;
    }

    public void setEvId(int evId) {
        this.evId = evId;
    }
}
